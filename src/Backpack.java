import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Backpack {
    private int weight, score;
    private int size;
    private static ArrayList<Element> elementList;
    private boolean[] active;
    private int maxWeight = 1000;


    public Backpack() {
        loadElements();
        size = elementList.size();
        active = new boolean[size];
        for (int i = 0; i < size; i++) {
            if ((int)(Math.random()*20) == 0)
                active[i] = true;
        }

    }

    public Backpack(boolean[] a){
        loadElements();
        size = elementList.size();
        active = a;
    }

    public void mutate(int chance) {
        int index;
        if ((int)(Math.random()*100) < chance) {
            index = (int) (Math.random() * size);
            if (active[index])
                active[index] = false;
            else
                active[index] = true;
        }
    }

    public void calcScore() {
        score = 0;
        weight = 0;
        for (int i = 0; i < size; i++) {
            if (active[i]) {
                weight += elementList.get(i).getWeight();
                score += elementList.get(i).getValue();
            }
        }
        if (weight > maxWeight)
            score =- (weight - maxWeight) * 50;
    }

    public int getScore() {
        return score;
    }

    public int getWeight() {
        return weight;
    }

    public Backpack[] mate(Backpack other) {
        int pivot = (int) (Math.random() * size);
        boolean[] b1 = new boolean[size];
        boolean[] b2 = new boolean[size];
        for (int i = 0; i < pivot; i++) {
            b1[i] = active[i];
            b2[i] = other.getActive()[i];
        }
        for (int i = pivot; i < size; i++) {
            b1[i] = other.getActive()[i];
            b2[i] = active[i];
        }
        Backpack[] children = {new Backpack(b1), new Backpack(b2)};
        return children;

    }


    public boolean[] getActive() {
        return active;
    }

    public static void loadElements() {
        elementList = new ArrayList<Element>();
        try (BufferedReader br = new BufferedReader(new FileReader("./src/Elements.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] arr = sCurrentLine.split(" ");
                elementList.add(new Element(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
