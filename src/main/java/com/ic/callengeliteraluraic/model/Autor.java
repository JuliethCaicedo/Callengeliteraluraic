package com.ic.callengeliteraluraic.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Integer fechaNacimiento;
    @Column(name = "fecha_muerte")
    private Integer fechaMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }
    
    public Autor (DatosAutores autor){;
        this.nombre = autor.name();
        this.fechaNacimiento = (autor.birthyear() != null) ? autor.birthyear() : 0;
        this.fechaMuerte = (autor.deathyear() != null) ? autor.deathyear() : 0;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }



        @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("\n-------- Autor --------\n")
                    .append("Nombre: ").append(nombre).append("\n")
                    .append("Fecha de Nacimiento: ").append(fechaNacimiento).append("\n")
                    .append("Fecha de Muerte: ").append(fechaMuerte).append("\n")
                    .append("Libros: \n");

            // Recorre la lista de libros y agrega cada título
            for (Libro libro : libros) {
                sb.append("  * ").append(libro.getTitulo()).append("\n");
            }

            sb.append("_______________________\n");
            return sb.toString();
    }
    
    public List<Libro> getLibros(){
        return libros;
    }

    public void setLibros (List<Libro> libros) {
        this.libros = libros;
    }
}
