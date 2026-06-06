# VendorContacts

Spring Boot 3 (Java 17) microservice that exposes a simple REST API to manage Local Vendor details. Uses Maven, JPA, and an in-memory H2 database.

## Prerequisites
- Java 17 installed and on PATH (`java -version` → 17)
- Maven 3.8+ (`mvn -v`)

## Build
```
mvn clean package
```
This produces `target/vendor-contacts-0.0.1-SNAPSHOT.jar`.

## Run
```
mvn spring-boot:run
```
Or run the packaged JAR:
```
java -jar target/vendor-contacts-0.0.1-SNAPSHOT.jar
```

Service will start at `http://localhost:8080`.

## H2 Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:vendordb`
- User: `sa`
- Password: (empty)

## API
Base path: `/api/vendors`

- List/search (paged):
  - `GET /api/vendors?name=city&city=Bengaluru&active=true&page=0&size=10&sort=id,asc`
- Get by id:
  - `GET /api/vendors/{id}`
- Create:
  - `POST /api/vendors`
  - Body:
    ```json
    {
      "name": "Fresh Farm Supplies",
      "contactName": "Anita Rao",
      "phone": "9876543210",
      "email": "anita@freshfarm.example",
      "address": "12 Market Road",
      "city": "Bengaluru",
      "state": "Karnataka",
      "postalCode": "560001",
      "country": "India",
      "active": true
    }
    ```
- Update (replace all fields):
  - `PUT /api/vendors/{id}`
  - Body: same as create
- Change active status only:
  - `PATCH /api/vendors/{id}/status?active=false`
- Delete:
  - `DELETE /api/vendors/{id}`

## Sample Data
On startup, a few vendors are inserted (see `src/main/resources/data.sql`).

## Notes
- This is a simple single service (microservice-friendly). For containerization, create a Dockerfile and build an image; Maven/Boot is already configured.
- To change the port, set `server.port` in `src/main/resources/application.properties` or via `--server.port=9090` on the command line.
