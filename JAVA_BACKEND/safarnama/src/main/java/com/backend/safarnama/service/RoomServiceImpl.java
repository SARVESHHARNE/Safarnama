package com.backend.safarnama.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import com.backend.safarnama.model.Hotel;
import com.backend.safarnama.model.Room;
import com.backend.safarnama.repository.BookedRoomRepository;
import com.backend.safarnama.repository.HotelRepository;
import com.backend.safarnama.repository.RoomRepository;
import com.backend.safarnama.response.BookingResponse;
import com.backend.safarnama.response.RoomAuth;
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
	private final HotelRepository hotelRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Room addNewRoom(MultipartFile photo, String roomNo, String roomType, double roomPrice, int capacity,Long hotelId) throws IOException, SerialException, SQLException {
		RoomAuth room =new RoomAuth();
		room.setRoomNo(roomNo);
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		room.setCapacity(capacity);
		room.setHotelId(hotelId);
		if(!photo.isEmpty()) {
			byte[] photoBytes=photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
		Hotel h=hotelRepository.findById(room.getHotelId()).orElseThrow();
		h.addRooms(mapper.map(room, Room.class));
		System.err.println(h.getCity());
		
		return mapper.map(room, Room.class);
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
			RoomResponse roomResponse= mapper.map(r,RoomResponse.class);
			roomResponse.setPhoto(Base64.encodeBase64String(photoBytes));
			roomResponse.setBookings(getAllBookingsByRoomId(r));
				
			return roomResponse;
		}).collect(Collectors.toList());
		return response;
	}

	private List<BookingResponse> getAllBookingsByRoomId(Room r) {
		
		return bookedRoomServices.getAllBookingsByRoomId(r);
	}

	@Override
	public void deleteRoom(Long roomId) {
		Optional<Room> room =roomRepository.findById(roomId);
		if(room.isPresent()) {
			roomRepository.delete(room.orElseThrow(()->new ResourceNotFoundException("room not found")));
		}
		
	}

	@Override
	public RoomResponse getRoomById(Long roomId) {
		Room room =roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("room not found"));
		byte[] photoBytes = new byte[0];
		try {
			photoBytes=room.getPhoto().getBytes(1,(int) room.getPhoto().length());
		} catch (SQLException e) {
			throw new PhotoRetrivalException(e.getMessage());
		} 
		RoomResponse roomResponse= mapper.map(room,RoomResponse.class);
		roomResponse.setPhoto(Base64.encodeBase64String(photoBytes));
		roomResponse.setBookings(getAllBookingsByRoomId(room));
			
		return roomResponse;
	}

	@Override
	public Room updateRoom(Long roomId, MultipartFile photo, String roomNo, String roomType, double roomPrice,
			int capacity,Long hotelId) throws SerialException, SQLException, IOException {
		Room room =new Room();
		room.setId(roomId);
		room.setRoomNo(roomNo);
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		room.setCapacity(capacity);
		
		if(!photo.isEmpty()) {
			byte[] photoBytes=photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
		Hotel h=hotelRepository.findById(hotelId).orElseThrow();
		h.addRooms(room);
		return room;
	}

	@Override
	public List<RoomResponse> getAllRoomById(Long hotelId) {
		Hotel h=new Hotel();
		h.setId(hotelId);
		List<RoomResponse> response = roomRepository.findAllByHotel(h).stream().map(r->{
			byte[] photoBytes = new byte[0];
			try {
				photoBytes=r.getPhoto().getBytes(1,(int) r.getPhoto().length());
				System.out.println(r.getHotel().getEmail());
			} catch (SQLException e) {
				throw new PhotoRetrivalException(e.getMessage());
			}    
			RoomResponse roomResponse= mapper.map(r,RoomResponse.class);
			roomResponse.setPhoto(Base64.encodeBase64String(photoBytes));	
			return roomResponse;
		}).collect(Collectors.toList());
		return response;
	}


}
