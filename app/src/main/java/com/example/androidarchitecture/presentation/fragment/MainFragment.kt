package com.example.androidarchitecture.presentation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.FragmentMainBinding
import com.example.androidarchitecture.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        init()
        binding.btnGetList.setOnClickListener {
            viewModel.getPost()
        }
    }

    private fun initObserver() {
        viewModel.dialogMessage.observe(viewLifecycleOwner){
            Log.i("error",it.message.toString())
            binding.tvData.text = it.message.toString()
        }

        viewModel.postList.observe(viewLifecycleOwner){
            Log.i("postList size",it.size.toString())
            binding.tvData.text = it.toString()
        }
    }

    private fun init() {
        viewModel.getPost()
    }

}