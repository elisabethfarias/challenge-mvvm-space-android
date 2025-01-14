package com.devpass.spaceapp.presentation.launch_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.presentation.LaunchActivity
import com.devpass.spaceapp.presentation.view_model.LaunchListViewModel

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter

    private val viewModel by viewModels<LaunchListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupRecycleView()
        observeLaunchList()

    }

    private fun observeLaunchList() {

        viewModel.launches.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter {
            Log.i(TAG, "on click $it")
            startActivity(Intent(baseContext, LaunchActivity::class.java).also { i ->
                i.putExtra(LAUNCH_MODEL, it)
            })
        }
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val TAG = "LaunchListActivity"
        private const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }
}
