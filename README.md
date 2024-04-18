# Project Overview
This Spring Boot application is designed for consuming Kafka messages related to emails. It includes functionalities to process emails based on their domains and provides testing mechanisms to ensure reliability and correctness.
## Setup
To run this application, you need
- Java 22
- Apache Maven
- Docker
  
Ensure that Kafka is running, i have included docker compose file which you may run by running `docker compose up` command.

## To run the application:
1. Clone the repository: ` git clone https://github.com/amagine/assignment.git`
2. Navigate to the project directory: `assignment`
3. Build the project: `mvn clean install`
4. Run the application using IDE

## Test the application:
1. Open a new terminal and run this command `curl -X POST http://localhost:8080/email/generate`, This will return a random email address and puclish an event to kafka broker and the consumer will consume the event based on the **domain**. You may verify this by checking logs where you can see partition. for now, application uses partition **0** for **gmail**, **1** for **outlook** and **2** for **hotmail**.
2. To get the all messages saved in H2 database, run `curl -X GET http://localhost:8080/email/statistic`

Feel free to include comments or provide feedback if needed.
Happy Coding :-)

Thanks for your attention. See you soon!
