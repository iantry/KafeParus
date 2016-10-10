package andreyivanov.kafeparus.classes;

import android.content.Context;
import android.content.Intent;

import andreyivanov.kafeparus.Activitys.BasketActivity;
import andreyivanov.kafeparus.Activitys.DishActivity;
import andreyivanov.kafeparus.Activitys.MainActivity;
import andreyivanov.kafeparus.Activitys.OrderRegistration;

/**
 * Created by Andrey on 20.08.2016.
 * class for starting Activitys
 */
public class FabrickIntent {

    public static final String WHAT_CATEGORY = "what_category";
    // методы для запуска разных активити

    public static void openDishsActivity(MainActivity activity, String dishes){

        Intent dishsIntent = new Intent(activity, DishActivity.class);
        dishsIntent.putExtra(WHAT_CATEGORY, dishes);
        activity.startActivity(dishsIntent);
    }

    public static void openBasketActivity(Context context) {

        Intent basketIntent = new Intent(context, BasketActivity.class);
        context.startActivity(basketIntent);
    }

    public static void openOrderRegistration(Context context) {

        Intent orderIntent = new Intent(context, OrderRegistration.class);
        context.startActivity(orderIntent);
    }

    public static void openMainActivity(Context context) {

        Intent mainIntent = new Intent(context , MainActivity.class);
        context.startActivity(mainIntent);
    }

}
