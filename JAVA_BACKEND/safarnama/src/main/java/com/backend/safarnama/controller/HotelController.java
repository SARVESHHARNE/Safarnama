package com.backend.safarnama.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Hotel;
import com.backend.safarnama.response.HotelResponse;
import com.backend.safarnama.response.RoomResponse;
import com.backend.safarnama.service.IHotelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {
	
		@Autowired
		private final IHotelService hotelService;
		@Autowired
		private final ModelMapper mapper;

		@PostMapping("/add/hotel")
		public ResponseEntity<HotelResponse> addNewHotel(@RequestParam("image")MultipartFile image,@RequestParam("name")String name,@RequestParam("contactNo")String contactNo,@RequestParam("email")String email,@RequestParam("street")String street,@RequestParam("city")String city,@RequestParam("state")String state,@RequestParam("password")String password) throws SerialException, IOException, SQLException{
				Hotel savedHotel = hotelService.addNewHotel(image,name,contactNo,email,street, city.toUpperCase(),state ,password);
				HotelResponse response =new HotelResponse(savedHotel.getId(),savedHotel.getName(),savedHotel.getContactNo(),savedHotel.getEmail(),savedHotel.getStreet(),savedHotel.getCity(),savedHotel.getState(),savedHotel.getImage().getBytes(1,(int) savedHotel.getImage().length()),savedHotel.getRooms().stream().map(r->mapper.map(r,RoomResponse.class)).toList());
				return ResponseEntity.ok(response);
		}
		
		@GetMapping("/citys")
		public ResponseEntity<?> getCitys(){
			return ResponseEntity.ok(hotelService.getAllHotelCitys());
		}
		
		@GetMapping("/all-hotels")
		public ResponseEntity<?> getAllHotels() {
			return ResponseEntity.ok(hotelService.getAllHotels());
		}
		
		@DeleteMapping("/delete/{hotelId}")
		public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId){
			hotelService.deleteHotel(hotelId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@GetMapping("/details/{hotelId}")
		public ResponseEntity<?> getHotelById(@PathVariable Long hotelId) throws SQLException {
			return ResponseEntity.ok(hotelService.getHotelById(hotelId));
		}
		
//		@PutMapping("/update/{HotelId}")
//		public ResponseEntity<?> updateHotel(@PathVariable Long hotelId,@RequestParam("image")MultipartFile image,@RequestParam("hotelType")String hotelType,@RequestParam("hotelPrice")double hotelPrice,@RequestParam("capacity")int capacity) throws SerialException, IOException, SQLException{
//			Hotel req = hotelService.updateHotel(hotelId, image,hotelType,hotelPrice,capacity);
//			HotelResponse response =new HotelResponse(req.getId(), req.getHotelType(), req.getHotelPrice(), req.getCapacity());
//			return ResponseEntity.ok(response);
//		}
		
		@PutMapping("/update/{hotelId}")
		public ResponseEntity<?> uotelHotel(@PathVariable Long hotelId,@RequestParam("image")MultipartFile image,@RequestParam("name")String name,@RequestParam("contactNo")String contactNo,@RequestParam("email")String email,@RequestParam("street")String street,@RequestParam("city")String city,@RequestParam("state")String state,@RequestParam("password")String password) throws SerialException, IOException, SQLException{
				Hotel savedHotel = hotelService.updateHotel(hotelId,image,name,contactNo,email,street, city.toUpperCase(),state ,password);
				HotelResponse response =new HotelResponse(savedHotel.getId(),savedHotel.getName(),savedHotel.getContactNo(),savedHotel.getEmail(),savedHotel.getStreet(),savedHotel.getCity(),savedHotel.getState(),savedHotel.getImage().getBytes(1,(int) savedHotel.getImage().length()),savedHotel.getRooms().stream().map(r->mapper.map(r,RoomResponse.class)).toList());
				return ResponseEntity.ok(response);
		}
		
		
	
}
