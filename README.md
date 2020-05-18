# survey-api project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `survey-api-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/survey-api-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/survey-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Appendix 1 Running native application with h2 database

The native application cn be executed with default database driver - H2. It is in-memory database which
can be used for testing purposes. When the application has been restarted the data 
will be lost. 

#### H2 database provider startup command
```
./survey-api-1.0.0-SNAPSHOT-runner \
 -Dquarkus.datasource.db-kind=h2 \
 -Dquarkus.hibernate-orm.database.generation=update
 ```
#### Postgres database provider startup command
```
./survey-api-1.0.0-SNAPSHOT-runner \
 -Dquarkus.datasource.db-kind=postgresql \
 -Dquarkus.datasource.username=surveyapi \
 -Dquarkus.datasource.password=P@ssp0rt \
 -Dquarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/surveyapi \
 -Dquarkus.hibernate-orm.database.generation=update
```

#### Microsoft SQL Server database provider startup command
```
./survey-api-1.0.0-SNAPSHOT-runner \
 -Dquarkus.datasource.db-kind=mssql \
 -Dquarkus.datasource.username=surveyapi \
 -Dquarkus.datasource.password=P@ssp0rt \
 -Dquarkus.datasource.jdbc.url=jdbc:sqlserver://localhost:1433;databaseName=SurveyApi \
 -Dquarkus.hibernate-orm.database.generation=update
```

## Container image build
In order to build a container image please follow this guide: https://quarkus.io/guides/container-image
 
