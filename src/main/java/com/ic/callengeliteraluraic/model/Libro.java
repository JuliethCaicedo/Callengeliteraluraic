package com.ic.callengeliteraluraic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String titulo;
    private String idioma;
    private Integer descargas;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro (){

    }


    public Libro (DatosLibro libro){
        this.titulo = libro.title();
        this.descargas = libro.downloadCount();
        if (!libro.languages().isEmpty())
            this.idioma = libro.languages().get(0);

        if (libro.authors() != null && !libro.authors().isEmpty()) {
            DatosAutores datosAutor = libro.authors().get(0);
            Autor autor = new Autor();
            autor.setNombre(datosAutor.name());
            autor.setFechaNacimiento(datosAutor.birthyear());
            autor.setFechaMuerte(datosAutor.deathyear());
            this.autor = autor;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n" + "------Libro--------" + "\n" +
                "titulo= " + titulo + "\n" +
                "autores= " + ( autor.getNombre()) +"\n" +
                "idioma= " + idioma + "\n" +
                "descargas= " + descargas + "\n" +
                "__________________" + "\n";
    }

}
