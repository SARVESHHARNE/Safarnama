package com.backend.safarnama.response;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class RoomResponse {
	private Long id;
	private String roomNo;
	private String roomType;
	private double roomPrice;
	private int capacity;
	private boolean isBooked=false;
	private String photo;
	
	private Long hotelId;
	
	private List<BookingResponse> bookings;

	public RoomResponse(Long id, String roomNo, String roomType, double roomPrice, int capacity) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.capacity = capacity;
	}

	public RoomResponse(Long id, String roomNo, String roomType, double roomPrice, int capacity, boolean isBooked,
			byte[] photo, List<BookingResponse> bookings,Long hotel) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.capacity = capacity;
		this.isBooked = isBooked;
		this.photo = photo!=null?Base64.encodeBase64String(photo):null;
		this.bookings = bookings;
		this.hotelId=hotel;
	}
	
}
