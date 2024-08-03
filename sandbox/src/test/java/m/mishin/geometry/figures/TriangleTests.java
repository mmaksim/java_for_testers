package m.mishin.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var triangle = new Triangle(3, 4, 5);
        double result = triangle.area();
        Assertions.assertEquals(6, result);
    }

    @Test
    void canCalculatePerimeter() {
        var triangle = new Triangle(4, 6, 5);
        double result = triangle.perimeter();
        Assertions.assertEquals(15, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-3, 4, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(3, 4, 0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(3, -4, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateInequalityTriangle() {
        try {
            new Triangle(15, 5, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(5, 15, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(2, 2, 10);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
}
