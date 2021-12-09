// Program Header
// Saunders, John
// CISP 401
// Final Project: Backlogger


package com.company;

import models.GameInfo;
import controllers.DataBase;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static String[] deleteGame;
    public static String[] deleteGenre;
    public static String[] deleteLength;




    public static void main(String[] args) {

        DataBase db = new DataBase("games.db");
        GameInfo newGame = new GameInfo(0, " ", " ", " ");
        db.createNewDatabase();
        db.addTables();




        Scanner scan = new Scanner(System.in);


        String gameName;
        String genre;
        String gameLength;
        int count = 0;
        String[] nameArray = new String[100];
        String[] genreArray = new String[100];
        String[] lengthArray = new String[100];
        Integer hours;


        System.out.println("John Saunders Final Project: Backlogger \n");
        System.out.println("In this program you will create a list of video games you wish to play \n");
        System.out.println("After you enter the name, you can select genre, platform and length of the game");
        System.out.println("You can choose to filter or sort your list by various variables like length of game or name \n");
        System.out.println("Choose from the following menu options below on what you would like to do \n \n");
        Scanner input = new Scanner(System.in);
//        scan.nextLine();

        int menu = 0;
        int removeGame = 0;


        System.out.println("Use the numbers 1-6 for you menu options. \n");
        System.out.println("1. Add a game to list \n" +
                "2. Remove a game from list \n" +
                "3. Show list \n" +
                "4. Filter games \n" +
                "5. Choose a random game \n" +
                "6. Exit Program \n");

        menu = input.nextInt();

        while (menu != 1 && menu != 2 && menu != 3 && menu != 4 && menu != 5 && menu != 6) {
            System.out.println("incorrect input. Please choose a menu option only using numbers 1-6");
            menu = input.nextInt();
        }

        while (menu != 6) {

            if (menu == 1) {
                System.out.println("Add a game \n");


                System.out.println("Enter the game's name: ");
                gameName = scan.nextLine();
                newGame.setName(gameName);

                nameArray[count] = gameName.toUpperCase();
                System.out.println("Game Name: " + gameName + " \n");

                System.out.println("Enter the game's genre: ");

                genre = scan.nextLine();
                newGame.setGenre(genre.toUpperCase());

                genreArray[count] = genre.toUpperCase(Locale.ROOT);
                System.out.println("Name: " + gameName + " Genre: " + genre + " \n");

                System.out.println("Enter the length of the game (use an integer for estimated number of hours needed for completion) \n");
                hours = Integer.valueOf(input.next());

                while (hours < 0) {
                    System.out.println("Error. Cannot enter a number less than 0. Please choose a number greater than 0");
                    hours = Integer.valueOf(input.next());
                }

                System.out.println("Hours needed: " + hours + " \n");

                if (hours >= 1 && hours <= 10) {

                    lengthArray[count] = "Short";
                    gameLength = lengthArray[count];
                    newGame.setLength(gameLength);

                } else if (hours > 10 && hours <= 20) {

                    lengthArray[count] = "Medium";
                    gameLength = lengthArray[count];
                    newGame.setLength(gameLength);

                } else if (hours > 20 && hours <= 35) {

                    lengthArray[count] = "Long";
                    gameLength = lengthArray[count];
                    newGame.setLength(gameLength);

                } else if (hours > 35) {

                    lengthArray[count] = "Very Long";
                    gameLength = lengthArray[count];
                    newGame.setLength(gameLength);

                }

//                db.addData(newGame.getName(), newGame.getGenre(), newGame.getLength());


                count++;

            } else if (menu == 2) {
                System.out.println("Remove a game from list \n");
                System.out.println("Which game would like to remove? Enter the number corresponding with the game name \n");

                for (int i = 0; i < count; i++) {
                    System.out.println(i + ". " + nameArray[i]);
                    System.out.println(" \n");
                }
                removeGame = input.nextInt();

                while (removeGame < 0 || removeGame > count) {
                    System.out.println("Invalid input. Please choose a number associated with a game on the list \n");
                    removeGame = input.nextInt();
                }


                nameArray = deleteGame(nameArray, removeGame, count);
                genreArray = deleteGenre(genreArray, removeGame, count);
                lengthArray = deleteLength(lengthArray, removeGame, count);
                count--;


            } else if (menu == 3) {
                System.out.println("Show List \n");


                for (int i = 0; i < count; i++) {
                    System.out.println("Name: " + nameArray[i] + " \n" + "Genre: " + genreArray[i] + " \n" + "Length: " + lengthArray[i] + " \n");
                    System.out.println("-------------------- \n");
                }

                ArrayList<GameInfo> showList = db.getData();
                for (GameInfo game : showList){
                    System.out.println(game.toString());
                }

            } else if (menu == 4) {

                int filterChoice = 0;

                System.out.println("Filter games \n");
                System.out.println("What would you like to filter by? genre or length? \n");
                System.out.println("1 for genre. 2 for length \n");

                filterChoice = input.nextInt();

                while (filterChoice != 1 && filterChoice != 2){
                    System.out.println("Invalid choice. Choose 1 or 2 \n");
                    filterChoice = input.nextInt();
                }




            } else if (menu == 5) {

                System.out.println("Choose a random game \n");
                int min = 0;
                int max = count;
                Random rand = new Random();
                int randomGame = rand.nextInt(max - min) + min;

                System.out.println("You're random game is: " + nameArray[randomGame] + " ! \n");

            } else if (menu == 6) {

                System.out.println("Exit Program. Goodbye! \n");
                System.exit(0);
            }

            db.addData(newGame.getName(), newGame.getGenre(), newGame.getLength());

            System.out.println("1. Add a game to list \n" +
                    "2. Remove a game from list \n" +
                    "3. Show list \n" +
                    "4. Filter games \n" +
                    "5. Choose a random game \n" +
                    "6. Exit Program \n");

            menu = input.nextInt();

            while (menu != 1 && menu != 2 && menu != 3 && menu != 4 && menu != 5 && menu != 6) {
                System.out.println("incorrect input. Please choose a menu option only using numbers 1-6");
                menu = input.nextInt();
            }

        }

    }
    public static String[] deleteGame(String[] nameArray, Integer removeGame, Integer count){

//        String[] temp = new String[count];
//
//        for (int i = 0; i < count; i++){
//            if (i != removeGame){
//                temp[i] = nameArray[i];
//            }
//        }
//        temp[count - 1] = null;

        for (int i =0; i < count; i++){
            if (i == removeGame){
                for (int s = i; s < count -1; s++){
                    nameArray[s] = nameArray[s+1];
                }
                break;
            }
        }

        return nameArray;

        }

    public static String[] deleteGenre(String[] genreArray, Integer removeGame, Integer count){

//        String[] temp = new String[count];
//
//        for (int i = 0; i < count; i++){
//            if (i != removeGame){
//                temp[i] = genreArray[i];
//            }
//        }
//        temp[count - 1] = null;

        for (int i =0; i < count; i++){
            if (i == removeGame){
                for (int s = i; s < count -1; s++){
                    genreArray[s] = genreArray[s+1];
                }
                break;
            }
        }

        return genreArray;

    }

    public static String[] deleteLength(String[] lengthArray, Integer removeGame, Integer count){

//        String[] temp = new String[count];
//
//        for (int i = 0; i < count; i++){
//            if (i != removeGame){
//                temp[i] = lengthArray[i];
//            }
//        }
//        temp[count - 1] = null;

        for (int i =0; i < count; i++){
            if (i == removeGame){
                for (int s = i; s < count -1; s++){
                    lengthArray[s] = lengthArray[s+1];
                }
                break;
            }
        }

        return lengthArray;

    }

}
