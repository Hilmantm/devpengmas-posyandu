package id.kodesumsi.telkompengmas.ui.main.parents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.base.BaseAdapter
import id.kodesumsi.telkompengmas.base.BaseFragment
import id.kodesumsi.telkompengmas.databinding.FragmentArtikelBinding
import id.kodesumsi.telkompengmas.databinding.ItemArtikelBinding
import id.kodesumsi.telkompengmas.domain.model.Article

@AndroidEntryPoint
class ArtikelFragment : BaseFragment<FragmentArtikelBinding>() {

    private val viewModel: ArtikelFragmentViewModel by viewModels()

    override fun setupViewBinding(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtikelBinding {
        return FragmentArtikelBinding::inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter: BaseAdapter<ItemArtikelBinding, Article> = BaseAdapter(ItemArtikelBinding::inflate) { article, binding ->
            Glide.with(requireContext()).load(article.thumbUrl).into(binding.itemArticleThumb)
            binding.itemArticleTitle.text = article.title
            binding.itemArticleTag.text = article.tag.first()
            binding.root.setOnClickListener {
                val toWebsite = Intent(Intent.ACTION_VIEW)
                toWebsite.data = Uri.parse(article.url)
                startActivity(toWebsite)
            }
        }
        binding.artikelRv.layoutManager = LinearLayoutManager(requireContext())
        binding.artikelRv.adapter = adapter

        viewModel.getArticles()
        viewModel.article.observe(viewLifecycleOwner) { articles ->
            if (articles.isNotEmpty()) {
                adapter.setData(articles)
                adapter.notifyDataSetChanged()
            }
        }
    }

}