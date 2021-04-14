package e_huffman;

import java.util.PriorityQueue;
import java.util.Scanner;

public abstract class HuffmanTree implements Comparable<HuffmanTree> {

    public final int frequency;

    public HuffmanTree(int freq) {
        this.frequency = freq;
    }

    public static void main(String[] args) {
        Main.main(null);
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return (frequency - tree.frequency);
    }

    static class Node extends HuffmanTree {

        public final HuffmanTree left, right;

        public Node(HuffmanTree l, HuffmanTree r) {
            super(l.frequency + r.frequency);
            left = l;
            right = r;
        }
    }

    static class Leaf extends HuffmanTree {

        public final char value;

        public Leaf(int freq, char val) {
            super(freq);
            value = val;
        }
    }

    static class Main {

        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
            String string = scn.next();

            int[] charFreqs = new int[256];
            for (char c : string.toCharArray()) {
                charFreqs[c]++;
            }

            //árvore de compactação
            HuffmanTree tree = buildCompactTree(charFreqs);

            System.out.println("Códigos");
            System.out.println("Símbolo\t-\tQuantidade\t-\tCódigo Huffman");
            printCodes(tree, new StringBuffer());

            //Compacta o texto
            String encode = encode(tree, string);

            System.out.println("\nTexto Compactado:");
            System.out.println(encode);

            System.out.println("\n\nTexto Decodificado");
            System.out.println(decode(tree, encode));
        }

        public static HuffmanTree buildCompactTree(int[] charFreqs) {
            PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();

            for (int i = 0; i < charFreqs.length; i++) {
                if (charFreqs[i] > 0) {
                    trees.offer(new Leaf(charFreqs[i], (char) i));
                }
            }

            while (trees.size() > 1) {
                HuffmanTree a = trees.poll();
                HuffmanTree b = trees.poll();

                trees.offer(new Node(a, b));
            }

            return trees.poll();
        }

        public static String encode(HuffmanTree tree, String encode) {
            assert tree != null;

            String encodeText = "";

            for (char c : encode.toCharArray()) {
                encodeText += getCodes(tree, new StringBuffer(), c);
            }

            return encodeText;
        }

        public static String getCodes(HuffmanTree tree, StringBuffer prefix, char c) {
            assert tree != null;

            if (tree instanceof Leaf) {
                Leaf leaf = (Leaf) tree;

                if (leaf.value == c) {
                    return prefix.toString();
                }
            } else if (tree instanceof Node) {
                Node node = (Node) tree;

                prefix.append('0');
                String left = getCodes(node.left, prefix, c);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                String right = getCodes(node.right, prefix, c);
                prefix.deleteCharAt(prefix.length() - 1);

                return (left == null) ? right : left; //OLHAR ISSO
            }
            return null;
        }

        public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
            assert tree != null;
            
            if(tree instanceof Leaf) {
                Leaf leaf = (Leaf) tree;
                
                System.out.println(leaf.value + "\t\t\t" + leaf.frequency + "\t\t\t" + prefix);
            } else if (tree instanceof Node) {
                Node node = (Node) tree;
                
                prefix.append('0');
                printCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
                
                prefix.append('1');
                printCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        public static String decode(HuffmanTree tree, String encode) {
            assert tree != null;

            String decodeText = "";
            Node node = (Node) tree;

            for (char code : encode.toCharArray()) {
                if (code == '0') {
                    if (node.left instanceof Leaf) {
                        decodeText += ((Leaf) node.left).value;
                        node = (Node) tree;
                    } else {
                        node = (Node) node.left;
                    }
                } else if (code == '1') {
                    if (node.right instanceof Leaf) {
                        decodeText += ((Leaf) node.right).value;
                        node = (Node) tree;
                    } else {
                        node = (Node) node.right;
                    }
                }
            }
            return decodeText;
        }
    }
}
