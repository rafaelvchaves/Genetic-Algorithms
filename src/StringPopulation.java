public class StringPopulation {

    private String goal;
    private StringChromosome[] population;
    private int[] costs;
    private String random;
    private int generation = 0;
    private int finalGeneration = 0;
    private int chance;


    public StringPopulation(String goal, int size, int chanceOfMutation) {
        this.goal = goal;
        chance = chanceOfMutation;
        population = new StringChromosome[size];
        for (int c = 0; c < size; c++) {
            for (int s = 0; s < goal.length(); s++) {
                random += (char) (int) (Math.random() * 255);
            }
            population[c] = new StringChromosome(random);
            random = "";
        }
        costs = new int[size];
    }

    public void sort(int arr[]) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            StringChromosome key2 = population[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                population[j + 1] = population[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            population[j + 1] = key2;
        }
    }

    public void display() {
        System.out.println("--------------------------");
        System.out.println("GENERATION " + generation);
        System.out.println("--------------------------");
        System.out.println();
        for (int i = 0; i < population.length; i++) {
            System.out.println("StringChromosome " + i);
            System.out.println("Code Length: " + population[i].code().length() + " Code: " + population[i].code() + " Cost: " + population[i].getCost());
            System.out.println();
        }
    }

    public void generation() {
        generation++;
        int size = population.length;

        for (int i = 0; i < size / 2 - 1; i++) {
            StringChromosome[] kids = population[i].mate(population[i + 1]);
            population[i + size / 2] = kids[0];
            population[i + size / 2 + 1] = kids[1];
        }

        for (int i = 0; i < size; i++) {
            population[i].mutate(chance);
        }
        for (int i = 0; i < size; i++) {
            population[i].CalcCost(goal);
            costs[i] = population[i].getCost();
        }
        sort(costs);
        display();
        if (population[0].getCost() != 0) {
            generation();
        }
        else {
//            System.out.println("Completed after " + generation + " generations");
            finalGeneration = generation;
        }

    }
    public int totalGenerations(){
        return finalGeneration;
    }

}
