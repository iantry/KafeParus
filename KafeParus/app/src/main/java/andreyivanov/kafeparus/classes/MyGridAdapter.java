package andreyivanov.kafeparus.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import andreyivanov.kafeparus.Activitys.DishActivity;
import andreyivanov.kafeparus.R;

/**
 * Created by Andrey on 01.09.2016.
 */
public class MyGridAdapter extends ArrayAdapter<Dish> {

    ArrayList<Dish> dishesList, orderedDishesList;
    LayoutInflater layoutInflater;
    Dish dish;
    int gridItem;
    Context context;

    public MyGridAdapter(Context context, int resource, ArrayList<Dish> dishesList) {
        super(context, resource, dishesList);
        this.dishesList = dishesList;
        this.gridItem = resource;
        this.context = context;
        layoutInflater  = LayoutInflater.from(context);
        orderedDishesList = new ArrayList<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = convertView;

        if(item == null) {

            item = layoutInflater.inflate(gridItem, parent, false);
        }

        dish = dishesList.get(position);

        if(dish != null) {

            ImageView imageViewIcon = (ImageView) item.findViewById(R.id.icon);
            TextView textViewDishName = (TextView)item.findViewById(R.id.dish);
            TextView textViewCost = (TextView)item.findViewById(R.id.cost);

            imageViewIcon.setImageResource(dish.getImageResurse());
            textViewDishName.setText(dish.getName());
            textViewCost.setText(dish.getCost());

            Button buttonOrder = (Button)item.findViewById(R.id.button_order);
            buttonOrder.setTag(position);
            buttonOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //формируем список
                    int pos = (int)v.getTag();
                    if(MyApplication.getInstance().getDishesList().isEmpty()) {
                        orderedDishesList.add(dishesList.get(pos));
                        Toast.makeText(context, "В корзине", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        orderedDishesList = MyApplication.getInstance().getDishesList();
                        if(contains(orderedDishesList, dishesList.get(pos))){
                            Toast.makeText(context, "Блюдо уже добавлено в корзину" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            orderedDishesList.add(dishesList.get(pos));
                            Toast.makeText(context, "В корзине", Toast.LENGTH_SHORT).show();
                        }
                    }
                    MyApplication.getInstance().setDishesList(orderedDishesList);
                }
            });

        }
        return item;
    }
    // метод который проверяет, есть ли в списке такое же блюдо
   private boolean contains(ArrayList<Dish> dishesListlist, Dish dish){

       for(Dish d : dishesListlist) {
           if(d.getName().equals(dish.getName())) {
               return true;
           }
       }
       return false;
   }
}
