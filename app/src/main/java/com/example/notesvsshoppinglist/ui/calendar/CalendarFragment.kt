package com.example.notesvsshoppinglist.ui.calendar

import android.os.Bundle
import android.view.View
import com.example.notesvsshoppinglist.databinding.FragmentCalendarBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(FragmentCalendarBinding::inflate) {

    private val calendarViewModel: CalendarViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarViewModel.text.observe(viewLifecycleOwner) {
            binding.textCalendar.text = it
        }
    }

}