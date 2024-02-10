package com.backend.safarnama.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.MethodInvocationRecorder.Recorded.ToMapConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.exception.PhotoRetrivalException;
import com.backend.safarnama.exception.ResourceNotFoundException;
import com.backend.safarnama.model.Room;
import com.backend.safarnama.repository.BookedRoomRepository;
import com.backend.safarnama.repository.RoomRepository;
import com.backend.safarnama.response.BookingResponse;
import com.backend.safarnama.response.RoomResponse;

import ch.qos.logback.core.encoder.EncoderBase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
	@Autowired
	private final RoomRepository roomRepository;
	@Autowired
	private final IBookedRoomServices bookedRoomServices;
	@Autowired
	private ModelMapper model;

	@Override
	public Room addNewRoom(MultipartFile photo, String roomNO, String roomType, double roomPrice, int capacity) throws IOException, SerialException, SQLException {
		Room room =new Room();
		room.setRoomNo(roomNO);
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		room.setCapacity(capacity);
		if(!photo.isEmpty()) {
			byte[] photoBytes=photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
	
		return roomRepository.save(room);
	}

	@Override
	public List<String> getAllRoomTypes() {
		
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public List<RoomResponse> getAllRooms() {
		List<RoomResponse> response = roomRepository.findAll().stream().map(r->{
			byte[] photoBytes = new byte[0];
			try {
				photoBytes=r.getPhoto().getBytes(1,(int) r.getPhoto().length());
			} catch (SQLException e) {
				throw new PhotoRetrivalException(e.getMessage());
			}    
			RoomResponse roomResponse= model.map(r,RoomResponse.class);
			roomResponse.setPhoto(Base64.encodeBase64String(photoBytes));
			roomResponse.setBookings(getAllBookingsByRoomId(r.getId()));
				
				return roomResponse;
		}).collect(Collectors.toList());
		return response;
	}

	private List<BookingResponse> getAllBookingsByRoomId(Long id) {
		
		return bookedRoomServices.getAllBookingsByRoomId(id);
	}

}