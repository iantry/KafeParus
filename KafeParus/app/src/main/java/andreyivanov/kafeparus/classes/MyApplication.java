package andreyivanov.kafeparus.classes;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Andrey on 22.08.2016.
 */
public class MyApplication extends Application {


    private ArrayList<Dish> dishesList;

    private static MyApplication fInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        dishesList = new ArrayList<>();
        fInstance = this;
    }

    public ArrayList<Dish> getDishesList() {
        return dishesList;
    }

    public static MyApplication getInstance() {
        return fInstance;
    }

    public void setDishesList(ArrayList<Dish> dishesList) {
        this.dishesList = dishesList;
    }

    public static void setInstance(MyApplication fInstance) {
        MyApplication.fInstance = fInstance;
    }
}
