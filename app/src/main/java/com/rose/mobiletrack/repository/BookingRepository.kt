package com.rose.mobiletrack.repository


import com.rose.mobiletrack.data.BookingDao
import com.rose.mobiletrack.model.Booking

class BookingRepository(private val bookingDao: BookingDao) {
    val allBooking = bookingDao.getAllBooking()

    suspend fun insert(booking: Booking) = bookingDao.insertBooking(booking)
    suspend fun update(booking:Booking) = bookingDao.updateBooking(booking)
    suspend fun delete(booking: Booking) = bookingDao.deleteBooking(booking)
    suspend fun getById(id: Int) = bookingDao.getBookingById(id)

}
