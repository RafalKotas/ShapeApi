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
- **SonarQube analysis** with test coverage reporting (run the analysis by executing the script `run-sonar-analysis.ps1`)

## SonarQube Setup

To run SonarQube analysis locally or in CI, make sure you have a **SonarQube token** created in your account.

You can then run the analysis with:

```bash
./mvnw sonar:sonar \
  -Dsonar.projectKey=your_project_key \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=your_generated_token
```

### How to Generate a SonarQube Token

1. **Run SonarQube in Docker**:  
   If SonarQube is not running on your machine, you can easily start it with Docker. Run the following command to start SonarQube:

   ```bash
   docker run -d -p 9000:9000 --name sonarqube sonarqube
   ```

   This will make SonarQube accessible at `http://localhost:9000` in your browser.

2. **Access the SonarQube Dashboard**:
   Open your browser and go to `http://localhost:9000`. This will bring you to the SonarQube dashboard.

3. **Log in to SonarQube**:
   Use the default credentials to log in:
   - **Username**: `admin`
   - **Password**: `admin`

4. **Generate a New Token**:
   - After logging in, click on your profile icon in the top-right corner and select **My Account**.
   - In the **Security** tab, you will see a section called **Tokens**.
   - Click on the **Generate Tokens** button.
   - Enter a name for your token (e.g., `SonarQubeAnalysisToken`), and choose **Token** as the type.
   - Click **Generate** to create the token.

5. **Save the Token**:
   - Once the token is generated, **copy it immediately**. This is the only time the token will be visible. **Make sure to save it somewhere secure** because it will be hidden once you navigate away from the page.

---

### Example Docker Command to Run SonarQube:

`docker run -d -p 9000:9000 --name sonarqube sonarqube`

After running the command, you can access the SonarQube interface at `http://localhost:9000`.

---

### Important Notes:

- **Token Security**: The generated token should be treated as sensitive information. Keep it secure and do not expose it in your code or logs.
- **SonarQube Credentials**: The default credentials are `admin/admin`, but you should change them for production use.

