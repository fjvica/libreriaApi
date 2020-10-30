package com.api.libreria.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.libreria.dao.LibroDAO;
import com.api.libreria.dto.LibroDTO;
import com.api.libreria.entity.Libro;
import com.google.gson.Gson;

@RestController
@RequestMapping("libro")
public class LibroREST {
	private static String UPLOADED_FOLDER = "C://Users//fran//eclipse-workspace//libreria//src//main//resources//images//";

	@Autowired
	private LibroDAO libroDAO;

	@GetMapping
	public ResponseEntity<List<Libro>> getLibros() {
		List<Libro> libros = libroDAO.findAll();
		return ResponseEntity.ok(libros);
	}

	@RequestMapping(value = "{libroId}") // /libro/{libroId}
	public ResponseEntity<Libro> getLibrosById(@PathVariable("libroId") long libroId) {
		Optional<Libro> optionalLibro = libroDAO.findById(libroId);

		if (!optionalLibro.isPresent()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(optionalLibro.get());
		}
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Libro> createLibro(@RequestPart String model,
			@RequestPart(required = false) MultipartFile file) throws IOException {
		Libro libro = new Libro();
		LibroDTO data = new Gson().fromJson(model, LibroDTO.class);
		if (file.isEmpty()) {
			libro = new Libro(data.getName(), Integer.parseInt(data.getPrice()), data.getAutor(), null);
		} else {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			libro = new Libro(data.getName(), Integer.parseInt(data.getPrice()), data.getAutor(),
					file.getOriginalFilename());
		}

		Libro newLibro = libroDAO.save(libro);

		return ResponseEntity.ok(newLibro);
	}

	@DeleteMapping(value = "{libroId}")
	public ResponseEntity<Void> deleteLibro(@PathVariable("libroId") Long libroId) {
		libroDAO.deleteById(libroId);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
		Optional<Libro> optionalLibro = libroDAO.findById(libro.getId());
		if (optionalLibro.isPresent()) {
			Libro updateLibro = optionalLibro.get();
			updateLibro.setName(libro.getName());
			updateLibro.setPrice(libro.getPrice());
			updateLibro.setAutor(libro.getAutor());
			libroDAO.save(updateLibro);
			return ResponseEntity.ok(updateLibro);
		} else {

			return ResponseEntity.noContent().build();
		}
	}
}
