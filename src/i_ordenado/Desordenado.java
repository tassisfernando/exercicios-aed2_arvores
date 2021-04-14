package i_ordenado;

import java.util.Scanner;

public class Desordenado {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        long[] vet = new long[n];
        for (int i = 0; i < n; i++) {
            vet[i] = scn.nextLong();
        }

        System.out.println("V = " + showArray(vet));
        vet = ordena(vet);
        System.out.println("O = " + showArray(vet));
    }

    public static long[] ordena(long[] vet) {
        long aux;

        if (vet.length % 2 == 0) {
            for (int j = vet.length - 2; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    if (i % 2 == 0) {
                        if (vet[i] > vet[i + 2]) {
                            aux = vet[i];
                            vet[i] = vet[i + 2];
                            vet[i + 2] = aux;
                        }
                    } else {
                        if (vet[i] < vet[i + 2]) {
                            aux = vet[i];
                            vet[i] = vet[i + 2];
                            vet[i + 2] = aux;
                        }
                    }
                }
            }
        } else {
            for (int j = vet.length - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    if (i % 2 == 0) {
                        if (vet[i] > vet[i + 2]) {
                            aux = vet[i];
                            vet[i] = vet[i + 2];
                            vet[i + 2] = aux;
                        }
                    } else {
                        if (i+2 < vet.length && vet[i] < vet[i + 2]) {
                            aux = vet[i];
                            vet[i] = vet[i + 2];
                            vet[i + 2] = aux;
                        }
                    }
                }
            }
        }

        return vet;
    }

    public static String showArray(long[] vet) {
        StringBuilder v = new StringBuilder("{ ");
        for (int i = 0; i < vet.length; i++) {
            if (i == vet.length - 1) {
                v.append(vet[i] + "}");
            } else {
                v.append(vet[i] + ", ");
            }
        }
        return v.toString();
    }
}
