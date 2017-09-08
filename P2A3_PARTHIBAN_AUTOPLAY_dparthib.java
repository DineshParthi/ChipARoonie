//Name: Dinesh Parthiban
//Original Created Date: 12th July 2017
//Modified Date: 14th July 2017
//Description: This class depicts a autoplay mode of ChipARoonie game which allows user to guess words.

import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class P2A3_PARTHIBAN_AUTOPLAY_dparthib extends P2A3_PARTHIBAN_GAME_dparthib{
  private int gameNum;
  //default constructor that sets players and games
  public P2A3_PARTHIBAN_AUTOPLAY_dparthib(int numPlayers,int numGame){
    super(numPlayers);
    gameNum=numGame;
  }

  //print a customized welcome message to the players
  @Override
    public void welcomeMsg(){
      //set player names
      for(int i=0;i<players.length;i++){
        players[i].setName("CPU"+(i+1));
      }

      //game rules
      System.out.println("Number of players:"+players.length);
      System.out.print("Welcome to the ChipARoonie Challenge :");
      for(int i=0;i<players.length;i++)
        System.out.print("\t"+players[i].getName());
      System.out.println("\n*********************************************************************************************************************************************************************************");
      System.out.println("\t\t\t\t\tRULES OF THE GAME");
      System.out.println("\nFor each round of the game, the player is prompted to input a guessed letter to see if that letter is in the secret word:\n"+

      "\t1.If the guessed letter is contained in the secret word, the player has won that round, and the guessed word thus far is printed (consisting of blank underscores and corrrectly guessed letters).\n"+
      "\t2.If the player's guessed letter is not in the secret word, the guessed word thus far is printed (consisting of blank underscores and any correctly guessed letters), and the player earns a tick.\n"+
      "\t\ta)The ticks add up. A player can only accumulate 6 incorrect ticks or he loses the game and the bomb goes off.\n"+
      "\t\tb)For each round that the player guesses a letter incorrectly, the color of the bomb is also displayed based on how many ticks the player has:\n"+
      "\t\tc)Each tick will correspond to the bomb exploding sooner, for each incorrectly guessed letter:\n"+
      "\t\t\t\t*1 tick = red\n"+
      "\t\t\t\t*2 ticks = orange\n"+
      "\t\t\t\t*3 ticks = yellow\n"+
      "\t\t\t\t*4 ticks = green\n"+
      "\t\t\t\t*5 ticks = blue\n"+
      "\t\t\t\t*6 ticks = purple  BOOM!!!\n");

      System.out.println("*********************************************************************************************************************************************************************************");

    }

    //validate whether user has entered a valid file name
    public boolean checkFileInput(String fileName){
      //handle incorrect file name
      boolean isFileThere=question.setFileName(fileName);
      return isFileThere;
    }

    //this method runs the game
    public String runAutoPlayGame(){
      String result="";
      question.generateQues();
      setGuessedWord();
      while(status.equals("Progress")){
        for(int i=0;i<players.length;i++){
          if(!isLost(players[i])){
            System.out.println("***********************************************SIMULATED GAME"+gameNum+"*******************************************************************************************************************");
            display(players[i]);
            if(!makeGuess())
              players[i].incTick();
          }
          if(checkWin()){
            System.out.println(players[i].getName()+" has won the game");
            System.out.println("The secret word was "+ question.getQuestion());
            result=status+","+players[i].getName()+","+question.getQuestion();
            break;
          }
          else if(isLost(players[i])){
            System.out.println("Bomb turned "+players[i].getColor()+" BOOM ");
            System.out.println(players[i].getName()+" has lost the game");
          }
          if(checkLoss()){
            System.out.println("Game Ends");
            System.out.println("The secret word was "+ question.getQuestion());
            result=status+","+"None"+","+question.getQuestion();
            break;
          }
        }
      }
      System.out.println("***********************************************END GAME"+gameNum+"*******************************************************************************************************************");
      return result;
    }

    //this method simulates the part where the user is allowed to input a word/letter
    @Override
    public boolean makeGuess(){
      boolean guessStatus=false;
      Random r = new Random();
      char letter = (char)(r.nextInt(26) + 'a');
      guessStatus=checkLetterGuess(letter);
      return guessStatus;
    }
}
