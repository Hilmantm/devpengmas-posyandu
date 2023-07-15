package id.kodesumsi.telkompengmas.ui.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.data.source.Resource
import id.kodesumsi.telkompengmas.databinding.ActivityChildDetailBinding
import id.kodesumsi.telkompengmas.domain.model.ChildStatistics
import id.kodesumsi.telkompengmas.ui.main.BerandaFragment
import id.kodesumsi.telkompengmas.utils.Constant
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEAD_CIRCUMFERENCE
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEIGHT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.WEIGHT
import id.kodesumsi.telkompengmas.utils.Utility.countMonthDiff
import id.kodesumsi.telkompengmas.utils.Utility.getAgeInMonth
import id.kodesumsi.telkompengmas.utils.Utility.getColorFromStatus
import id.kodesumsi.telkompengmas.utils.Utility.isStunting
import id.kodesumsi.telkompengmas.utils.Utility.toLocalDate

@AndroidEntryPoint
class ChildDetailActivity : BaseActivity<ActivityChildDetailBinding>() {

    private lateinit var lineDataSet: LineDataSet
    private val viewModel: ChildDetailActivityViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater) -> ActivityChildDetailBinding {
        return ActivityChildDetailBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        // visible if this data is ready
//        binding.childDetailGlance.childDetailGlanceHeightCard.visibility = View.GONE
//        binding.childDetailGlance.childDetailGlanceWeightCard.visibility = View.GONE
//        binding.childDetailGlance.childDetailGlanceHeadCircumferenceCard.visibility = View.GONE

        val name = intent.getStringExtra(BerandaFragment.KEY_NAME)
        val gender = intent.getStringExtra(BerandaFragment.KEY_GENDER)
        val birthDate = intent.getStringExtra(BerandaFragment.KEY_BIRTH_DATE)
        val image = intent.getStringExtra(BerandaFragment.KEY_IMAGE)
        val id = intent.getIntExtra(BerandaFragment.KEY_ID, 0)

        // set child data
        binding.childDetailName.text = name
        binding.childDetailAge.text = "${getChildAgeInMonth(birthDate)} bulan"
        if (image == null) {
            when (gender) {
                Constant.MAN -> Glide.with(this).load(R.drawable.boy_illustration).into(binding.childDetailAvatar)
                Constant.WOMAN -> Glide.with(this).load(R.drawable.girl_illustration).into(binding.childDetailAvatar)
            }
        }

        viewModel.getUser()
        viewModel.currentUser.observe(this) { currentUser ->
            if (currentUser != null) {
                binding.childDetailUpdate.setOnClickListener {
                    val updateBottomSheet = BottomSheetUpdateChildData()
                    updateBottomSheet.setChildId(id)
                    updateBottomSheet.setToken(currentUser.token!!)
                    updateBottomSheet.setUserRole(currentUser.role!!)
                    updateBottomSheet.show(supportFragmentManager, "Update Bottom Sheet")
                }

                viewModel.getChildStatistics(token = currentUser.token!!, userRole = currentUser.role!!, childId = id).observe(this) {
                    when (it) {
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            Log.e("getchildstatistics", "Error ${it.message}", )
                        }
                        is Resource.Success -> {
                            if (it.data?.size == 0) {
                                Toast.makeText(
                                    this,
                                    "Data Statistik Anak kosong",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onBackPressedDispatcher.onBackPressed()
                                return@observe
                            }

                            Log.d("GetChildStatistics", "data = ${it.data}")
                            val lastData = it.data?.last()

                            // set last data
                            binding.childDetailGlance.childDetailGlanceHeight.text = lastData?.height.toString()
                            binding.childDetailGlance.childDetailGlanceWeight.text = lastData?.weight.toString()
                            binding.childDetailGlance.childDetailGlanceHeadCircumference.text = lastData?.headCircumference.toString()

                            // data statistics
                            val weightStatistics = lastData?.statistics?.weight
                            val heightStatistics = lastData?.statistics?.height
                            val headCircumferenceStatistics = lastData?.statistics?.headCircumference

                            if (weightStatistics != null && heightStatistics != null && headCircumferenceStatistics != null) {
                                // set status from last data
                                binding.childDetailGlance.childDetailGlanceWeightStatus.text = weightStatistics
                                binding.childDetailGlance.childDetailGlanceHeightStatus.text = heightStatistics
                                binding.childDetailGlance.childDetailGlanceHeadCircumferenceStatus.text = headCircumferenceStatistics

                                // set action based on the data
                                // binding.weightAction.text = "Z Score berat terhadap umur ${lastData?.zScoreWeight} maka ${getActionFromStatus(this, WEIGHT, weightStatistics)}"
                                // binding.heightAction.text = "Z Score tinggi terhadap umur ${lastData?.zScoreHeight} maka ${getActionFromStatus(this, HEIGHT, heightStatistics)}"
                                // binding.headCircumferenceAction.text = "Z Score lingkar kepala terhadap umur ${lastData?.zScoreHeadCircumference} maka ${getActionFromStatus(this, HEAD_CIRCUMFERENCE, headCircumferenceStatistics)}"
                                binding.weightAction.text = "Ukuran berat badan normalnya diumur ini adalah ${lastData.statistics.normalWeight} dalam satuan kg"
                                binding.heightAction.text = "Ukuran tinggi badan normalnya diumur ini adalah ${lastData.statistics.normalHeight} dalam satuan cm"
                                binding.headCircumferenceAction.text = "Ukuran lingkar kepala normalnya diumur ini adalah ${lastData.statistics.normalHeadCircumference} dalam satuan cm"

                                // set color status based on the data
                                binding.childDetailGlance.childDetailGlanceWeightCard.background.setTint(getColorFromStatus(this, WEIGHT, weightStatistics))
                                binding.childDetailGlance.childDetailGlanceHeightCard.background.setTint(getColorFromStatus(this, HEIGHT, heightStatistics))
                                binding.childDetailGlance.childDetailGlanceHeadCircumferenceCard.background.setTint(getColorFromStatus(this, HEAD_CIRCUMFERENCE, headCircumferenceStatistics))

                                // set is stunting?
                                val isStuntingConclusion = isStunting(this, WEIGHT, weightStatistics) || isStunting(this, HEIGHT, heightStatistics) || isStunting(this, HEAD_CIRCUMFERENCE, headCircumferenceStatistics)
                                Log.d("ChildDetailActivity", "weight stunting: ${isStunting(this, WEIGHT, weightStatistics)}")
                                Log.d("ChildDetailActivity", "height stunting: ${isStunting(this, HEIGHT, heightStatistics)}")
                                Log.d("ChildDetailActivity", "head circumference stunting: ${isStunting(this, HEAD_CIRCUMFERENCE, headCircumferenceStatistics)}")
                                Log.d("ChildDetailActivity", "isStunting: $isStuntingConclusion")
                                // binding.stuntingConclusion.text = if (isStuntingConclusion) getString(R.string.stunting_conclusion_yes) else getString(R.string.stunting_conclusion_no)
                                binding.stuntingConclusion.text = getString(R.string.default_saran)
                            } else {
                                binding.childDetailAction.visibility = View.GONE
                                binding.childDetailGlance.childDetailGlanceWeightStatus.visibility = View.GONE
                                binding.childDetailGlance.childDetailGlanceHeightStatus.visibility = View.GONE
                                binding.childDetailGlance.childDetailGlanceHeadCircumferenceStatus.visibility = View.GONE
                            }

                            if (it.data?.size != 0 && it.data != null) {
                                // post data entries for data graph mapping
                                viewModel.weightEntries.postValue(setWeightEntries(it.data, birthDate!!))
                                viewModel.heightEntries.postValue(setHeightEntries(it.data, birthDate))
                                viewModel.headCircumferenceEntries.postValue(setHeadCircumferences(it.data, birthDate))
                                Log.d("ChildDetailActivity", "setEntries weight ${setWeightEntries(it.data, birthDate!!)}")
                                Log.d("ChildDetailActivity", "setEntries height ${setHeightEntries(it.data, birthDate)}")
                                Log.d("ChildDetailActivity", "setEntries hc ${setHeadCircumferences(it.data, birthDate)}")
                            }
                        }
                    }
                }
            }
        }

        binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary_dark))


        viewModel.weightEntries.observe(this) { entries ->
            if (entries.isNotEmpty()) {
                lineDataSet = LineDataSet(entries, "Bulan")
                val dataSets = arrayListOf<ILineDataSet>()
                dataSets.add(lineDataSet)
                val lineData = LineData(dataSets)
                binding.childDetailChart.data = lineData
                binding.childDetailChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                binding.childDetailChart.invalidate()
                binding.childDetailChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                        //Toast.makeText(applicationContext, "Berat badan: ${e?.y} dan Umur: ${e?.x}", Toast.LENGTH_SHORT).show()
                        binding.childDetailDate.text = "Berat badan: ${e?.y} dan Umur: ${e?.x}"
                    }

                    override fun onNothingSelected() {

                    }
                })

                binding.chartFilterWeight.setOnClickListener {
                    binding.chartFilterWeight.setCardBackgroundColor(getColor(R.color.primary))
                    binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary_dark))
                    binding.chartFilterHeadCircumference.setCardBackgroundColor(getColor(R.color.primary_dark))
                    updateDataSet(entries, "Berat Badan")
                }
            }
        }

        viewModel.heightEntries.observe(this) { entries ->
            if (entries.isNotEmpty()) {
                binding.chartFilterHeight.setOnClickListener {
                    binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary))
                    binding.chartFilterWeight.setCardBackgroundColor(getColor(R.color.primary_dark))
                    binding.chartFilterHeadCircumference.setCardBackgroundColor(getColor(R.color.primary_dark))
                    updateDataSet(entries, "Tinggi Badan")
                }
            }
        }

        viewModel.headCircumferenceEntries.observe(this) { entries ->
            if (entries.isNotEmpty()) {
                binding.chartFilterHeadCircumference.setOnClickListener {
                    binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary_dark))
                    binding.chartFilterWeight.setCardBackgroundColor(getColor(R.color.primary_dark))
                    binding.chartFilterHeadCircumference.setCardBackgroundColor(getColor(R.color.primary))
                    updateDataSet(entries, "Lingkar Kepala")
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getChildAgeInMonth(birthDate: String?): String? {
        return birthDate?.toLocalDate()?.getAgeInMonth().toString()
    }

    // from this, dummy data for line chart
    fun setHeightEntries(statisticsList: List<ChildStatistics>, birthDate: String): List<Entry> {
        val result: MutableList<Entry> = mutableListOf()

//        result.add(Entry(6f, 40f))
//        result.add(Entry(12f, 52f))
//        result.add(Entry(18f, 63f))
//        result.add(Entry(21f, 80f))
//        result.add(Entry(28f, 90f))
//        result.add(Entry(30f, 95f))
//        result.add(Entry(36f, 96f))

        for (data in statisticsList) {
            result.add(Entry(countMonthDiff(birthDate, data.date).toFloat(), data.height.toFloat()))
        }

        return result.sortedBy { value -> value.x }
    }

    fun setWeightEntries(statisticsList: List<ChildStatistics>, birthDate: String): List<Entry> {
        val result: MutableList<Entry> = mutableListOf()

//        result.add(Entry(6f, 10f))
//        result.add(Entry(12f, 20f))
//        result.add(Entry(18f, 30f))
//        result.add(Entry(21f, 40f))
//        result.add(Entry(28f, 50f))
//        result.add(Entry(30f, 60f))
//        result.add(Entry(36f, 70f))

        for (data in statisticsList) {
            result.add(Entry(countMonthDiff(birthDate, data.date).toFloat(), data.weight.toFloat()))
        }

        return result.sortedBy { value -> value.x }
    }

    fun setHeadCircumferences(statisticsList: List<ChildStatistics>, birthDate: String): List<Entry> {
        val result: MutableList<Entry> = mutableListOf()

//        result.add(Entry(6f, 10f))
//        result.add(Entry(12f, 20f))
//        result.add(Entry(18f, 30f))
//        result.add(Entry(21f, 40f))
//        result.add(Entry(28f, 50f))
//        result.add(Entry(30f, 60f))
//        result.add(Entry(36f, 70f))

        for (data in statisticsList) {
            result.add(Entry(countMonthDiff(birthDate, data.date).toFloat(), data.headCircumference.toFloat()))
        }

        return result.sortedBy { value -> value.x }
    }

    fun updateDataSet(entries: List<Entry>, type: String) {
        val currentData = binding.childDetailChart.data
        if (currentData.dataSets.size != 0) {
            currentData.removeDataSet(0)
        }

        val newLineDataSet = LineDataSet(entries, "Bulan")

        currentData.addDataSet(newLineDataSet)
        currentData.notifyDataChanged()

        binding.childDetailChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                binding.childDetailDate.text = "$type: ${e?.y} dan Umur: ${e?.x}"
                //Toast.makeText(applicationContext, "$type: ${e?.y} dan Umur Bayi: ${e?.x}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {

            }
        })

        binding.childDetailChart.notifyDataSetChanged()
        binding.childDetailChart.invalidate()
    }


}