package com.maxst.rxjavaandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import java.util.*

private const val TAG = "MapFragment"

class MapFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onResume() {
        super.onResume()

        val simpleObservable: Observable<String> =
            Observable.create { emitter ->
                emitter.onNext("Hello RxAndroid!!")
                emitter.onComplete()
            }

        setSubscriber(simpleObservable)
    }

    private fun setSubscriber(observable: Observable<String>) {
        val disposable = observable
            .map { inputString ->
                inputString.toUpperCase(Locale.getDefault())
            }
            .subscribe(
                { value ->
                    Log.d(TAG, "onNext")
                    (requireView().findViewById(R.id.textView) as TextView).setText(value)
                },
                {
                    Log.d(TAG, "onError")
                },
                {
                    Log.d(TAG, "onComplete")
                })
    }
}