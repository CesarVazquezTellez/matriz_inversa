import java.io.*;
import java.util.*;

public class MatrizInversaFile {

    private static final String BASE_PATH = "C:\\archivos\\";

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Ingresa el nombre del archivo de entrada:");
            String inputFile = br.readLine();

            System.out.println("Ingresa el nombre del archivo de salida:");
            String outputFile = br.readLine();

            String inputPath = BASE_PATH + inputFile;
            String outputPath = BASE_PATH + outputFile;

            double[][] matriz = readMatrixFromFile(inputPath);
            double[][] inversa = invertMatrix(matriz);

            writeMatrixToFile(outputPath, matriz, inversa);
            printMatrixToConsole(matriz, inversa);

            System.out.println("Archivo generado: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double[][] readMatrixFromFile(String filename) throws IOException {
        List<double[]> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            double[] row = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                row[i] = Double.parseDouble(parts[i]);
            }
            rows.add(row);
        }

        br.close();
        return rows.toArray(new double[0][]);
    }

    public static double[][] invertMatrix(double[][] A) {
        int n = A.length;

        for (double[] row : A)
            if (row.length != n)
                throw new IllegalArgumentException("La matriz no es cuadrada.");

        double[][] aug = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, aug[i], 0, n);
            aug[i][n + i] = 1;
        }

        for (int i = 0; i < n; i++) {
            double p = aug[i][i];
            if (p == 0) throw new ArithmeticException("La matriz no tiene inversa.");

            for (int j = 0; j < 2 * n; j++)
                aug[i][j] /= p;

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aug[k][i];
                    for (int j = 0; j < 2 * n; j++)
                        aug[k][j] -= factor * aug[i][j];
                }
            }
        }

        double[][] inv = new double[n][n];

        for (int i = 0; i < n; i++)
            System.arraycopy(aug[i], n, inv[i], 0, n);

        return inv;
    }

    public static void writeMatrixToFile(String filename, double[][] original, double[][] inverse) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        bw.write("Matriz original:\n");
        for (double[] row : original) {
            for (double v : row) bw.write(v + " ");
            bw.write("\n");
        }

        bw.write("\nMatriz inversa:\n");
        for (double[] row : inverse) {
            for (double v : row) bw.write(v + " ");
            bw.write("\n");
        }

        bw.close();
    }

    public static void printMatrixToConsole(double[][] original, double[][] inverse) {
        System.out.println("Matriz original:");
        for (double[] r : original) {
            for (double v : r) System.out.print(v + " ");
            System.out.println();
        }

        System.out.println("\nMatriz inversa:");
        for (double[] r : inverse) {
            for (double v : r) System.out.print(v + " ");
            System.out.println();
        }
    }
}
