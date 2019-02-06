package com.egtinteractive.test;

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

public class PlayGameTest {
  @DataProvider(name = "playGameAI")
  public Object[][] machine() {
    return Provider.playGameAI();
  }

  @Test(dataProvider = "playGameAI")
  public void playGameWhenFinishShouldChangeStateToSELECT_GAME(ArcadeMachine machine) {
    // Act
    machine.playGame();

    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
  
  @Test(dataProvider = "playGameAI", expectedExceptions = IllegalStateException.class)
  public void methodPutCoinsUnsuportedForTheStateShouldTrowException(ArcadeMachine machine) {
    // Act
    machine.putCoins(10);
  }
  
  @Test(dataProvider = "playGameAI", expectedExceptions = IllegalStateException.class)
  public void methoSelectGamedUnsuportedForTheStateShouldTrowException(ArcadeMachine machine) {
    //Arrange
    Board board = new TicTacToeBoard(3);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    
    // Act
    machine.selectGame(new TicTacToeGame(board, opponents));
  }
}
