package com.backend.safarnama.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Package;
import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.PackageResposnse;
import com.backend.safarnama.response.RoomResponse;
import com.backend.safarnama.service.IBookedRoomServices;
import com.backend.safarnama.service.IPackageService;
import com.backend.safarnama.service.IRoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:3000")
public class PackageController {
	
	@Autowired
	private final IPackageService packageService;
	@Autowired
	private final ModelMapper mapper;
	
	@GetMapping("/all-package")
	public ResponseEntity<?> getPackages() {
		return ResponseEntity.ok(packageService.getPackages());
	}
	
	@PostMapping("/add/package")
	public ResponseEntity<?> addNewPackage(@RequestParam("photo")MultipartFile photo,@RequestParam("name")String name,@RequestParam("price")double price,@RequestParam("days")int days,@RequestParam("city")String city,@RequestParam("state")String state,@RequestParam("description")String desc) throws SerialException, IOException, SQLException{
			Package savedPackage=packageService.addNewPackage(photo,name,price,days,city.toUpperCase(),state,desc);
			PackageResposnse response =new PackageResposnse();
			response.setId(savedPackage.getId());
			return ResponseEntity.ok(response);
	}
//	
//	@GetMapping("/types")
//	public ResponseEntity<?> get(){
//		return ResponseEntity.ok(roomService.getAllRoomTypes());
//	}
//	
//	@GetMapping("/all-rooms")
//	public ResponseEntity<?> getAllRooms() {
//		return ResponseEntity.ok(roomService.getAllRooms());
//	}
//	
//	@DeleteMapping("/delete/{roomId}")
//	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
//		roomService.deleteRoom(roomId);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
	@GetMapping("/details/{packageId}")
	public ResponseEntity<?> getPackageById(@PathVariable Long packageId) {
		return ResponseEntity.ok(packageService.getPackageById(packageId));
	}
//	
//	@PutMapping("/update/{roomId}")
//	public ResponseEntity<?> updateRoom(@PathVariable Long roomId,@RequestParam("photo")MultipartFile photo,@RequestParam("roomType")String roomType,@RequestParam("roomPrice")double roomPrice,@RequestParam("roomNo")String roomNo,@RequestParam("capacity")int capacity) throws SerialException, IOException, SQLException{
//		Room req = roomService.updateRoom(roomId, photo,roomNo,roomType,roomPrice,capacity);
//		RoomResponse response =new RoomResponse(req.getId(), req.getRoomNo(), req.getRoomType(), req.getRoomPrice(), req.getCapacity());
//		return ResponseEntity.ok(response);
//	}
	
	
}
