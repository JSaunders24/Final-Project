// Program Header
// Saunders, John
// CISP 401
// Final Project: Backlogger


package com.company;
import models.GameInfo;
import controllers.DataBase;
import views.MenuCmds;


import java.sql.SQLException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws SQLException {

        DataBase db = new DataBase("games.db");
        GameInfo newGame = new GameInfo(0, " ", " ", " ");
        MenuCmds command = new MenuCmds();

        db.createNewDatabase();
        db.addTables();

        command.programIntro();

        System.out.println("Choose from the following menu options below on what you would like to do \n \n");
        Scanner input = new Scanner(System.in);

        String menu;

        // Menu Options

        System.out.println("Use the numbers 1-6 for you menu options. \n");
        System.out.println("""
                1. Add a game to list\s
                2. Remove a game from list\s
                3. Show list\s
                4. Filter games\s
                5. Choose a random game\s
                6. Exit Program\s
                """);


        menu = input.nextLine();

        // Check if menu input is valid

        while (!menu.matches("[1-6]+")){
            System.out.println("incorrect input. Please choose a menu option only using numbers 1-6");
            menu = input.next();

        }

        while (!(menu.matches("[6]"))) {

            if (menu.matches("[1]")) {

                // Add new game info and store into database

                command.addAGame(newGame, db);




            } else if (menu.matches("[2]")) {
                // Allows user to remove game from list
                command.removeAGame(db);



            } else if (menu.matches("[3]")) {
                // Prints list of all games on list
                command.showList(db);



            } else if (menu.matches("[4]")) {
                // Choose to filter list based on genre or length

                command.filterList(db);




            } else {

                // Picks random game from list

                command.pickRandomGame(db);



            }


            System.out.println("""
                    1. Add a game to list\s
                    2. Remove a game from list\s
                    3. Show list\s
                    4. Filter games\s
                    5. Choose a random game\s
                    6. Exit Program\s
                    """);

            menu = input.nextLine();

            while (!menu.matches("[1-6]+")){
                System.out.println("Invalid");
                menu = input.nextLine();
            }
            System.out.println("Valid \n");

//            menu = input.nextLine();

            // Check if menu input is valid
//        while (menu != "1" && menu != "2" && menu != "3" && menu != "4" && menu != "5" && menu != "6") {
            while (!menu.matches("[1-6]+")){
                System.out.println("incorrect input. Please choose a menu option only using numbers 1-6");
                menu = input.next();

            }


//            while (menu != "1" && menu != "2" && menu != "3" && menu != "4" && menu != "5" && menu != "6") {
//                System.out.println("incorrect input. Please choose a menu option only using numbers 1-6");
//                menu = input.nextLine();
//            }

        }

        command.exitProgram();
//        System.out.println("Exiting Program. Goodbye!");

    }


}
