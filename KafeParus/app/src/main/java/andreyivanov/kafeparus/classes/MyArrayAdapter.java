package andreyivanov.kafeparus.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import andreyivanov.kafeparus.Activitys.BasketActivity;
import andreyivanov.kafeparus.R;

/**
 * Created by Andrey on 01.09.2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Dish> {


    static final String TAG = "My Log!!!!!";
    ArrayList<Dish> dishesList;
    LayoutInflater layoutInflater;
    Dish dish;
    int listItem;
    String dishPrice;
    Context context;
    BasketActivity basketActivity;

    public MyArrayAdapter(Context context, int resource, ArrayList<Dish> dishesList) {
        super(context, resource, dishesList);
        this.dishesList = dishesList;
        this.listItem = resource;
        this.context = context;
        this.basketActivity = (BasketActivity) context;
        layoutInflater  = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;

        if(item == null) {
            item = layoutInflater.inflate(listItem, parent, false);
        }

        Log.d(TAG, item.toString());

        dish = dishesList.get(position);

        if(dish != null) {

            ImageView imageViewIcon = (ImageView) item.findViewById(R.id.icon);
            TextView textViewDishName = (TextView)item.findViewById(R.id.dish);
            TextView textViewCost = (TextView)item.findViewById(R.id.cost);
            TextView textViewCount = (TextView)item.findViewById(R.id.textViewCount);
            Button buttonMinus = (Button)item.findViewById(R.id.button_minus);
            Button buttonPlus = (Button)item.findViewById(R.id.button_plus);
            buttonPlus.setTag(position);
            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //увеличиваем колличество блюд на один, формируем новый список, отправляем его в глобальное хранилище и сообщаем адаптеру, что нужно перерисовать список
                    int pos = (int)view.getTag();
                    dish = dishesList.get(pos);
                    dish.setCount(dish.getCount() + 1);
                    dishesList.set(pos, dish);
                    MyApplication.getInstance().setDishesList(dishesList);
                    basketActivity.remodelAdapter();
                }
            });

            buttonMinus.setTag(position);
            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = (int)view.getTag();
                    dish = dishesList.get(pos);

                    if (dish.getCount() == 1) {
                        showRemoveDialog(pos);
                    }
                    else {
                        //уменьшаем колличество блюд на один, формируем новый список, отправляем его в глобальное хранилище и сообщаем адаптеру, что нужно перерисовать список
                        dish.setCount(dish.getCount() - 1);
                        dishesList.set(pos, dish);
                        MyApplication.getInstance().setDishesList(dishesList);
                        basketActivity.remodelAdapter();
                    }
                }
            });

            dishPrice = (dish.getCount() * basketActivity.getIntPriceFromString(dish.getCost())
                    + " " + context.getResources().getString(R.string.ruble));
            imageViewIcon.setImageResource(dish.getImageResurse());
            textViewDishName.setText(dish.getName());
            textViewCost.setText(dishPrice);
            textViewCount.setText(String.valueOf(dish.getCount()));
            buttonPlus.setText("+");
            buttonMinus.setText("-");
        }

        return item;
    }
// метод для вызова диалогового окна с вопросом об удалении блюда.
    private void showRemoveDialog(final int pos) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Удалить блюдо из корзины");
        alertDialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dishesList.remove(pos);
                MyApplication.getInstance().setDishesList(dishesList);
                basketActivity.remodelAdapter();
            }
        });
        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog.show();
    }

}
