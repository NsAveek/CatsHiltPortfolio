package com.personal.hilt.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.personal.hilt.R

class FavouriteBreedsFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteBreedsFragment()
    }

    private lateinit var viewModel: FavouriteBreedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_breeds_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteBreedsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}