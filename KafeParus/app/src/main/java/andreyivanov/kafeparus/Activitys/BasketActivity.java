package andreyivanov.kafeparus.Activitys;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;


import andreyivanov.kafeparus.R;
import andreyivanov.kafeparus.classes.Dish;
import andreyivanov.kafeparus.classes.FabrickIntent;
import andreyivanov.kafeparus.classes.MyApplication;
import andreyivanov.kafeparus.classes.MyArrayAdapter;

public class BasketActivity extends AppCompatActivity {

    ArrayList<Dish> dishList;
    MyArrayAdapter adapter;
    int totalCost;
    Button buttonCheckout;
    TextView textViewCount, textViewCost;
    RadioButton radioButtonShipping;
    ListView dishListView;
    static boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();

        if(!dishList.isEmpty()) {
            setContentView(R.layout.activity_basket);
            initViews();
            setClickListener();
            setAdapter();
            getTotalCost();
            setViews();
        }
        else setContentView(R.layout.activity_empty_basket);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basket_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        isBack = true;
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
    }


    private void initVariables() {

        // получаем список блюд из MyApplication унаследованного от Application
        dishList = MyApplication.getInstance().getDishesList();
        totalCost = 0;
    }

    private void initViews() {

        dishListView = (ListView)findViewById(R.id.dishListView);
        textViewCount = (TextView)findViewById(R.id.textViewCount);
        buttonCheckout = (Button)findViewById(R.id.button_checkout);
        radioButtonShipping = (RadioButton)findViewById(R.id.radioButtonShipping);
        textViewCost = (TextView) findViewById(R.id.textViewCost);
    }

    private void setClickListener() {

        buttonCheckout.setOnClickListener(onClickListener);

    }

    private void setAdapter() {

        adapter = new MyArrayAdapter(this, R.layout.list_item, dishList);
        dishListView.setOnItemClickListener(itemClickListener);
        dishListView.setAdapter(adapter);

    }

    // метод используется для оповещения адаптера об изменении в списке, а так же для перерасчета данных.
    public void remodelAdapter() {

        adapter.notifyDataSetChanged();
        dishList = MyApplication.getInstance().getDishesList();
        getTotalCost();
        setViews();
        if(dishList.isEmpty())
            setContentView(R.layout.activity_empty_basket);
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
            Toast.makeText(getApplicationContext(), itemClicked.findViewById(R.id.dish).toString(), Toast.LENGTH_LONG).show();
        }
    };
// используется для перерисовки TextView c ценой
    private void setViews() {

        String strCost = totalCost + " " + this.getResources().getString(R.string.ruble);
        textViewCost.setText(strCost);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_checkout:

                    if(radioButtonShipping.isChecked()) {
                        FabrickIntent.openOrderRegistration(BasketActivity.this);
                    }
                    else {
                        showTimeDialog();
                    }
                    break;
            }
        }
    };

    // выводит диалоговое окно с выбором времени
    private void showTimeDialog(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(BasketActivity.this, onTimeSetListener, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
        timePickerDialog.show();
    }


    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        // событие возникает, когда пользователь выбрал время и нажал кнопку установить.
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Toast.makeText(BasketActivity.this, "Стол зарезервирвоан на " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            // обнуляем список выбраных пользователем блюд
            sendOrder(hourOfDay, minute, MyApplication.getInstance().getDishesList());
            MyApplication.getInstance().setDishesList(new ArrayList<Dish>());
            finish();
        }
    };

// метод заглушка для отправки данных на сервер.
    private void sendOrder(int hour, int minute, ArrayList<Dish> dishesList){
        int[] timeSendToServer = new int[2];
        ArrayList<Dish> listDishesSendToServer = dishesList;
        timeSendToServer[0] = hour;
        timeSendToServer[1] = minute;
    }

    // получаем целочисленное значение из строки
    public int getIntPriceFromString(String price) {
        String [] forSplit;
        forSplit = price.split(" ");
        return Integer.parseInt(forSplit[0]);
    }

    // получаем общую сумму всех блюд
    private void getTotalCost() {

        totalCost = 0;
        for (Dish dish: dishList) {
            totalCost += getIntPriceFromString(dish.getCost()) * dish.getCount();
        }
    }
}
