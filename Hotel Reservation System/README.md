# Hotel Reservation System

## Project Overview
The Hotel Reservation System is a Java-based console application that allows users to manage hotel room bookings. The system connects to a MySQL database to store and retrieve room and reservation information. It provides functionalities like booking a room, checking room availability, and canceling reservations.

## Features
- **Room Booking**: Book a room for a guest based on room type and availability.
- **Check Availability**: Check available rooms based on the room type.
- **Cancel Reservation**: Cancel a room reservation and update room availability.

## Project Structure
- **DBConnection.java**: Handles the MySQL database connection using JDBC.
- **Room.java**: Represents a hotel room, including attributes like room number, type, and availability.
- **Reservation.java**: Represents a reservation, associating a guest with a specific room.
- **HotelReservationSystem.java**: The main driver class that integrates all the modules and contains the methods for booking rooms, checking availability, and canceling reservations.

## Technologies Used
- **Java**: Core language for developing the application.
- **JDBC (Java Database Connectivity)**: For connecting to and interacting with a MySQL database.
- **MySQL**: Database used to store room and reservation data.

## Database Setup
1. **Create the Database and Tables**: Use the `hotelDB.sql` file to create the necessary database schema and insert sample data.
2. **Database Structure**:
   - **Database Name**: `hotelDB`
   - **Tables**:
     - **`Room`**: Stores room information (`room_number`, `room_type`, `is_available`).
     - **`Reservation`**: Stores reservation details (`reservation_id`, `guest_name`, `room_number`).

3. **Sample Schema and Data**:
   The `hotelDB.sql` file includes the schema and some sample data to get you started. To apply this schema, run:
   ```Command Prompt if you're using windows
   mysql -u subramanyamavanapu -p < hotelDB.sql

4. **To Compile and Run the application**:
   Open Command Prompt:
   Compilation: javac *.java
   Run: java -cp ".;C:\Users\murth\Downloads\mysql-connector-j-8.0.31 (1)\mysql-connector-j-8.0.31\mysql-connector-j-8.0.31.jar" HotelReservationSystem

