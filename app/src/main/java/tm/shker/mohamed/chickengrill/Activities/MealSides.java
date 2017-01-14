package tm.shker.mohamed.chickengrill.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import tm.shker.mohamed.chickengrill.R;

public class MealSides extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_sides);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlMealSidesOptins);
        //relativeLayout.setVisibility(View.GONE);
    }
}
