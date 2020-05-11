package pl.wsikora.petahp.algorithms;

import java.lang.reflect.Array;
import java.util.*;

public class Ahp {
    private int noOfCriteria;
    private double[][] matrix;

    public Ahp() {
    }

    public Ahp(int noOfCriteria, double[][] matrix) {
        this.noOfCriteria = noOfCriteria;
        this.matrix = matrix;
    }

    public int getNoOfCriteria() {
        return noOfCriteria;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setNoOfCriteria(int noOfCriteria) {
        this.noOfCriteria = noOfCriteria;
        this.matrix = createMatrix();
    }

    public void showMatrix() {
        StringBuilder result = new StringBuilder("matrix values in this view have been approximated\n[");
        for (int i = 0; i < noOfCriteria; i++) {
            result.append("\t[");
            for (int j = 0; j < noOfCriteria; j++) {
                String strDouble = String.format("%.3f", matrix[i][j]);
                result.append(strDouble);
                if (j < noOfCriteria - 1) {
                    result.append("\t");
                }
            }
            if (i < noOfCriteria - 1) {
                result.append("]\n");
            } else {
                result.append("]");
            }
        }
        result.append("\t]\n");
        System.out.println(result);
    }

    public double[][] createMatrix() {
        double[][] array = new double[noOfCriteria][noOfCriteria];
        for (int i = 0; i < noOfCriteria; i++) {
            for (int j = 0; j < noOfCriteria; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
            }
        }
        return array;
    }

    public void completeMatrix(List<Double> preferences) {
        for (int i = 0; i < noOfCriteria; i++) {
            for (int j = 0; j < noOfCriteria; j++) {
                if (i < j) {
                    matrix[i][j] = preferences.get(0);
                    preferences.remove(0);
                }
                if (i > j) {
                    matrix[i][j] = 1 / matrix[j][i];
                }
            }
        }
    }

    public void matrixCorrection(List<Double> sums) {
        for (int i = 0; i < noOfCriteria; i++) {
            for (int j = 0; j < noOfCriteria; j++) {
                matrix[i][j] /= sums.get(i);
            }
        }
    }

    public List<Double> getSumOfEach(boolean columnOrRow) {
        List<Double> sums = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < noOfCriteria; i++) {
            for (int j = 0; j < noOfCriteria; j++) {
                if (columnOrRow) {
                    sum = sum + matrix[j][i];
                } else {
                    sum = sum + matrix[i][j];
                }
            }
            sums.add(sum);
            sum = 0;
        }
        return sums;
    }

    public List<Double> getMeanOfEachRow() {
        List<Double> means = new ArrayList<>();
        for (Double sum : getSumOfEach(false)) {
            means.add(sum / noOfCriteria);
        }
        return means;
    }

    public List<Double> getWeights(List<Double> preferences) {
        completeMatrix(preferences);
        matrixCorrection(getSumOfEach(true));
        return getMeanOfEachRow();
    }



    public boolean checkJudgments(double consistencyRatio) {
        return consistencyRatio < 0.1;
    }

}
