package m.mishin.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {

    public double sideA;
    public double sideB;
    public double sideC;

    public Triangle(double sideOfTriangleA, double sideOfTriangleB, double sideOfTriangleC) {
        this.sideA = sideOfTriangleA;
        this.sideB = sideOfTriangleB;
        this.sideC = sideOfTriangleC;
    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static double area(double a, double b, double c) {
        double p = perimeter(a, b, c) / 2;
        return sqrt((p * (p - a) * (p - b) * (p - c)));
    }
}
