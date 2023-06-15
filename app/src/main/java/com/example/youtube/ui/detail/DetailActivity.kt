package com.example.youtube.ui.detail

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.base.BaseActivity
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.databinding.ActivityDetailBinding
import com.example.youtube.di.utils.NoConnection
import com.example.youtube.ui.adapter.DetailAdapter

class DetailActivity() : BaseActivity<ActivityDetailBinding,DetailViewModel>() {

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
        binding.rvPlaylist.adapter = adapter
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
                    binding.rvPlaylist.adapter = adapter
                    adapter.addList(it.data?.items as List<Playlist.Item>)
                    binding.description.text = getIntentDesc
                    binding.title.text = getIntentTitle
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
        NoConnection(application).observe(this) {
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
    companion object {
        const val KEY_ID = "key_id"
        const val DESCRIPTION = "DESCRIPTION"
        const val TITLE = "TITLE"
    }
}