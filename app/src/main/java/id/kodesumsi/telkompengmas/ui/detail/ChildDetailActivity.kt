package id.kodesumsi.telkompengmas.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityChildDetailBinding

class ChildDetailActivity : BaseActivity<ActivityChildDetailBinding>() {

    private lateinit var lineDataSet: LineDataSet

    override fun setupViewBinding(): (LayoutInflater) -> ActivityChildDetailBinding {
        return ActivityChildDetailBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {

        lineDataSet = LineDataSet(getHeightEntries(), "Bulan 1")

        val dataSets = arrayListOf<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)
        binding.childDetailChart.data = lineData

        binding.childDetailChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.childDetailChart.invalidate()

        binding.childDetailChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                Toast.makeText(applicationContext, "Tinggi badan: ${e?.y} dan Umur Bayi: ${e?.x}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {

            }
        })

        binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary_dark))

        binding.chartFilterWeight.setOnClickListener {
            binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary))
            binding.chartFilterWeight.setCardBackgroundColor(getColor(R.color.primary_dark))
            updateDataSet(getWeightEntries())
        }

        binding.chartFilterHeight.setOnClickListener {
            binding.chartFilterHeight.setCardBackgroundColor(getColor(R.color.primary_dark))
            binding.chartFilterWeight.setCardBackgroundColor(getColor(R.color.primary))
            updateDataSet(getHeightEntries())
        }

        binding.childDetailUpdate.setOnClickListener {
            val updateBottomSheet = BottomSheetUpdateChildData()
            updateBottomSheet.show(supportFragmentManager, "Update Bottom Sheet")
        }

    }

    // from this, dummy data for line chart
    fun getHeightEntries(): List<Entry> {
        val result: MutableList<Entry> = mutableListOf()

        result.add(Entry(6f, 40f))
        result.add(Entry(12f, 52f))
        result.add(Entry(18f, 63f))
        result.add(Entry(21f, 80f))
        result.add(Entry(28f, 90f))
        result.add(Entry(30f, 95f))
        result.add(Entry(36f, 96f))

        return result
    }

    fun getWeightEntries(): List<Entry> {
        val result: MutableList<Entry> = mutableListOf()

        result.add(Entry(6f, 10f))
        result.add(Entry(12f, 20f))
        result.add(Entry(18f, 30f))
        result.add(Entry(21f, 40f))
        result.add(Entry(28f, 50f))
        result.add(Entry(30f, 60f))
        result.add(Entry(36f, 70f))

        return result
    }

    fun updateDataSet(entries: List<Entry>) {
        val currentData = binding.childDetailChart.data
        currentData.removeDataSet(0)

        val newLineDataSet = LineDataSet(entries, "Bulan 1")

        currentData.addDataSet(newLineDataSet)
        currentData.notifyDataChanged()

        binding.childDetailChart.notifyDataSetChanged()
        binding.childDetailChart.invalidate()
    }


}