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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.safarnama.exception.PhotoRetrivalException;
import com.backend.safarnama.exception.ResourceNotFoundException;
import com.backend.safarnama.model.Package;
import com.backend.safarnama.model.Room;
import com.backend.safarnama.repository.PackageReository;
import com.backend.safarnama.repository.RoomRepository;
import com.backend.safarnama.response.PackageResposnse;
import com.backend.safarnama.response.RoomResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PackageServiceImpl implements IPackageService {
	
	@Autowired
	private final PackageReository packageReository;
	@Autowired
	private final ModelMapper mapper;

	@Override
	public List<PackageResposnse> getPackages() {
		List<PackageResposnse> response = packageReository.findAll().stream().map(r->{
			byte[] photoBytes = new byte[0];
			try {
				photoBytes=r.getPhoto().getBytes(1,(int) r.getPhoto().length());
			} catch (SQLException e) {
				throw new PhotoRetrivalException(e.getMessage());
			}    
			PackageResposnse packageResposnse= mapper.map(r,PackageResposnse.class);
			packageResposnse.setPhoto(Base64.encodeBase64String(photoBytes));
			return packageResposnse;
		}).collect(Collectors.toList());
		return response;
	}

	@Override
	public Package addNewPackage(MultipartFile photo, String name, double price, int days, String city, String state,String desc) throws SerialException, SQLException, IOException {
		
		Package package1=new Package();
		package1.setName(name);
		package1.setPrice(price);
		package1.setDays(days);
		package1.setCity(city);
		package1.setState(state);
		package1.setDescription(desc);
		if(!photo.isEmpty()) {
			byte[] photoBytes=photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			package1.setPhoto(photoBlob);
		}
	
		return packageReository.save(package1);
	}

	@Override
	public PackageResposnse getPackageById(Long packageId) {
		Package p=packageReository.findById(packageId).orElseThrow(()->new ResourceNotFoundException("faild to get package"));
		byte[] photoBytes = new byte[0];
		try {
			photoBytes=p.getPhoto().getBytes(1,(int) p.getPhoto().length());
		} catch (SQLException e) {
			throw new PhotoRetrivalException(e.getMessage());
		}    
		PackageResposnse packageResposnse= mapper.map(p,PackageResposnse.class);
		packageResposnse.setPhoto(Base64.encodeBase64String(photoBytes));
		return packageResposnse;
	}

}
