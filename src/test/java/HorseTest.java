import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class HorseTest {
    Horse alfa = new Horse("alfa",1,1);
    // тесты на конструктор
    @Test
    public void nullName(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
    }
    @Test
    public void nullNameMessage(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
        assertEquals("Name cannot be null.", ex.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t"})
    public void emptyName(String name){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse(name,1,1));
        assertEquals("Name cannot be blank.", ex.getMessage());
    }
    @Test
    public void negativeSpeedMessage(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse("name",-1,1));
        assertEquals("Speed cannot be negative.", ex.getMessage());
    }
    @Test
    public void negativeDistanceMessage(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Horse("name",1,-1));
        assertEquals("Distance cannot be negative.", ex.getMessage());
    }
    //тест на методы
    @Test
    public void nameReturn(){
        Horse alfa = new Horse("alfa",1,1);
        assertEquals("alfa", alfa.getName());
    }
    @Test
    public void speedReturn(){
        assertEquals(1.0,alfa.getSpeed());
    }
    @Test
    public void distanceReturn(){
        assertEquals(1.0,alfa.getDistance());
    }
    @Test
    public void moveRandomCall(){
        try (MockedStatic<Horse> mockedHorse =  Mockito.mockStatic(Horse.class)) {
            alfa.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 1.0, 2.0, 3.0})
    public void moveDistanceCheck(double rValue) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(rValue);
            alfa.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(1 + 1*rValue, alfa.getDistance());
        }
    }
}
