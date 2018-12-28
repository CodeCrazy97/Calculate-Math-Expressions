package performmathcalculations;

import java.util.Scanner;

public class PerformMathCalculations {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string of numbers and math expressions you want calculated: ");
        try {
            double mathExpression = evaluateText(input.nextLine());
            System.out.println("Answer: " + mathExpression);
        } catch (NumberFormatException nfe) {
            System.out.println("There was a problem with your input :(");
        }
    }

    public static double evaluateText(String text) {
        String[] parts = text.split("(?=[/*+-])|(?<=[/*+-])");

        try {
            double result = Double.parseDouble(parts[0]);

            for (int i = 1; i < parts.length; i += 2) {
                String op = parts[i];
                double val = Double.parseDouble(parts[i + 1]);
                switch (op) {
                    case "*":
                        result *= val;
                        break;
                    case "/":
                        if (result / val < 1.0) {  //Most likely in decimal format - need to convert to integer percent (like 89%).
                            result = (result / val) * 100;  //Turn into a number.
                        } else {  //No need to convert to integer
                            result /= val;
                        }
                        break;
                    case "+":
                        result += val;
                        break;
                    case "-":
                        result -= val;
                        break;
                }
            }
            return result;
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
    }

}
