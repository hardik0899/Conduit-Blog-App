package io.realworld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.realworld.android.databinding.FragmentFeedBinding

class GlobalFeedFragment : Fragment() {

    private var binding : FragmentFeedBinding? = null
    private lateinit var viewModel : FeedViewModel
    private lateinit var feedAdapter : ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter()

        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.feedRecyclerView?.adapter = feedAdapter
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({lifecycle}) {
            feedAdapter.submitList(it)
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}