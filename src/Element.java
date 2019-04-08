public class Element {
    private String name;
    private int weight;
    private int value;

    public Element (String n, int w, int v){
        weight = w;
        value = v;
        name = n;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
