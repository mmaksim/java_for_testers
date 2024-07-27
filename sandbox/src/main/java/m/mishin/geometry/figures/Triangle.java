package m.mishin.geometry.figures;

import static java.lang.Math.sqrt;

public record Triangle(double sideA, double sideB, double sideC) {

 /*   public double sideA;
    public double sideB;
    public double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }*/

    public double area(Triangle triangle) {
        double p = triangle.perimeter() / 2;
        return sqrt((p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC)));
    }

    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
}
