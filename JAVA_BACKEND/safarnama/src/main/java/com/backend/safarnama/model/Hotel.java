package com.backend.safarnama.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String contactNo;
	@Column(unique = true,nullable = false)
	private String email;
	private String street;
	private String city;
	private String state;
	@Lob
	private Blob image;
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Room> rooms;

	public Hotel() {
		this.rooms=new ArrayList<>();
	}
	

	public void addRooms(Room room) {
		if(room==null) {
			rooms=new ArrayList<>();
		}
		rooms.add(room);
		room.setHotel(this);
	}
	

}
