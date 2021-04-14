package f_delacao;

import java.util.*;

public class DelacaoNovo {

    private static final int MAXN = 100000;

    private static ArrayList<Integer>[] adj = new ArrayList[MAXN];
    private static int[] prox = new int[MAXN];
    private static int[] qtd = new int[MAXN];

    private static int[] validos = new int[MAXN];
    private static int cont = 0;

    public static void main(String[] args) {
        int n, k, in;

        inicializa();

        Scanner scn = new Scanner(System.in);
        n = scn.nextInt(); //número de membros da Máfia
        k = scn.nextInt(); //número de membros que o vidente consegue

        for (int i = 1; i < n; i++) {
            in = scn.nextInt();
            adj[in - 1].add(i);
        }
        
        dfsCompute(0); 
        dfsCalc(0, true);

        if (cont < k) {
            k = cont;
        }

        countingSort(validos, cont);
        int res = 0;
        for (int i = 0; i < k; i++) {
            res += validos[i];
        }

        System.out.print(res);
        System.out.print("\n");
    }

    private static void dfsCompute(int u) {
        int foco;
        prox[u] = -1;
        qtd[u] = 0;

        for (int k = 0; k < adj[u].size(); k++) {
            foco = adj[u].get(k);
            dfsCompute(foco);
            if (qtd[u] < qtd[foco]) {
                qtd[u] = qtd[foco];
                prox[u] = foco;
            }
        }
        qtd[u] = qtd[u] + 1;
    }

    private static void dfsCalc(int u, boolean type) {
        int foco;
        if (type) {
            validos[cont++] = qtd[u];
        }

        for (int k = 0; k < adj[u].size(); k++) {
            foco = adj[u].get(k);
            dfsCalc(foco, (foco != prox[u]));
        }
    }

    private static void countingSort(int[] vet, int n) {
        int[] contAux = new int[MAXN];
        int[] salv = new int[MAXN];

        for (int i = 0; i < MAXN; i++) {
            contAux[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            contAux[vet[i]]++;
            salv[i] = vet[i];
        }

        for (int i = MAXN - 2; i >= 0; i--) {
            contAux[i] += contAux[i + 1];
        }

        for (int i = 0; i < n; i++) {
            vet[contAux[salv[i]] - 1] = salv[i];
            contAux[salv[i]]--;
        }
    }

    public static void inicializa() {
        for (int i = 0; i < MAXN; i++) {
            adj[i] = new ArrayList<>();
        }
    }
}
