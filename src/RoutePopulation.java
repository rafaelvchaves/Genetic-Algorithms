import java.util.*;

public class RoutePopulation {
    private ArrayList<Route> population;
    private int size;
    private int generation = 0;
    private int chance;
//    private int same;
//    private ArrayList<Double> firstVals = new ArrayList<>();

    public RoutePopulation(int size, int chanceOfMutation) {
        this.size = size;
        chance = chanceOfMutation;
        population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            population.add(new Route());
        }
    }

    public void calcDistances(){
        for (int i = 0; i < size; i++) {
            population.get(i).calcDistance();
        }
    }

    public void sort(){
//        List<Route> list = Arrays.asList(population);
        Collections.sort(population, new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                return (int)(o1.getDistance() - o2.getDistance());
            }
        });
    }

    public void display(){
        System.out.println("--------------------------");
        System.out.println("GENERATION " + generation);
        System.out.println("--------------------------");
        System.out.println();
        for (int i = 0; i < population.size(); i++) {
            System.out.println("Route " + i);
            System.out.println();
            System.out.println("Distance: " + population.get(i).getDistance());
            System.out.println();
//            for (int j = 0; j < population.get(0).getRoute().size(); j++) {
//                System.out.print("(" + population.get(i).getRoute().get(j).getX() + ", " + population.get(i).getRoute().get(j).getY() + "), ");
//            }
//            System.out.println();
        }

    }

    public void generation(){
        generation++;
        calcDistances();
        sort();
        for (int i = size/10*9; i < size; i++) {
            population.remove(i);
            population.add(new Route(population.get(i - size/10*9)));
        }
        for (int i = 1; i < size; i++) {
            population.get(i).mutate(chance);
        }

        calcDistances();
        sort();
        display();
        if (generation == 1000){
            System.out.println("--------------------------");
            System.out.println("Best Distance Score: " + population.get(0).getDistance());
        }
        else {
            generation();
        }
    }

}
