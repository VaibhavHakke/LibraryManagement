# Library Management

A Java 17 console application for maintaining an in-memory book catalog. It supports adding books, listing and searching titles, checking books out, and returning them. Book IDs must be unique.

## Requirements

- JDK 17 or newer
- Apache Maven 3.8 or newer

## Build and run

From this directory:

```powershell
mvn clean package
java -jar target\library-management-1.0.0.jar
```

The catalog is held in memory and starts empty each time the application runs.

## Demonstrate the Maven build lifecycle

Run individual phases to see Maven execute each phase and all earlier phases in its lifecycle:

```powershell
mvn validate
mvn compile
mvn test
mvn package
mvn verify
mvn install
```

`package` creates the executable JAR under `target`. `install` also places the built artifact in the local Maven repository. `deploy` publishes an artifact to a configured remote Maven repository; it is intentionally not run without repository credentials and configuration.

## Project versions on GitHub

The Maven project version is set in `pom.xml`. For each release, update that version (for example, `1.1.0`), commit the change, then tag and push the release:

```powershell
git add pom.xml
git commit -m "Release library-management 1.1.0"
git tag -a v1.1.0 -m "Library Management 1.1.0"
git push origin HEAD
git push origin v1.1.0
```

Use semantic versioning: increment the major number for incompatible changes, minor for backward-compatible features, and patch for backward-compatible fixes. Git tags preserve each released project version; create a GitHub Release from each pushed tag.
