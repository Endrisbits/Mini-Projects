# Mini-Projects
A collection of simple java projects for experimentation and learning.
These projects were mainly built around a specific task, usually within a day of sprint. Not much effort was put in
refactoring the code and making it easy to expand for later. For the same reasons most of them are console based in 
terms of interaction with the user with no features of a GUI.

## Cinema Room Manager
This is an application that helps with managing a cinema room. The user gives the rows and columns as input. Then a simple loop offers the following functions: 
- Printing the room's layout
- Booking a seat by buying a ticket
- Some statistics regarding booked seats and income.

A room with less than 60 seats total is considered small and each ticket has a price of $10. If more than 60 seats are available, the seats in the front half cost $10, while the seats in the back half cost $8.
The statistics show the following:
- The number of purchased tickets 
- The filled ratio represented as a percentage rounded to 2 decimal places
- Current income
- The total income that shows how much money the owner will get if all the tickets are sold.

## Simple Tic-Tac-Toe
This is a standard version of the TicTacToe game on console. Two players take turns (PlayerX and PlayerO) filling the 3x3 field until one achieves to fill a row, a column or a diagonal with their symbol and wins the game or until the game ends in a draw.

A perfectly played game should always result in a draw. In order to fill a field the Player must give a position coordinate (x,y) where 1<=x,y<=3 on their turn. If the user inputs is malformed, an error message is displayed, asking for a new input. The focus of this project is mostly on correctly implementing the logic of the game and the logical checks. 

## Coffee Machine
This is an application that controls a virtual coffee machine, which can make coffee by mixing different ratios of water, milk and coffee beans depending on the type of coffee. This is achieved by using a finite state machine model for: 
- Awaiting a command,
- Ordering coffee, 
- Refilling the resources of the machine.

The machine has a screen for interacting with the user and the given commands. Depending on the command given and the current state of the machine, a decision is made and the state is updated. Then the loop restarts again with the new updated state. 