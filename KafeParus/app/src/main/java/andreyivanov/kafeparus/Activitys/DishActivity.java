package andreyivanov.kafeparus.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import java.util.ArrayList;

import andreyivanov.kafeparus.classes.Dish;
import andreyivanov.kafeparus.classes.FabrickIntent;
import andreyivanov.kafeparus.R;
import andreyivanov.kafeparus.classes.MyGridAdapter;

public class DishActivity extends AppCompatActivity {

    GridView dishesGridView;
    ArrayList<Dish> dishesList;
    Dish dish;
    MyGridAdapter adapter;
    ArrayList<Integer> imageRes;
    String[] nameRes;
    String[] costRes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        initViews();
        initVariables();
        setAdapter();
    }


    private void initViews() {

        dishesGridView = (GridView)findViewById(R.id.dishesGridView);
    }


    private void initVariables() {

        dishesList = new ArrayList<>();
        imageRes = new ArrayList<>();


        // заполняем список и массивы в зависимости от того какая категоия блюд была выбрана

        switch(getIntent().getStringExtra(FabrickIntent.WHAT_CATEGORY)) {
            case MainActivity.SOUPS: {
                imageRes.add(R.drawable.dish1_image);
                imageRes.add(R.drawable.dish2_image);
                imageRes.add(R.drawable.dish3_image);
                imageRes.add(R.drawable.dish4_image);

                nameRes = getResources().getStringArray(R.array.soups);
                costRes = getResources().getStringArray(R.array.cost_soups);
                break;
            }
            case MainActivity.SECOND_DISHES: {

                imageRes.add(R.drawable.dish5_image);
                imageRes.add(R.drawable.dish6_image);
                imageRes.add(R.drawable.dish7_image);
                imageRes.add(R.drawable.dish8_image);

                nameRes = getResources().getStringArray(R.array.second_dish);
                costRes = getResources().getStringArray(R.array.cost_second_dish);
                break;
            }
            case MainActivity.SALADS: {

                imageRes.add(R.drawable.dish9_image);
                imageRes.add(R.drawable.dish10_image);
                imageRes.add(R.drawable.dish11_image);
                imageRes.add(R.drawable.dish12_image);
                imageRes.add(R.drawable.dish13_image);
                imageRes.add(R.drawable.dish14_image);
                imageRes.add(R.drawable.dish15_image);
                imageRes.add(R.drawable.dish16_image);
                imageRes.add(R.drawable.dish17_image);

                nameRes = getResources().getStringArray(R.array.salads);
                costRes = getResources().getStringArray(R.array.cost_salads);
                break;
            }
            case MainActivity.SNACKS: {

                imageRes.add(R.drawable.dish18_image);
                imageRes.add(R.drawable.dish19_image);
                imageRes.add(R.drawable.dish20_image);

                nameRes = getResources().getStringArray(R.array.snacks);
                costRes = getResources().getStringArray(R.array.cost_snacks);
                break;
            }
            case MainActivity.DESERTS: {

                imageRes.add(R.drawable.dish21_image);
                imageRes.add(R.drawable.dish22_image);
                imageRes.add(R.drawable.dish23_image);
                imageRes.add(R.drawable.dish24_image);
                imageRes.add(R.drawable.dish25_image);

                nameRes = getResources().getStringArray(R.array.deserts);
                costRes = getResources().getStringArray(R.array.cost_deserts);
                break;
            }
            case MainActivity.DRINKS: {

                imageRes.add(R.drawable.dish26_image);
                imageRes.add(R.drawable.dish27_image);
                imageRes.add(R.drawable.dish28_image);
                imageRes.add(R.drawable.dish29_image);
                imageRes.add(R.drawable.dish30_image);

                nameRes = getResources().getStringArray(R.array.drinks);
                costRes = getResources().getStringArray(R.array.cost_drinks);
                break;
            }

        }
        // формируем список блюд из ресурсов
        for (int i = 0; i < nameRes.length; i++) {
            dish = new Dish(nameRes[i], costRes[i], imageRes.get(i));
            dishesList.add(dish);
        }

    }

    private void setAdapter() {

        adapter = new MyGridAdapter(this, R.layout.grid_item, dishesList);
        dishesGridView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_dish_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.back:
                finish();
                break;
            case R.id.basket:
                FabrickIntent.openBasketActivity(DishActivity.this);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
