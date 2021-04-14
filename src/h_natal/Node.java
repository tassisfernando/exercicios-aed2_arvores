package h_natal;

import java.util.List;

public class Node {

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
