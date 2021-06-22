package com.maxst.rxjavaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		supportFragmentManager.beginTransaction()
				.replace(android.R.id.content, MainFragment(), this.toString())
				.commit()
	}
}