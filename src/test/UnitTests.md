Manual Unit Tests:

Test 1a: Are players prompted on start up for number of player?

    - Method:
      - Step 1: Compile & run
      - Step 2: Check to see if a JOptionPane prompting for the number of players appears.

    - PASS: If there is a prompt that asks how many players are playing the game, this test passes.
    - FAIL: If there is no GUI prompt for the number of players, this test fails.

Test 1b: Is there a correct number of options for the number of players?
    - Method:
      - Step 1: Compile & run
      - Step 2: Check to see if the first JOptionPane that appears has four options for the number.

    - PASS: If the first prompt asks for between 1 and 4 players, this test passes.
    - FAIL: If there is no GUI prompt for the number of players, this test fails.

Test 2a: Is there a prompt to input the players' name?
    - Method:
      - Step 1: Compile & run
      - Step 2: Enter the number of players.
      - Step 3: After the player is prompted for the number of players, check to see if a new JOptionPane has opened prompting for a String input for the player's name.

    - PASS: If there are prompts for the players' names, this test passes.
    - FAIL: If there is no way for the players to enter their names in, this test fails.

Test 2b: Do the players' names actually show up in the game?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: Check to see if the tokens corresponding to each player correctly displays the players' names.

    - PASS: If the players' names are indeed displayed on the board, this test passes.
    - FAIL: If the player's names are not displayed, this test fails.

Test 3a: Is there a game board?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: Check to see if a JPanel showing the game board is created.

    - PASS: If a game board is clearly displayed, this test passes.
    - FAIL: If no board is displayed, this test fails.

Test 3b: Is the game board setup with a appropriate color arrangement?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if there is a game path, with bright multi-colored tiles.

    - PASS: If there is a colorful path on the board, this test passes.
    - FAIL: If there is no path with colorful tiles, this test fails.

Test 4a: Are the correct number of tokens on the board placed on the board?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if number of tokens displayed matches the number of players there are.

    - PASS: If the correct number of tokens is displayed on the board, this test passes.
    - FAIL: If there are no tokens or an incorrect number of tokens displayed, this test fails.

Test 4b: Do the tokens on the board correspond with each player?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if there the tokens displayed are assigned to each player.

    - PASS: If the tokens on the board are assigned to each player, this test passes.
    - FAIL: If the tokens are not assigned to the correct players, this test fails.

Test 5a: Are there two decks displayed?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if there are also 2 Deck objects displayed.

    - PASS: If there are only 2 decks displayed in the game, this test passes.
    - FAIL: If there are fewer than or greater than 2 decks displayed, this test fails.

Test 5b: If there are two decks, do they display what they're supposed to?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if one deck shows the back of the card (to be drawn from) and from .

    - PASS: If there are only 2 decks displayed in the game, this test passes.
    - FAIL: If there are fewer than or greater than 2 decks displayed, this test fails.

Test 6a: Can multiple player tokens be placed on a board tile?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check the first tile to see if there are multiple tokens on the board.

    - PASS: If the board tile can hold multiple tokens, this test passes.
    - FAIL: If the board tile cannot hold multiple tokens, this test fails.

Test 6b: Can the player see individual tokens that are placed on the same board tile?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if the first tile to see if the tokens that are placed there can each be seen without covering up another token.

    - PASS: If all tokens can be seen and distinguished, this test passes.
    - FAIL: If the tokens can not be visibly distinguished, this test fails.

Test 7a: (See WorldOfSweetsTest.java)

Test 7b: Is there a button that draws from the deck?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if a button is displayed that, when pressed, draws from the deck.

    - PASS: If the button for drawing from the deck is displayed and functions correctly, this test passes.
    - FAIL: If there is no button for drawing from the deck or if there is button designated for this function, but does not work, this test fails.

Test 8a: (See WorldOfSweetsTest.java)

Test 8b: (See WorldOfSweetsTest.java)
