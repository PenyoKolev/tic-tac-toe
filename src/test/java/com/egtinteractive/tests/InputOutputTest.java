package com.egtinteractive.tests;

import static org.testng.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class InputOutputTest {
  @DataProvider(name = "arcadeMachineConsoleIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineConsoleIO();
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void readShouldReadNextLine(final ArcadeMachine machine) {
    // Arrange
    String fakeInput = "Hello";
    System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));
    InputOutput io = new ConsoleIO();

    // Act
    String result = io.read();

    // Assert
    assertEquals(result, fakeInput);
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void readNextIntShouldReadNextInt(final ArcadeMachine machine) {
    // Arrange
    String fakeInput = "1 2 3";
    System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));
    InputOutput io = new ConsoleIO();

    // Act
    int result = io.readNextInt();

    // Assert
    assertEquals(result, fakeInput.charAt(0) - 48);
  }
  
  

//  @Test(dataProvider = "arcadeMachineConsoleIO")
//  public void writeShouldWrite(final ArcadeMachine machine) {
//    // Arrange
//    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    System.setOut(new PrintStream(outContent));
//    InputOutput io = new ConsoleIO();
//
//    // Act
//    io.write("Hello");
//
//    // Assert
//    assertEquals(outContent.toString(), "Hello\n");
//    
//    System.setOut(System.out);
//  }
}
