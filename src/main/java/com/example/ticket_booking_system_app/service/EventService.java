package com.example.ticket_booking_system_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ticket_booking_system_app.model.Event;
import com.example.ticket_booking_system_app.repository.EventRepository;

@Service
public class EventService {
	
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	
	public Event getEventByid(Long id) {
		return eventRepository.getEventById(id);
	}
	
	public List<Event> getAllEvents() {
		return eventRepository.getAllEvents();
		
	}
	
	public int addEvent(Event event) {
		return eventRepository.addEvent(event);
	}
	
	public int updateEvent(Long id,Event event) {
		return eventRepository.updateEvent(id, event);
	}
	
	public int deleteEvent(Long id) {
		return eventRepository.deleteEvent(id);
	}

}
