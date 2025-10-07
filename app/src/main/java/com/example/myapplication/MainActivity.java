package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> listaProduktow;
    private ArrayAdapter<String> arrayAdapter;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaProduktow =  new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaProduktow);
        listView = findViewById(R.id.listViewListaZakupow);
        listView.setAdapter(arrayAdapter);
        button = findViewById(R.id.buttonDodajProdukt);
        editText = findViewById(R.id.editTextProdukt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProduktow.add(editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i zawiera intex kliknietego elementu
                //view.setBackgroundColor(Color.GREEN);
                //listaProduktow.remove(i);
                TextView textView = (TextView)view;
                if (textView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG){
                    textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                }
                else{
                    textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}