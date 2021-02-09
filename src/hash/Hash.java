package hash;

import java.util.Scanner;

public class Hash {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int cont = 1;

        while (cont <= n) {
            int m = scn.nextInt();
            String[] hash = new String[m];
            for (int i = 0; i < m; i++) {
                hash[i] = "";
            }

            int c = scn.nextInt();
            for (int i = 1; i <= c; i++) {
                int input = scn.nextInt();
                int pos = input % m;
                hash[pos] += input + " -> ";
            }

            for (int i = 0; i < m; i++) {
                System.out.println(i + " -> " + hash[i] + " \\");
            }
            
            System.out.println("");
            cont++;
        }

    }
}
