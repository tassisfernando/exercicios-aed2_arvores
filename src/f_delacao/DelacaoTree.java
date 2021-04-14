package f_delacao;

import java.util.Scanner;

public class DelacaoTree {

    /*
8 2
1 1 2 3 4 4 6

10 3
1 1 2 2 3 3 4 4 5

     */
    private static Node root = new Node(1);
    private static int cont = 0;

    private static class Node {

        private int value;
        private Node left, right, parent;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getRight() {
            return right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

    }

    private static void inserir(int value) {
        inserir(root, value);
    }

    private static void inserir(Node node, int value) {
        if (value != node.getValue()) {
            if (value < node.getValue()) {
                if (node.getLeft() != null) {
                    inserir(node.getLeft(), value);
                } else {
                    node.setLeft(new Node(value));
                    node.getLeft().setParent(node);
                }
            } else {
                if (node.getRight() != null) {
                    inserir(node.getRight(), value);
                } else {
                    node.setRight(new Node(value));
                    node.getRight().setParent(node);
                }
            }
        }
    }

    public static void redSearch(Node node) {
        if (node != null) {
            cont++;
            redSearch(node.getLeft());
            redSearch(node.getRight());
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int k = scn.nextInt();
        n--;

        int[] v = new int[n];

        for (int i = 0; i < v.length; i++) {
            v[i] = scn.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            inserir(v[i]); //insere ao contrário
        }

        redSearch(root);
        int total = k + cont;
        System.out.println(total);
    }

}
