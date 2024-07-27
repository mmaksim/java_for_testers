package m.mishin.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var triangle = new Triangle(3, 4, 5);
        double result = triangle.area(triangle);
        Assertions.assertEquals(6, result);
    }

    @Test
    void canCalculatePerimeter() {
        var triangle = new Triangle(4, 6, 5);
        double result = triangle.perimeter();
        Assertions.assertEquals(15, result);
    }
}
