
package com.rose.mobiletrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rose.mobiletrack.model.Booking
import com.rose.mobiletrack.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository) : ViewModel() {
    val allBooking = repository.allBooking

    private val _selectedBooking = MutableStateFlow<Booking?>(null)
    val selectedBooking: StateFlow<Booking?> = _selectedBooking.asStateFlow()

    fun insert(booking: Booking) = viewModelScope.launch {
        repository.insert(booking)
    }

    fun update(booking: Booking) = viewModelScope.launch {
        repository.update(booking)
    }

    fun delete(booking: Booking) = viewModelScope.launch {
        repository.delete(booking)
    }

    fun loadBookingById(id: Int) = viewModelScope.launch {
        _selectedBooking.value = repository.getById(id)
    }


}

