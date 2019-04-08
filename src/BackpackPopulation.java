import java.util.*;

public class BackpackPopulation {
    private Backpack[] population;
    private int size;
    private int generation = 0;
    private int chance;
    private int same;
    private ArrayList<Integer> firstVals = new ArrayList<>();
    public BackpackPopulation(int s, int chanceOfMutation){
        size = s;
        chance = chanceOfMutation;
        population = new Backpack[size];
        for (int i = 0; i < size; i++) {
            population[i] = new Backpack();
        }
    }



    public void calcScores(){
        for (int i = 0; i < size; i++) {
            population[i].calcScore();
        }
    }

    public void sort(){
        List<Backpack> list = Arrays.asList(population);
        Collections.sort(list, new Comparator<Backpack>() {
            @Override
            public int compare(Backpack o1, Backpack o2) {
                return o2.getScore() - o1.getScore();
            }
        });
    }

    public void display(){
        System.out.println("--------------------------");
        System.out.println("GENERATION " + generation);
        System.out.println("--------------------------");
        System.out.println();
//        for (int i = 0; i < population.length; i++) {
//            System.out.println("Backpack " + i);
//            System.out.println("Weight: " + population[i].getWeight() + " Score: " + population[i].getScore());
//            System.out.println("{");
//            for (int j = 0; j < population[0].getActive().length; j++) {
//                System.out.print(" " + population[i].getActive()[j]);
//            }
//            System.out.println("}");
//            System.out.println();
//        }
        System.out.println("Weight: " + population[0].getWeight() + " Score: " + population[0].getScore());
    }
    public void generation(){
        generation++;
        calcScores();
        for (int i = 0; i < size/3; i+=2) {
            Backpack[] kids = population[i].mate(population[i + 1]);
            population[size - i*2 - 2] = kids[0];
            population[size - i*2 - 1] = kids[1];
        }

        for (int i = 0; i < size; i++) {
            population[i].mutate(chance);
        }
        calcScores();
        sort();
//        display();
        firstVals.add(population[0].getScore());
        if (generation > 1 && firstVals.get(firstVals.size() - 1).equals(firstVals.get(firstVals.size() - 2)))
            same++;
        else
            same = 0;
        if (same == 50){
//            System.out.println("Completed after " + generation + " generations");
            System.out.println("Score: " + population[0].getScore() + " Weight: "  + population[0].getWeight());
        }
        else
            generation();

    }






}
