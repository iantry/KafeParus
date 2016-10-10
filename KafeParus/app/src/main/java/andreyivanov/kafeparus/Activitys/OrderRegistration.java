package andreyivanov.kafeparus.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import andreyivanov.kafeparus.R;
import andreyivanov.kafeparus.classes.Dish;
import andreyivanov.kafeparus.classes.FabrickIntent;
import andreyivanov.kafeparus.classes.MyApplication;

public class OrderRegistration extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String FEEDBACK = "feedBack";


    Button buttonOrder;
    EditText editTextName, editTextPhone, editTextStreet, editTextHome, editTextCorpus, editTextStroenie, editTextFlat, editTextFeedBack;
    HashMap<String,String> orderList;
    ArrayList<Dish> dishesList;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_registration);

        initVariables();
        initViews();
        setClickListener();
    }


    private void initVariables() {

        orderList = new HashMap<>();
        dishesList = new ArrayList<>();
        address = "";
    }

    private void initViews() {

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextStreet = (EditText)findViewById(R.id.editTextStreet);
        editTextHome = (EditText)findViewById(R.id.editTextHome);
        editTextCorpus = (EditText)findViewById(R.id.editTextCorpus);
        editTextStroenie = (EditText)findViewById(R.id.editTextStroenie);
        editTextFlat = (EditText)findViewById(R.id.editTextFlat);
        editTextFeedBack = (EditText)findViewById(R.id.editTextFeedBack);
        buttonOrder = (Button)findViewById(R.id.buttonOrder);
    }

    private void setClickListener(){

        buttonOrder.setOnClickListener(onClickListener);
    }

   View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            address = "ул. " + editTextStreet.getText().toString() + ", дом " + editTextHome.getText().toString() + ", корп. " +
                    editTextCorpus.getText().toString() + ", стр. " + editTextStroenie.getText().toString() + ", кв. " +
                    editTextFlat.getText().toString();

            orderList.put(NAME, editTextName.getText().toString());
            orderList.put(PHONE, editTextPhone.getText().toString());
            orderList.put(ADDRESS, address);
            orderList.put(FEEDBACK, editTextFeedBack.getText().toString());

            Toast.makeText(OrderRegistration.this,"Спасибо " + editTextName.getText().toString() + ". Ваш заказа на следующий адрес " + address + " принят" , Toast.LENGTH_LONG).show();

            sendOrder(orderList , MyApplication.getInstance().getDishesList());
            FabrickIntent.openMainActivity(OrderRegistration.this);
            MyApplication.getInstance().setDishesList(dishesList);
        }
    };


// метод заглушка для отправки данных на сервер
    private void sendOrder(HashMap<String, String> orderList , ArrayList<Dish> listOfDishes) {

        HashMap<String, String> objectForSendToServer = orderList;
        ArrayList<Dish> listDishesSendToServer = listOfDishes;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basket_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

}
