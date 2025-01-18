package com.ic.callengeliteraluraic.service;

import com.ic.callengeliteraluraic.model.Autor;
import com.ic.callengeliteraluraic.model.Libro;
import com.ic.callengeliteraluraic.repository.AutorRepository;
import com.ic.callengeliteraluraic.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro guardarLibro(Libro libro) {
        if (libro.getAutor() == null) {
            // Buscar el autor en la base de datos si es que el nombre ya está disponible
            Optional<Autor> autorExistente = autorRepository.findByNombre(libro.getAutor().getNombre());

            if (autorExistente.isPresent()) {
                // Si el autor ya existe, asignar el autor encontrado
                Autor autor = autorExistente.get();
                if (autor.getFechaNacimiento() == null) {
                    autor.setFechaNacimiento(libro.getAutor().getFechaNacimiento());
                }
                if (autor.getFechaMuerte() == null) {
                    autor.setFechaMuerte(libro.getAutor().getFechaMuerte());
                }
                libro.setAutor(autor);
            } else {
                // Si no existe, guardar el autor primero y luego asignarlo al libro
                Autor autorGuardado = autorRepository.save(libro.getAutor());
                libro.setAutor(autorGuardado);
            }
        }
        // Después de asegurar que el autor esté persistido, guardar el libro
        return libroRepository.save(libro);
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public List<Libro> librosBuscados() {
        return libroRepository.findAll();
    }

    public List<Autor> obtenerAutoresConLibros() {
        return autorRepository.findAllWithBooks();

    }
}
