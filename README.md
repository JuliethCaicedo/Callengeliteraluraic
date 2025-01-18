 <h1> 💪 LITERALURAIC  </h1>

**LITERALURAIC** es un desafío desarrollado con **Spring Boot** y **Java**, diseñado para interactuar con una API de libros, almacenar la información en una base de datos **PostgreSQL** y permitir la interacción con los usuarios a través de un menú simple y funcional.

## Funcionalidades

El sistema permite realizar las siguientes acciones mediante un menú:

### Menú Principal
```
Elija la Opción a través de su número:
1. Buscar Libro por Título
2. Lista de Libros Registrados
3. Lista de Autores Registrados
4. Lista de Autores Vivos en un Determinado Año
5. Lista de Libros por Idioma

0. Salir
```

### 📙 1. Buscar Libro por Título
- **Descripción**: Busca un libro en la API por su título. Si el libro se encuentra, se guarda en la base de datos.
- **Posibles Respuestas**:
  - Si se encuentra el libro:
    ```
    ------Libro--------
    título= Don Quijote
    autores= Cervantes Saavedra, Miguel de
    idioma= es
    descargas= 16201
    __________________
    ```
  - Si el libro ya existe en la base de datos:
    ```
    El libro ya existe en la base de datos.
    ```
  - Si no se encuentra el libro:
    ```
    No se encontró un libro con ese título.
    ```

### 📚 2. Lista de Libros Registrados
- **Descripción**: Muestra todos los libros guardados en la base de datos.
- **Respuesta Ejemplo**:
  ```
  Los libros registrados en la base de datos son:
  ------Libro--------
  título= La Odisea
  autores= Homer
  idioma= es
  descargas= 4156
  __________________
  ```

### 👩🏻👨🏻 3. Lista de Autores Registrados
- **Descripción**: Lista todos los autores registrados en la base de datos junto con sus libros.
- **Respuesta Ejemplo**:
  ```
  Los autores registrados en la base de datos son:
  -------- Autor --------
  Nombre: Austen, Jane
  Fecha de Nacimiento: 1775
  Fecha de Muerte: 1817
  Libros:
    * Pride and Prejudice
    * Sense and Sensibility
  _______________________
  ```

### ✨ 4. Lista de Autores Vivos en un Determinado Año
- **Descripción**: Busca autores vivos en un año ingresado por el usuario y muestra sus datos.
- **Respuesta Ejemplo**:
  ```
  Ingrese el año para buscar autor(es) vivos: 1600
  Autores que vivieron en el año 1600 son:
  -------- Autor --------
  Nombre: Shakespeare, William
  Fecha de Nacimiento: 1564
  Fecha de Muerte: 1616
  Libros:
    * Romeo and Juliet
  _______________________
  ```

### ✈️ 5. Lista de Libros por Idioma
- **Descripción**: Muestra los libros registrados que corresponden al idioma ingresado por el usuario.
- **Respuesta Ejemplo**:
  ```
  Ingrese el idioma para buscar los libros:
  es - Español
  en - Inglés

  ------Libro--------
  título= Romancero gitano
  autores= García Lorca, Federico
  idioma= es
  descargas= 251
  __________________
  ```
  - Si no hay libros en el idioma ingresado:
    ```
    No se encontraron libros en el idioma ingresado: xx
    ```

### 🤗 0. Salir
- **Descripción**: Finaliza el sistema.
- **Respuesta**:
  ```
  Vuelve Pronto... Cerrando
  ```

### 🫡 Manejo de Opciones Inválidas
- Si el usuario ingresa una opción no válida:
  ```
  Opción inválida, intenta de nuevo
  ```

---

## 👩🏻‍💻 Tecnologías Utilizadas

- **Backend**: Spring Boot (Java)
- **Base de Datos**: PostgreSQL
- **API de Libros**: Integración con una API externa para obtener información de libros.

## 💯Instalación y Ejecución

1. Clona este repositorio:
   ```
   git clone https://github.com/JuliethCaicedo/Callengeliteraluraic.git
   ```
2. Configura la base de datos PostgreSQL en el archivo `application.properties`.
3. Interactúa con el sistema a través del menú principal.

## 💕Agradecimientos

Este trabajo se realiza con fines de estudio, con ayuda de tegnologias IA.
<h1>Ingrid Caicedo.</h1>
