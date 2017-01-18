package tm.shker.mohamed.chickengrill.Objects;

import java.io.Serializable;

/**
 * Created by mohamed on 18/01/2017.
 */

public class MealOrder implements Serializable {
    private Meal orderedMeal;//this contains all the data about the specific order that the customer ordered.
    private int numOfDuplicationOfTheMeal;//how much meals with the exact same ingredients do the customer ordered.

    public MealOrder() {
    }

    public MealOrder(Meal orderedMeal) {
        this.orderedMeal = orderedMeal;
        numOfDuplicationOfTheMeal = 1;
    }

    public MealOrder(Meal orderedMeal, int numOfDuplicationOfTheMeal) {
        this.orderedMeal = orderedMeal;
        this.numOfDuplicationOfTheMeal = numOfDuplicationOfTheMeal;
    }

    public Meal getOrderedMeal() {
        return orderedMeal;
    }

    public void setOrderedMeal(Meal orderedMeal) {
        this.orderedMeal = orderedMeal;
    }

    public int getNumOfDuplicationOfTheMeal() {
        return numOfDuplicationOfTheMeal;
    }

    public void setNumOfDuplicationOfTheMeal(int numOfDuplicationOfTheMeal) {
        this.numOfDuplicationOfTheMeal = numOfDuplicationOfTheMeal;
    }

    @Override
    public String toString() {
        return "MealOrder{" +
                "orderedMeal=" + orderedMeal +
                ", numOfDuplicationMeals=" + numOfDuplicationOfTheMeal +
                '}';
    }
}
