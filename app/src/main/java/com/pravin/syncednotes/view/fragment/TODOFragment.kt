package com.pravin.syncednotes.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pravin.syncednotes.databinding.TODOFragmentBinding
import com.pravin.syncednotes.viewmodel.TODOViewModel

class TODOFragment : Fragment() {
    private lateinit var viewModel: TODOViewModel
    private lateinit var binding:TODOFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TODOFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TODOViewModel::class.java)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchTodos("11")
    }

    override fun onStart() {
        super.onStart()

    }

}