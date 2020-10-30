package com.api.libreria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false, length = 45)
	private String name;
	@Column(name = "price", nullable = false)
	private int price;
	@Column(name = "autor", nullable = false, length = 45)
	private String autor;
	@Column(name = "imagen", length = 200)
	private String imagen;

	public Libro(String name, int price, String autor, String imagen) {
		this.name = name;
		this.price = price;
		this.autor = autor;
		this.imagen = imagen;
	}
	public Libro() {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
