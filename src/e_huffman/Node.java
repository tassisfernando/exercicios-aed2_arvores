package e_huffman;

import java.util.Map;

public class Node implements Comparable<Node> {
    
    private char symbol;
    private int freq;
    
    private Node left;
    private Node right;
    
    public Node(char symbol) {
        this.symbol = symbol;
    }
    
    public Node(Node l, Node r) {
        this.symbol = '+';
        this.left = l;
        this.right = r;
    }
    
    public boolean isLeaf() {
        return (left == null && right == null);
    }
    
    public int getFrequency() {
        if (isLeaf()) {
            return freq;
        }
        return left.getFrequency() + right.getFrequency();
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    public Node getLeft() {
        return left;
    }
    
    public Node getRight() {
        return right;
    }
    
    public void add() {
        this.freq++;
    }
    
    @Override
    public String toString() {
        String ch = symbol == '\n' ? "\\n" : "" + symbol;
        
        return String.format("'%s': %d", ch, getFrequency());
    }
    
    public void fillCodeMap(Map<Character, String> codemap, String work) {
        if (isLeaf()) {
            codemap.put(getSymbol(), work);
            return;
        }
        left.fillCodeMap(codemap, work + "0");
        right.fillCodeMap(codemap, work + "1");
    }
    
    @Override
    public int compareTo(Node n) {
        return getFrequency() - n.getFrequency();
    }
    
}
