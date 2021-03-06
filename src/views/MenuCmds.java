package views;

import models.GameInfo;
import controllers.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuCmds {

    Scanner scan = new Scanner(System.in);
    Scanner input = new Scanner(System.in);

public void programIntro(){
    System.out.println("John Saunders Final Project: Backlogger \n");
    System.out.println("In this program you will create a list of video games you wish to play \n");
    System.out.println("After you enter the name, you can select genre, and length of the game");
    System.out.println("You can choose to filter your list by variables like length of game or genre \n");
    System.out.println("NOTE 1: This program uses a SQLite database and will require the user to have a SQLite JDBC driver installed on their device. \n");
    System.out.println("""
            NOTE 2: It is recommended to add at least 1 game to your list before using other menu options\s
            The program will still run with an empty list, however the program will not run certain methods unless there is at least one game on the list\s
            """);


}

public void addAGame(GameInfo newGame, DataBase db) throws SQLException {
    // Add new game info and store into database

    System.out.println("Add a game \n");
    int hours;
    String name;
    String genre;
    String gameLength;
    boolean nameCheck;

    System.out.println("Enter the game's name: ");
    name = scan.nextLine();

    nameCheck = db.nameCheck(name);

    // Checks if game name already exists

    while (nameCheck){
        System.out.println("Enter a valid game that is not already on the list \n");
        name = scan.nextLine();
        nameCheck = db.nameCheck(name);
    }


    System.out.println("This game is new and can be added to the list \n");
    newGame.setName(name.toLowerCase());



    System.out.println("Game Name: " + name + " \n");

    System.out.println("Enter the game's genre (RPG, racing, shooter, sports, strategy, etc.) ");

    genre = scan.nextLine();
    newGame.setGenre(genre.toUpperCase());


    System.out.println("Name: " + name + " Genre: " + genre + " \n");

    System.out.println("Enter the length of the game (use an integer for estimated number of hours needed for completion) \n");
    hours = Integer.parseInt(input.next());

    while (hours < 0) {
        System.out.println("Error. Cannot enter a number less than 0. Please choose a number greater than 0");
        hours = Integer.parseInt(input.next());
    }

    System.out.println("Hours needed: " + hours + " \n");

    if (hours >= 1 && hours <= 10) {


        gameLength = "Short";
        newGame.setLength(gameLength);

    } else if (hours > 10 && hours <= 20) {


        gameLength = "Medium";
        newGame.setLength(gameLength);

    } else if (hours > 20 && hours <= 35) {


        gameLength = "Long";
        newGame.setLength(gameLength);

    } else if (hours > 35) {


        gameLength = "Very Long";
        newGame.setLength(gameLength);

    }

    db.addData(newGame.getName(), newGame.getGenre(), newGame.getLength());


}

public void removeAGame(DataBase db) throws SQLException {
    // Allows user to remove game from list

    System.out.println("Remove a game from list \n");
    int removeGame;
    ArrayList<GameInfo> showList = db.getData();

    for (GameInfo game : showList){
        System.out.println(game.toString());
    }

    Boolean empty = db.emptyCheck();

    if (!empty){
        System.out.println("Whoops! Your list is empty! Come back when you have at least one game on your list \n");

    }
    else {


        System.out.println("Choose a game ID number to delete from data base \n");
        removeGame = input.nextInt();
        boolean idCheck = db.idCheck(removeGame);

        // Check if id is valid

        while (!idCheck){
            System.out.println("Enter a valid ID that is associated with the game you wish to remove \n");
            removeGame = input.nextInt();
            idCheck = db.idCheck(removeGame);
        }


        System.out.println("That ID is valid. Removing game \n");

        db.deleteGame(removeGame);
    }



}

public void showList(DataBase db) throws SQLException {
    // Prints list of all games on list

    Boolean empty = db.emptyCheck();

    if (!empty){
        System.out.println("Whoops! Your list is empty! Come back when you have at least one game on your list \n");

    }
    else {

        System.out.println("Show List \n");

        ArrayList<GameInfo> showList = db.getData();
        System.out.println("-------------------------------------------------- ");
        for (GameInfo game : showList) {
            System.out.println(game.toString());
        }

        System.out.println("-------------------------------------------------- \n \n");
    }
}

public void filterList(DataBase db) throws SQLException {
                String filterChoice;

                System.out.println("Filter games \n");


    Boolean empty = db.emptyCheck();

    if (!empty){
        System.out.println("Whoops! Your list is empty! Come back when you have at least one game on your list \n");

    }
    else {

        System.out.println("What would you like to filter by? genre or length? \n");
        System.out.println("1 for Genre. 2 for Length \n");

        filterChoice = input.nextLine();

        while (!(filterChoice.matches("[1-2]+"))){
            System.out.println("Invalid choice. Choose 1 or 2 \n");
            filterChoice = input.nextLine();
        }
        String genreChoice;
        String lengthChoice;
        String lengthFilter;



        ArrayList<GameInfo> showList = db.getData();
        for (GameInfo game : showList){
            System.out.println(game.toString());
        }

        if (filterChoice.matches("[1]")){
            // Filter list based on genre

            boolean genreCheck;
            System.out.println("Choose a genre to filter by. These are the available genres to filter by. \n");


            genreChoice = scan.nextLine().toUpperCase();

            genreCheck = db.genreCheck(genreChoice);

            while (!genreCheck){
                System.out.println("Enter a valid genre that is already on the list \n");
                genreChoice = scan.nextLine().toUpperCase();
                genreCheck = db.genreCheck(genreChoice);
            }


            System.out.println("Filtered List \n");


            ArrayList<GameInfo> filterList = db.filterGenre(genreChoice);
            for (GameInfo game : filterList){
                System.out.println(game.toString());
            }



        }
        else {

            // Filter list based on length

            System.out.println("Choose a length to filter by. 1 for Short, 2 for Medium, 3 for Long, and 4 for or Very Long.");
            lengthChoice = input.nextLine();

            while (!(lengthChoice.matches("[1-4]+"))){
                System.out.println("Invalid choice. Choose 1, 2, 3, or 4 \n");
                lengthChoice = input.nextLine();
            }

            if (lengthChoice.matches("[1]")){
                lengthFilter = "Short";
            }
            else if (lengthChoice.matches("[2]")){
                lengthFilter = "Medium";
            }
            else if (lengthChoice.matches("[3]")){
                lengthFilter = "Long";
            }
            else {
                lengthFilter = "Very Long";
            }

            ArrayList<GameInfo> filterList = db.filterLength(lengthFilter);
            for (GameInfo game : filterList){
                System.out.println(game.toString());
            }



        }


    }

}

public void pickRandomGame(DataBase db) throws SQLException {


    // Picks random game from list

    Boolean empty = db.emptyCheck();

    if (!empty){
        System.out.println("Whoops! Your list is empty! Come back when you have at least one game on your list \n");

    }
    else {


        System.out.println("Choose a random game \n");

        int randomID;
        String randomName;

        randomID = db.randID();
        System.out.println("Random ID: " + randomID + " \n");
        randomName = db.randName(randomID);

        System.out.println("You're random game is: " + randomName + " ! \n");
    }
}

public void exitProgram(){
    System.out.println("Exiting Program. Goodbye!");

}


}
