package com.backend.safarnama.response;

import java.sql.Blob;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.backend.safarnama.model.Room;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class HotelResponse {
	private Long id;
	private String name;
	private String contactNo;
	private String email;
	private String street;
	private String city;
	private String state;
	private String image;
	
	List<RoomResponse> rooms;

	public HotelResponse(Long id, String name, String contactNo, String email, String street, String city, String state,
			byte[] image, List<RoomResponse> list) {
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.email = email;
		this.street = street;
		this.city = city;
		this.state = state;
		this.image = image!=null?Base64.encodeBase64String(image):null;
		this.rooms = list;
	}
	
	
}
