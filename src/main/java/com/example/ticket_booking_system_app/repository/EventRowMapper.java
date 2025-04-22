package com.example.ticket_booking_system_app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.ticket_booking_system_app.model.Event;

public class EventRowMapper implements RowMapper<Event>{

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong("id"));
		event.setEventName(rs.getString("event_name"));
		event.setDate(rs.getDate("date").toLocalDate());
		event.setPrice(rs.getDouble("price"));
		event.setAvailableSeats(rs.getInt("available_seats"));
		return event;
	}

}
