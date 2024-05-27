import java.util.Scanner;

public class PrimeNumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter number: ");
        int a = sc.nextInt();
        if(a>1 && a%2!=0 && a%3!=0 && a%5!=0 && a%7!=0){
            System.out.println(a+"is a prime number");
        }

    }
}
