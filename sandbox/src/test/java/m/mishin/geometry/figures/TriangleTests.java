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

    @Test
    void testTriangle() {
        var triangle1 = new Triangle(3, 4, 5);
        var triangle2 = new Triangle(4, 5, 3);
        var triangle3 = new Triangle(5, 4, 3);
        var triangle4 = new Triangle(3, 5, 4);
        var triangle5 = new Triangle(4, 3, 5);
        var triangle6 = new Triangle(5, 3, 4);

        Assertions.assertEquals(triangle1, triangle2);
        Assertions.assertEquals(triangle1, triangle3);
        Assertions.assertEquals(triangle1, triangle4);
        Assertions.assertEquals(triangle1, triangle5);
        Assertions.assertEquals(triangle1, triangle6);
        Assertions.assertEquals(triangle2, triangle3);
        Assertions.assertEquals(triangle2, triangle4);
        Assertions.assertEquals(triangle2, triangle5);
        Assertions.assertEquals(triangle2, triangle6);
        Assertions.assertEquals(triangle3, triangle4);
        Assertions.assertEquals(triangle3, triangle5);
        Assertions.assertEquals(triangle3, triangle6);
        Assertions.assertEquals(triangle4, triangle5);
        Assertions.assertEquals(triangle4, triangle6);
        Assertions.assertEquals(triangle5, triangle6);
    }
}
