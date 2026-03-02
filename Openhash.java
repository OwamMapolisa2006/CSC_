public class OpenHash {

    // These two arrays store the keys and their corresponding values
    private String[] keys;
    private String[] values;

    // m is the size of the table
    // size keeps track of how many elements are currently stored
    private int m;
    private int size;

    // Constructor – creates an empty hash table of size m
    public OpenHash(int m) {
        this.m = m;
        keys = new String[m + 1];   // we use indices 1..m
        values = new String[m + 1];
        size = 0;
    }

    // This method converts a key into a table index
    // We use Java's built-in hashCode and adjust it to fit inside [1..m]
    private int hash(String key) {
        return Math.abs(key.hashCode()) % m + 1;
    }

    // Insert a new key-value pair into the table
    // If a collision happens, we move forward (linear probing)
    public void insert(String key, String value) {

        int i = hash(key);

        // Keep moving forward until we find an empty spot
        // or the same key (in case we are updating)
        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i % m) + 1;
        }

        // If the slot was empty, increase the size count
        if (keys[i] == null) {
            size++;
        }

        keys[i] = key;
        values[i] = value;
    }

    // Search for a key and return its value
    // If we reach an empty slot, the key is not in the table
    public String lookup(String key) {

        int i = hash(key);

        while (keys[i] != null) {

            if (keys[i].equals(key)) {
                return values[i];
            }

            i = (i % m) + 1;
        }

        return null; // key not found
    }

    // Check if the table is completely full
    public boolean isFull() {
        return size == m;
    }

    // Check if the table has no elements
    public boolean isEmpty() {
        return size == 0;
    }
}