package com.warh.demolab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ListadoCategorias extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected CategoriaAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected List<String> categoriasList;

    Button botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_categorias);

        categoriasList = Arrays.asList(getResources().getStringArray(R.array.categorias));

        mRecyclerView = findViewById(R.id.listaCategoriasRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CategoriaAdapter(categoriasList);
        mRecyclerView.setAdapter(mAdapter);

        botonAceptar = findViewById(R.id.aceptarCategoriaBtn);
        botonAceptar.setOnClickListener(view -> {
            int cantSeleccionados = 0;
            int posSeleccionada = -1;
            for (int i=0; i<categoriasList.size(); i++){
                if (((CategoriaAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(i)).getEstaSeleccionada()){
                    posSeleccionada = i;
                    cantSeleccionados++;
                }
            }
            if (cantSeleccionados == 1) {
                String nombreCheckedPos = ((CategoriaAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(posSeleccionada)).getNombreInput().getText().toString();

                Intent i1 = new Intent(ListadoCategorias.this, MainActivity.class);
                i1.putExtra("CAT_SELECCIONADA", nombreCheckedPos);
                setResult(RESULT_OK, i1);
                finish();
            } else {
                Toast.makeText(this, "Selecciona 1 categoría", Toast.LENGTH_SHORT).show();
            }
        });
    }
}