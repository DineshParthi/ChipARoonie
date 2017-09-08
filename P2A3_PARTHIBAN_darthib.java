//Name: Dinesh Parthiban
//Original Created Date: 13th July 2017
//Modified Date: 14th July 2017
//Description: This program depicts a game which allows user to guess words.
//It has simlation and autoplay mode.

import java.io.*;
import java.util.Scanner;

public class P2A3_PARTHIBAN_darthib{
  public static void main(String args[]) throws FileNotFoundException{
    int gameContinue=1; //stores the value to continue
    Scanner input=new Scanner (System.in);
    while(gameContinue==1){
      System.out.println("Enter 1 to Manual mode and 2 for Autoplay:");
      int gameType=input.nextInt();

      if(gameType==1){ //manual mode
        System.out.println("Enter number of players:");
        int num=input.nextInt(); //get number of player from the user
        if(num>0){
          P2A3_PARTHIBAN_GAME_dparthib game=new P2A3_PARTHIBAN_GAME_dparthib(num);
          game.welcomeMsg();
          game.runGame();
          System.out.println("*********************************************************************************************************************************************************************************");
        }
        else
          System.out.println("Invalid number of players");
      }
      else if(gameType==2){// autoplay mode
          System.out.println("Enter number of games to simulate:");
          int numGame=input.nextInt(); //get number of games from the user
          System.out.println("Enter number of players:");
          int num=input.nextInt(); //get number of player from the user
          System.out.println("Enter question file for the game");
          input.nextLine(); //get blank line
          String fileName=input.nextLine(); //get the file name from user
          String []results; //to store game results
          boolean fileCheck=false; //store file check results
          if((num>0)&&(numGame>0)){ //validate number of games and players
            results= new String[numGame];
            for(int i=0;i<numGame;i++){
              P2A3_PARTHIBAN_AUTOPLAY_dparthib auto=new P2A3_PARTHIBAN_AUTOPLAY_dparthib(num,i+1);
              fileCheck=auto.checkFileInput(fileName);
              if(!fileCheck){ //validate file name
                System.out.println("Invalid fileName");
                break;
              }
              auto.welcomeMsg();
              results[i]=auto.runAutoPlayGame();
            }
            if(fileCheck)
              display(results,numGame);
            System.out.println("*********************************************************************************************************************************************************************************");
          }
          else
            System.out.println("Invalid number of players or Game");
        }
        else
          System.out.println("Invalid mode");
      System.out.println("Press 1 to start a new game and any other number to stop");
      gameContinue=input.nextInt();
    }
  }

//display the overall results of autoplay mode in a table format
  public static void display(String []results, int numGame){
    String id="Game Number",status="Status",nm="Won by",word="Secret Word";
    System.out.println("----------------------------------------------------------------");
    System.out.format("%15s|%15s|%15s|%15s|\n", id,status,nm,word);
    System.out.println("----------------------------------------------------------------");
    for(int i=0;i<numGame;i++){
      String[] temp=results[i].split(",");
      System.out.format("%15s|%15s|%15s|%15s|\n", (i+1),temp[0],temp[1],temp[2]);
      System.out.println("----------------------------------------------------------------");
    }
  }
}
