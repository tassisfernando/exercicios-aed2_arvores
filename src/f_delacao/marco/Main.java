package f_delacao.marco;

import java.util.Scanner;

/**
 *
 * @author Marco
 */
public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        
        long n = ler.nextInt();
        while (n < 3 || n > 100000) {
            n = ler.nextInt();
        }
        
         int k = ler.nextInt();
        while (k < 1 || k >= n) {
            k = ler.nextInt();
        }

        long[] mafi = new long[(int)n-1];
        for (int i = 0; i < n-1; i++) {
            mafi[i] = ler.nextLong();
        }
        DelacaoPremiada dp = new DelacaoPremiada();
        dp.Mafioso(mafi,k);
    }

}
