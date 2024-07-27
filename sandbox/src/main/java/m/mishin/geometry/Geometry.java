package m.mishin.geometry;

import m.mishin.geometry.figures.Triangle;

import java.util.Scanner;

public class Geometry {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter side A: ");
        double a = sc.nextDouble();
        System.out.print("Enter side B: ");
        double b = sc.nextDouble();
        System.out.print("Enter side C: ");
        double c = sc.nextDouble();

        Triangle triangle = new Triangle(a, b, c);

        String textP = String.format("Периментр треугольника со сторонами %f, %f, %f, = %f",
                triangle.sideA(),
                triangle.sideB(),
                triangle.sideC(),
                triangle.perimeter());
        System.out.println(textP);

        String textS = String.format("Площадь треугольника со сторонами %f, %f, %f, = %f",
                triangle.sideA(),
                triangle.sideB(),
                triangle.sideC(),
                triangle.area(triangle));
        System.out.println(textS);
    }
}
