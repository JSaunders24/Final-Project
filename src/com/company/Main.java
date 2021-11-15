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
//     int count = 0;
     String[] nameArray = new String[100];
     String[] genreArray = new String[100];
     String[] lengthArray = new String[100];
     Integer hours;


     System.out.println("John Saunders Final Project: Backlogger \n");
     System.out.println("In this program you will create a list of video games you wish to play \n");
     System.out.println("After you enter the name, you can select genre, platform and length of the game");
     System.out.println("You can choose to filter or sort your list by various variables like length of game or name \n");

     Scanner input = new Scanner(System.in);
//        scan.nextLine();



     System.out.println("Enter a game name, genre, and time length. When you are done, press 1 to add another item to the list or 2 to exit \n");
     int goChoice = 0;


     while (goChoice != 2) {

      System.out.println("Press 1 to continue or 2 to Exit \n");
      goChoice = input.nextInt();

      // This loop will check if the user's input is valid. Only 1 and 2 are valid inputs.
      while (goChoice != 1 && goChoice != 2) {
       System.out.println("Sorry, that input is not accepted. Please enter 1 to continue or 2 to exit \n");
       goChoice = input.nextInt();
      }

      if (goChoice == 1) {
       int count = 0;
       System.out.println("Enter the game's name: ");
       gameName = scan.nextLine();

       nameArray[count] = gameName;
       System.out.println("Game Name: " + gameName + " \n");

       System.out.println("Enter the game's genre: ");
       genre = scan.nextLine();
       genreArray[count] = genre;
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
       } else if (hours > 10 && hours <= 20) {
        lengthArray[count] = "Medium";
       } else if (hours > 20 && hours <= 35) {
        lengthArray[count] = "Long";
       } else if (hours > 35) {
        lengthArray[count] = "Very Long";
       }

//        gameLength = scan.nextLine();
//        lengthArray[0] = gameLength;
//        System.out.println("Name: " + gameName + " Genre: " + genre + " Length: " + gameLength + " \n");

//       if (count == 0){
//        System.out.println("Name: " + nameArray[0]  +" \n" + "Genre: " + genreArray[0] + " \n" + "Length: " + lengthArray[0] + " \n");
//       }

       System.out.println("Array Test \n");
       for (int i = 0; i < count; i ++) {
        System.out.println("Name: " + nameArray[i]  +" \n" + "Genre: " + genreArray[i] + " \n" + "Length: " + lengthArray[i] + " \n");
        System.out.println(" \n");
       }
       count++;
      }
     }
    }
}
