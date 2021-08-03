package com.personal.hilt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.personal.hilt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var getCatsJob : Job? = null
    private val mainFragmentViewModel by viewModels<MainFragmentViewModel>()
    private lateinit var binding : FragmentMainBinding
    private val adapter = CatsDataAdapter()
//    @Inject lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)

//        viewModel = ViewModelProvider(this, viewModelFactory)
//            .get(MainFragmentViewModel::class.java)

        getCatsJob?.cancel()
        getCatsJob = lifecycleScope.launch {
            mainFragmentViewModel.getCatsData().collectLatest {
                adapter.submitData(it)
            }
        }
        binding.retryButton.setOnClickListener{
            adapter.retry()
        }

        initAdapter()

        return binding.root
    }

    private fun initAdapter(){
        binding.list.adapter = adapter.withLoadStateFooter(
            footer = CatsLoadStateAdapter{adapter.retry()}
        )
        adapter.addLoadStateListener {
            // show empty list
            loadState -> val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isListEmpty)
            binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error && adapter.itemCount == 0


            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}