package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String []cities = {"Edmonton", "Paris", "London"};

    ArrayList<String> cityList;

    ArrayAdapter<String> cityAdapter;// Adapter bridges the gap between listview and Java(????)makes arrays work

    ListView listView;

    //Attributes
    Button AddCityButton;//Add City button
    Button confirmTextButton;//Confirm button
    Button DeleteCityButton;//Delete City button
    EditText inputText;//Textfield for user input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.city_list);
        cityList = new ArrayList<>();
        cityList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, cityList);

        listView.setAdapter(cityAdapter);// set the array adapter to the list view.







        inputText = (EditText) findViewById(R.id.editTextText);// sets inputText to the ID of the textfield for typing in the city


        AddCityButton = findViewById(R.id.addButton);//sets AddCityButton variable to ID of "Add City" button.
        AddCityButton.setOnClickListener(view -> {//Does functionality for when button is pressed


            confirmTextButton.setVisibility(View.VISIBLE);
            inputText.setVisibility(View.VISIBLE);



        });//AddCityButton End



        confirmTextButton = findViewById(R.id.confirmButton);//sets confirmTextButton variable to ID of "Confirm" button.
        confirmTextButton.setOnClickListener(view -> {//Does functionality when button is clicked


            //If user does not input text and presses button do nothing
            if(inputText.getText().toString().isEmpty() ){
                Toast.makeText(this, "Empty Textfield", Toast.LENGTH_SHORT).show();

                return;
            }


            cityList.add(inputText.getText().toString());// we convert the thing we get from textfield into a string, and then add it to the array.
            inputText.getText().clear();
            cityAdapter.notifyDataSetChanged();//Updates changes to array

        });//confirm button end


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){//onitemclick is slightly different than onclicklistener in the sense that you're able to select one of the elements(i think???)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);//Gets item selected by user in the list

                // Log.d("Checking",selectedItem); //For testing. see "Logcat" at bottom and search "Checking" for tag.

                // Boolean b = cityList.remove(selectedItem); // this is the second version, using remove object. Not preferred
                //Log.d("",b.toString());
                //cityAdapter.notifyDataSetChanged();


                DeleteCityButton = findViewById(R.id.deleteButton);//sets confirmTextButton variable to ID of confirm button
                DeleteCityButton.setOnClickListener(view2 -> {

                    for (int i = 0; i < cityList.size(); i++) { //Removes selected item from the list
                        if (cityList.get(i) == selectedItem) {
                            cityList.remove(i);
                            break;

                        }
                    }


                    cityAdapter.notifyDataSetChanged();//Updates array
                });//Delete City button end


            }//onItemClick


        });



    }
}