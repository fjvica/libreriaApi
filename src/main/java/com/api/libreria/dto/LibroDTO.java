package com.api.libreria.dto;

import org.springframework.web.multipart.MultipartFile;

public class LibroDTO {

	private long id;
	private String name;
	private String price;
	private String autor;

	public LibroDTO(long id, String name, String price, String autor) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.autor = autor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
