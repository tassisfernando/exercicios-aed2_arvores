package f_delacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Delacao {

    /*
    8 2
    1 1 2 3 4 4 6
    
    10 3
    1 1 2 2 3 3 4 4 5
     */
    static final int MAXN = 100000;

    static int[][] adj; //aqui !!!!!!
    static int[] prox = new int[MAXN];
    static int[] qtd = new int[MAXN];

    static int[] validos = new int[MAXN];
    static int cont = 0;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int k = scn.nextInt();

        adj = new int[MAXN][n];

        int pos = 0;
        for (int i = 1; i < n; i++) {
            int in = scn.nextInt();
            adj[in - 1][pos] = i; //aqui !!!!!!
            pos++;
        }

        buscaProfund(0);
        dfsCalc(0, true);

        if (cont < k) {
            k = cont;
        }

        //ordenar validos
        countSort(validos);

        int res = 0;

        for (int i = 0; i < k; i++) {
            res += validos[i];
        }
        System.out.println(res);
    }

    static void countSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void buscaProfund(int u) {
        int foco;
        prox[u] = -1;
        qtd[u] = 0;

        for (int i = 0; i < adj[u].length; i++) { //aqui !!!!!!
            foco = adj[u][i]; //aqui !!!!!!!!
            buscaProfund(foco);

            if (qtd[u] < qtd[foco]) {
                qtd[u] = qtd[foco];
                prox[u] = foco;
            }
        }

        qtd[u]++;
    }

    public static void dfsCalc(int u, boolean type) {
        int foco;

        if (type) {
            validos[cont++] = qtd[u];
        }
        if (adj[u] != null) {
            for (int i = 0; i < adj[u].length; i++) {
                foco = adj[u][i];
                dfsCalc(foco, (foco != prox[u]));
            }
        }

    }

}
