package andreyivanov.kafeparus.classes;

/**
 * Created by Andrey on 23.08.2016.
 */


public class Dish {

    private String name;
    private String cost;
    private int imageResurse;
    private int count = 1;

    public Dish(String name, String cost, int imageResurse) {
        this.name = name;
        this.cost = cost;
        this.imageResurse = imageResurse;

    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public int getImageResurse() {
        return imageResurse;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
