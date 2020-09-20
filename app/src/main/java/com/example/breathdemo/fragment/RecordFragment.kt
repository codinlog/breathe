package com.example.breathdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.breathdemo.R
import com.example.breathdemo.databinding.FragmentRecordBinding

class RecordFragment : BaseFragment<FragmentRecordBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.action_recordFragment_to_resultFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecordFragment()
    }
}