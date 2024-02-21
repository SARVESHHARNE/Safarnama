package com.backend.safarnama.response;

import java.sql.Blob;
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
public class RoomAuth {
	private Long id;
	private String roomNo;
	private String roomType;
	private double roomPrice;
	private int capacity;
	private boolean isBooked=false;
	private Blob photo;
	
	private Long hotelId;

	
}
