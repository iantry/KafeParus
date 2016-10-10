package andreyivanov.kafeparus.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import andreyivanov.kafeparus.classes.FabrickIntent;
import andreyivanov.kafeparus.R;

public class MainActivity extends AppCompatActivity {

    public static final String SOUPS = "soups";
    public static final String SECOND_DISHES = "secondDishes";
    public static final String SALADS = "salads";
    public static final String SNACKS = "snacks";
    public static final String DESERTS = "deserts";
    public static final String DRINKS = "drinks";


    Button buttonSoups, buttonSecondDish, buttonSalads, buttonSnacks, buttonDeserts, buttonDrinks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setClickListener();
    }

    private void setClickListener() {

        buttonSoups.setOnClickListener(onClickListener);
        buttonSecondDish.setOnClickListener(onClickListener);
        buttonSalads.setOnClickListener(onClickListener);
        buttonSnacks.setOnClickListener(onClickListener);
        buttonDeserts.setOnClickListener(onClickListener);
        buttonDrinks.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FabrickIntent.openBasketActivity(this);
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {

        buttonSoups = (Button) findViewById(R.id.button_soups);
        buttonSecondDish = (Button)findViewById(R.id.button_second_dish);
        buttonSalads = (Button)findViewById(R.id.button_salads);
        buttonSnacks = (Button)findViewById(R.id.button_snacks);
        buttonDeserts = (Button)findViewById(R.id.button_desserts);
        buttonDrinks = (Button)findViewById(R.id.button_drinks);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_soups:
                    FabrickIntent.openDishsActivity(MainActivity.this, SOUPS);
                    break;
                case R.id.button_second_dish:
                    FabrickIntent.openDishsActivity(MainActivity.this, SECOND_DISHES);
                    break;
                case R.id.button_salads:
                    FabrickIntent.openDishsActivity(MainActivity.this, SALADS);
                    break;
                case R.id.button_snacks:
                    FabrickIntent.openDishsActivity(MainActivity.this, SNACKS);
                    break;
                case R.id.button_desserts:
                    FabrickIntent.openDishsActivity(MainActivity.this, DESERTS);
                    break;
                case R.id.button_drinks:
                    FabrickIntent.openDishsActivity(MainActivity.this, DRINKS);
                    break;

            }
        }
    };

}
