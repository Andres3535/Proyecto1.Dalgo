import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class ProblemaP1 {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int casos = Integer.parseInt(lector.readLine().trim());
        for (int t = 0; t < casos; t++) {
            String[] datos = lector.readLine().trim().split(" ");
            int total = Integer.parseInt(datos[0]);
            int requeridos = Integer.parseInt(datos[1]);
            int maxSwaps = Integer.parseInt(datos[2]);
            int[] energias = new int[total];
            for (int i = 0; i < total; i++) {
                energias[i] = Integer.parseInt(datos[i + 3]);
            }
            System.out.println(calcularMinimo(energias, requeridos, maxSwaps));
        }
    }

    public static int calcularMinimo(int[] energias, int requeridos, int maxSwaps) {
        int n = energias.length;

        int[][] dp = new int[requeridos + 1][maxSwaps + 1];
        for (int k = 0; k <= requeridos; k++) {
            Arrays.fill(dp[k], INF);
        }
        dp[0][0] = 0;

        for (int pos = 0; pos < n; pos++) {

            int[][] nuevo = new int[requeridos + 1][maxSwaps + 1];
            for (int k = 0; k <= requeridos; k++) {
                System.arraycopy(dp[k], 0, nuevo[k], 0, maxSwaps + 1);
            }

            for (int k = 0; k < requeridos; k++) {
                for (int s = 0; s <= maxSwaps; s++) {
                    if (dp[k][s] == INF)
                        continue;

                    int costoExtra = pos - k;
                    if (s + costoExtra <= maxSwaps) {
                        nuevo[k + 1][s + costoExtra] = Math.min(
                                nuevo[k + 1][s + costoExtra],
                                dp[k][s] + energias[pos]);
                    }
                }
            }
            dp = nuevo;
        }

        int minimo = INF;
        for (int s = 0; s <= maxSwaps; s++) {
            minimo = Math.min(minimo, dp[requeridos][s]);
        }
        return minimo;
    }
}
