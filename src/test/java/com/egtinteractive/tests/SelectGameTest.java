package com.egtinteractive.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.ai.AI;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.Order;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.player.AIPlayer;
import com.egtinteractive.player.Opponent;
import com.egtinteractive.provider.Provider;

public class SelectGameTest {
  @DataProvider(name = "arcadeMachineConsoleIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineConsoleIO();
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void selectGameShouldChangeStateToPLAY_GAME(final ArcadeMachine machine) {
    // Arrange
    InputOutput io = new ConsoleIO();
    Board board = new TicTacToeBoard(3);
    Marker marker = Marker.X;
    Order order = Order.PLAYER_ONE_FIRST;
    AI ai = new AITicTacToe();
    Opponent opponent = new AIPlayer(ai);
    machine.putCoins(10);

    // Act
    machine.selectGame(new TicTacToeGame(io, board, marker, order, opponent));

    // Assert
    assertEquals(machine.getStateName(), "PLAY_GAME");
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void selectGameShouldNotChangeStateIfCoinsNotEnough(final ArcadeMachine machine) {
    // Arrange
    InputOutput io = new ConsoleIO();
    Board board = new TicTacToeBoard(3);
    Marker marker = Marker.X;
    Order order = Order.PLAYER_ONE_FIRST;
    AI ai = new AITicTacToe();
    Opponent opponent = new AIPlayer(ai);
    machine.putCoins(8);

    // Act
    machine.selectGame(new TicTacToeGame(io, board, marker, order, opponent));

    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
  }

  @Test(dataProvider = "arcadeMachineConsoleIO", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }
}
