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
    private ArrayList<Produkt> listaProduktow;
    private ArrayAdapter<Produkt> arrayAdapter;
    private Button button;
    private EditText editText;
    private EditText editTextCena;
    private TextView textViewSuma;

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
        editTextCena = findViewById(R.id.editTextCena);
        textViewSuma = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nazwa = editText.getText().toString();
                double cena = Double.parseDouble(editTextCena.getText().toString());
                listaProduktow.add(new Produkt(nazwa, cena));
                arrayAdapter.notifyDataSetChanged();
                wypiszSumeCen();
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i zawiera intex kliknietego elementu
                //view.setBackgroundColor(Color.GREEN);
                //listaProduktow.remove(i);
                if (listaProduktow.get(i).isCzySkreslone()){
                    listaProduktow.get(i).setCzySkreslone(false);
                }
                else{
                    listaProduktow.get(i).setCzySkreslone(true);
                }
                TextView textView = (TextView)view;
                if (textView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG){
                    textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                }
                else{
                    textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                arrayAdapter.notifyDataSetChanged();
                wypiszSumeCen();
            }
        });
    }
    public void wypiszSumeCen(){
        double suma = 0;
        for(int i = 0; i < listaProduktow.size(); i++){
            if(!listaProduktow.get(i).isCzySkreslone()) {
                suma += listaProduktow.get(i).getCena();
            }
        }
        textViewSuma.setText("Suma cen: " + suma);
    }
}
