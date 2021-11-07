// Program Header
// Saunders, John
// CISP 401
// Final Project: Backlogger


package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scan = new Scanner(System.in);

//         String[] gameName = new String[Integer.parseInt(scan.next())];
         String gameName;
         String genre;
         String gameLength;
         String[] nameArray = new String[1];
         String[] genreArray = new String[1];
         String[] lengthArray = new String[1];
         Integer hours;



       System.out.println("John Saunders Final Project: Backlogger \n");
       System.out.println("In this program you will create a list of video games you wish to play \n");
        System.out.println("After you enter the name, you can select genre, platform and length of the game");
        System.out.println("You can choose to filter or sort your list by various variables like length of game or name \n");

        Scanner input = new Scanner(System.in);
//        scan.nextLine();
        System.out.println("Enter the game's name: ");
        gameName = scan.nextLine();
        nameArray[0] = gameName;


        System.out.println("Game Name: " + gameName + " \n");

        System.out.println("Enter the game's genre: ");
        genre = scan.nextLine();
        genreArray[0] = genre;
        System.out.println("Name: " + gameName + " Genre: " + genre + " \n");

        System.out.println("Enter the length of the game (use an integer for estimated number of hours needed for completion) \n");
        hours = Integer.valueOf(input.next());

        while (hours < 0){
         System.out.println("Error. Cannot enter a number less than 0. Please choose a number greater than 0");
         hours = Integer.valueOf(input.next());
        }

        System.out.println("Hours needed: " + hours + " \n");

        if (hours >= 1 && hours <= 10){
         lengthArray[0] = "Short";
        }
        else if (hours > 10 && hours <= 20){
         lengthArray[0] = "Medium";
        }
        else if (hours > 20 && hours <= 35){
         lengthArray[0] = "Long";
        }
        else if (hours > 35){
         lengthArray[0] = "Very Long";
        }

//        gameLength = scan.nextLine();
//        lengthArray[0] = gameLength;
//        System.out.println("Name: " + gameName + " Genre: " + genre + " Length: " + gameLength + " \n");

        System.out.println("Array Test \n");
        System.out.println("Name: " + nameArray[0] + " \n" + "Genre: " + genreArray[0] + " \n" + "Length: " + lengthArray[0] + " \n");

    }
}
