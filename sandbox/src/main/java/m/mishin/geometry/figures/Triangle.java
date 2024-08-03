package m.mishin.geometry.figures;

import static java.lang.Math.sqrt;

public record Triangle(double sideA, double sideB, double sideC) {

    public Triangle {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Triangle sides must be positive");
        }
        if ((sideA + sideB) <= sideC || (sideB + sideC) <= sideA || (sideC + sideA) <= sideB) {
            throw new IllegalArgumentException("The sum of any two sides of the triangle must be at least the third side");
        }
    }

    public double area() {
        double p = perimeter() / 2;
        return sqrt((p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC)));
    }

    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
}
