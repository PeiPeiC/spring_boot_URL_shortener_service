# Spring Boot URL Shortener Service

This project is a simple URL shortener service built with Spring Boot. Below are the steps to set up and test the service on your local environment.

## Setup

### 1. Clone the Repository

First, clone the repository to your local machine using the following command:

```bash
git clone <repository-url>
```

Replace `<repository-url>` with the URL of the repository.

### 2. Set Up the Database

Before running the service, you need to set up a database. This project uses a simple H2 database. However, you can configure it to use any other relational database like MySQL or PostgreSQL.

- Create a new database and name it `url_shortener`.
- Update the `application.properties` file with your database credentials and URL.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

### 3. Build and Run the Service

Navigate to the project directory and build the project using Maven:

```bash
cd springbooturlshortenerservice
mvn clean install
```

Now, run the service:

```bash
mvn spring-boot:run
```

The service will start and listen on port 8080 (or the port you specified in `application.properties`).

## Testing the APIs

You can test the APIs using `curl` commands or Postman.

### Using curl

1. **Shorten URL:**

```bash
curl -X POST -H "Content-Type: application/json" -d '{"longURL": "https://example.com"}' http://localhost:8080/shorten
```

2. **Redirect to Long URL:**

```bash
curl http://localhost:8080/<shortURL>
```

Replace `<shortURL>` with the shortened URL path you received from the first API.

3. **Delete URL by Short URL:**

```bash
curl -X DELETE http://localhost:8080/<shortURL>
```

4. **Delete URL by ID:**

```bash
curl -X DELETE http://localhost:8080/id/<id>
```

Replace `<id>` with the ID of the URL you want to delete.

### Using Postman

1. **Shorten URL:**
    - Method: POST
    - URL: `http://localhost:8080/shorten`
    - Body: `{"longURL": "https://example.com"}`

2. **Redirect to Long URL:**
    - Method: GET
    - URL: `http://localhost:8080/<shortURL>`

3. **Delete URL by Short URL:**
    - Method: DELETE
    - URL: `http://localhost:8080/<shortURL>`

4. **Delete URL by ID:**
    - Method: DELETE
    - URL: `http://localhost:8080/id/<id>`

Replace `<shortURL>` and `<id>` with the respective values from your database.

Now you should be able to use and test the URL shortener service on your local environment.
