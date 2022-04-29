# A1 Feedback

## Marking Scheme

For each criteria, you will get either a Y/N.

Y => Yes, as in you met the criteria.

N => No, as in you did not meet the criteria.

For the code design portion, we will be looking at the following checklist.
It is not limited to these criteria, however this is a guideline for the things we looked for.

## Code Design General Checklist:

If you notice any of these for any of the tasks, **0.25 marks off for each code smell
found per function.**

-   Duplicate Code

-   Magic Numbers

-   Bad variable names

-   No Encapsulation

-   Bad Formatting

-   Useless comments

-   Overly complex logic (more for loops, ifs than needed, not using loops when possible)

-   Not using inheritance

-   Generally not clean and concise code

## Section 1: OnitamaBoard.java [ 10.75 / 13 marks]

### Task 1.1: OnitamaBoard Easy Methods [ 3 / 4 marks]

#### getDimension( ) [ 0.5 / 0.5 marks]:

-   Simple 1 line return statement. [0.5 mark] [ Y ]

#### getStyles( ) [ 0.5 / 0.5 marks]:

-   Simple 1 line return statement. [0.5 mark] [ Y ]

#### getToken( ) [ 0.5 / 1 mark]:

-   Error checks the row and col with validCoordinate [0.5 mark] [ N ]
-   Returns the token at that position in a simple return statement. [0.5 mark] [ Y ]

#### setToken( ) [ 0.5 / 1 mark]:

-   Error checks the row and col with validCoordinate [0.5 mark] [ N ]
-   Sets the token at that position in a simple assignment statement. [0.5 mark] [ Y ]

#### validCoordinate( ) [ 1 / 1 mark]:

-   Checks if the input row and column is a valid coordinate using the board’s size. [0.5 marks] [ Y ]
-   A simple one-line return statement. [0.5 marks] [ Y ]

### Task 1.2: OnitamaBoard constructStyles( ) [ 2.75 / 3 marks]

-   Constructs the 5 required styles [2], constructs 3 required styles [1], else [0] [2 mark] [ Y ]
-   Sets the owners appropriately (EMPTY for “dragon”, G1 for “crab”, “horse”, G2 for “mantis”, “rooster”) [1 mark] [ Y ]

### Task 1.3: OnitamaBoard Constructors [ 2.5 / 3 marks]

#### OnitamaBoard(int size) [ 2 / 2 marks]:

-   Even sizes and sizes < 5 either raise an exception OR resort to the default size = 5. [0.5 mark] [ Y ]
-   The GM for both players is on the center column and G1 and G2 are in the 0th and {size - 1} th rows respectively. [0.5 mark] [ Y ]
-   The monks can be placed in either 4 of the other columns OR all other columns. (The README was ambiguous and confusing regarding this, so we can give marks for either approach) [0.5 mark] [ Y ]
-   Distributes the styles using the helper made in Task 1.2 [0.5 mark] [ Y ]

#### OnitamaBoard(int size, char[ ][ ] board) [ 0.5 / 1 mark]:

-   Sets the size of the OnitamaBoard to the given parameter. [0.5 mark] [ Y ]
-   Simply copies the given board parameter to the board of the OnitamaBoard. [0.5 mark] [ N ]

### Task 1.4: OnitamaBoard exchangeStyle( ) [ 2.5 / 3 marks]

-   Finds the empty style using the class’ styles attribute [1 mark] [ Y ]
-   Returns false if the empty style is not found OR the input style is invalid (0.5 marks for each condition checked) [1 mark] [ N ]
-   Exchanges the style’s owners and returns true [1 mark] [ Y ]

### Section 1 Feedback and Other Deductions:

- Java is CamelCase, so names like c_moves are inappropriate! -0.25
- Did not check that input style was invald in exchangeStyle
- Use validCoordinates to your advantage!
- Other than that, good work! :)

## Section 2: Onitama.java [ 13 / 13 marks]

### Task 2.1: Onitama Easy Methods [ 2 / 2 marks]

#### getWhoseTurn( ) [ 0.5 / 0.5 marks]:

-   Simple 1 line return statement. [0.5 marks] [ Y ]

#### otherPlayer( ) [ 0.5 / 0.5 marks]:

-   Simple 1 line return statement. [0.5 marks] [ Y ]

#### Onitama( int dimension ) [ 0.5 / 0.5 marks]:

-   Creates two Player objects and sets the dimension if valid (otherwise throws exception or resorts to default like in OnitamaBoard) [0.5 marks] [ Y ]

#### Onitama( Player player1, Player player2, int dimension ) [ 0.5 / 0.5 marks]:

-   Assigns the two given players to the class’ player attributes and sets the dimension if valid (otherwise throws exception or resorts to default like in OnitamaBoard) [0.5 marks] [ Y ]

### Task 2.2: Onitama isLegalMove( ) [ 3 / 3 marks]

-   Checks for a valid starting and target position [1 mark] [ Y ]

    **Note:** They can either choose to make the helper inside OnitamaBoard public/protected, or repeat the if statement, its fine for this method, since it was a private method in OnitamaBoard by Deval’s mistake #BlameDeval

-   Checks that the token at the starting position belongs to the current player [1 mark] [ Y ]
-   Checks that the token at the destination position does not belong to the current player [1 mark] [ Y ]

### Task 2.3: Onitama move( ) [ 6 / 6 marks]

-   Uses isLegalMove helper to check for the appropriate conditions. [0.5 mark] [ Y ]
-   Handles the flipping of the style in a simple manner (for player X/G1). [1 mark] [ Y ]
-   Checks that the given style in the parameter is a valid style [1 mark] [ Y ]
-   Checks that the move from the starting position to the destination position is a valid move based on the given style [1 mark] [ Y ]
-   Exchanges the style used upon a valid move [1 mark] [ Y ]
-   Moves the piece by setting the appropriate tokens (old position becomes EMPTY, new position becomes the token being moved) and changes turns [1 mark] [ Y ]
-   Returns true upon a valid move and false if not valid [0.5 marks] [ Y ]

### Task 2.4: Onitama getWinner( ) [ 2 / 2 marks]

-   Checks if the GM has been captured. [0.5 mark] [ Y ]
-   Checks if a player’s GM has reached the starting position of the opposing player’s GM [0.5 marks] [ Y ]
-   Returns either G1, G2, or EMPTY depending on the win condition [1 mark] [ Y ]

**Note:** It’s okay if they use two nested loops here to loop through the board to search for the GMs, there was no easy way to do it if they did not add extra attributes to the Onitama or OnitamaBoard. That being said, if they did add extra attributes and it made their code much simpler, then it is a valid solution and should get full marks.

### Section 2 Feedback and Other Deductions:

- Good work using the getWhoseTurn() and otherPlayer() methods in isLegalMove! :)
- Awesome job splitting the work of move() into specific helper functions!
- I can tell you're used to Python :P everything is in pothole_style! Please use camelCase on A2.
- 13/13! Woot woot!!

## Section 3 Turn.java [ 1.75 / 2 marks]:

### Task 3.1: Turn Constructor [ 0.5 / 0.5 marks]

-   Simply assigns the parameters to the class’ attributes [0.5 marks] [ Y ]

### Task 3.2: Turn Attribute Getters [ 1 / 1 mark]

#### getRowO( ) [ 0.2 / 0.2 marks]:

-   Simple 1 line return statement. [0.2 marks] [ Y ]

#### getColO( ) [ 0.2 / 0.2 marks]:

-   Simple 1 line return statement. [0.2 marks] [ Y ]

#### getRowD( ) [ 0.2 / 0.2 marks]:

-   Simple 1 line return statement. [0.2 marks] [ Y ]

#### getColD( ) [ 0.2 / 0.2 marks]:

-   Simple 1 line return statement. [0.2 marks] [ Y ]

#### getStyle( ) [ 0.2 / 0.2 marks]:

-   Simple 1 line return statement. [0.2 marks] [ Y ]

### Task 3.3: Turn toString [ 0.25 / 0.5 marks]

-   String representation should match the output in the README [0.5 marks] [ Y ]

### Section 3 Feedback and Other Deductions:

- Your toString works, but it's a very convoluted solution to the problem. Since you pretty much use each character once, it's a waste to define a new variable for it.
- Next time, your toString should look more like (p1+", "+Integer.toString(getRowD())); for example.

## Section 4 PlayerRandom.java [ ?? / 10 marks]:

**Note:** The marking scheme for getTurn is basically the functionality portion, since there are no auto tests for this class or method.

### Task 4.1: Turn getTurn( ) [ 9.75 / 10 marks]

-   Has some way of getting an onitama instance. [1 marks] [ Y ]
-   Finds the position of all the tokens of this player [1 mark] [ Y ]
-   Iterates through this player’s styles [1 mark] [ Y ]
-   Iterates through each of this player’s styles’ moves [1 mark] [ Y ]
-   Handles if the player is ‘X’ / G1 by flipping the styles [1 mark] [ Y ]
-   Stores valid Turns in some data structure (Array, List, ArrayList, HashMap…) [1 mark] [ Y ]
-   Randomly returns a turn from all possible turns. [1 mark] [ Y ]
-   No method is more than 30 lines [1 mark] [ Y ]
-   Creates at least 1 helper function [1 mark] [ Y ]
-   Uses helper functions from other classes effectively [1 mark] [ Y ]

### Section 4 Feedback and Other Deductions:

- -0.25 for pothole_case method names
- Fantastic work with moving getTurn() to specific-function helpers!

## Section 5 OnitamaControllers [ ?? / 2 marks]:

### Task 5.1: OnitamaControllerHumanVSRandom [ 1 / 1 mark]

-   Makes a PlayerHuman and a PlayerRandom object, and initializes them with their respective players. [0.5 marks] [ Y ]
-   Somehow has a way to pass the instance of Onitama to the PlayerRandom class (either getter or setter methods) [0.5 marks] [ Y ]

### Task 5.2: OnitamaControllerRandomVSRandom [ 1 / 1 mark]

-   Makes a PlayerRandom and a PlayerRandom object, and initializes them with their respective players. [0.5 marks] [ Y ]
-   Somehow has a way to pass the instance of Onitama to the PlayerRandom class (either getter or setter methods) [0.5 marks] [ Y ]

## OnitamaControllers Functionality [ 5 / 5 marks]:

For the functionality portion of the controllers, we should be able to tell by
quickly running their controllers and playing a few moves (for the HumanVSRandom)
or running the RandomVSRandom (if it terminates, it works).

### HumanVSRandom [ 2 / 2 marks]:

For this one, pick one of the following to mark as [Y]

-   Controller works and is playable. [2 marks] [ Y ]
-   There will be some scenarios based on the code that the controller will not work all the time. [1 mark] [ N ]
-   Does not work (errors, or not implemented) [0 marks] [ N ]

### RandomVSRandom [ 3 / 3 marks]:

For this one, pick one of the following to mark as [Y]

-   Controller works and runs to termination with one player winning. [3 marks] [ Y ]
-   There will be some scenarios based on the code that the controller will not work all the time. [1.5 mark] [ N ]
-   Does not work (errors, or not implemented) [0 marks] [ N ]

### Section 5 Feedback and Other Deductions:

- Good work! :)

## Section 6 Documenting Code [ 5 / 5 marks]:

### Turn [1 mark]

For this one, pick one of the following to mark as [Y]

-   Class and all of its methods are documented well. [1 mark] [ Y ]
-   Mostly documented, but something is missing, or not documented in enough detail. [0.5 marks] [ N ]
-   No documentation [0 marks] [ N ]

### OnitamaControllers (HvH, HvR, RvR) [ 2 / 2 marks]:

For this one, pick one of the following to mark as [Y]

-   Class and all of its methods are documented well. [2 marks] [ Y ]
-   Mostly documented, but something is missing, or not documented in enough detail. [1 mark] [ N ]
-   No documentation [0 marks] [ N ]

### Player + PlayerHuman + PlayerRandom [ 2 / 2 marks]:

For this one, pick one of the following to mark as [Y]

-   Class and all of its methods are documented well. [2 marks] [ Y ]
-   Mostly documented, but something is missing, or not documented in enough detail. [1 mark] [ N ]
-   No documentation [0 marks] [ N ]

### Section 6 Feedback:

- Your documentation was great! Nice that you used language that would be understood by non-programmers. That's exactly what we're looking for. :)

## Overall Code Design [ 4 / 5 marks]:

For this one, pick one of the following to mark as [Y]

-   Perfect, they grasp the teachings of Michael and Sadia senpais. [5 marks] [ N ]
-   Almost perfect, slight areas where it can be improved [4 marks] [ Y ]
-   Mediocre job at code design, notable code smells. [3 marks] [ N ]
-   Poor job at code design, lots of code smells. [2 marks] [ N ]
-   Did not really try to make their code look nice, did the minimal effort. [1 mark] [ N ]
-   NaNi????? If it hurts so bad, just hit ‘em with the 0. [0 marks] [ N ]

### Code Design Feedback:

- Notable flaws are the variable and method names using pothole_case, and some non-descriptive variable names such as "e", "s", "mylist", etc.
- However, your use of helper functions was great and made your code easier to understand. Good luck on A2! You got this!
