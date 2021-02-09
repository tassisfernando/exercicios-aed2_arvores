package monk;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Monk2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        List<Long> list = new LinkedList();

        for (int i = 0; i < length; i++) {
            list.add(input.nextLong());
        }

        check(list);
    }

    private static void check(List<Long> list) {
        long div = 100000;
        long ex = 1;
        long check;

        do {
            check = 0;
            for (Long aLong : list)
                check = check + (Math.abs((aLong % div / ex)));

            if (check > 0)
                radix(list, div, ex);

            div = div * 100000;
            ex = ex * 100000;

        } while (check != 0);
    }

    private static void radix(List<Long> list, long div, long ex) {
        int value = 10;
        LinkedList<Long>[] print = new LinkedList[19];
        for (int i = 0; i < print.length; i++) {
            print[i] = new LinkedList<>();

        }

        for (int j = 0; j < 5; j++) {
            for (Long aLong : list) {
                int s = (int) ((aLong % div / ex));
                s = (s % value / (value / 10));
                long y = aLong;

                print[s + 9].add(y);
            }
            list.clear();
            for (LinkedList<Long> longs : print) {
                int g = 0;
                for (int k = 0; k < longs.size() + g; k++) {
                    list.add(longs.pop());
                    g++;
                }

            }

            value = value * 10;
        }
        print(list);
        System.out.println("");
    }

    private static void print(List<Long> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i + 1 < list.size())
                System.out.print(" ");
        }
    }
}