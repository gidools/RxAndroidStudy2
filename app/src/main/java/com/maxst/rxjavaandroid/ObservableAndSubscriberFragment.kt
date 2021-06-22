package com.maxst.rxjavaandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException


private const val TAG = "ObservableAndSubscriber"

class ObservableAndSubscriberFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_observable_and_subscriber, container, false)
    }

    override fun onResume() {
        super.onResume()

        val simpleObservable = Observable.create(ObservableOnSubscribe<String> { emitter ->
            // emitter.onError(RuntimeException("Error!!"))
            emitter.onNext("Hello RxAndroid!")
            emitter.onComplete()
        })

        setSubscriber1(simpleObservable)
        setSubscriber2(simpleObservable)
    }

    private fun setSubscriber1(observable: Observable<String>) {
        observable
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onNext(text: String) {
                    Log.d(TAG, "onNext")
                    (requireView().findViewById(R.id.textView1) as TextView).text = text
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError!")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete!")
                }
            })
    }

    private fun setSubscriber2(observable: Observable<String>) {
        val result = observable.subscribe(
            { inputText ->
                Log.d(TAG, "OnNext consumer")
                (requireView().findViewById(R.id.textView2) as TextView).text = "length: " + inputText.length
            },
            { Log.d(TAG, "OnError consumer") },
            { Log.d(TAG, "OnComplete consume") }
        )
    }
}