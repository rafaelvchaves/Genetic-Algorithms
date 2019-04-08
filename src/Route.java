import java.util.ArrayList;

public class Route {
    ArrayList<City> route;
    int size;
    double distance;
    Cities cityList = new Cities();


    public Route() {
        size = cityList.getCities().size();
        ArrayList<City> cL = cityList.getCities();
        route = new ArrayList<>();
        route.add(cL.get(0));
        for (int i = 1; i < size; i++) {
            route.add(null);
        }
        for (int i = 1; i < size; i++) {
            int rand = (int) (Math.random() * size);
            if (route.get(rand) == null)
                route.set(rand, cL.get(i));
            else
                i--;

        }

    }
    public Route(Route original){
        route = new ArrayList<>();
        for(City c : original.getRoute()){
            route.add(new City(c));
        }
        size = original.size;
        calcDistance();

    }

    public void mutate(int chance) {
        int index1;
        int index2;
        if ((int) (Math.random() * 100) < chance) {
            index1 = (int) (Math.random() * size);
            index2 = (int) (Math.random() * size);
            if (index1 != 0 && index2 != 0) {
                route.add(index1, route.remove(index2));
            }
        }
    }

    public void calcDistance() {
        distance = 0;
        for (int i = 0; i < size - 1; i++) {
            distance += route.get(i).distanceTo(route.get(i + 1));
        }
        distance += route.get(size - 1).distanceTo(route.get(0));
    }

    public double getDistance() {
        return distance;
    }

    public ArrayList<City> getRoute() {
        return route;
    }


}
