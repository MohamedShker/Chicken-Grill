package tm.shker.mohamed.chickengrill.Activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tm.shker.mohamed.chickengrill.Managers.Constants;
import tm.shker.mohamed.chickengrill.Objects.Meal;
import tm.shker.mohamed.chickengrill.Objects.MealSides;
import tm.shker.mohamed.chickengrill.R;

public class MealSidesActivity extends AppCompatActivity {

    //for meal big display:
    ImageView ivBigMealImage;
    TextView tvBigMealName, tvBigMealPrice, tvMealSidesDrinkTitle, tvMealSideTitle, tvMealPossibleModificationsTitle, tvMealSaladsTitle, tvMealSaucesTitle;
    RelativeLayout rlMealSidesDrink, rlMealSide, rlMealPossibleModifications, rlMealSalads, rlMealSauces, rlMealNotes;
    RadioGroup rgDrinks, rgSides;
    EditText etMealNotes;
    CardView cardView;
    Meal mealToDisplay;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_sides);
        Bundle bundle = this.getIntent().getExtras();
        mealToDisplay = (Meal) bundle.getSerializable(Constants.MEAL_OPJECT);
        initViews();
        displayBurgerMeal(mealToDisplay);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initViews() {
        //for meal big display:
        cardView = (CardView) findViewById(R.id.cvBigMealDisplay);
        ivBigMealImage = (ImageView) findViewById(R.id.ivBigMealImage);
        tvBigMealName = (TextView) findViewById(R.id.tvBigMealName);
        tvBigMealPrice = (TextView) findViewById(R.id.tvBigMealPrice);

        //for displaying drinks options:
        rlMealSidesDrink = (RelativeLayout) findViewById(R.id.rlMealSidesDrink);
        tvMealSidesDrinkTitle = (TextView) findViewById(R.id.tvMealSidesDrinkTitle);
        rgDrinks = (RadioGroup) findViewById(R.id.rgDrinks);


        //for displaying sides options:
        rlMealSide = (RelativeLayout) findViewById(R.id.rlMealSide);
        tvMealSideTitle = (TextView) findViewById(R.id.tvMealSideTitle);
        rgSides = (RadioGroup) findViewById(R.id.rgSides);

        //for displaying possibleModifications options:
        rlMealPossibleModifications = (RelativeLayout) findViewById(R.id.rlMealPossibleModifications);
        tvMealPossibleModificationsTitle = (TextView) findViewById(R.id.tvMealpossibleModificationsTitle);

        //for displaying salad options:
        rlMealSalads = (RelativeLayout) findViewById(R.id.rlMealSalads);
        tvMealSaladsTitle = (TextView) findViewById(R.id.tvMealSaladsTitle);

        //for displaying sauces options:
        rlMealSauces = (RelativeLayout) findViewById(R.id.rlMealSauces);
        tvMealSaucesTitle = (TextView) findViewById(R.id.tvMealSaucesTitle);

        //for receiving the meal notes from the customer:
        rlMealNotes = (RelativeLayout) findViewById(R.id.rlMealNotes);
        etMealNotes = (EditText) findViewById(R.id.etMealNotes);
    }

    private void displayBigMeal(String mealName, String mealCost, String mealURLImage) {
        Picasso.with(this).
                load(mealURLImage).
                into(ivBigMealImage);

        tvBigMealName.setText(mealName);
        if(!mealCost.equals(" ")) {
            tvBigMealPrice.setText(mealCost + "₪");
        }
        else{
            tvBigMealPrice.setText(" ");
        }
    }


    public void displayBurgerMeal(Meal meal) {
        displayBigMeal(meal.getMealName(), meal.getMealCost(), meal.getMealURLImage());
        MealSides mealSides = meal.getMealSides();
        ArrayList<String> drinks = mealSides.getDrinks();
        ArrayList<String> sides = mealSides.getSides();
        ArrayList<String> possibleModifications = mealSides.getPossibleModifications();
        ArrayList<String> salads = mealSides.getSalad();
        ArrayList<String> sauces = mealSides.getSauces();

        //display possible modifications:
        if (possibleModifications.get(1).equals("none")) {
            rlMealPossibleModifications.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < possibleModifications.size(); i++) {

            }
        }

        //display drinks:

        tvMealSidesDrinkTitle.setText("שתיה לבחירה :");
        for (int i = 1; i < drinks.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(drinks.get(i));
            radioButton.setTextColor(ContextCompat.getColor(this,R.color.orange));
            rgDrinks.addView(radioButton);
        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MealSides Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
