# Github Repository Details Service

The GitHub Repository Details Service is a RESTful service that provides comprehensive information about a given GitHub repository. It includes details such as the full name, description, Git clone URL, number of stars, and creation date.
## Getting Started

These instructions will guide you through the process of setting up the project on your local machine for development and testing purposes.

### Prerequisites

-   Java 17 or higher
-   Gradle
-   Docker (optional)

### Building the project

Build the project using Gradle:

`./gradlew build`

### Running the application

Run the application using the Gradle `bootRun` task:


`./gradlew bootRun`

The service will be available at [http://localhost:8080](http://localhost:8080/).

### Using Docker (optional)

To build and run the application using Docker, follow these steps:

1.  Build the Docker image:


`docker build -t github-repo-details-service .`

2.  Run the Docker container:


`docker run -p 8080:8080 github-repo-details-service`

The service will be available at [http://localhost:8080](http://localhost:8080/).

## API Usage

The service exposes a single endpoint to fetch Github repository details:


`GET /repositories/{owner}/{repository-name}`

**Example:**

Request:


`GET /repositories/octocat/hello-world`

Response:

`{
"fullName": "octocat/Hello-World",
"description": "This is your first repository",
"cloneUrl": "https://github.com/octocat/Hello-World.git",
"stars": 10,
"createdAt": "2011-01-26T19:01:12Z"
}`

## Running tests

To run tests, execute the following command:


`./gradlew test`