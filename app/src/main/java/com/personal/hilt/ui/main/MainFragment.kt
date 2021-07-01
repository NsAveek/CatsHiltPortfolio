package com.personal.hilt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.personal.hilt.Injection
import com.personal.hilt.databinding.FragmentMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*
* in nav_graph.xml
* <action android:id="@+id/next_action"
            app:destination="@id/detailsFragment"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />

* is required to initiate an action
* Typically, to go from source to destination, we require action
* You may define animation from the code itself if not from xml
* For type-safe data passing, we require safe-args plugin integration. There are some known issues, can be solved using gradle 7.0
* */
class MainFragment : Fragment() {

    private var getCatsJob : Job? = null
    private lateinit var viewModel : MainFragmentViewModel
    private lateinit var binding : FragmentMainBinding
    private val adapter = CatsDataAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_main, container, false)

        binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(MainFragmentViewModel::class.java)

        getCatsJob?.cancel()
        getCatsJob = lifecycleScope.launch {
            viewModel.getCatsData().collectLatest {
                adapter.submitData(it)
            }
        }

        // region action button
//
//        val options = navOptions {
//            anim {
//                enter = R.anim.slide_in_right
//                exit = R.anim.slide_out_left
//                popEnter = R.anim.slide_in_left
//                popExit = R.anim.slide_out_right
//            }
//        }
////        val actionBtn = view.findViewById<Button>(R.id.action_btn_to_details_fragment)
//        val actionBtn = binding.actionBtnToDetailsFragment
////        actionBtn?.setOnClickListener ( Navigation.createNavigateOnClickListener(R.id.next_action, null))
//
////            Same thing another implementation
////            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, null, options)
//
//        actionBtn.setOnClickListener{ // To pass the data, you need action
//            val flowStepArg = 10
//            val action = MainFragmentDirections.nextAction(flowStepArg)
//            findNavController().navigate(action,options)
//
//        }

        // endregion

        initAdapter()

        return binding.root
    }

    private fun initAdapter(){
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CatsLoadStateAdapter{adapter.retry()},
            footer = CatsLoadStateAdapter{adapter.retry()}
        )
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
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