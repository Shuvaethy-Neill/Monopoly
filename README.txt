# The Monopoly Game
[Version 2.0] 
November 8, 2021


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

- This is our implementation of Monopoly that can be played with a user interface following the MVC model.

- The milestone is composed of the following files:

	Monopoly.jar
	SYSC3110_M2_Documentation.pdf
	SYSC3110_M2_UML.png
	SequenceDiagram_PlayMethod.png

- The jar file has '.java' and '.class' files for the following classes
	BoardPanel
	BoardSpace
	BoardSquares
	Dice
	DiceDisplay
	FreeParking
	MonopolyClient
	MonopolyController
	MonopolyEvent
	MonopolyFrame
	MonopolyModel
	MonopolyView
	Player
	PlayerPanel
	Property
	Tax
-----------------------------------------------------------------
===========
CHANGELOG
===========

For Milestone 2, the following changes were made:
- Board.java was renamed to MonopolyModel.java as the MVC model
- Several methods in MonopolyMethod.java were refactored to accommodate MVC.
- Created a MonopolyController.java class as the MVC controller
- Created an Enumeration class BoardSquares.java that contains the names and properties of Monopoly Board pieces
- Created a MonopolyModel test class to test methods in the MonopolyModel.java Class using JUnit4
- Created MonopolyFrame.java for the MVC view of the Monopoly GUI
- DiceDisplay.java, BoardPanel.java & PlayerPanel.java are the helper classes for the MVC view (MonopolyFrame.java)
- Declared all possible commands as constants
- Created a MonopolyView interface that updates the MonopolyEvent.
  Interface was used so that multiple classes are able to update the event their own way.
- Created MonopolyEvent.java that extends EventObject to notify other classes that an action has occurred on the GUI.
  Events are created though user actions such as clicking a button or reaching bankruptcy.

-----------------------------------------------------------------
=============
KNOWN ISSUES
=============

All known issues have been resolved.


-----------------------------------------------------------------
===============
ROADMAP AHEAD
===============

Milestone 3:
		Add houses, hotels and special properties/squares
		Add AI players

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

Copyright (c) 2021 Evan Smedley, Dorothy Tran, Harsimran Kanwar, & Shuvaethy Niell

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
