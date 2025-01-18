package com.ic.callengeliteraluraic.repository;

import com.ic.callengeliteraluraic.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithBooks();

    Optional<Autor> findByNombre(String nombre);
}
