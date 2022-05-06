package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(primary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.getTorpedoCount()).thenReturn(5);
    when(primary.fire(5)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(5);
  }

 @Test
 public void fireTorpedo_All_Failure(){
   // Arrange
   when(primary.getTorpedoCount()).thenReturn(0);
   when(primary.isEmpty()).thenReturn(true);
   when(secondary.getTorpedoCount()).thenReturn(0);
   when(secondary.isEmpty()).thenReturn(true);
   // Act
   boolean result = ship.fireTorpedo(FiringMode.ALL);
   // Assert
   verify(primary, times(0)).fire(0);
   verify(secondary, times(0)).fire(0);
 }

 @Test
 public void fireTorpedo_Single_Failure(){
   // Arrange
   when(primary.getTorpedoCount()).thenReturn(0);
   when(primary.isEmpty()).thenReturn(true);
   when(secondary.getTorpedoCount()).thenReturn(0);
   when(secondary.isEmpty()).thenReturn(true);
   // Act
   boolean result = ship.fireTorpedo(FiringMode.ALL);
   // Assert
   verify(primary, times(0)).fire(0);
   verify(secondary, times(0)).fire(0);
 }

}
