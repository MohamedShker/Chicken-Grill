package tm.shker.mohamed.chickengrill.Activities;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.zip.Inflater;

import tm.shker.mohamed.chickengrill.Managers.Constants;
import tm.shker.mohamed.chickengrill.Objects.Meal;
import tm.shker.mohamed.chickengrill.Objects.MealSides;
import tm.shker.mohamed.chickengrill.R;

public class MealSidesActivity extends AppCompatActivity {

    //for meal big display:
    private ImageView ivBigMealImage;
    private TextView tvBigMealName, tvBigMealPrice, tvMealSidesDrinkTitle, tvMealSideTitle, tvMealPossibleModificationsTitle, tvMealSaladsTitle, tvMealSaucesTitle;
    private RelativeLayout rlMealSidesDrink, rlMealSide, rlMealNotes;
    private LinearLayout llMealPossibleModifications, llMealSalads, llMealSauces, llMealSidesWrapper;
    private RadioGroup rgDrinks, rgSides;
    private EditText etMealNotes;
    private Meal mealToDisplay;
    private String mealType;

    ArrayList<String> possibleModifications, salads, sides, drinks, sauces;
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
        initMealSides(mealToDisplay);


        if(mealType.equals("עסקיות בורגרים") || mealType.equals("עסקיות")) {
            displayMeal();
        }
        else if(mealType.equals("תוספות")){
            displaySides();

        }
        else if( mealType.equals("מנות בג'בטה") || mealType.equals("מנות בבגט")){
            displayBagetMeal();
        }
        else if(mealType.equals("קומבינציות")){
            displayCombinationMeal();
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void initViews() {
        //for meal big display:
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
        llMealPossibleModifications = (LinearLayout) findViewById(R.id.llMealPossibleModifications);
        tvMealPossibleModificationsTitle = (TextView) findViewById(R.id.tvMealpossibleModificationsTitle);

        //for displaying salad options:
        llMealSalads = (LinearLayout) findViewById(R.id.llMealSalads);
        tvMealSaladsTitle = (TextView) findViewById(R.id.tvMealSaladsTitle);

        //for displaying sauces options:
        llMealSauces = (LinearLayout) findViewById(R.id.llMealSauces);
        tvMealSaucesTitle = (TextView) findViewById(R.id.tvMealSaucesTitle);

        //for receiving the meal notes from the customer:
        rlMealNotes = (RelativeLayout) findViewById(R.id.rlMealNotes);
        etMealNotes = (EditText) findViewById(R.id.etMealNotes);

        //main linear layout (parent)
        llMealSidesWrapper = (LinearLayout) findViewById(R.id.llMealSidesWrapper);
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


    private void initMealSides(Meal meal){
        MealSides mealSides = meal.getMealSides();
         drinks = mealSides.getDrinks();
         sides = mealSides.getSides();
         possibleModifications = mealSides.getPossibleModifications();
         salads = mealSides.getSalad();
         sauces = mealSides.getSauces();
         mealType = meal.getMealType();
    }

    private int getNumOfMealsInCombination() {
        int ans = 2;

        if(mealToDisplay.getMealName().equals("קומבינציה משולשת")){
            ans = 3;
        }
        else if(mealToDisplay.getMealName().equals("קומבינציה מרובעת")){
            ans = 4;
        }
        return ans;
    }

    private void displayCombinationMeal() {
        displayBigMeal(mealToDisplay.getMealName(), mealToDisplay.getMealCost(), mealToDisplay.getMealURLImage());

        int i = getNumOfMealsInCombination();

        llMealSalads.setVisibility(View.GONE);
        llMealSauces.setVisibility(View.GONE);
        llMealPossibleModifications.setVisibility(View.GONE);

        LayoutInflater inflater = LayoutInflater.from(this);
        int numOfMeal = 0;
        for (int j = 0; j < i*2; j++) {
            if( numOfMeal <= i)
             numOfMeal++;
            View view = inflater.inflate(R.layout.layout_for_duplicating_meal_side, null);
            LinearLayout llMealPM = (LinearLayout) view;
            TextView myTv = (TextView) llMealPM.getChildAt(0);
            if(j%2 == 0) {
                //display possible modification options:
                StringBuilder stringBuilder = new StringBuilder(" בחר סוג בשר ");
                stringBuilder.append(numOfMeal);
                stringBuilder.append(" לבחירה : " );
                myTv.setText(stringBuilder.toString());
                if (possibleModifications.get(1).equals("none")) {
                    llMealPM.setVisibility(View.GONE);
                } else {
                    RadioGroup radioGroup = new RadioGroup(this);
                    llMealPM.addView(radioGroup);
                    radioButtonDislay(possibleModifications, radioGroup);
                }
                llMealSidesWrapper.addView(view,j+2);
            }
            else{
                //display salads and sauces options:
                numOfMeal--;
                StringBuilder stringBuilder = new StringBuilder(" סלט ורטבים לתוך מנה ");
                stringBuilder.append(numOfMeal);
                stringBuilder.append(" לבחירה : " );
                myTv.setText(stringBuilder.toString());
            if (salads.get(1).equals("none") && sauces.get(1).equals("none")) {
                llMealPM.setVisibility(View.GONE);
            } else {
                checkBoxDisplay(salads,llMealPM);
                checkBoxDisplay(sauces,llMealPM);
            }
                llMealSidesWrapper.addView(view,j+2);
            }
        }

        //display sides options:
        tvMealSideTitle.setText("תוספת לבחירה :");
        if (sides.get(1).equals("none")) {
            rlMealSide.setVisibility(View.GONE);
        } else {
            radioButtonDislay(sides,rgSides);
        }

        //display drinks:
        tvMealSidesDrinkTitle.setText("שתיה לבחירה :");
        if (drinks.get(1).equals("none")) {
            rlMealSidesDrink.setVisibility(View.GONE);
        } else {
            radioButtonDislay(drinks,rgDrinks);
        }
    }

    private void displayBagetMeal() {
        displayBigMeal(mealToDisplay.getMealName(), mealToDisplay.getMealCost(), mealToDisplay.getMealURLImage());

        //display possible modification options:
        tvMealPossibleModificationsTitle.setText("בחר רוטב :");
        if (possibleModifications.get(1).equals("none")) {
            llMealPossibleModifications.setVisibility(View.GONE);
        } else {
            RadioGroup radioGroup = new RadioGroup(this);
            llMealPossibleModifications.addView(radioGroup);
            radioButtonDislay(possibleModifications,radioGroup);
        }

        //display salads options:
        tvMealSaladsTitle.setText("סלט לתוך המנה לבחירה :");
        if (salads.get(1).equals("none")) {
            llMealSalads.setVisibility(View.GONE);
        } else {
            checkBoxDisplay(salads,llMealSalads);
        }

        //display sides options:
        tvMealSideTitle.setText("תוספת אישית לבחירה :");
        if (sides.get(1).equals("none")) {
            rlMealSide.setVisibility(View.GONE);
        } else {
            radioButtonDislay(sides,rgSides);
        }

        //display drinks:
        tvMealSidesDrinkTitle.setText("שתיה לבחירה :");
        if (drinks.get(1).equals("none")) {
            rlMealSidesDrink.setVisibility(View.GONE);
        } else {
            radioButtonDislay(drinks,rgDrinks);
        }

        //display sauses options:
        tvMealSaucesTitle.setText("רטבים לתוך המנה לבחירה :");
        if (sauces.get(1).equals("none")) {
            llMealSauces.setVisibility(View.GONE);
        } else {
            checkBoxDisplay(sauces,llMealSauces);
        }

    }

    private void displaySides() {
        displayBigMeal(mealToDisplay.getMealName(), mealToDisplay.getMealCost(), mealToDisplay.getMealURLImage());

        //display possible modification options:
        tvMealPossibleModificationsTitle.setText("בחר גודל :");
        if (possibleModifications.get(1).equals("none")) {
            llMealPossibleModifications.setVisibility(View.GONE);
        } else {
            RadioGroup radioGroup = new RadioGroup(this);
            llMealPossibleModifications.addView(radioGroup);
            radioButtonDislay(possibleModifications,radioGroup);
        }

        llMealSalads.setVisibility(View.GONE);
        rlMealSide.setVisibility(View.GONE);
        rlMealSidesDrink.setVisibility(View.GONE);
        llMealSauces.setVisibility(View.GONE);
    }

    private void displayMeal() {
        displayBigMeal(mealToDisplay.getMealName(), mealToDisplay.getMealCost(), mealToDisplay.getMealURLImage());

        //display possible modifications:
        tvMealPossibleModificationsTitle.setText("שינויים אפשריים במנה :");
        if (possibleModifications.get(1).equals("none")) {
            llMealPossibleModifications.setVisibility(View.GONE);
        } else {
            checkBoxDisplay(possibleModifications,llMealPossibleModifications);
        }


        //display salads options:
        tvMealSaladsTitle.setText("סלט לבחירה :");
        if (salads.get(1).equals("none")) {
            llMealSalads.setVisibility(View.GONE);
        } else {
            checkBoxDisplay(salads,llMealSalads);
        }

        //display sides options:
        tvMealSideTitle.setText("תוספת אישית לבחירה :");
        if (sides.get(1).equals("none")) {
            rlMealSide.setVisibility(View.GONE);
        } else {
            radioButtonDislay(sides,rgSides);
        }

        //display drinks:
        tvMealSidesDrinkTitle.setText("שתיה לבחירה :");
        if (drinks.get(1).equals("none")) {
            rlMealSidesDrink.setVisibility(View.GONE);
        } else {
            radioButtonDislay(drinks,rgDrinks);
        }

        //display sauses options:
        tvMealSaucesTitle.setText("רטבים לבחירה :");
        if (sauces.get(1).equals("none")) {
            llMealSauces.setVisibility(View.GONE);
        } else {
            checkBoxDisplay(sauces,llMealSauces);
        }
    }


    private void radioButtonDislay(ArrayList<String> arrToDisplay,RadioGroup radioGroup){
        for (int i = 1; i < arrToDisplay.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            if(i==1){
                radioButton.setId(R.id.first_rb);
            }
            else {
                radioButton.setId(i + 200);
            }
            radioButton.setText(arrToDisplay.get(i));
            radioButton.setTextColor(ContextCompat.getColor(this, R.color.white));
            radioButton.setTextSize(20);
            radioGroup.addView(radioButton);
        }
        radioGroup.check(R.id.first_rb);
    }



    private void checkBoxDisplay(ArrayList<String> arrToDisplay,LinearLayout linearLayout) {
        for (int i = 1; i < arrToDisplay.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i + 100);
            checkBox.setText(arrToDisplay.get(i));
            checkBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            checkBox.setTextSize(20);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(checkBox);

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
