package com.backend.safarnama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.safarnama.model.Room;
import com.backend.safarnama.response.RoomResponse;
import com.backend.safarnama.model.Hotel;


public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query("SELECT DISTINCT r.roomType FROM Room r")
	List<String> findDistinctRoomTypes();

	List<Room> findAllByHotel(Hotel hotel);

}
