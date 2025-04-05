package com.example.pract_14;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Sample data for ListView
    String[] listItems = {"Item1", "Item2", "Item3", "Item4", "Item5"};

    // Sample images for GridView (Replace with your own images in drawable folder)
    int[] images = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        ListView listView = findViewById(R.id.listView);
        GridView gridView = findViewById(R.id.gridView);

        // Set up ListView Adapter
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listItems
        );
        listView.setAdapter(listAdapter);

        // Handle ListView Item Click
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "Clicked: " + listItems[position], Toast.LENGTH_SHORT).show()
        );

        // Set up GridView Adapter
        ImageAdapter gridAdapter = new ImageAdapter(this, images);
        gridView.setAdapter(gridAdapter);

        // Handle GridView Item Click
        gridView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "Image " + position + " Clicked", Toast.LENGTH_SHORT).show()
        );
    }
}
