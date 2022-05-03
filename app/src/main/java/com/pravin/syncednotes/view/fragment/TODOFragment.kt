package com.pravin.syncednotes.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pravin.syncednotes.R
import com.pravin.syncednotes.databinding.TODOFragmentBinding
import com.pravin.syncednotes.viewmodel.TODOViewModel

class TODOFragment : Fragment() {
    private lateinit var viewModel: TODOViewModel
    private lateinit var binding:TODOFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TODOViewModel::class.java)
        binding = TODOFragmentBinding.inflate(inflater, container, false)



        return binding.root
    }


}