package controllers;


import models.GameInfo;

import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataBase {


//DBConnect.forName("org.sqlite.JDBC");



        private String dbName;
        private String url;

//    Class.forName("org.sqlite.JDBC");

        public DataBase(String dbName) {
            this.dbName = dbName;
            this.url = "jdbc:sqlite:C:/sqlite/db/" + dbName;
        }

        public void createNewDatabase() {

            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void addTables(){

            String sql = "CREATE TABLE IF NOT EXISTS games (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	genre text NOT NULL,\n"
                    + " length text NOT NULL\n"
                    + ");";

            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Tables added");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void addData(String gameName, String genre, String length){

            String sql = "insert into Game Info(gameName, genre, length) VALUES ('" + gameName + "', '" + genre + "', '" + length + "');";
            System.out.println(sql);
            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Data added");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        public ArrayList<GameInfo> getData(){
            String sql = "SELECT id, name, genre, length FROM GameInfo";
            ArrayList<GameInfo> gameList = new ArrayList<>();

            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                ResultSet games = statement.executeQuery(sql);
                while(games.next())
                {
                    int id = games.getInt("id");
                    String gameName = games.getString("Name");
                    String genre = games.getString("Genre");
                    String length = games.getString("Length");
                    GameInfo game = new GameInfo(id, gameName, genre, length);

                    gameList.add(game);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return gameList;
        }

}
