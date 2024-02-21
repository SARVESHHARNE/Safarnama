package com.backend.safarnama.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Hotel;
import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.BookingResponse;
import com.backend.safarnama.response.RoomResponse;

public interface IRoomService {

	List<String> getAllRoomTypes();

	List<RoomResponse>getAllRooms();

	void deleteRoom(Long roomId);

	RoomResponse getRoomById(Long roomId);

	Room updateRoom(Long roomId, MultipartFile photo, String roomNo, String roomType, double roomPrice, int capacity,Long hotelId) throws SerialException, SQLException, IOException;

	List<RoomResponse> getAllRoomById(Long hotelId);

	Room addNewRoom(MultipartFile photo, String roomNo, String roomType, double roomPrice, int capacity, Long hotelId)
			throws IOException, SerialException, SQLException;

}
