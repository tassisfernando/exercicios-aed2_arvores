package e_huffman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String text = scn.nextLine().trim();
        Huffman tree = new Huffman();
        String encoded = tree.encode(text);

        int normalSize = text.getBytes().length * 8;
        int compressedSize = encoded.length();

        //System.out.println("Normal size: " + normalSize);
        //System.out.println("Compressed size: " + compressedSize);

        int total = normalSize + compressedSize - 4;
        System.out.println(total + " bits");

        /*
        System.out.println("Encoded data:");
        System.out.println(encoded);
        System.out.println();
        System.out.println("Decoded text:");
        System.out.println(tree.decode(encoded));
         */
    }
}
