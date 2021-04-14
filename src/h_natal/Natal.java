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

public class Natal {

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(isr);
        try {
            int n = Integer.parseInt(bfr.readLine());

            HashMap<Branch, Branch> hTableBranchs = new HashMap<>();
            HashMap<Integer, Node> hTable = new HashMap<>();

            Node root = new Node(0, 0, 0, new LinkedList<Node>());
            hTable.put(0, root); //começa a raiz com 0

            for (int i = 0; i < (n - 1); i++) {
                String[] lin = bfr.readLine().split(" ");
                int idBranch = Integer.parseInt(lin[0]);
                int idA = Integer.parseInt(lin[1]);
                int idB = Integer.parseInt(lin[2]);
                int beauty = Integer.parseInt(lin[3]);

                Node noA = hTable.get(idA);
                Node noB = hTable.get(idB);

                if (noA == null) {
                    noA = new Node(idA, 0, 0, new LinkedList<>());
                    hTable.put(idA, noA);
                }
                if (noB == null) {
                    noB = new Node(idB, 0, 0, new LinkedList<>());
                    hTable.put(idB, noB);
                }

                Branch branch = new Branch(noA, noB, idBranch, beauty);
                hTableBranchs.put(new Branch(noA, noB, 0, 0), branch);
                noA.nodes.add(noB);
                noB.nodes.add(noA);
            }

            updateBeauties(root, hTableBranchs);
            Cut cuts = cut(root);
            System.out.println(cuts.beauty + " " + cuts.numCuts);
            Collections.sort(cuts.idBranchs);

            for (int i = 0; i < cuts.idBranchs.size() - 1; i++) {
                Integer value = cuts.idBranchs.get(i);
                System.out.print(value + " ");
            }
            int k = cuts.idBranchs.size() - 1;
            if (!cuts.idBranchs.isEmpty()) {
                System.out.println(cuts.idBranchs.get(k));
            }

        } catch (IOException ex) {
            System.out.println("----ERRO NA LEITURA----");
        }
    }

    public static Cut cut(Node root) {
        if (root == null) {
            return null;
        }
        Cut cut = new Cut();
        for (Node node : root.nodes) {
            Cut aux = analyzesCuts(node, root);
            cut.beauty += aux.beauty;
            cut.numCuts += aux.numCuts;
            cut.idBranchs.addAll(aux.idBranchs);
        }
        return cut;
    }

    public static Cut analyzesCuts(Node node, Node father) {
        if (node == null) {
            return null;
        }

        Cut cut = new Cut();
        List<Node> delete = new ArrayList<>();

        for (Node no : node.nodes) {
            if (!no.equals(father)) {
                if (isLeaf(no)) {
                    if (no.beauty <= 0) {
                        cut.numCuts++;
                        cut.idBranchs.add(no.idBranch);
                        delete.add(no);
                    } else {
                        cut.beauty += no.beauty;
                    }
                } else {
                    Cut aux = analyzesCuts(no, node);
                    cut.beauty += aux.beauty;
                    for (int i = 0; i < aux.idBranchs.size(); i++) {
                        cut.idBranchs.add(aux.idBranchs.get(i));
                    }
                    cut.numCuts += aux.numCuts;
                }
            }
        }

        for (Node no : delete) {
            node.nodes.remove(no);
        }

        if (node.beauty + cut.beauty <= 0) {
            cut.beauty = 0;
            cut.numCuts = 1;
            cut.idBranchs = new ArrayList<>();
            cut.idBranchs.add(node.idBranch);
        } else {
            cut.beauty += node.beauty;
        }
        return cut;
    }

    public static void updateBeauties(Node root, HashMap<Branch, Branch> hTable) {
        if (root != null && hTable != null) {
            for (Node node : root.nodes) {
                Branch branch = new Branch();
                branch.noA = root;
                branch.noB = node;
                branch = hTable.get(branch);
                node.beauty = branch.beauty;
                node.idBranch = branch.id;
                if (!isLeaf(node)) {
                    updateBeautiesFather(node, root, hTable);
                }
            }

        }
    }

    public static boolean isLeaf(Node node) {
        if (node != null) {
            return node.nodes.size() == 1;
        }
        return false;
    }

    public static void updateBeautiesFather(Node root, Node father, HashMap<Branch, Branch> hTable) {
        if (root != null && father != null && hTable != null) {
            for (Node node : root.nodes) {
                if (!node.equals(father)) {
                    Branch branch = new Branch();
                    branch.noA = root;
                    branch.noB = node;
                    branch = hTable.get(branch);
                    node.beauty = branch.beauty;
                    node.idBranch = branch.id;
                    if (!isLeaf(node)) {
                        updateBeautiesFather(node, root, hTable);
                    }
                }
            }
        }
    }

    private static class Branch {

        Node noA;
        Node noB;
        int id;
        int beauty;

        public Branch(Node noA, Node noB, int id, int beauty) {
            this.noA = noA;
            this.noB = noB;
            this.id = id;
            this.beauty = beauty;
        }

        public Branch() {

        }

        @Override
        public int hashCode() {
            if (noA != null && noB != null) {
                int h = 3;
                if (noA.id < noB.id) {
                    h = 59 * h + Objects.hashCode(this.noA);
                    h = 59 * h + Objects.hashCode(this.noB);
                } else {
                    h = 59 * h + Objects.hashCode(this.noB);
                    h = 59 * h + Objects.hashCode(this.noA);
                }
                return h;
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Branch) {
                Branch b = (Branch) o;
                if (!(noA.equals(b.noA) && noB.equals(b.noB))) {
                    return noA.equals(b.noB) && noB.equals(b.noA);
                }
                return true;
            }
            return false;
        }
    }

    private static class Cut {

        int beauty;
        int numCuts;
        List<Integer> idBranchs = new ArrayList<>();
    }

    public static class Node {

        int id;
        int idBranch;
        int beauty;
        List<Node> nodes;

        public Node(int id, int idBranch, int beauty, List<Node> nodes) {
            this.id = id;
            this.idBranch = idBranch;
            this.beauty = beauty;
            this.nodes = nodes;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Node) {
                Node node = (Node) o;
                return id == node.id;
            }
            return false;
        }
    }
}
