import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
    }

    @Test

    public void nullNameMessage(){
        try{
            new Horse(null, 1, 1);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    public void getName() throws NoSuchFieldException, IllegalArgumentException{
        Horse horse = new Horse("alexey", 1,1);
        assertEquals("alexey", horse.getName());
    }
    @Test
    public void getSpeed(){
        double expectedSpeed = 443;
        Horse horse = new Horse("alexey", expectedSpeed,1);
        assertEquals(expectedSpeed, horse.getSpeed());
    }
    @Test
    public void getDistance() {
        Horse horse = new Horse("alexey", 1,283);
        assertEquals(283, horse.getDistance());
    }
    @Test
    public void zeroDistanceByDefault(){
        Horse horse = new Horse("alexey", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveUsesGetRandom(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("alexey", 31, 283).move();
            mockedStatic.verify(()  -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}
