# PDF Generation Service

This project is a Spring Boot application that provides an API for generating PDF files from HTML content.

## Technologies Used

- Java 17
- Spring Boot 3.4.3
- Gradle
- Lombok
- Flying Saucer PDF
- Thymeleaf

## Getting Started

### Prerequisites

- Java 17
- Gradle

### Building the Application

To build the application and create an executable JAR file, run the following command in the project directory:

```sh
./gradlew build
```

### Running the Application

To run the application, use the following command:

```sh
java -jar build/libs/your-application-0.0.1-SNAPSHOT.jar
```

The application will start on port 9327 by default. You can change the port by modifying the `server.port` property in the `application.properties` file.

### API Endpoints

#### Generate PDF

- **URL**: `/api/v1/pdf/generate`
- **Method**: `POST`
- **Consumes**: `multipart/form-data`
- **Produces**: `application/pdf`

**Request**:

- `RequestDTO` object containing the HTML content and the desired file name.

**Response**:

- A PDF file generated from the provided HTML content.

### Example Request

```sh
curl -X POST http://localhost:9327/api/v1/pdf/generate \
  -F "html=<html><body><h1>Hello, World!</h1></body></html>" \
  -F "name=example.pdf" \
  -o example.pdf
```

## Project Structure

- `src/main/java/com/pdf/controller/PdfController.java`: REST controller for handling PDF generation requests.
- `src/main/java/com/pdf/service/impl/PdfGeneratorServiceImpl.java`: Service implementation for generating PDF files.
- `src/main/resources/application.properties`: Configuration file for the application.
- `build.gradle`: Build configuration file.

## Dependencies

- `org.springframework.boot:spring-boot-starter-web`: Spring Boot starter for building web applications.
- `org.projectlombok:lombok`: Library for reducing boilerplate code.
- `org.xhtmlrenderer:flying-saucer-pdf`: Library for generating PDF files from XHTML.
- `org.springframework.boot:spring-boot-starter-thymeleaf`: Spring Boot starter for Thymeleaf template engine.

## License

This project is licensed under the MIT License.