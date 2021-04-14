package e_huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {
    private Node root;
     
    public PriorityQueue<Node> countFreq(char[] symbols) {
        Map<Character, Node> count = new HashMap<>();
        
        for(char ch : symbols) {
            if(!count.containsKey(ch)) {
                count.put(ch, new Node(ch));
            }
            count.get(ch).add();
        }
        return new PriorityQueue<>(count.values());
    }
    
    public Node createTree(PriorityQueue<Node> nodes) {
        while(true) {
            Node n1 = nodes.poll();
            Node n2 = nodes.poll();
            
            Node parent = new Node(n1, n2);
            
            if(nodes.isEmpty()) {
                return parent;
            }
            
            nodes.add(parent);
        }
    }
    
    public char[] getChars(String text) {
        char[] symbols = new char[text.length()];
        text.getChars(0, text.length(), symbols, 0);
        return symbols;
    }
    
    public Map<Character, String> createCodeMap() {
        Map<Character, String> result = new TreeMap<>();
        root.fillCodeMap(result, "");
        return result;
    }
    
    public String encode(String text) {
        char[] symbols = getChars(text);
        root = createTree(countFreq(symbols));
        Map<Character, String> codemap = createCodeMap();
        
        StringBuilder data = new StringBuilder();
        
        for(char ch : symbols) {
            data.append(codemap.get(ch));
        }
        
        return data.toString();
    }
    
    public String decode(String data) {
        Node current = root;
        
        StringBuilder result = new StringBuilder();
        for(char ch : getChars(data)) {
            if(ch == '0') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            
            if(current.isLeaf()) {
                result.append(current.getSymbol());
                current = root;
            }            
        }
        return result.toString();
    }
}
