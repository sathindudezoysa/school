// DatabaseHelper.java
package com.example.abs_version_2;


import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:alarms.db";
    private static DatabaseHelper instance;

    private DatabaseHelper() {
        createTable();
    }

    public static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS alarms (\n"
                + " id TEXT PRIMARY KEY,\n"
                + " name TEXT NOT NULL,\n"
                + " time TEXT NOT NULL,\n"
                + " sound TEXT NOT NULL,\n"
                + " isActive INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Alarms table created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAlarm(Alarm alarm) {
        String sql = "INSERT INTO alarms(id, name, time, sound, isActive) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alarm.getId());
            pstmt.setString(2, alarm.getName());
            pstmt.setString(3, alarm.getTime().toString()); // Store as string "HH:mm:ss"
            pstmt.setString(4, alarm.getSound());
            pstmt.setInt(5, alarm.isActive() ? 1 : 0);
            pstmt.executeUpdate();
            System.out.println("Alarm added to database: " + alarm.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Alarm> getAllAlarms() {
        String sql = "SELECT * FROM alarms";
        List<Alarm> alarms = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alarm alarm = new Alarm(
                        rs.getString("id"),
                        rs.getString("name"),
                        LocalTime.parse(rs.getString("time")),
                        rs.getString("sound"),
                        rs.getInt("isActive") == 1
                );
                alarms.add(alarm);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return alarms;
    }

    public void deleteAlarm(String id) {
        String sql = "DELETE FROM alarms WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            System.out.println("Alarm deleted: " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAlarmStatus(String id, boolean isActive) {
        String sql = "UPDATE alarms SET isActive = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isActive ? 1 : 0);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}