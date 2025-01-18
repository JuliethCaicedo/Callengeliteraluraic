package com.ic.callengeliteraluraic.principal;

import com.ic.callengeliteraluraic.model.Autor;
import com.ic.callengeliteraluraic.model.Datos;
import com.ic.callengeliteraluraic.model.Libro;
import com.ic.callengeliteraluraic.repository.AutorRepository;
import com.ic.callengeliteraluraic.service.ConsumoApi;
import com.ic.callengeliteraluraic.service.ConvierteDatos;
import com.ic.callengeliteraluraic.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?languages=en,es";
    private Scanner teclado = new Scanner(System.in);

    private final LibroService libroService;
    private AutorRepository autorRepository;

    @Autowired
    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    public void muestraElMenu() {

        //Opciones del menu que se muestra al usuario
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la Opcion a través de su número:
                    1. Buscar Libro por Titulo
                    2. Lista de Libros Registrados
                    3. lista de Autores Registrados
                    4. Lista de Autores Vivos en un determinado año
                    5. Lista de Libros por idioma
                    
                    0.Salir
                    
                    """;

            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                //Opciones
                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        mostarLibrosRegistrados();
                        break;
                    case 3:
                        mostrarAutoresRegistrados();
                        break;
                    case 4:
                        mostrarAutoresVivosPorFecha();
                        break;
                    case 5:
                        buscarLibroPorIdioma();
                        break;
                    case 0:
                        System.out.println("\n" + "Vuelve Pronto .. Cerrando"+ "\n");
                        break;
                    default:
                        System.out.println("\n" + "Opcion invalida, intenta de nuevo"+ "\n");

                }
            } catch (InputMismatchException e) {
                System.out.println("\n" + "Entrada inválida. Por favor, ingrese un número correspondiente a una opción del menú." + "\n" );
                teclado.nextLine();
            }
        }
    }

    // Formulas

        private Datos getDatosLibro() {
            //Interacción con el Usuario para que ingrese el libro a buscar
            System.out.println("Ingrese el nombre del libro que deseas buscar");
            var tituloLibro = teclado.nextLine();

            //Busco en la API segun el libro ingresado
            var json = consumoApi.obtenerDatos(URL_BASE + "&search=" + tituloLibro.replace(" ", "+"));

            //Convierto los Datos obtenidos
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

            return datosBusqueda;
        }

        private void buscarLibroPorTitulo() {

            // Obtén los datos del libro desde la API
            Datos datos = getDatosLibro();

            // Verifico si existe el libro
                if (!datos.listaLibros().isEmpty()) {

            // Convierte los datos del primer libro a la entidad Libro
                Libro libro = new Libro(datos.listaLibros().get(0));

            //varifico que no este repetido el libro
                Optional<Libro> existente = libroService.buscarPorTitulo(libro.getTitulo());
                    if (existente.isEmpty()) {
                        libroService.guardarLibro(libro);
                        //Imprimo en pantalla
                        System.out.println(libro.toString());
                    } else {
                        System.out.println("\n" + "El libro ya existe en la base de datos." + "\n" );
                    }

            }else {
                    System.out.println("\n" + "No se encontró un libro con ese titulo."+"\n");
                }
        }

        private void mostarLibrosRegistrados() {
           // Obtengo los libros
            var librosBuscados = libroService.librosBuscados();
          // Valido que no este vacia la base de datos
            if (librosBuscados.isEmpty()) {
                System.out.println("\n" + "Lista vacía, no has agregado libros" + "\n" );
            } else {
                //muestra los libros
                System.out.println("\n" + "Los libros registrados en la base de datos son : ");
                librosBuscados.forEach(libro -> System.out.println(libro.toString()));
            }
        }

        private void mostrarAutoresRegistrados() {

            // lista de los autores con sus libros
            List<Autor> autores = libroService.obtenerAutoresConLibros();

            // Uso del Map para agrupar autores por su nombre
            Map<String, Autor> autoresUnicos = new LinkedHashMap<>();

            // Agrupamos los libros de cada autor
            for (Autor autor : autores) {
                if (autoresUnicos.containsKey(autor.getNombre())) {
                    // Si el autor ya está en el mapa, agregamos sus libros
                    Autor autorExistente = autoresUnicos.get(autor.getNombre());
                    autorExistente.getLibros().addAll(autor.getLibros());
                } else {
                    // Si no está en el mapa, lo agregamos
                    autoresUnicos.put(autor.getNombre(), autor);
                }
            }

            // Si no hay autores
            if (autoresUnicos.isEmpty()) {
                System.out.println("\n" +"No hay autores registrados."+"\n" );
            } else {
                // Imrimimos los autores
                System.out.println("\n" + "Los autores registrados en la base de datos son : ");
                for (Autor autor : autoresUnicos.values()) {
                    System.out.println(autor.toString());
                }
            }
        }

        private void mostrarAutoresVivosPorFecha() {
            //interacion con el usuario
             System.out.println("\n" + "Ingrese el año  para buscar autor(es) vivos ");
            int anio = teclado.nextInt();
            teclado.nextLine();

            // Obtenemos todos los autores con sus libros
            List<Autor> autores = libroService.obtenerAutoresConLibros();

            // Se utiliza Map para agrupar autores por su nombre
            Map<String, Autor> autoresUnicos = new LinkedHashMap<>();

            // Filtramos autores que estaban vivos en el año ingresado
            for (Autor autor : autores) {
                boolean estabaVivo =
                        (autor.getFechaNacimiento() != null && autor.getFechaNacimiento() <= anio) &&
                                (autor.getFechaMuerte() == null || autor.getFechaMuerte() > anio);

                if (estabaVivo) {
                    if (autoresUnicos.containsKey(autor.getNombre())) {
                        // Si el autor ya está en el mapa, agregamos sus libros
                        Autor autorExistente = autoresUnicos.get(autor.getNombre());
                        autorExistente.getLibros().addAll(autor.getLibros());
                    } else {
                        // Si no está en el mapa, lo agregamos
                        autoresUnicos.put(autor.getNombre(), autor);
                    }
                }
            }
            // Si no hay autores vivos en el año indicado
            if (autoresUnicos.isEmpty()) {
                System.out.println("\n" + "No se encontraron autores vivos en el año " +"* "+ anio +" *"+ ".");
            } else {
                // Imprimimos los autores vivos con sus libros
                System.out.println("\n" + " Autores que vivieron en el año " +"* " + anio + " *" + " son : ");
                for (Autor autor : autoresUnicos.values()) {
                    System.out.println(autor.toString());
                }
            }
        }

        private void buscarLibroPorIdioma() {

             var menu = """
              Ingrese el idioma para buscar los libros:
              es - Español
              en - Inglés
              """;
            System.out.println(menu);
            String opcion = teclado.nextLine().toLowerCase().trim();

            // Obtengo la lista de libros registrados
            var librosBuscados = libroService.librosBuscados();

            // Filtro los libros según el idioma ingresado
            List<Libro> librosPorIdioma = librosBuscados.stream()
                    .filter(libro -> libro.getIdioma().equalsIgnoreCase(opcion))
                    .toList();

            // Imprimir el resultado en consola
            if (librosPorIdioma.isEmpty()) {
                System.out.println("\n" + "No se encontraron libros en el idioma ingresado: " + opcion + "\n" );
            } else {
                System.out.println("\n" + "Los libros en el idioma " + "*" + opcion + "*" +" son:" + "\n" );
                librosPorIdioma.forEach(libro -> System.out.println(libro.toString()));
            }
        }
}