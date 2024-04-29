package com.mastercoding.g15_lee_seol_comp304sec001_lab3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.AirlineScheduleApplication
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.adapter.AirScheduleAdapter
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule.Schedule
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.databinding.ActivityAirScheduleBinding
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.viewmodels.AirlineScheduleViewModel
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class AirScheduleActivity : AppCompatActivity() {

    private var _binding: ActivityAirScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AirScheduleAdapter

    private val viewModel: AirlineScheduleViewModel by viewModels {
        AirlineScheduleViewModelFactory((application as AirlineScheduleApplication).database.scheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAirScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Airline Schedule"

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.fullSchedule().collect { schedules ->
                adapter.submitList(schedules)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = AirScheduleAdapter { schedule ->
            navigateToDetailedSchedule(schedule)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun navigateToDetailedSchedule(schedule: Schedule) {
        val intent = Intent(this, DetailedScheduleActivity::class.java)
        intent.putExtra("AIRLINE_NAME", schedule.airlineName)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
