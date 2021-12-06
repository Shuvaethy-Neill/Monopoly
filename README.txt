# The Monopoly Game
[Version 4.0] 
December 6, 2021


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
	SYSC3110_M4_Documentation.pdf
	SYSC3110_M4_UML.png
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

For Milestone 4, the following changes were made:
- implemented save feature that lets you save your current game state
- implemented load game feature that lets you load the game state you previously saved and begin playiing where you left off
- implemented an english and french version of the board that are read from xml files (a user can enter which version they would like to use when setting up the game)
- "Save" and "Quit" buttons are now displayed under a clickable Menu in the top left corner of the game frame
- "Build Houses/Hotels" button has been added to the list of buttons

-----------------------------------------------------------------
=============
KNOWN ISSUES
=============

All known issues have been resolved.


-----------------------------------------------------------------
===============
ROADMAP AHEAD
===============

Project is completed
		

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
