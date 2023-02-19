package com.example.to_dolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class Detail_Fragment : Fragment() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            list=it.getParcelable(ARG_LIST)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_, container, false)
    }

    companion object {

        private val ARG_LIST = "list"

        fun newInstance(list : TaskList) : Detail_Fragment{

            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST,list)

            val fragment = Detail_Fragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}