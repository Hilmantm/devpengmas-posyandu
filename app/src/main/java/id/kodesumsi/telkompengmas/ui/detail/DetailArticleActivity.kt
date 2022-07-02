package id.kodesumsi.telkompengmas.ui.detail

import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseActivity
import id.kodesumsi.telkompengmas.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : BaseActivity<ActivityDetailArticleBinding>() {

    override fun setupViewBinding(): (LayoutInflater) -> ActivityDetailArticleBinding {
        return ActivityDetailArticleBinding::inflate
    }

    override fun setupViewInstance(savedInstanceState: Bundle?) {
        binding.penyebabStunting.setOnClickListener {
            StfalconImageViewer.Builder<Drawable>(this, listOf(getDrawable(R.drawable.penyebab_stunting))) { view, image ->
                Glide.with(this@DetailArticleActivity).load(image).into(view)
            }.show()
        }
        binding.syndromeStunting.setOnClickListener {
            StfalconImageViewer.Builder<Drawable>(this, listOf(getDrawable(R.drawable.syndrome_stunting))) { view, image ->
                Glide.with(this@DetailArticleActivity).load(image).into(view)
            }.show()
        }
        binding.adaptasi.setOnClickListener {
            StfalconImageViewer.Builder<Drawable>(this, listOf(getDrawable(R.drawable.adaptasi))) { view, image ->
                Glide.with(this@DetailArticleActivity).load(image).into(view)
            }.show()
        }
        binding.dampakKurangGizi.setOnClickListener {
            StfalconImageViewer.Builder<Drawable>(this, listOf(getDrawable(R.drawable.dampak_kurang_gizi))) { view, image ->
                Glide.with(this@DetailArticleActivity).load(image).into(view)
            }.show()
        }
    }
}