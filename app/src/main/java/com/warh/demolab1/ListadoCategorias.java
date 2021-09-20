package com.warh.demolab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListadoCategorias extends AppCompatActivity {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CategoriaAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected List<String> categoriasList;

    //Button botonAceptar;

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
    }
}