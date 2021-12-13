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

            String sql = """
                    CREATE TABLE IF NOT EXISTS games (
                    	id integer PRIMARY KEY,
                    	name text NOT NULL,
                    	genre text NOT NULL,
                     length text NOT NULL
                    );""";

            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Tables added");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void addData(String name, String genre, String length){

            String sql = "insert into games (name, genre, length) VALUES ('" + name + "', '" + genre + "', '" + length + "');";
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
            String sql = "SELECT id, name, genre, length FROM games";
            ArrayList<GameInfo> gameList = new ArrayList<>();

            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                ResultSet games = statement.executeQuery(sql);
                while(games.next())
                {
                    int id = games.getInt("id");
                    String name = games.getString("Name");
                    String genre = games.getString("Genre");
                    String length = games.getString("Length");
                    GameInfo game = new GameInfo(id, name, genre, length);

                    gameList.add(game);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return gameList;
        }


        private Connection connectDB(String dbName){
            this.dbName = dbName;
            this.url = "jdbc:sqlite:C:/sqlite/db/" + dbName;
            Connection conn = null;
            try{
                conn = DriverManager.getConnection((url));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return conn;
        }


        public void deleteGame(int id) throws SQLException {
            String sql = "DELETE FROM games WHERE id = ?";

            PreparedStatement prepState = connectDB(dbName).prepareStatement(sql);{

            prepState.setInt(1, id);
            prepState.executeUpdate();

            }

        }


        public Boolean nameCheck(String name) throws SQLException {
            String sql = "SELECT EXISTS (SELECT 1 FROM games WHERE name = ?)";
            boolean exists = false;


            try (PreparedStatement prepState = connectDB(dbName).prepareStatement(sql)) {
                {
                    prepState.setString(1, name);
                    ResultSet result = prepState.executeQuery();

                    if (result.next()) {
                        boolean check = result.getBoolean(1);
                        String checkStatement;


                        if (check){
                            checkStatement = "Name: " + name + " already exists. ";

                            System.out.println(checkStatement);
                            exists = true;
                        }
                        else {
                            checkStatement = "Name: " + name + " is new. ";
                            System.out.println(checkStatement);
//
                        }


                    }

                    return exists;
                }
            }
        }

    public Boolean genreCheck(String genre) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM games WHERE genre = ?)";
        boolean exists = false;

        try (PreparedStatement prepState = connectDB(dbName).prepareStatement(sql)) {
            {
                prepState.setString(1, genre);
                ResultSet result = prepState.executeQuery();

                if (result.next()) {
                    boolean check = result.getBoolean(1);
                    String checkStatement;


                    if (check){
                        checkStatement = "Genre: " + genre + " is valid. ";

                        System.out.println(checkStatement);
                        exists = true;
                    }
                    else {
                        checkStatement = "Genre: " + genre + " doesn't exist. ";
                        System.out.println(checkStatement);
//                        exists = false;
                    }


                }

                return exists;
            }
        }
    }


        public Integer randID() {
            String sql = "SELECT id FROM games ORDER BY RANDOM() LIMIT 1";
            int randomID = 0;
            try(Connection conn = DriverManager.getConnection(url)){
                Statement statement = conn.createStatement();
                ResultSet randID = statement.executeQuery(sql);
                while(randID.next())
                {
                    randomID = randID.getInt("id");

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return randomID;
        }

    public String randName(int id) {
        String sql = "SELECT name FROM games WHERE id = " + id;
        String rName = " ";
        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            ResultSet randName = statement.executeQuery(sql);
            while(randName.next())
            {
                rName = randName.getString("name");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rName;
    }

    public Boolean idCheck(int id) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM games WHERE id = ?)";
        boolean exists = false;


        try (PreparedStatement prepState = connectDB(dbName).prepareStatement(sql)) {
            {
                prepState.setInt(1, id);
                ResultSet result = prepState.executeQuery();

                if (result.next()) {
                    boolean check = result.getBoolean(1);
                    String checkStatement;


                    if (check){
                        checkStatement = "ID " + id + " is Valid. ";

                        System.out.println(checkStatement);
                        exists = true;
                    }
                    else {
                        checkStatement = "ID " + id + " is Invalid. ";
                        System.out.println(checkStatement);

                    }


                }

                return exists;
            }
        }
    }

    public ArrayList<GameInfo> filterGenre(String choice) {
        ArrayList<GameInfo> filterList = new ArrayList<>();
        String sql = "SELECT id, name, genre, length FROM games WHERE genre = ?";
        try(PreparedStatement prepState = connectDB(dbName).prepareStatement(sql)){


            prepState.setString(1, choice);
            ResultSet filtGenre = prepState.executeQuery();

            while (filtGenre.next())
            {

                int id = filtGenre.getInt("id");
                String name = filtGenre.getString("Name");
               String genre = filtGenre.getString("Genre");
               String length = filtGenre.getString("Length");
               GameInfo game = new GameInfo(id, name, genre, length);

               filterList.add(game);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return filterList;

    }

    public ArrayList<GameInfo> filterLength(String choice) {
        ArrayList<GameInfo> filterList = new ArrayList<>();
        String sql = "SELECT id, name, genre, length FROM games WHERE length = ?";
        try(PreparedStatement prepState = connectDB(dbName).prepareStatement(sql)){


            prepState.setString(1, choice);
            ResultSet filtGenre = prepState.executeQuery();

            while (filtGenre.next())
            {

                int id = filtGenre.getInt("id");
                String name = filtGenre.getString("Name");
                String genre = filtGenre.getString("Genre");
                String length = filtGenre.getString("Length");
                GameInfo game = new GameInfo(id, name, genre, length);

                filterList.add(game);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return filterList;

    }


}
