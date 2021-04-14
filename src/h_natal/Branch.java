package h_natal;

import java.util.Objects;

public class Branch {

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
