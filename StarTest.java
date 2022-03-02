import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StarTest {
    @Test
    public void convertingCoordinatesTest() {
        Star s1 = new Star(-1, 1,0);
        assertEquals(0, s1.getGUIX());
        assertEquals(0, s1.getGUIY());
        Star s2 = new Star(1,1,0);
        assertEquals(StarMapFrame.WIDTH, s2.getGUIX());
        assertEquals(0, s2.getGUIY());
        Star s3 = new Star(1, -1, 0);
        assertEquals(StarMapFrame.WIDTH, )


    }
}