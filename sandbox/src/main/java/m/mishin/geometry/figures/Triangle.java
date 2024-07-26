import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Triangle {
    static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    static double square(double a, double b, double c) {
        double p = perimeter(a, b, c)/2;
        return sqrt((p * (p - a) * (p - b) * (p - c)));
    }
    double sideOfTriangleA;
    double sideOfTriangleB;
    double sideOfTriangleC;

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter side A: ");
        triangle.sideOfTriangleA = sc.nextDouble();
        System.out.print("Enter side B: ");
        triangle.sideOfTriangleB = sc.nextDouble();
        System.out.print("Enter side C: ");
        triangle.sideOfTriangleC = sc.nextDouble();

        System.out.println("Perimeter of the triangle = " +  triangle.perimeter(triangle.sideOfTriangleA, triangle.sideOfTriangleB, triangle.sideOfTriangleC));
        System.out.println("Square of the triangle = " +  triangle.square(triangle.sideOfTriangleA, triangle.sideOfTriangleB, triangle.sideOfTriangleC));
    }
}
