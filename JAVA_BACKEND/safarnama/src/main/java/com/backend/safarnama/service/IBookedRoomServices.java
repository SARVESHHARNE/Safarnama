package com.backend.safarnama.service;

import java.util.List;

import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.BookingResponse;

public interface IBookedRoomServices {
	List<BookingResponse> getAllBookingsByRoomId(Room r);
}
