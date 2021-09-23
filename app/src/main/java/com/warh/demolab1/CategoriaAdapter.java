package com.warh.demolab1;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder>{

    private final List<String> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreInput;
        CheckBox estaSeleccionada;
        View view;

        public ViewHolder(View fila){
            super(fila);
            view = fila;
            nombreInput = fila.findViewById(R.id.nombreFilaInput);
            estaSeleccionada = fila.findViewById(R.id.filaCatCheckBox);
        }

        public View contenedor(){
            return view;
        }

        public TextView getNombreInput(){
            return nombreInput;
        }

        public boolean getEstaSeleccionada() { return estaSeleccionada.isChecked(); }
    }

    public CategoriaAdapter(List<String> dataSet){
        mDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_cat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.nombreInput.setTag(position);
        viewHolder.estaSeleccionada.setTag(position);
        String categoria = mDataSet.get(position);

        switch (position){
            case 0:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_indumentaria));
                break;
            case 1:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_electronica));
                break;
            case 2:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_entretenimiento));
                break;
            case 3:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_jardin));
                break;
            case 4:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_vehiculos));
                break;
            case 5:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(ContextCompat.getColor(viewHolder.view.getContext(), R.color.color_juguetes));
                break;
        }

        viewHolder.nombreInput.setText(categoria);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
