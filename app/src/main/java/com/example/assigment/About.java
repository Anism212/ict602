package com.example.assigment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about); // Ensure this layout file exists

        ImageView profileImage = findViewById(R.id.profile_image);
        profileImage.setBackgroundResource(R.drawable.circular_shape);

        TableLayout tableLayout = findViewById(R.id.table_layout);

        // Set up the GitHub button to open the GitHub page
        Button githubButton = findViewById(R.id.github_button);
        githubButton.setOnClickListener(v -> {
            // Create an Intent to open the GitHub URL
            String url = "https://github.com/Anism212/ict602.git"; // Replace with your actual GitHub URL
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); // Inflate your menu resource
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSettings) {
            // Navigate to MainActivity
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            return true;
        } else if (id == R.id.menuAbout) {
            // No action needed as we are already in About activity
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
