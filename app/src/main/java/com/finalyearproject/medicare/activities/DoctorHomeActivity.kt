package com.finalyearproject.medicare.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.finalyearproject.medicare.R
import com.finalyearproject.medicare.helpers.AppSharedPreference
import kotlinx.android.synthetic.main.activity_doctor_home.*

class DoctorHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_home)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        text_hello_user_name.text = "Hello, ${AppSharedPreference(this).getString("userName")}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                AppSharedPreference(this).clearSharedPref(AppSharedPreference.DEFAULT_SHARED_PREF)
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
                true
            }
            R.id.action_request_report -> {
                startActivity(Intent(this, RequestReportActivity::class.java))
                true
            }
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_doctor_home, menu)
        return true
    }
}
