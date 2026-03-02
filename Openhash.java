public class Openhash {
     private String[] keys;
    private String[] values;
    private int m;
    private int size;

    public OpenHash(int m) {
        this.m = m;
        keys = new String[m + 1];   // indices 1..m
        values = new String[m + 1];
        size = 0;
    }
    
}
