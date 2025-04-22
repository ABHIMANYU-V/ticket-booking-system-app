package com.example.ticket_booking_system_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_booking_system_app.model.Event;
import com.example.ticket_booking_system_app.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/get-instance")
	public Event getInstance() {
		return new Event();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Event event = eventService.getEventByid(id);
		if(event == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(event);
	}
	
	@GetMapping("/get-all-events")
	public ResponseEntity<List<Event>> getAllEvent() {
		
		List<Event> events = eventService.getAllEvents();
		
		if(events == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(events);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add-event")
	public ResponseEntity<String> addEvent(@RequestBody Event event){
		int rowsAffected = eventService.addEvent(event);
		String message = rowsAffected > 0 ? "events added":"process failed";
		return ResponseEntity.ok(message);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/add-event")
	public ResponseEntity<String> updateEvent(@PathVariable Long id,@RequestBody Event event){
		int rowsAffected = eventService.updateEvent(id, event);
		String message = rowsAffected > 0 ? "updated":"process failed";
		return ResponseEntity.ok(message);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}/delete-event")
	public ResponseEntity<String> addEvent(@PathVariable Long id){
		int rowsAffected = eventService.deleteEvent(id);
		String message = rowsAffected > 0 ? "deleted":"process failed";
		return ResponseEntity.ok(message);
	}
}
