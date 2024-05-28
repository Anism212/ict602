package com.example.assigment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCalculate, btnClear; // Added btnClear
    EditText etUnits, etRebate;
    TextView tvResult;
//commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear); // Initialize btnClear
        etUnits = findViewById(R.id.etUnits);
        etRebate = findViewById(R.id.etRebate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this); // Set OnClickListener for btnClear

        // The Toolbar defined in the layout has the id "my_toolbar".
        setSupportActionBar(findViewById(R.id.my_toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSettings) {
            // Since we are in MainActivity, this can be a no-op or log a message
            return true;
        } else if (id == R.id.menuAbout) {
            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {
        if (view == btnCalculate) {
            String unitsStr = etUnits.getText().toString();
            String rebateStr = etRebate.getText().toString();

            if (unitsStr.isEmpty() || rebateStr.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the input contains a point (.)
            if (unitsStr.contains(".") || rebateStr.contains(".")) {
                Toast.makeText(this, "Please enter whole numbers only", Toast.LENGTH_SHORT).show();
                return;
            }

            double units = Double.parseDouble(unitsStr);
            double rebate = Double.parseDouble(rebateStr);

            if (rebate < 0 || rebate > 5) {
                Toast.makeText(this, "Please enter a number between 0% and 5% for Rebate", Toast.LENGTH_SHORT).show();
                return;
            }

            double charges = calculateCharges(units);

            double totalBill = charges - (charges * rebate / 100);

            tvResult.setText("Estimated Bill: RM " + String.format("%.2f", totalBill));
        } else if (view == btnClear) {
            clearFields(); // Call clearFields() when btnClear is clicked
        }
    }


    private double calculateCharges(double units) {
        double charges;
        if (units <= 200) {
            charges = units * 0.218;
        } else if (units <= 300) {
            charges = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            charges = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else {
            charges = 200 * 0.218 + 100 * 0.334 + 300 * 0.515 + (units - 600) * 0.546;
        }
        return charges;
    }
    private void clearFields() {
        etUnits.setText("");
        etRebate.setText("");
        tvResult.setText("Result");
    }
}

