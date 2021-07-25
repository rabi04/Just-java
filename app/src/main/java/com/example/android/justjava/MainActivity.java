package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("checking ","what is log used for");
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    boolean isChecked = false;

    public  void submitOrder(View view) {
        display(quantity);
        Log.v("checked box","it done working fine");
        displayPrice( 10 * quantity);
    }
    public void increment(View view){
        quantity++;
        display(quantity);
    }
    public void decrement(View view){
        if(quantity > 0)
        quantity--;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    @SuppressLint("QueryPermissionsNeeded")
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        String orderBill="";
        EditText nameEnter = findViewById(R.id.nameEnter);
        CheckBox whippedCream = findViewById(R.id.whippedCream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        orderBill = nameEnter.getText() + "\n";
        orderBill += " Add whippedCream?"+ whippedCream.isChecked() +"\n";
        orderBill += "Add chocolates?"+ chocolate.isChecked() + "\n";
        orderBill += "Total:"+NumberFormat.getCurrencyInstance().format(number)+"\n ThankYou !";
        //to send to email of the customer
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT,orderBill);
        if(intent.resolveActivity(getPackageManager()) != null)
           try {
                startActivity(intent);
           }
           catch (Exception e){
               priceTextView.setText( e.toString());
           }
    }
}