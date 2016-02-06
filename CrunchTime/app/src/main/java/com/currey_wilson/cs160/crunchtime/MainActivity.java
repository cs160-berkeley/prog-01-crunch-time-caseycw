package com.currey_wilson.cs160.crunchtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup minutesRepsGroup;
    RadioGroup exerciseTypeGroup;
    Button updateButton;
    EditText input;
    TextView calories;
    TextView pushups;
    TextView situps;
    TextView jumpingjacks;
    TextView jogging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        addButtonListener();
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

    public void addButtonListener() {
        minutesRepsGroup = (RadioGroup) findViewById(R.id.group1);
        exerciseTypeGroup = (RadioGroup) findViewById(R.id.group2);
        updateButton = (Button) findViewById(R.id.update);
        input = (EditText) findViewById(R.id.count);
        calories = (TextView) findViewById(R.id.calories);
        pushups = (TextView) findViewById(R.id.pushupEquiv);
        situps = (TextView) findViewById(R.id.situpEquiv);
        jumpingjacks = (TextView) findViewById(R.id.jumpingjackEquiv);
        jogging = (TextView) findViewById(R.id.joggingEquiv);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minutesRepsId = minutesRepsGroup.getCheckedRadioButtonId();
                int exerciseTypeId = exerciseTypeGroup.getCheckedRadioButtonId();
                double inputNumber = Double.parseDouble(input.getText().toString());

                boolean error = true;
                double result = 0.0;

                if (minutesRepsId == R.id.reps) {
                    if (exerciseTypeId == R.id.pushups) {
                        error = false;
                        result = inputNumber / 3.5;
                    }
                    else if (exerciseTypeId == R.id.situps) {
                        error = false;
                        result = inputNumber / 2.0;
                    }
                }
                else if (minutesRepsId == R.id.minutes) {
                    if (exerciseTypeId == R.id.jumpingjacks) {
                        error = false;
                        result = inputNumber * 10.0;
                    }
                    else if (exerciseTypeId == R.id.jogging) {
                        error = false;
                        result = (inputNumber / 12.0) * 100.0;
                    }
                }
                if (!error) {
                    calories.setText(String.valueOf(result));
                    pushups.setText(String.valueOf(3.5 * result));
                    situps.setText(String.valueOf(2.0 * result));
                    jumpingjacks.setText(String.valueOf(0.1 * result));
                    jogging.setText(String.valueOf(0.12 * result));
                }
                else {
                    calories.setText("error");
                    pushups.setText("...");
                    situps.setText("...");
                    jumpingjacks.setText("...");
                    jogging.setText("...");
                }


            }
        });
    }
}
