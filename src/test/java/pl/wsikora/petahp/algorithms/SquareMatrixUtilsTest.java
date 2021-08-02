package pl.wsikora.petahp.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareMatrixUtilsTest {

    private final double[][] arr = new double[][]{{1., 1., 2.}, {1., 1., 2.}, {0.5, 0.5, 1.}};

    @Test
    void calculateRowsSum() {
        double[] rowsSum = SquareMatrixUtils.calculateRowsSum(arr);
        assertEquals(4., rowsSum[0]);
        assertEquals(4., rowsSum[1]);
        assertEquals(2., rowsSum[2]);
    }

    @Test
    void calculateColumnsSum() {
        double[] columnsSum = SquareMatrixUtils.calculateColumnsSum(arr);
        assertEquals(2.5, columnsSum[0]);
        assertEquals(2.5, columnsSum[1]);
        assertEquals(5.0, columnsSum[2]);
    }

    @Test
    void cloneValidArray() {
        double[][] clonedArray = SquareMatrixUtils.clone(arr);
        for (int i = 0; i < 3; i++) {
            assertArrayEquals(arr[i], clonedArray[i]);
        }
    }


    @Test
    void cloneInvalidArray() {
        assertThrows(IllegalArgumentException.class, () -> SquareMatrixUtils.clone(new double[][]{{1., 2., 3.}, {1., 1.}}));
    }


}
