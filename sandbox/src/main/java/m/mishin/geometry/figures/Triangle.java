package m.mishin.geometry.figures;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideC) == 0)
                || (Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideA) == 0)
                || (Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideB) == 0)
                || (Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideB) == 0)
                || (Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideA) == 0)
                || (Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideC) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public double area() {
        double p = perimeter() / 2;
        return sqrt((p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC)));
    }

    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
}
