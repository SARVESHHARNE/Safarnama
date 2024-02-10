package com.backend.safarnama.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.BookingResponse;
import com.backend.safarnama.response.RoomResponse;

public interface IRoomService {

	Room addNewRoom(MultipartFile photo, String roomNO, String roomType, double roomPrice, int capacity) throws IOException, SerialException, SQLException;

	List<String> getAllRoomTypes();

	List<RoomResponse>getAllRooms();

}