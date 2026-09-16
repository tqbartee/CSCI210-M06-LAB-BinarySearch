package edu.desu.sortutils;

import java.util.Scanner;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateScrambledIntArray;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateSequentialIntArray;
import static edu.desu.testdatagenerator.TestArrayGenerator.generateReverseSequentialIntArray;

/**
 * Description of what this utility class does.
 * Follows Effective Java item 4: Enforce noninstantiability with a private constructor.
 */
public final class SelectionSort {

    // 1. Private constructor prevents instantiation from within and outside the class
    private SelectionSort() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    // 2. Static Utility Methods here; can duplicate
    // Perform selection sort on an array of int
    public static void selectionSort(int[] data) {

        // Throws NullPointerException immediately if data is null, matching Arrays.sort()
        if (data == null) {
            throw new NullPointerException("The input array cannot be null");
        }

        // Core selection sort code here
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            // --- SWAP-OPTIMIZED CHECK ---
            // Only perform the 3-step memory swap if the minimum element
            // is not already positioned at the front of the unsorted section.
            if (minIndex != i) {
                int temp = data[minIndex];
                data[minIndex] = data[i];
                data[i] = temp;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Warming up JVM...");
        for (int i = 0; i < 200; i++) {
            int[] warmupData = generateScrambledIntArray(500);
            selectionSort(warmupData);
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

            // 4. Run selectionSort on the array.
            selectionSort(data);

            // 5. Set a second timestamp for the end of the timing test using the java current time in nanoseconds.
            long endTime = System.nanoTime();

            // 6. Subtract the first time in nanoseconds from the second time.
            long elapsedTime = endTime - startTime;

            // 7. Print "Time to complete selectionSort call: " and then the number of nanoseconds to run selectionSort.
            System.out.println("Time to complete selectionSort call: " + String.format("%,d", elapsedTime) + " nanoseconds");

            // 8. Compute and print time in seconds in human readable decimal format (avoiding scientific notation)
            double elapsedTimeInSeconds = (double) elapsedTime / 1_000_000_000.0;
            System.out.println("Time to complete selectionSort call in seconds: " + String.format("%.9f", elapsedTimeInSeconds) + " seconds");
        }

        scanner.close();
    }
}
