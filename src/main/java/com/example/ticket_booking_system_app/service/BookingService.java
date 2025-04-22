package com.example.ticket_booking_system_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ticket_booking_system_app.model.Booking;
import com.example.ticket_booking_system_app.repository.BookingRepository;
import com.example.ticket_booking_system_app.repository.BookingRowMapper;
import com.example.ticket_booking_system_app.repository.EventRepository;

@Service
public class BookingService {
	
	private final BookingRepository bookingRepository;
	
	private final EventRepository eventRepository;
	
	public BookingService(BookingRepository bookingRepository,EventRepository eventRepository) {
		this.bookingRepository = bookingRepository;
		this.eventRepository = eventRepository;
	}
	
	
	public Booking getBookingByid(Long id) {
		
		return bookingRepository.getBookingByid(id);
	}
	
	public List<Booking> getAllBookings(){
		return bookingRepository.getAllBookings();
	}
	
	public List<Booking> getConfirmedTickets(){
		return bookingRepository.getConfirmedBookings();
	}
	
	public List<Booking> getCanceledTickets(){
		return bookingRepository.getCanceledBookings();
	}
	
	public int bookTicket(Booking booking) {
		eventRepository.decreaseSeat(booking.getEventId());
		return bookingRepository.bookTicket(booking);
	}
	
	public int cancelTicket(Long id) {
		Booking booking = bookingRepository.getBookingByid(id);
		eventRepository.increaseSeat(booking.getEventId());
		return bookingRepository.cancelTicket(id);
	}
	

}
