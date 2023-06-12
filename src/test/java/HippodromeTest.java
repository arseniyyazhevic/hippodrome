import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void emptyHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(""+ i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for(Horse horse : horses){
            verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse = new Horse("n1", 1, 2);
        Horse horse1 = new Horse("n2", 2 , 10);
        Horse horse2 = new Horse("n3", 1 ,1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse, horse2, horse1));

        assertSame(horse1, hippodrome.getWinner());
    }
}
