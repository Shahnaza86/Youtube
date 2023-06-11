package com.example.youtube.ui.detail

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.ui.base.BaseActivity
import com.example.youtube.databinding.ActivityDetailBinding
import com.example.youtube.ui.playlist.adapter.DetailAdapter

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun setUI() {
        super.setUI()
        adapter = DetailAdapter()
        binding.recyclerView.adapter = adapter
    }

    override fun setupLiveData() {
        super.setupLiveData()
        val getIntentId =
            intent.getStringExtra(KEY_ID)
        val getIntentDesc = intent.getStringExtra(DESCRIPTION)
        val getIntentTitle = intent.getStringExtra(TITLE)
        viewModel.getPlaylistItems(getIntentId!!).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items as List<Playlists.Item>)
                    binding.tvDescription.text = getIntentDesc
                    binding.tvTitle.text = getIntentTitle
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }
    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                setupLiveData()
            }
        }
    }
}