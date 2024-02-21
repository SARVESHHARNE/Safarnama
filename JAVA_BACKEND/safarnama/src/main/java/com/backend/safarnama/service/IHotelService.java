package com.backend.safarnama.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.model.Hotel;

public interface IHotelService {

	void deleteHotel(Long hotelId);

	Object getAllHotels();

	Object getAllHotelCitys();

	Object getHotelById(Long hotelId) throws SQLException;

	Hotel addNewHotel(MultipartFile image, String name, String contactNo, String email, String street, String city,
			String state, String password) throws SerialException, SQLException, IOException;

	Hotel updateHotel(Long hotelId, MultipartFile image, String name, String contactNo, String email, String street,
			String city, String state, String password) throws IOException, SerialException, SQLException;

}
