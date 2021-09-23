package com.warh.demolab1;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder>{

    private final String TAG = "CategoriaAdapter";
    private List<String> mDataSet;

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
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#cfcf00"));
                break;
            case 1:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#9ecf00"));
                break;
            case 2:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#6ecf00"));
                break;
            case 3:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#37cf00"));
                break;
            case 4:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#00cf8d"));
                break;
            case 5:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#00a9cf"));
                break;
            default:
                viewHolder.contenedor().findViewById(R.id.filaCatCardView).setBackgroundColor(Color.parseColor("#ffffff"));
                break;
        }

        viewHolder.nombreInput.setText(categoria);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
