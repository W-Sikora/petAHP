package pl.wsikora.petahp.algorithms;

import java.util.*;

public class Ahp {
    private List<Double> list;
    private int dimension;
    private double[][] array;

    public Ahp(List<Double> list) {
        this.list = list;
    }

    public void initialize() {
        dimension = calcDimension();
        array = new double[dimension][dimension];
        complete();
    }

    private int calcDimension() {
        return (int) (1 + Math.sqrt(1 + 8 * list.size())) / 2;
    }

    public StringBuilder show1() {
        StringBuilder result = new StringBuilder("[ ");
        for (int i = 0; i < dimension ; i++) {
            if (i == dimension - 1) {
                result.append(Arrays.toString(array[i]));
            } else {
                result.append(Arrays.toString(array[i]) + ", ");
            }
        }
        result.append(" ]");
        return result;
    }

    public StringBuilder show2() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < dimension; i++) {
            result.append("\t[");
            for (int j = 0; j < dimension; j++) {
                result.append(array[i][j]);
                if (j < dimension - 1) {
                    result.append(",\t");
                }
            }
            if (i < dimension - 1) {
                result.append("],\n");
            } else {
                result.append("]");
            }
        }
        result.append("\t]\n");
        return result;
    }

    private void complete() {
        List<Double> copy = new ArrayList<>(list);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
                if (i < j) {
                    array[i][j] = copy.get(0);
                    copy.remove(0);
                }
                if (i > j) {
                    array[i][j] = 1 / array[j][i];
                }
            }
        }
    }

    private double[] rowsColumnsSums(double[][] arr, boolean rowsOrColumns) {
        double[][] doubles = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            doubles[i] = arr[i].clone();
        }

        double[] totalSums = new double[doubles.length];
        for (int i = 0; i < doubles.length; ++i) {
            double sum = 0;
            for (int j = 0; j < doubles[0].length; ++j) {
                if (rowsOrColumns) {
                    sum += doubles[i][j];
                } else {
                    sum += doubles[j][i];
                }
            }
            totalSums[i] = sum;
        }
        return totalSums;
    }

    public double[] calcWeights() {
        double[][] doubles = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            doubles[i] = array[i].clone();
        }

        double[] weights = new double[dimension];
        double[] columnsSums = rowsColumnsSums(array, false);

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                doubles[i][j] /= columnsSums[j];
            }
        }

        double[] rowsSums = rowsColumnsSums(doubles, true);

        for (int i = 0; i < dimension; i++) {
            weights[i] = rowsSums[i] / dimension;
        }
        return weights;
    }

    public double calcLambdaMax() {
        double[] weights = calcWeights();
        double[][] doubles = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            doubles[i] = array[i].clone();
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension ; j++) {
                doubles[i][j] *= weights[j];
            }
        }
        double[] lambdas = rowsColumnsSums(doubles, true);
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += lambdas[i] / weights[i];
        }
        return sum / dimension;
    }

    public double calcConsistencyIndex() {
        return (calcLambdaMax() - dimension) / (dimension - 1);
    }

    public double calcConsistencyRatio() {
        double[] randomIndex = {0.,0.,0.58,0.90,1.12,1.24,1.32,1.41,1.45,1.49,1.51,1.54,1.56,1.57,1.58};
        return calcConsistencyIndex() / randomIndex[dimension - 1];
    }

    public boolean examineConsistency() {
        return calcConsistencyRatio() < 0.1;
    }

    public StringBuilder summarizeResults() {
        StringBuilder result = new StringBuilder("Ahp summary {");
        result.append("\n\tweights = " + Arrays.toString(calcWeights()));
        result.append(",\n\tlambda max = " + calcLambdaMax());
        result.append(",\n\tconsistency index = " + calcConsistencyIndex());
        result.append(",\n\tconsistency ratio = " + calcConsistencyRatio());
        result.append(",\n\tis consistent = " + examineConsistency());
        result.append("\n}");
        return result;
    }

    @Override
    public String toString() {
        return "Ahp {" +
                "\n\tlist of preferences = " + list.toString() +
                ",\n\tdimension = " + dimension + " x " + dimension +
                ",\n\tarray = " + show1() +
                "\n}";
    }

}
