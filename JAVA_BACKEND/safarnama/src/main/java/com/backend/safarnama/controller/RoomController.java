package com.backend.safarnama.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Hotel;
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
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo")MultipartFile photo,@RequestParam("roomType")String roomType,@RequestParam("roomPrice")double roomPrice,@RequestParam("roomNo")String roomNo,@RequestParam("capacity")int capacity,@RequestParam("hotelId")Long hotelId) throws SerialException, IOException, SQLException{
			Room savedRoom = roomService.addNewRoom(photo,roomNo,roomType,roomPrice,capacity,hotelId);
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
	
	@DeleteMapping("/delete/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
		roomService.deleteRoom(roomId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/details/{roomId}")
	public ResponseEntity<?> getRoomById(@PathVariable Long roomId) {
		return ResponseEntity.ok(roomService.getRoomById(roomId));
	}
	
	@GetMapping("/all-rooms/{hotelId}")
	public ResponseEntity<?> getAllRoomById(@PathVariable Long hotelId) {
		return ResponseEntity.ok(roomService.getAllRoomById(hotelId));
	}
	
	@PutMapping("/update/{roomId}")
	public ResponseEntity<?> updateRoom(@PathVariable Long roomId,@RequestParam("photo")MultipartFile photo,@RequestParam("roomType")String roomType,@RequestParam("roomPrice")double roomPrice,@RequestParam("roomNo")String roomNo,@RequestParam("capacity")int capacity,@RequestParam("hotelId")Long hotelId) throws SerialException, IOException, SQLException{
		Room req = roomService.updateRoom(roomId, photo,roomNo,roomType,roomPrice,capacity,hotelId);
		RoomResponse response =new RoomResponse(req.getId(), req.getRoomNo(), req.getRoomType(), req.getRoomPrice(), req.getCapacity());
		return ResponseEntity.ok(response);
	}
	
	
}
