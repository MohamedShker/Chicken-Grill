package tm.shker.mohamed.chickengrill.Managers;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import tm.shker.mohamed.chickengrill.Objects.Meal;
import tm.shker.mohamed.chickengrill.R;


/**
 * Created by mohamed on 04/01/2017.
 */

public class AddToCartListener implements View.OnClickListener {
    private Meal meal;

    public AddToCartListener() {
    }

    public AddToCartListener(Meal meal) {
        this.meal = meal;
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(v.getContext(), meal, Toast.LENGTH_SHORT).show();
//        View parent = (View) v.getParent();
//        TextView tvMealName = (TextView) parent.findViewById(R.id.tvMealName);
//        CharSequence mealName = tvMealName.getText();
//        Toast.makeText(v.getContext(), mealName, Toast.LENGTH_SHORT).show();
//
//
//        Query queryRef = FirebaseDatabase.getInstance().getReference().orderByChild("fullName").equalTo(mealName.toString());

        Toast.makeText(v.getContext(), meal.getMealSides().toString(), Toast.LENGTH_SHORT).show();


    }




//    //SET ADD TO CART LISTENER
//    mealItem = inflater.inflate(R.layout.meal,container,false);
//    View addToCart = mealItem.findViewById(R.id.ibAddToCard);
//    addToCart.setOnClickListener(new AddToCartListener(mealType));
}
