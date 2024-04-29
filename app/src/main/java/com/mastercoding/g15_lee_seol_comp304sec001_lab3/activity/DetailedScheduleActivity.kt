package com.mastercoding.g15_lee_seol_comp304sec001_lab3.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.AirlineScheduleApplication
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.adapter.DetailedScheduleAdapter
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.databinding.ActivityDetailedScheduleBinding
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.viewmodels.AirlineScheduleViewModel
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class DetailedScheduleActivity : AppCompatActivity() {

    private var _binding: ActivityDetailedScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DetailedScheduleAdapter

    private val viewModel: AirlineScheduleViewModel by viewModels {
        AirlineScheduleViewModelFactory((application as AirlineScheduleApplication).database.scheduleDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailedScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val airlineName = intent.getStringExtra("AIRLINE_NAME") ?: ""
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = airlineName
        }
        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.scheduleForAirlineName(airlineName).collect { schedules ->
                adapter.submitList(schedules)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = DetailedScheduleAdapter {}
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}