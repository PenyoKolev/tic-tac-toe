package com.egtinteractive.machinetest;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;
import com.egtinteractive.provider.Provider;

public class SelectGameTest {
  @DataProvider(name = "selectGame")
  public Object[][] machine() {
    return Provider.selectGame();
  }

  @Test(dataProvider = "selectGame")
  public void putCoinsShouldAddToBalance(ArcadeMachine machine) {
    // Arrange
    int coins = 10;
    int oldBalance = machine.getBalance();

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), oldBalance + coins);
  }

  @Test(dataProvider = "selectGame")
  public void putNegativeCoinsShouldNotAddToBalance(ArcadeMachine machine) {
    // Arrange
    int coins = -10;
    int oldBalance = machine.getBalance();

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), oldBalance);
  }

  @Test(dataProvider = "selectGame")
  public void selectGameShouldChangeStateToPLAY_GAME(ArcadeMachine machine) {
    // Arrange
    machine.putCoins(10);
    Board board = new TicTacToeBoard(3);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));

    // Act
    machine.selectGame(new TicTacToeGame(board, opponents));

    // Assert
    assertEquals(machine.getStateName(), "PLAY_GAME");
  }

  @Test(dataProvider = "selectGame")
  public void selectGameShouldNotChangeStateIfNotEnoughMoney(ArcadeMachine machine) {
    // Arrange
    machine.putCoins(5);
    Board board = new TicTacToeBoard(3);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));

    // Act
    machine.selectGame(new TicTacToeGame(board, opponents));

    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
  }

  @Test(dataProvider = "selectGame", expectedExceptions = IllegalStateException.class)
  public void methodPlayGameUnsuportedForTheStateShouldTrowException(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }
}
