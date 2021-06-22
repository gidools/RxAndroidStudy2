package com.maxst.rxjavaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.Observable

private const val TAG = "ViewClick"

class ViewClickFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_click, container, false)
    }

    override fun onResume() {
        super.onResume()

        val disposable = Observable.create<View> { emitter ->
            requireView().findViewById<Button>(R.id.leftButton).setOnClickListener {
                emitter.onNext(it)
            }
        }.map { _ ->
            "left"
        }.subscribe { outputString ->
            (requireView().findViewById(R.id.textView) as TextView).text = outputString
        }
    }
}