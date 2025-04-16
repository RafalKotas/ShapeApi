# ShapeAPI

## About

Simple project related to adding shapes to the database and retrieving them – all based on a given type of shape.

At the moment, the project uses the built-in H2 database for storing shape entities.

The shapes available are:
- Circles
- Rectangles
- Squares

## Design

The project is based on the **Strategy pattern**, which selects the appropriate implementation depending on the shape type provided. This makes it easy to extend with new shape types in the future.

The API provides the following endpoints:
- `POST /api/vX/shapes` – to create a shape (requires type and parameters) 
- `GET /api/vX/shapes?type=...` – to retrieve all shapes of a given type
X = {v1, v2}

The project also includes:
- **OpenAPI documentation** (Swagger UI)
- **SonarQube analysis** with test coverage reporting

## SonarQube Setup

To run SonarQube analysis locally or in CI, make sure you have a **SonarQube token** created in your account.

You can then run the analysis with:

```bash
./mvnw sonar:sonar \
  -Dsonar.projectKey=your_project_key \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=your_generated_token
