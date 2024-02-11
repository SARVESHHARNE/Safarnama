package com.backend.safarnama.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.safarnama.model.Room;
import com.backend.safarnama.repository.BookedRoomRepository;
import com.backend.safarnama.response.BookingResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookedRoomServicesImpl implements IBookedRoomServices {
	
	@Autowired
	private final BookedRoomRepository bookedRoomRepository;
	@Autowired
	private final ModelMapper mapper;
	
	@Override
	public List<BookingResponse> getAllBookingsByRoomId(Room r) {
		List<BookingResponse> resp=bookedRoomRepository.getByRoom(r).stream().map(s->mapper.map(r, BookingResponse.class)).toList();
		return resp;
	}

}
