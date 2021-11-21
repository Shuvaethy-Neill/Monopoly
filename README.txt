# The Monopoly Game
[Version 3.0] 
November 22, 2021


-----------------------------------------------------------------
=========
AUTHORS
=========

Contact Name:		

		Shuvaethy Neill, 101143478
		Dorothy Tran, 101141902
		Harsimran Kanwar, 101143556
		Evan Smedley, 100148695

Affiliation: 		
		Carleton University - Systems and Computer Engineering
		SYSC3110 Software Development Project

-----------------------------------------------------------------
============
DESCRIPTION
============

- This is our implementation of Monopoly that can be played using a user interface which follows the MVC model.

- The milestone is composed of the following files:

	Monopoly.jar
	SYSC3110_M3_Documentation.pdf
	SYSC3110_M3_UML.png
	SequenceDiagram_PlayMethod.png

- The jar file has '.java' and '.class' files for the following classes
	BoardPanel
	BoardSpace
	BoardSquares
	ColouredProperty
	Dice
	DiceDisplay
	FreeParking
	Go
	Jail
	MonopolyAIPlayer
	MonopolyClient
	MonopolyController
	MonopolyEvent
	MonopolyFrame
	MonopolyModel
	MonopolyModelTest
	MonopolyView
	Player
	PlayerPanel
	Property
	Railroad
	Utility
	Tax
-----------------------------------------------------------------
===========
CHANGELOG
===========

For Milestone 3, the following changes were made:
- Added the ability to play with AI players. Players have the choice when the game starts with a JOptionPane asking if the user would like to play with a specified number of AI players (up to 8 players in total including human players and AI players)
- Added board pieces to represent players on the Monopoly board
- Added features when a player purchases a house/hotel when they own three coloured properties
- Added additional board squares: Go to Jail, Jail/Just Visiting, Railroads, Utilities, Community Chest & Chance
- Implemented a Jail Class that handles players when they are sent to Jail which is handled in the MonopolyModel class 
- Implemented a Utility class to handle situations when a player lands on one of the two utility board squares (collect rent from the user) which is handled in the MonopolyModel class 
- Implemented a Railroad class to handle situations when a player lands on one of the four railroad board squares (collect rent from the user)
- Implemented a MonopolyAIPlayer class that extends the Player class to represent AI players in the game
- Implemented a Go class that allows a user to collect money when they fully rotate through the Monopoly board and collect $200 (if they are not sent to Jail)

-----------------------------------------------------------------
=============
KNOWN ISSUES
=============

All known issues have been resolved.


-----------------------------------------------------------------
===============
ROADMAP AHEAD
===============

Milestone 4:
		Add save/load feature
		Add international game versions with custom street names
		

-----------------------------------------------------------------
=======
CREDITS
=======

Thanks to the support of TAs and Instructor during the development of this
application.


-----------------------------------------------------------------
=======
LICENSE
=======

[MIT](https://choosealicense.com/licenses/mit/)

Copyright (c) 2021 Evan Smedley, Dorothy Tran, Harsimran Kanwar, & Shuvaethy Neill

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
