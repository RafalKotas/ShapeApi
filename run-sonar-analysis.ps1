# run-sonar-analysis.ps1

$mvnCommand = @(
    "clean",
    "verify",
    "sonar:sonar",
    "sonar:sonar",
    "-Dsonar.projectKey=Shape-API",
    "-Dsonar.projectName=Shape-API",
    "-Dsonar.host.url=http://localhost:9000",
    "-Dsonar.token=$env:SONAR_TOKEN",
    "-Dsonar.java.binaries=target/classes"
)

Write-Host "Executing SonarQube analysis..." -ForegroundColor Cyan
mvn $mvnCommand
