package edu.desu.testdatagenerator;

import java.util.*;

public class TestArrayGenerator {

    // The following three methods support generation of test
    // data for sorting functions

    public static int[] generateSequentialIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateReverseSequentialIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - 1 - i;
        }
        return array;
    }

    public static int[] generateScrambledIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        scrambleIntArray(array);
        return array;
    }

    // This method supports the generation of a scrambled
    // int array
    public static void scrambleIntArray(int[] intArray) {
        // AI-generated from Perplexity.ai
        // 1. Convert int[] to Integer[]
        Integer[] integerArray = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = intArray[i];
        }
        // 2. Convert Integer[] to a Collection (List)
        List<Integer> integerList = new ArrayList<>(Arrays.asList(integerArray));
        // 3. Shuffle the Collection
        Collections.shuffle(integerList);
        // 4. Convert back to int[]
        int[] scrambledIntArray = new int[integerList.size()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = integerList.get(i);
        }
        intArray = scrambledIntArray;
    }

    // Additional methods here

	// Generate multiple sequential int arrays
    public static int[][] generateSequentialIntArrays(int numArrays, int size) {
        int[][] array = new int[3][size];
        int curnumber = 0;
        for (int i = 0; i < numArrays; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = curnumber;
                curnumber++;
            }
        }
        return array;
    }

    public static Integer[] convertintArrayToInteger(int[] data) {
        Integer[] result = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public static class DefaultComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    public static void waitOneSecond() {
        try {
            // Pause the current thread for 1000 milliseconds (1 second)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the InterruptedException if the thread is interrupted while sleeping
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Thread was interrupted during sleep.");
        }
    }

}
