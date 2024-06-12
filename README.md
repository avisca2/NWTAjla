
# NWT

NWT is a microservices medical project dedicated for patients and doctors to facilitate the process of treatment

## Features

- Authentication and Authorization
- See the services available in the hospital
- Patient can take an appointment
- Patient can give feedback about a doctor
- Patient can receive a treatment from the doctor
- Other patients can see the reviews given to a specific doctor

## Run Locally

Clone the project

```bash
  git clone https://github.com/avisca2/NWTAjla.git
```

Go to the project directory

```bash
  cd NWTAjla
```
Create PostgresSql Databases : 

- review_db
- tit_db
- security_db
- registration_db

Go to the application.yml in resources folder of each microservice and set your database credentials (username and password).


Go to each microservice and run your app (Review service example)

```bash
  cd review-service
```

```bash
  mvn springboot:run
```

Go to front service

```bash
  cd NWT-front
```

Install dependencies

```bash
  npm install
```

Start the server

```bash
  npm run dev
```


## Running Tests

To run tests of review service, make sure you're in review-service path if you're not run

```bash
  cd review-service
```
And run the following command 

```bash
  mvn test
```

## Tech Stack

**Client:** React, TailwindCSS

**Server:** Springboot, Microservices, Maven, Gradle, Spring Data JPA, Hibernate

**Database:** PostgreSql

**Testing:** JUnit, Mockito

**Security:** Spring Security, JWT

## Feedback

If you have any feedback, please reach out to us at avisca2@etf.unsa.ba
