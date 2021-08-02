package pl.wsikora.petahp.algorithms;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public final class SquareMatrixUtils {

    private SquareMatrixUtils() {
        throw new AssertionError();
    }

    public static double[] calculateRowsSum(double[][] array) {
        return Arrays.stream(array)
                .mapToDouble(arr -> DoubleStream.of(arr).sum())
                .toArray();
    }

    public static double[] calculateColumnsSum(double[][] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            double sum = 0;
            for (double[] doubles : array) {
                sum += doubles[i];
            }
            result[i] = sum;
        }
        return result;
    }

    public static double[][] clone(double[][] array) {
        validate(array);
        double[][] doubles = new double[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            doubles[i] = array[i].clone();
        }
        return doubles;
    }

    private static void validate(double[][] array) {
        int length = array.length;
        for (double[] arr : array) {
            if (arr.length != length) {
                throw new IllegalArgumentException("Square matrix must have the same number of rows and columns");
            }
        }
    }

}
