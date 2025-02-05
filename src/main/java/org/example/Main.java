package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    //user neon: valim34536@rykone.com
    //password neon: valim34536@rykone.comQ
//postgresql://neondb_owner:npg_fkWVUtH6e4mq@ep-summer-smoke-a28kfphw-pooler.eu-central-1.aws.neon.tech/neondb?sslmode=require

    private static final String URL = "jdbc:postgresql://ep-summer-smoke-a28kfphw-pooler.eu-central-1.aws.neon.tech:5432/neondb";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_fkWVUtH6e4mq";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        var con = connect();
        String sql = """
                CREATE TABLE IF NOT EXISTS games (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    developer VARCHAR(100) NOT NULL,
                    publisher VARCHAR(100) NOT NULL,
                    release_date DATE NOT NULL,
                    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0)
                );
                """;
        try(var command = con.createStatement()) {
            int rows = command.executeUpdate(sql);
            System.out.println("Таблицю створено. Оновлено записів: " + rows);
        } catch(SQLException ex) {
            System.out.println("Щось пішло не так "+ ex.getMessage());
        }
    }
}