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

private const val TAG = "LambdaFragment"

class LambdaFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lambda, container, false)
    }

    override fun onResume() {
        super.onResume()

        val simpleObservable = Observable.just("Hello RxAndroid")

        setSubscriber1(simpleObservable)
    }

    private fun setSubscriber1(observable: Observable<String>) {
        val disposable = observable
            .map { inputString ->
                    Log.d(TAG, "inputString: $inputString")
                    Random().nextInt()
            }.subscribe(
                { value ->
                    Log.d(TAG, "onNext: $value")
                    val textView = requireView().findViewById(R.id.textView1) as TextView
                    textView.text = "Random number : $value"
                },
                { error -> Log.d(TAG, "onError" + error.message) },
                { Log.d(TAG, "onComplete") }
            )

        disposable.dispose()
    }
}