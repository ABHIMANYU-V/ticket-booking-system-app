package com.example.ticket_booking_system_app.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ticket_booking_system_app.model.Booking;
import com.example.ticket_booking_system_app.model.BookingStatus;

@Repository
public class BookingRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public BookingRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Booking getBookingByid(Long id) {
		String sql = "select * from booking where id = ?";
		return jdbcTemplate.queryForObject(sql, new BookingRowMapper(),id);
	}
	
	public List<Booking> getAllBookings(){
		String sql = "select * from booking ";
		return jdbcTemplate.query(sql, new BookingRowMapper());
	}
	
	public List<Booking> getConfirmedBookings(){
		String sql = "select * from booking where status ='CONFIRMED'";
		return jdbcTemplate.query(sql, new BookingRowMapper());
	}
	
	public List<Booking> getCanceledBookings(){
		String sql = "select * from booking where status ='CANCELED'";
		return jdbcTemplate.query(sql, new BookingRowMapper());
	}
	
	public int bookTicket(Booking booking) {
		String sql = "insert into booking (event_id,username,date,status) values(?,?,?,?)";
		return jdbcTemplate.update(sql,booking.getEventId(),booking.getUsername(),booking.getDate(),booking.getStatus());
	}
	
	public int cancelTicket(Long id) {
		String sql ="update booking set status = ? where id =?";
		return jdbcTemplate.update(sql,BookingStatus.CANCELED.toString(),id);
	}
	

}
