package com.backend.safarnama.response;

import java.sql.Blob;

import org.apache.tomcat.util.codec.binary.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class PackageResposnse {
	private Long id;
	private String name;
	private String city;
	private String state;
	private String description;
	private double price;
	private int days;
	private String photo;
	
	public PackageResposnse(Long id,String name, String city, String state, String description, double price, int days,
			byte[] photo) {
		super();
		this.id = id;
		this.name=name;
		this.city = city;
		this.state = state;
		this.description = description;
		this.price = price;
		this.days = days;
		this.photo = photo!=null?Base64.encodeBase64String(photo):null;
	}
	
	
}
