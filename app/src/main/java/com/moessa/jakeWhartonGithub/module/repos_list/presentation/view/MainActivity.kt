package com.moessa.jakeWhartonGithub.module.repos_list.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moessa.jakeWhartonGithub.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_repos, ReposListFragment.newInstance())
                .commit()
        }
    }
}