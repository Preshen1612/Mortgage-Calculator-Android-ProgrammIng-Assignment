package com.example.i851409.mortgagecalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    private String amount_borrowed_string = "";
    private Double progress_value = 0.0D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the background image of the Main Activity Screen
        getWindow().setBackgroundDrawableResource(R.drawable.mortgage_calculator_background_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Displaying the Progress Value of the Seek Bar
        SeekBar sb = (SeekBar) findViewById(R.id.seek_bar);
        final TextView tv_sb_val = (TextView) findViewById(R.id.seek_bar_val);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = (double)progress/100;
                String temp1 = getResources().getString(R.string.seek_bar_value);
                String temp2 = null;
                if(temp1.contains(" ")){
                    temp2 = temp1.substring(0,temp1.indexOf(" "));
                }
                tv_sb_val.setText(temp2+progress_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Broadcast Listener to be executed when the User clicks the Button

    public void calculateMonthlyPayment(View view){

        boolean flag1 = true;
        boolean flag2 = true;
        TextView tv = (TextView) findViewById(R.id.result_field);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkbox);
        int radio_button_id;

        //Calculation Variables
        Double monthly_payment_var = 0.0D;
        Double amount_borrowed_var = 0.0D;
        int no_of_months_var = 0;
        Double monthly_interest_rate_var = 0.0D;
        Double annual_interest_rate_var = 0.0D;
        Double monthly_taxes_and_insurances_var = 0.0D;

        //Firstly check whether the Amount Borrowed EditText field is Empty or Not
        //If Empty then display an Error Message
        //Else go ahead
        EditText edit_text_one = (EditText) findViewById(R.id.amount_borrowed_field);
        amount_borrowed_string = edit_text_one.getText().toString();
        if(amount_borrowed_string.matches("")){
            //Setting the appropriate error message
            edit_text_one.setError(getResources().getString(R.string.error_message));
            flag1 = false;
            tv.setText("");
        }

        //Secondly you also need to show an Error Message if none of the Radio Buttons in a RadioGroup are Checked
        RadioGroup radio_group_instance = (RadioGroup) findViewById(R.id.radio_group);
        RadioButton radio_button_instance = (RadioButton) findViewById(R.id.radio_button_three);

        radio_button_id  = radio_group_instance.getCheckedRadioButtonId();

        if(radio_group_instance.getCheckedRadioButtonId() == -1){
            //Setting the appropriate error message
            radio_button_instance.setError("Required");
            flag2 = false;
            tv.setText("");
        }

        //If everything is filled correctly then go further for the calculation
        if(flag1&&flag2) {
            //Remove the Error Messages
            radio_button_instance.setError(null);

            //Fetching the Amount Borrowed value entered by the user in float form
            amount_borrowed_var = Double.parseDouble(edit_text_one.getText().toString());

            //Fetching the Number of Months from the Years from the Radio Buttons selected by the user
            no_of_months_var = (Integer.valueOf(((RadioButton) findViewById(radio_button_id)).getText().toString()))*12;

            //Check1: For interest rate = 0%
            if((progress_value.intValue())==0) {
                //tv.setText("0 Selected");


                //Check2: If the user has selected the Checkbox corresponding to TAXES and INSURANCES
                if(cb2.isChecked()) {
                    //tv.setText("CHECKBOX SELECTED");
                    //Fetching the value of the Monthly Taxes and Insurances as 0.1% of the Amount Borrowed
                    monthly_taxes_and_insurances_var = (amount_borrowed_var*(0.1/100));
                    //Check3: For Loan Term = 7 years
                    if(radio_button_id == R.id.radio_button_one){
                        //tv.setText(""+no_of_months_var);
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var/no_of_months_var)+monthly_taxes_and_insurances_var);

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 15 years
                    if(radio_button_id == R.id.radio_button_two){
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var/no_of_months_var)+monthly_taxes_and_insurances_var);

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 30 years
                    if(radio_button_id == R.id.radio_button_three){
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var/no_of_months_var)+monthly_taxes_and_insurances_var);

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                }

                //Check2: If the user has not selected the Checkbox corresponding to TAXES and INSURANCES
                else {
                    //Check3: For Loan Term = 7 years
                    if(radio_button_id == R.id.radio_button_one){
                        //Calculate the final value
                        monthly_payment_var = amount_borrowed_var/no_of_months_var;

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 15 years
                    if(radio_button_id == R.id.radio_button_two){
                        //Calculate the final value
                        monthly_payment_var = amount_borrowed_var/no_of_months_var;

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 30 years
                    if(radio_button_id == R.id.radio_button_three){
                        //Calculate the final value
                        monthly_payment_var = amount_borrowed_var/no_of_months_var;

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                }
            }
            //Check1: For interest rate other than 0%
            else {
                //Fetching the Monthly Interest Rate selected by the user through the seek bar
                monthly_interest_rate_var = progress_value/1200;

                //Check2: If the user has selected the Checkbox corresponding to TAXES and INSURANCES
                if(cb2.isChecked()) {
                    //Fetching the value of the Monthly Taxes and Insurances as 0.1% of the Amount Borrowed
                    monthly_taxes_and_insurances_var = (amount_borrowed_var*(0.1/100));
                    //Check3: For Loan Term = 7 years
                    if(radio_button_id == R.id.radio_button_one){
                        //Calculate the final value
                        monthly_payment_var = (((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))))+(monthly_taxes_and_insurances_var));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 15 years
                    if(radio_button_id == R.id.radio_button_two){
                        //Calculate the final value
                        monthly_payment_var = (((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))))+(monthly_taxes_and_insurances_var));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 30 years
                    if(radio_button_id == R.id.radio_button_three){
                        //Calculate the final value
                        monthly_payment_var = (((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))))+(monthly_taxes_and_insurances_var));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                }
                //Check2: If the user has not selected the Checkbox corresponding to TAXES and INSURANCES
                else {
                    //Check3: For Loan Term = 7 years
                    if(radio_button_id == R.id.radio_button_one){
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 15 years
                    if(radio_button_id == R.id.radio_button_two){
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                    //Check3: For Loan Term = 30 years
                    if(radio_button_id == R.id.radio_button_three){
                        //Calculate the final value
                        monthly_payment_var = ((amount_borrowed_var)*((monthly_interest_rate_var)/((1)-(Math.pow((1+monthly_interest_rate_var),-(no_of_months_var))))));

                        //Setting the final result
                        tv.setText(""+monthly_payment_var);
                    }
                }
            }
        }
    }
}
