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


       System.out.println("John Saunders Final Project: Backlogger \n");
       System.out.println("In this program you will create a list of video games you wish to play \n");
        System.out.println("After you enter the name, you can select genre, platform and length of the game");
        System.out.println("You can choose to filter or sort your list by various variables like length of game or name \n");

        Scanner input = new Scanner(System.in);
//        scan.nextLine();
        System.out.println("Enter the game's name: ");
        gameName = scan.nextLine();
//        scan.nextLine();
//        gameName[0] = input.next();

//        for (int i = 0; i < gameName.length; i ++)
//        {
//            gameName[i] = scan.nextLine();
//            System.out.println(gameName[i] + " ");
//        }



        System.out.println("Game Name: " + gameName + " \n");

        System.out.println("Enter the game's genre: ");
        genre = input.next();
        System.out.println("Name: " + gameName + " Genre: " + genre + " \n");

        System.out.println("Enter the length of the game (Short, Medium, Long, Very Long) \n");
        gameLength = scan.nextLine();
        System.out.println("Name: " + gameName + " Genre: " + genre + " Length: " + gameLength + " \n");

    }
}
