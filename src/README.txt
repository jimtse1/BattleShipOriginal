=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. You may copy and paste from your proposal
  document if you did not change the features you are implementing.

  1. 2-D Array. A 2-D Array was necessary to track the coordinates of the ships and the 
  coordinates of where the user both places the ships and attacks specific coordinates.
  In general, BattleShip is founded on boards and thus a 2-D Array

  2. J-Unit Testing. I implemented a testing of the general state of the model. This included
  seeing whether someone has won or not, whether a ship has sunk or not, whether a ship is
  placed correctly or not, etc. This is similar to the testing done in the ChatServer Homework.

  3. Complex Search. I constantly utilized a complex search in my game to best search 
  throughout the board. For example, I used a complex search of the board to make a random
  board and ensure boats placed are placed appropriately. Additionally, I used a complex search
  to make my AI program, which plays like a regular human being (if it sees a point being sunk, it
  plays around that point).

  4. Recursion. I used recursion to implement my AI program. It was necessary to use recursion 
  because I used a Random Generator to generate a random direction. If the direction 
  does not work, then it must call on the function again until it is able to determine
  a direction randomly. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The Ship interface, and the classes that implement this interface, are the ship objects.
  The main purpose of ship objects are to indicate whether a ship is sunk or not. They are
  placed in the board of the game.
  
  The Board class, and the Random Board class that extends it, is essentially the class behind
  most of the game logic. The Board class implements functions such as a random AI program,
  placing ships on a board, targeting locations, etc. The Random Board class is a Board 
  that creates a board with pieces randomly placed.
  
  The game class handles the Swing portion of the game. The GUI is handled mainly in this class.
  
  The BattleShipGameTest class holds all my JUnit testing.


- Revisit your proposal document. What components of your plan did you end up
  keeping? What did you have to change? Why?
  
  A lot of my proposal document had to be changed. I realized my interface/subtyping may or may
  not be considered a core concept. I also did not at the time know that the best way to 
  implement my function would be to use recursion.  
  
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Swing and making my AI were the most stumbling blocks of the game. I extremely underestimated
  the difficulty of making this game.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  If I were given the chance to refactor, I would probably change some of the methods or
  instance fields of my ship class, points class, and even Board class. Some of these
  methods/instance fields may or may not have been important.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  None.

