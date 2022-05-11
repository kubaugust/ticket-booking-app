# ticket-booking-app
This is a REST API application for a seat reservation system for a multiplex.

### My setup
I used JDK 18 to create the project, but at least JDK 11 should be compatible and able to build and run the application.
I tested the scripts using macOS, so they should also be fine when running on linux, but probably not on a Windows-based machine.
I used H2 in-memory database in the project.

### My additional assumptions and notes
1. I prepared basic endpoints with GET, POST, PUT and DELETE methods for some of the endpoints, but then I realised that maybe it could be better to focus on the use case scenario, as business requirements may be different from my assumptions. However, I decided to leave them as they were for testing purposes.
2. Screening Rooms should be automatically filled with Seats. Then, Screening Seats should be generated to make the reservation process easier.
3. There are 15 minutes given for each reservation to expire.
4. I wasn't sure what to do with a given name and surname to make a reservation, so I just simply compare the personal data with the data stored in the database.
5. Although it wasn't specified, I created a simple validator for overlapping screenings as I thought it should be a basic ability for a multiplex system.
6. I set a large 'endTime' parameter in the script to make sure it works at least for a couple of weeks, because database initialisation is based on the current time.
7. I didn't format json responses when using curl, because a solution to do so would require an additional software.

### Building and running the application
1. Open your CLI (e.g. Terminal) and change directory to the destination, where you want to download the project.
2. Type "git clone https://github.com/kubaugust/ticket-booking-app" into your CLI and downloading should start.
3. To build and run the app type "cd ticket-booking-app" and then "sh run-the-app.sh" into your CLI or simply run "./mvnw spring-boot:run".
4. Open another window of your CLI and again perform steps to go inside "*ticket-booking-app*" directory or change directory to the place where you put "*curl-script.sh*" file.
5. Run "sh curl-script.sh" and you should see some requests and responses provided for the given use case scenario.