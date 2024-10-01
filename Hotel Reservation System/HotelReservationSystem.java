import java.sql.*;
import java.util.*;

public class HotelReservationSystem {
    private Connection conn;

    public HotelReservationSystem() {
        conn = DBConnection.connect(); // Establish the database connection
    }

    public void bookRoom(String guestName, String roomType) {
        try {
            String query = "SELECT * FROM Room WHERE room_type = ? AND is_available = TRUE LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int roomNumber = rs.getInt("room_number");

                // Insert reservation into database
                String insertRes = "INSERT INTO Reservation (guest_name, room_number) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertRes);
                insertStmt.setString(1, guestName);
                insertStmt.setInt(2, roomNumber);
                insertStmt.executeUpdate();

                // Update room availability
                String updateRoom = "UPDATE Room SET is_available = FALSE WHERE room_number = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateRoom);
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();

                System.out.println("Room " + roomNumber + " booked successfully for " + guestName);
            } else {
                System.out.println("No available rooms of type " + roomType);
            }
        } catch (SQLException e) {
            System.err.println("Error booking room: " + e.getMessage());
        }
    }

    public void cancelReservation(int reservationId) {
        try {
            String query = "SELECT room_number FROM Reservation WHERE reservation_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int roomNumber = rs.getInt("room_number");

                // Delete reservation from database
                String deleteRes = "DELETE FROM Reservation WHERE reservation_id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteRes);
                deleteStmt.setInt(1, reservationId);
                deleteStmt.executeUpdate();

                // Update room availability
                String updateRoom = "UPDATE Room SET is_available = TRUE WHERE room_number = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateRoom);
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();

                System.out.println("Reservation " + reservationId + " cancelled successfully.");
            } else {
                System.out.println("Reservation ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error canceling reservation: " + e.getMessage());
        }
    }

    public void checkAvailability(String roomType) {
        try {
            String query = "SELECT * FROM Room WHERE room_type = ? AND is_available = TRUE";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Available rooms of type " + roomType + ":");
            while (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                System.out.println("Room Number: " + roomNumber);
            }
        } catch (SQLException e) {
            System.err.println("Error checking availability: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem hotelSystem = new HotelReservationSystem();

        // Test booking a room
        hotelSystem.bookRoom("Alice", "Single");

        // Test checking availability
        hotelSystem.checkAvailability("Double");

        // Test canceling a reservation
        hotelSystem.cancelReservation(1);
    }
}
