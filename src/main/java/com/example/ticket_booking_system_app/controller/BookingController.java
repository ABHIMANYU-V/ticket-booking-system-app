package com.example.ticket_booking_system_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_booking_system_app.model.Booking;
import com.example.ticket_booking_system_app.model.Event;
import com.example.ticket_booking_system_app.service.BookingService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	private BookingService bookingService;
	
	@GetMapping("/get-instance")
	public Booking getInstance() {
		return new Booking();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingByid(@PathVariable Long id){
		Booking booking = bookingService.getBookingByid(id);
		
		if(booking == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/get-all-bookings")
	public ResponseEntity<List<Booking>> getBookings(){
		List<Booking> booking = bookingService.getAllBookings();
		
		if(booking == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/get-confirmed-bookings")
	public ResponseEntity<List<Booking>> getConfirmedBookings(){
		List<Booking> booking = bookingService.getConfirmedTickets();
		
		if(booking == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/get-canceled-bookings")
	public ResponseEntity<List<Booking>> getCanceledBookings(){
		List<Booking> booking = bookingService.getCanceledTickets();
		
		if(booking == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(booking);
	}
	
	@PostMapping("/book-ticket")
	public ResponseEntity<String> bookTicket(@RequestBody Booking booking){
		int rowsAffected = bookingService.bookTicket(booking);
		String message = rowsAffected > 0 ? "booked":"process failed";
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/{id}/cancel-ticket")
	public ResponseEntity<String> getCancel(@PathVariable Long id) {
		int rowsAffected = bookingService.cancelTicket(id);
		String message = rowsAffected > 0 ? "canceled":"process failed";
		return ResponseEntity.ok(message);
	}
	

}
