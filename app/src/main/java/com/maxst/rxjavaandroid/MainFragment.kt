package com.maxst.rxjavaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        layout.findViewById<Button>(R.id.rx_basic).setOnClickListener {
            clickedOn(ObservableAndSubscriberFragment())
        }
        layout.findViewById<Button>(R.id.rx_lambda).setOnClickListener {
            clickedOn(LambdaFragment())
        }
        layout.findViewById<Button>(R.id.rx_map).setOnClickListener {
            clickedOn(MapFragment())
        }

        return layout
    }

    private fun clickedOn(fragment: Fragment) {
        val tag: String = fragment.javaClass.toString()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .replace(android.R.id.content, fragment, tag)
            .commit()
    }

}