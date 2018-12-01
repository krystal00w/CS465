package edu.illinois.cs465.tbbt.OrderMemory;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents an ordered drink. Some parameters will be moved to a different class once we implement data storage & menu logic.
 */
public class Drink {
    public String drinkName;
    public int quantity;
    public String notes;
    public boolean doubleShot;
    public double price;
    public Calendar time;

    /**
     * Constructor
     * @param drinkName drink name
     * @param doubleShot whether the drink is a double
     * @param quantity quantity
     * @param notes customer-specified notes
     * @param price price of the drink
     */
    public Drink(String drinkName, int quantity, boolean doubleShot, String notes, double price) {
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.doubleShot = doubleShot;
        this.notes = notes;
        this.price = price;
        time = Calendar.getInstance();
        return;
    }

    /**
     * overrides hashcode. required, since we need to override equals.
     * @return hashcode
     */
    @Override
    public int hashCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String desc = drinkName + "###" + String.valueOf(quantity) + "###" + notes + String.valueOf(price) + sdf.format(time.getTime());
        return desc.hashCode();
    }

    /**
     * overrides equals
     * @param obj object to be compared to
     * @return true if equals, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Drink other = (Drink) obj;
        return !(!drinkName.equals(other.drinkName) || quantity != other.quantity || !notes.equals(other.notes) || doubleShot != other.doubleShot || price != other.price || time != other.time);

    }

}
