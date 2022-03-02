/*
 * CS1050 - Computer Science I - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 18 - Decrypt
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Decrypt {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("encrypted.txt"));
        String out = "";
        while (sc.hasNext()) {
            String line = sc.nextLine();
            for (int i = 0; i <= line.length(); i++) {
                char c = line.charAt(i);
                if (c == '.') {
                    out += " ";
                } else if (c == ' ') {
                    out += "-";
                } else
                    out += (Character.toString(c - 10));


                //sc.CharactertoString();
                out += "\n";
            }


            // TODO: open the "encrypted.txt" file

            // TODO: read one line at a time using a loop


            // TODO: create an output string

            // TODO: read one character at a time using a 2nd loop


            // TODO: if the character is a period, write a space in the output string

            // TODO: else, if the character is a space, write a dash in the output string

            // TODO: else, write the character that is 10 codes less in the output string


            // TODO: display the output string


            // TODO: close the input file
            sc.close();
        }
    }
}