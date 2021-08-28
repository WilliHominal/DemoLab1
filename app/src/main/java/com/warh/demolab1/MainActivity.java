package com.warh.demolab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner categoriaSpinner;
    ArrayAdapter<CharSequence> adapterCategoriaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriaSpinner = (Spinner) findViewById(R.id.categoriaSpinner);
        adapterCategoriaSpinner = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_dropdown_item);

        categoriaSpinner.setAdapter(adapterCategoriaSpinner);
        categoriaSpinner.setPrompt(getResources().getString(R.string.categoria_label).toUpperCase());

    }
}