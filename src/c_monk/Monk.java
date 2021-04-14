package c_monk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Monk {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<Long> vet = new ArrayList();

        for (int i = 0; i < n; i++) {
            vet.add(scn.nextLong());
        }

        ordena(vet);
    }

    private static void ordena(List<Long> list) {
        long valueD = 100000;
        long aux = 1;
        long control;

        do {
            control = 0;
            for (Long aLong : list) {
                control = control + (Math.abs((aLong % valueD / aux)));
            }

            if (control > 0) {
                radixAlg(list, valueD, aux);
            }

            aux = aux * 100000;
            valueD = valueD * 100000;

        } while (control != 0);
    }

    private static void radixAlg(List<Long> vet, long valueD, long aux) {
        int lav = 10;
        LinkedList<Long>[] ordenaAux = new LinkedList[19];
        for (int i = 0; i < ordenaAux.length; i++) {
            ordenaAux[i] = new LinkedList();

        }

        int k = 0;

        while (k < 5) {
            for (Long atualN : vet) {
                int p = (int) ((atualN % valueD / aux));
                p = (p % lav / (lav / 10));
                long num = atualN;
                ordenaAux[p + 9].add(num);
            }
            vet.clear();
            for (LinkedList<Long> vAtual : ordenaAux) {
                int cont = 0;
                int j = 0;
                while (j < vAtual.size() + cont) {
                    vet.add(vAtual.pop());
                    cont++;
                    j++;
                }
            }

            lav = lav * 10;
            k++;
        }
        exibe(vet);
    }

    //exibe a lista
    private static void exibe(List<Long> vet) {
        for (int i = 0; i < vet.size(); i++) {
            System.out.print(vet.get(i));

            //Dá o espaço só se não for o último elemento
            if (i + 1 < vet.size()) {
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }
}
