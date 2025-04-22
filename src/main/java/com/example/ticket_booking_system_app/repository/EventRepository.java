package com.example.ticket_booking_system_app.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ticket_booking_system_app.model.Event;

@Repository
public class EventRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public EventRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public Event getEventById(Long id){
		String sql= "select * from events where id = ?";
		return jdbcTemplate.queryForObject(sql, new EventRowMapper(),id);
	}
	
	public List<Event> getAllEvents(){
		String sql= "select * from events";
		return jdbcTemplate.query(sql, new EventRowMapper());
	}
	
	public int addEvent(Event event) {
		String sql ="insert into events (event_name,date,price,available_seats) values(?,?,?,?)";
		return jdbcTemplate.update(sql,event.getEventName(),event.getDate(),event.getPrice(),event.getAvailableSeats());
	}
	
	
	public int updateEvent(Long id,Event event) {
		String sql = "update events set event_name=?,date=?,price=?,available_seats=? where id = ?";
		return jdbcTemplate.update(sql,event.getEventName(),event.getDate(),event.getPrice(),event.getAvailableSeats(),id);
	}
	
	public int deleteEvent(Long id) {
		String sql = "delete from events where id = ?";
		return jdbcTemplate.update(sql,id);
	}
	
	public void decreaseSeat(Long eventId) {
		String sql ="update events set available_seats = available_seats - 1 where id = ?";
		jdbcTemplate.update(sql,eventId);
	}
	
	public void increaseSeat(Long eventId) {
		String sql ="update events set available_seats = available_seats + 1 where id = ?";
		jdbcTemplate.update(sql,eventId);
	}

}
