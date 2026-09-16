package edu.desu.sortsearchutils;

import edu.desu.searchutils.BinarySearch;
import edu.desu.testdatagenerator.TestArrayGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTests {

    @Test
    @DisplayName("Test binarySearch finds target in middle of array")
    void testTargetPresentInMiddle() {
        int[] data = {1, 3, 5, 7, 9, 11, 13};
        assertTrue(BinarySearch.binarySearch(data, 7));
    }

    @Test
    @DisplayName("Test binarySearch finds target at the beginning of array")
    void testTargetPresentAtStart() {
        int[] data = {2, 4, 6, 8, 10};
        assertTrue(BinarySearch.binarySearch(data, 2));
    }

    @Test
    @DisplayName("Test binarySearch finds target at the end of array")
    void testTargetPresentAtEnd() {
        int[] data = {2, 4, 6, 8, 10};
        assertTrue(BinarySearch.binarySearch(data, 10));
    }

    @Test
    @DisplayName("Test binarySearch in single-element array when target is present")
    void testTargetPresentSingleElementArray() {
        int[] data = {42};
        assertTrue(BinarySearch.binarySearch(data, 42));
    }

    @Test
    @DisplayName("Test binarySearch in single-element array when target is absent")
    void testTargetAbsentSingleElementArray() {
        int[] data = {42};
        assertFalse(BinarySearch.binarySearch(data, 10));
    }

    @Test
    @DisplayName("Test binarySearch on empty array returns false")
    void testTargetAbsentEmptyArray() {
        int[] data = {};
        assertFalse(BinarySearch.binarySearch(data, 5));
    }

    @Test
    @DisplayName("Test binarySearch returns false when target is smaller than all elements")
    void testTargetAbsentSmallerThanAll() {
        int[] data = {10, 20, 30, 40};
        assertFalse(BinarySearch.binarySearch(data, 5));
    }

    @Test
    @DisplayName("Test binarySearch returns false when target is larger than all elements")
    void testTargetAbsentLargerThanAll() {
        int[] data = {10, 20, 30, 40};
        assertFalse(BinarySearch.binarySearch(data, 50));
    }

    @Test
    @DisplayName("Test binarySearch returns false when target falls between existing elements")
    void testTargetAbsentBetweenElements() {
        int[] data = {10, 20, 30, 40};
        assertFalse(BinarySearch.binarySearch(data, 25));
    }

    @Test
    @DisplayName("Test binarySearch with negative numbers")
    void testTargetPresentWithNegativeNumbers() {
        int[] data = {-50, -30, -10, 0, 10, 30, 50};
        assertTrue(BinarySearch.binarySearch(data, -30));
        assertTrue(BinarySearch.binarySearch(data, 0));
        assertFalse(BinarySearch.binarySearch(data, -20));
    }

    @Test
    @DisplayName("Test binarySearch with duplicate elements in array")
    void testTargetPresentWithDuplicates() {
        int[] data = {1, 2, 2, 2, 3, 4, 5};
        assertTrue(BinarySearch.binarySearch(data, 2));
    }

    @Test
    @DisplayName("Test binarySearch on generated sequential array when target is present")
    void testTargetPresentInGeneratedSequentialArray() {
        int size = 100;
        int[] data = TestArrayGenerator.generateSequentialIntArray(size);
        assertTrue(BinarySearch.binarySearch(data, 0));
        assertTrue(BinarySearch.binarySearch(data, 49));
        assertTrue(BinarySearch.binarySearch(data, 99));
    }

    @Test
    @DisplayName("Test binarySearch on generated sequential array when target is absent")
    void testTargetAbsentInGeneratedSequentialArray() {
        int size = 100;
        int[] data = TestArrayGenerator.generateSequentialIntArray(size);
        assertFalse(BinarySearch.binarySearch(data, -1));
        assertFalse(BinarySearch.binarySearch(data, 100));
    }

    @Test
    @DisplayName("Test binarySearch throws NullPointerException on null input array")
    void testNullArrayThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> BinarySearch.binarySearch(null, 5));
    }

    @Test
    @DisplayName("Test range binarySearch finds target within valid sub-range")
    void testRangeSearchTargetFoundWithinBounds() {
        int[] data = {10, 20, 30, 40, 50, 60, 70};
        assertTrue(BinarySearch.binarySearch(data, 30, 1, 5));
        assertTrue(BinarySearch.binarySearch(data, 60, 1, 5));
    }

    @Test
    @DisplayName("Test range binarySearch returns false if target is outside sub-range")
    void testRangeSearchTargetOutsideSubrange() {
        int[] data = {10, 20, 30, 40, 50, 60, 70};
        assertFalse(BinarySearch.binarySearch(data, 10, 2, 5));
        assertFalse(BinarySearch.binarySearch(data, 70, 2, 5));
    }

    @Test
    @DisplayName("Test range binarySearch returns false when low > high")
    void testRangeSearchLowGreaterThanHigh() {
        int[] data = {10, 20, 30, 40, 50};
        assertFalse(BinarySearch.binarySearch(data, 30, 4, 2));
    }

    @Test
    @DisplayName("Test range binarySearch with single element range low == high")
    void testRangeSearchSingleElementRange() {
        int[] data = {10, 20, 30, 40, 50};
        assertTrue(BinarySearch.binarySearch(data, 30, 2, 2));
        assertFalse(BinarySearch.binarySearch(data, 20, 2, 2));
    }

    @Test
    @DisplayName("Test BinarySearch private constructor throws AssertionError")
    void testPrivateConstructorThrowsAssertionError() throws Exception {
        Constructor<BinarySearch> constructor = BinarySearch.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(AssertionError.class, exception.getCause());
        assertEquals("Utility class cannot be instantiated", exception.getCause().getMessage());
    }
}
