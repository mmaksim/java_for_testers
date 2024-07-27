package m.mishin.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        double result = Triangle.area(3, 4, 5);
        Assertions.assertEquals(6, result);
    }

    @Test
    void canCalculatePerimeter() {
        double result = Triangle.perimeter(3, 4, 5);
        Assertions.assertEquals(12, result);
    }
}
