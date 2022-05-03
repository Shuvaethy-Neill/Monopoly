
# Monopoly!

-----------------------------------------------------------------------------
## Description

We implemented a Java based version of the game Monopoly that follows the MVC design pattern. The game can be booted up through your terminal and will display as a frame. User input during game play will be taken through button interactions (via mouse) and no text inputs will be accepted.

**Objective:** The objective of Monopoly is to become the wealthiest player through buying and renting property. Players roll a pair of dice to move around the board and have the opportunity to purchase any available property they land on. The winner of the game is the last player left who has not reached bankruptcy.

<img width="900" alt="image" src="https://user-images.githubusercontent.com/23216104/166589430-ecfdef4e-48c5-41d2-a800-eceeb7d81366.png">


**For more details on the rules of the game, view our User Manual pdf that can be found within the documentation folder!**

-----------------------------------------------------------------------------
## Setup
1. Download the provided **.jar** file and store it in a directory on your computer. 
2. Open your terminal.
3. Navigate to the directory in which the .jar file is located in (you can use the cd command to do so)</br>
For ex:</br> 
```
cd Downloads
```
4. Run the .jar file 
5. Use the following command: 
```
java -jar Monopoly.jar
```

Setup complete! You should now see a pop-up message prompting you to select either new game or load game.


-----------------------------------------------------------------
## Change Log

For the most recent version of the game, the following changes were made:
- Implemented save feature that lets you save your current game state
- Implemented load game feature that lets you load the game state you previously saved and begin playiing where you left off
- Implemented an english and french version of the board that are read from xml files (a user can enter which version they would like to use when setting up the game)
- "Save" and "Quit" buttons are now displayed under a clickable Menu in the top left corner of the game frame
- "Build Houses/Hotels" button has been added to the list of buttons
- Implemented test methods for go, jail, AI players, and save/load feature
- Ensured that AI players cannot buy houses/hotels
- Implemented the MonopolyAIPlayer class
- Resized the board to work on Mac/Windows without full screening 
- If a player saves the game state, the program will exit

-----------------------------------------------------------------------------
## Authors	
- Shuvaethy Neill<br/>
- Dorothy Tran<br/>
- Harsimran Kanwar<br/>
- Evan Smedley<br/>

-----------------------------------------------------------------
## CREDITS

Thanks to the support of TAs and Instructor during the development of this
application.


-----------------------------------------------------------------
## LICENSE

**Copyright (c) 2021 Evan Smedley, Dorothy Tran, Harsimran Kanwar, & Shuvaethy Neill**

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
