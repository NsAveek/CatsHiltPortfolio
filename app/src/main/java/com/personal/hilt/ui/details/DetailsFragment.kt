package com.personal.hilt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.personal.hilt.R

/*
* nav_graph.xml requires an
* <argument
            android:name="flowStepNumber"
            app:argType="integer"
  />
* to initiate Navigation and receive the value from source
* DetailsFragmentArgs by navArgs() will be activated by then
* then you receive the data from the source using the android:name.
*
* From source, you may send data in any variable but you need to receive
* using the name only in the destination fragment
* */
class DetailsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val safeArgs : DetailsFragmentArgs by navArgs()
        val counterDateFromNavArgs = safeArgs.flowStepNumber
        Toast.makeText(requireContext(), "data : $counterDateFromNavArgs", Toast.LENGTH_SHORT).show()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}