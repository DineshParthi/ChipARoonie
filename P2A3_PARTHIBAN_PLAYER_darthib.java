//Name: Dinesh Parthiban
//Original Created Date: 11th July 2017
//Modified Date: 13th July 2017
//Description: This class describes a player for the ChipARoonie game

public class P2A3_PARTHIBAN_PLAYER_darthib{

  private int tick; //stores the incorrect ticks
  private String color[]= {"yet to be triggered","red","orange","yellow","green","blue","purple"}; //stores the color of the bomb
  private String name; //stores the name of the Player

    //no arg constructor
  public P2A3_PARTHIBAN_PLAYER_darthib(){
    this(" ");
  }

  //default constructor that sets the player name
  public P2A3_PARTHIBAN_PLAYER_darthib(String nm){
    name=nm;
    tick=0;
  }

  //getter method for tick field
  public int getTick(){
      return tick;
  }

  //increment method for tick field
  public void incTick(){
      tick++;
  }

  //getter method for color field
  public String getColor(){
      return color[tick];
  }

  //getter method for name field
  public String getName(){
      return name;
  }

  //setter method for name field
  public void setName(String nm){
      name=nm;
  }

}
