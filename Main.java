package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input a temperature in Fahrenheit: ");
        Double user_input = sc.nextDouble();
        if (user_input > 90)
            System.out.print("This area is under a heat warning!");
        else
        {
            if (user_input < 30)
                System.out.print("This area is under icy conditions!");

        }

	// write your code here
    }
}
