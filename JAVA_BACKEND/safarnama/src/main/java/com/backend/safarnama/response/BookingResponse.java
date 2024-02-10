package com.backend.safarnama.response;

import java.time.LocalDate;

import com.backend.safarnama.model.Room;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponse {
	
	private Long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String guestFullName;
	private String Email;
	private int numOfGuest;
	private String bookingConfirmationCode;
	
	private RoomResponse room;

	public BookingResponse(Long id, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode) {
		super();
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	
	
}
