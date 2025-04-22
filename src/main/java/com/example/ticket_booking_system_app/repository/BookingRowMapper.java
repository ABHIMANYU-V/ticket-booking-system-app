package com.example.ticket_booking_system_app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.ticket_booking_system_app.model.Booking;
import com.example.ticket_booking_system_app.model.BookingStatus;

public class BookingRowMapper implements RowMapper<Booking> {

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		Booking booking = new Booking();
		booking.setId(rs.getLong("id"));
		booking.setEventId(rs.getLong("event_id"));
		booking.setUsername(rs.getString("username"));
		booking.setDate(rs.getDate("date").toLocalDate());
		booking.setStatus(rs.getString("status"));
		return booking;
	}

}
