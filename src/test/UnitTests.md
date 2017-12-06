Manual Unit Tests:

SPRINT 1:
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


SPRINT 2:

As a Player,
I want to be alerted when a player enters the Grandma's House tile,
So that I know when I have won (or lost) the game and it is over.

Test 2a: When a player enters a win condition, is the player alerted?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed and the game is setup, play through the game.
      - Step 4: When any player's enters the "Grandma's House" tile, check to see if a JOptionPane displaying that the player has won.

    - PASS: If a player enters the "Grandma's House" tile and the player is alerted that the player was won, this test passes.
    - FAIL: If a player enters the "Grandma's House" tile, and the player is not alerted that they have won, this test fails.

Test 2b: When a player enters a win condition, does the game end?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed and the game is setup, play through the game.
      - Step 4: When any player's enters the "Grandma's House" tile, and after the player is alerted the have won, check to see if a           JOptionPane, asking the player if they want to play again or end the game, pops up.

    - PASS: If a player wins the game, and an option to play again or exit, this test passes.
    - FAIL: If a player wins and they are not alerted the game does not end with a "play again" option, this test fails.

As a user, I want to be able to see the picture of the card I drew, so that I don't have to read and it's nicer to look at

Test 3a: Do the basic cards display appropriately?

    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player info prompts.
      - Step 3: Verify the cards for each color display as their appropriate color as well as with a "x#" label indicating the number of spaces.

    - PASS: The cards all reflect the color the player moves to and number of spaces the player moves.
    - FAIL: A card does not reflect the player's movement.

Test 3b: Do the special cards display appropriately? (Middle, Skip)

    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player info prompts.
      - Step 3: Verify the middle card appears as a purple card with 'MIDDLE' written in its center, and skip appears as a white card with 'SKIP' written in the center

    - PASS: The cards display as described above and the players action reflects the intended card behavior
    - FAIL: The card display is incorrectly formatted or doesn't reflect where the player moves

SPRINT 3:

Test 14a: Do all the tiles on the board have appropriate images?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, check if every tile on the board is displayed using an image that corresponds with the color/location it should be.

    - PASS: If all tokens on the board are displayed with images of different colored tiles or locations, this test passes.
    - FAIL: If the tokens are not all displayed by images, this test fails.

Test 14b: Does the card drawn display an image that corresponds with the tile the player must move to?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts.
      - Step 3: When the game board is displayed, draw a card.
      - Step 4: When the player moves, check to see if the card panel displays an image that is identical to the image displayed on the tile the player moved to.

    - PASS: If the card drawn has the same image as the tile a player moves to when the card is drawn, this test passes.
    - FAIL: If the card drawn does not have the same image as the tile a player moves to when the card is drawn, this test fails.
SPRINT 4:

Test 1: Does the player "Dad" move to the worst possible space each turn?
    - Method:
      - Step 1: Compile & run
      - Step 2: Complete the player information prompts, with one player being named "Dad".
      - Step 3: When the game board is displayed, draw a card as "Dad".
      - Step 4: When the player moves, check to see if the tile "Dad" moved forward to the closest space to the start. For example, Dad should always move to the first red tile on the first turn.

    - PASS: Given "Dad" has not drawn a Skip card, if "Dad" moves only once space forward each turn, this test passes.
    - FAIL: Given "Dad" has not drawn a Skip card, if "Dad" does not move one space forward, this test fails.

Test 15: Are you prompted to play in strategic or normal on load?
    - Method:
      - Step 1: Compile & run
      - Step 2: Select new game
      - Step 3: Verify the menu is displayed. Try both options to verify the appropriate gamemode is initialized

      - PASS: Prompt is displayed and appropriate gamemode begins
      - FAIL: Prompt is not displayed or the gamemode selection doesn't work properly.

Test 16: Do AI players make a move without player input?
    - Method:
      - Step 1: Compile & run
      - Step 2: Select new game
      - Step 3: Make at least one player be AI
      - Step 4: Verify that on every turn for AI players, no input is needed.

      - PASS: AI takes turn without input.
      - FAIL: AI does not take turn without input

Test 17: Does the game send out a prompt after every AI move that says whether they drew or sent a boomerang?
    - Method:
      - Step 1: Compile & run
      - Step 2: Select new game
      - Step 3: Make at least one player be AI
      - Step 4: Verify that on every turn for AI players, a summary prompt appears.

      - PASS: After AI turn, prompt shows whether they drew a card or used a boomerang.
      - FAIL: After AI turn, prompt does not appear or does not show whether they used a card or used a boomerang.
