package edu.desu.searchutils;

import java.util.Scanner;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateSequentialIntArray;

/**
 * Description of what this utility class does.
 * Follows Effective Java item 4: Enforce noninstantiability with a private constructor.
 */
public final class LinearSearch {

    // 1. Private constructor prevents instantiation from within and outside the class
    private LinearSearch() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    // 2. Static Utility Methods here; can duplicate
    // Performs a linear search for 1 if answer == true, or 0 if answer == false.
    public static boolean linearSearch(int[] data, int target) {

        for (int index = 0; index < data.length; index++) {
            if (data[index] == target) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println("Warming up JVM...");
        int[] warmupData = generateSequentialIntArray(2_000);
        for (int i = 0; i < 5_000; i++) {
            linearSearch(warmupData, i % 2_000);
        }
        System.out.println("Warmup complete.\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 1. Ask the user to input an integer for array size or 'Q' to quit.
            System.out.print("Enter array size (or 'Q' to quit): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("Q")) {
                break;
            }

            int arraySize = Integer.parseInt(input.replace(",", "").trim());

            // 2. Ask the user to input an integer for a target.
            System.out.print("Enter target: ");
            String targetInput = scanner.next();
            int target = Integer.parseInt(targetInput.replace(",", "").trim());

            // 3. Generate an array of integers of array size using generateSequentialIntArray.
            int[] data = generateSequentialIntArray(arraySize);

            // 4. Set a timestamp for the start of a timing test using the java current time in nanoseconds.
            long startTime = System.nanoTime();

            // 5. Run linearSearch on the array and the target.
            boolean targetFound = linearSearch(data, target);

            // 6. Set a second timestamp for the end of the timing test using the java current time in nanoseconds.
            long endTime = System.nanoTime();

            // 7. Subtract the first time in nanoseconds from the second time.
            long elapsedTime = endTime - startTime;

            // 8. Print "Target found = <True or False> depending on the return Boolean from step 5.
            System.out.println("Target found = " + targetFound);

            // 9. Print "Time to complete linearSearch call: " and then the number of nanoseconds to run linearSearch.
            System.out.println("Time to complete linearSearch call: " + String.format("%,d", elapsedTime) + " nanoseconds");

            // Compute and print time in seconds in human readable decimal format (avoiding scientific notation)
            double elapsedTimeInSeconds = (double) elapsedTime / 1_000_000_000.0;
            System.out.println("Time to complete linearSearch call in seconds: " + String.format("%.9f", elapsedTimeInSeconds) + " seconds");
        }

        scanner.close();
    }

}
