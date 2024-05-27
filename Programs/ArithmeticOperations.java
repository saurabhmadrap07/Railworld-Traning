import java.util.Scanner;

public class ArithmeticOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter 1st input: ");
        double a = sc.nextDouble();
        System.out.print("Please enter 2nd input: ");
        double b = sc.nextDouble();

        double sum = a + b;
        double difference = a - b;
        double product = a * b;
        double quotient = a / b;

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
    }
}
