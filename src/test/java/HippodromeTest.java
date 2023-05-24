import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    List<Horse> emptyHorses = List.of();

    @Test
    public void nullHorses (){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", ex.getMessage());
    }
    @Test
    public void emptyHorses (){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyHorses));
        assertEquals("Horses cannot be empty.", ex.getMessage());
    }
    @Test
    public void horsesReturn(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i<30; i++) {
            horses.add(new Horse("name"+i, 1.0));
        }
        Hippodrome hip = new Hippodrome(horses);
        assertEquals(horses, hip.getHorses());
    }
    @Test
    public void move50Horses(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i<50; i++){
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hip = new Hippodrome(horses);
        hip.move();
        for(Horse h: horses){
            Mockito.verify(h).move();
        }
    }
    @Test
    public void rightWinner() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4, 5.0),
                new Horse("Туз Пик", 2.5, 6.7),
                new Horse("Зефир", 2.6, 9.3));
        Hippodrome hip = new Hippodrome(horses);
        assertEquals("Зефир", hip.getWinner().getName());
    }
}
