package com.backend.safarnama.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.RoomResponse;
import com.backend.safarnama.service.IBookedRoomServices;
import com.backend.safarnama.service.IRoomService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
	@Autowired
	private final IRoomService roomService;
	private final IBookedRoomServices bookedRoomServices;

	@PostMapping("/add/room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo")MultipartFile photo,@RequestParam("roomType")String roomType,@RequestParam("roomPrice")double roomPrice,@RequestParam("roomNo")String roomNO,@RequestParam("capacity")int capacity) throws SerialException, IOException, SQLException{
			Room savedRoom = roomService.addNewRoom(photo,roomNO,roomType,roomPrice,capacity);
			RoomResponse response =new RoomResponse(savedRoom.getId(), savedRoom.getRoomNo(), savedRoom.getRoomType(), savedRoom.getRoomPrice(), savedRoom.getCapacity());
			return ResponseEntity.ok(response);
	}
	
	@GetMapping("/types")
	public ResponseEntity<?> get(){
		return ResponseEntity.ok(roomService.getAllRoomTypes());
	}
	
	@GetMapping("/all-rooms")
	public ResponseEntity<?> getAllRooms() {
		return ResponseEntity.ok(roomService.getAllRooms());
	}
	
	
}
