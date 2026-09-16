package edu.desu.sortutils;

import java.util.Scanner;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateScrambledIntArray;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateSequentialIntArray;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateReverseSequentialIntArray;

/**
 * Description of what this utility class does.
 * Follows Effective Java item 4: Enforce noninstantiability with a private constructor.
 */
public final class InsertionSort {

    // 1. Private constructor prevents instantiation from within and outside the class
    private InsertionSort() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    // 2. Static Utility Methods here; can duplicate
    // Perform selection sort on an array of int
    public static void insertionSort(int[] data) {

        // Throws NullPointerException immediately if data is null, matching Arrays.sort()
        if (data == null) {
            throw new NullPointerException("The input array cannot be null");
        }

        int n = data.length;
        for (int k=1; k < n; k++) {                   // begin with second character
            int cur = data[k];                       // time to insert cur=data[k]
            int j = k;                                // find correct index j for cur
            while (j > 0 && data[j-1] > cur) {        // thus, data[j-1] must go after cur
                data[j] = data[j-1];                   // slide data[j-1] rightward
                j--;                                  // and consider previous j for cur
            }
            data[j] = cur;                            // this is the proper place for cur
        }

    }

    public static void main(String[] args) {
        System.out.println("Warming up JVM...");
        for (int i = 0; i < 200; i++) {
            int[] warmupData = generateScrambledIntArray(500);
            insertionSort(warmupData);
        }
        System.out.println("Warmup complete.\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 1. Ask the user if the generated array should be 1) scrambled, 2) sorted, or 3) reverse sorted.
            System.out.print("Should the generated array be 1) scrambled, 2) sorted, or 3) reverse sorted (or 'Q' to quit)? ");
            String optionInput = scanner.next();

            if (optionInput.equalsIgnoreCase("Q")) {
                break;
            }

            // 2. Ask the user to input an integer for array size or 'Q' to quit.
            System.out.print("Enter array size (or 'Q' to quit): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("Q")) {
                break;
            }

            int arraySize = Integer.parseInt(input.replace(",", "").trim());

            // 3. Call the appropriate method in TestArrayGenerator.java based on user choice.
            int[] data;
            switch (optionInput) {
                case "1":
                    data = generateScrambledIntArray(arraySize);
                    break;
                case "2":
                    data = generateSequentialIntArray(arraySize);
                    break;
                case "3":
                    data = generateReverseSequentialIntArray(arraySize);
                    break;
                default:
                    data = generateScrambledIntArray(arraySize);
                    break;
            }

            // 3. Set a timestamp for the start of a timing test using the java current time in nanoseconds.
            long startTime = System.nanoTime();

            // 4. Run insertionSort on the array.
            insertionSort(data);

            // 5. Set a second timestamp for the end of the timing test using the java current time in nanoseconds.
            long endTime = System.nanoTime();

            // 6. Subtract the first time in nanoseconds from the second time.
            long elapsedTime = endTime - startTime;

            // 7. Print "Time to complete selectionSort call: " and then the number of nanoseconds to run selectionSort.
            System.out.println("Time to complete insertionSort call: " + String.format("%,d", elapsedTime) + " nanoseconds");

            // 8. Compute and print time in seconds in human readable decimal format (avoiding scientific notation)
            double elapsedTimeInSeconds = (double) elapsedTime / 1_000_000_000.0;
            System.out.println("Time to complete insertionSort call in seconds: " + String.format("%.9f", elapsedTimeInSeconds) + " seconds");
        }

        scanner.close();
    }
}
