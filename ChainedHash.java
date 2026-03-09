 class Node {
    String key;
    String value;
    Node next;

    Node(String k, String v) {
        key = k;
        value = v;
        next = null;
    }
}

public class ChainedHash {

    private Node[] table;
    private int m;

    public ChainedHash(int m) {
        this.m = m;
        table = new Node[m + 1];
    }
     private int hash(String key) {
        return Math.abs(key.hashCode()) % m + 1;
    }

    public void insert(String key, String value) {

        int i = hash(key);
        Node current = table[i];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = table[i];
        table[i] = newNode;
    }

    public String lookup(String key) {

        int i = hash(key);
        Node current = table[i];

        while (current != null) {
            if (current.key.equals(key))
                return current.value;
            current = current.next;
        }

        return null;
    }
}
