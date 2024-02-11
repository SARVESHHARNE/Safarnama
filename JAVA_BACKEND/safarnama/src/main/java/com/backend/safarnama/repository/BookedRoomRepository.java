package com.backend.safarnama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.safarnama.model.BookedRoom;
import com.backend.safarnama.model.Room;


public interface BookedRoomRepository extends JpaRepository<BookedRoom,Long> {
	List<BookedRoom> getByRoom(Room room);
}
