package h_natal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NatalAntigo {

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        int n = Integer.parseInt(in.readLine());

        HashMap<Integer, Node> hashtable = new HashMap<>();
        HashMap<Galho, Galho> hashtableDeGalhos = new HashMap<>();

        Node root = new Node(0, 0, new LinkedList<Node>(), 0);
        hashtable.put(0, root);

        //recebe e preenche a arvore
        for (int i = 0; i < n - 1; i++) {
            String[] line = in.readLine().split(" ");
            int idGalho = Integer.parseInt(line[0]);
            int a = Integer.parseInt(line[1]);
            int b = Integer.parseInt(line[2]);
            int beleza = Integer.parseInt(line[3]);
            Node nodeA = hashtable.get(a);
            Node nodeB = hashtable.get(b);
            if (nodeA == null) {
                nodeA = new Node(0, 0, new LinkedList<>(), a);
                hashtable.put(a, nodeA);
            }
            if (nodeB == null) {
                nodeB = new Node(0, 0, new LinkedList<>(), b);
                hashtable.put(b, nodeB);
            }
            Galho g = new Galho(nodeA, nodeB, idGalho, beleza);
            hashtableDeGalhos.put(new Galho(nodeA, nodeB, 0, 0), g);
            nodeA.nos.add(nodeB);
            nodeB.nos.add(nodeA);
        }
        
        //AQUI
        atualizaBelezas(root, hashtableDeGalhos);
        Corte cortes = corta(root);
        int i = 0;
        System.out.println(cortes.beleza + " " + cortes.numCortes);
        Collections.sort(cortes.idGalhos);
        for (; i < cortes.idGalhos.size() - 1; i++) {
            Integer value = cortes.idGalhos.get(i);
            System.out.print(value + " ");
        }
        if (!cortes.idGalhos.isEmpty()) {
            System.out.println(cortes.idGalhos.get(i));
        }
    }

    public static void atualizaBelezas(Node root, HashMap<Galho, Galho> hashtable) {
        if (root != null) {
            for (Node no : root.nos) {
                Galho g = new Galho();
                g.nodeA = root;
                g.nodeB = no;
                g = hashtable.get(g);
                no.beleza = g.beleza;
                no.idGalho = g.idGalho;
                if (!isFolha(no)) {
                    atualizaBelezas(no, root, hashtable);
                }
            }

        }
    }

    public static void atualizaBelezas(Node root, Node father, HashMap<Galho, Galho> hashtable) {
        if (root != null) {
            for (Node no : root.nos) {
                if (!no.equals(father)) {
                    Galho g = new Galho();
                    g.nodeA = root;
                    g.nodeB = no;
                    g = hashtable.get(g);
                    no.beleza = g.beleza;
                    no.idGalho = g.idGalho;
                    if (!isFolha(no)) {
                        atualizaBelezas(no, root, hashtable);
                    }
                }
            }
        }
    }

    public static Corte corta(Node root) {
        Corte corte = new Corte();
        for (Node no : root.nos) {
            Corte aux = analisaCortes(no, root);
            corte.beleza += aux.beleza;
            corte.numCortes += aux.numCortes;
            corte.idGalhos.addAll(aux.idGalhos);
        }
        return corte;

    }

    public static Corte analisaCortes(Node node, Node pai) {
        if (node != null) {
            Corte corte = new Corte();
            List<Node> excluir = new ArrayList<>();
            for (Node no : node.nos) {
                if (!no.equals(pai)) {
                    if (isFolha(no)) {
                        if (no.beleza <= 0) {
                            corte.numCortes++;
                            corte.idGalhos.add(no.idGalho);
//                            node.nos.remove(no);
                            excluir.add(no);
                        } else {
                            corte.beleza += no.beleza;
                        }
                    } else {
                        Corte aux = analisaCortes(no, node);
                        corte.beleza += aux.beleza;
                        for (int i = 0; i < aux.idGalhos.size(); i++) {
                            corte.idGalhos.add(aux.idGalhos.get(i));
                        }
                        corte.numCortes += aux.numCortes;
                    }
                }
            }
            
            for (Node aux : excluir) {
                node.nos.remove(aux);
            }
            if ((node.beleza + corte.beleza) <= 0) {
                corte.beleza = 0;
                corte.numCortes = 1;
                corte.idGalhos = new ArrayList<>();
                corte.idGalhos.add(node.idGalho);
            } else {
                corte.beleza += node.beleza;
            }
            return corte;
        }
        return null;
    }

    public static boolean isFolha(Node node) {
        if (node != null) {
            return node.nos.size() == 1;
        }
        return false;
    }

    private static class Node {

        int idGalho;
        int idNode;
        int beleza;
        List<Node> nos;

        public Node(int idGalho, int beleza, List<Node> nos, int idNode) {
            this.idGalho = idGalho;
            this.beleza = beleza;
            this.nos = nos;
            this.idNode = idNode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return idNode == node.idNode;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return idNode;
        }

    }

    private static class Corte {

        int beleza;
        int numCortes;
        ArrayList<Integer> idGalhos = new ArrayList<>();

    }

    private static class Galho {

        Node nodeA;
        Node nodeB;
        int idGalho;
        int beleza;

        public Galho(Node nodeA, Node nodeB, int idGalho, int beleza) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.idGalho = idGalho;
            this.beleza = beleza;
        }

        public Galho() {
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Galho) {
                Galho g = (Galho) obj;
                if (!(nodeA.equals(g.nodeA) && nodeB.equals(g.nodeB))) {
                    return ((nodeA.equals(g.nodeB) && nodeB.equals(g.nodeA)));
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.nodeA != null && this.nodeB != null) {
                int hash = 3;
                if (this.nodeA.idNode < this.nodeB.idNode) {
                    hash = 59 * hash + Objects.hashCode(this.nodeA);
                    hash = 59 * hash + Objects.hashCode(this.nodeB);
                } else {
                    hash = 59 * hash + Objects.hashCode(this.nodeB);
                    hash = 59 * hash + Objects.hashCode(this.nodeA);
                }
                return hash;
            }
            return 0;

        }

    }

}
