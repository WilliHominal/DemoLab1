package com.warh.demolab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner categoriaSpinner;
    ArrayAdapter<CharSequence> adapterCategoriaSpinner;

    Switch descuentoSwitch;
    CheckBox retiroEnPersonaCheckBox;
    CheckBox terminosCondicionesCheckBox;
    Button publicarBtn;
    SeekBar descuentoEnvioSeekbar;

    LinearLayout descuentoLayout;
    TextView direccionRetiroLabel;
    EditText direccionRetiroInput;
    TextView descuentoActualLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descuentoLayout = (LinearLayout) findViewById(R.id.descuentoEnvioLayout);
        direccionRetiroLabel = (TextView) findViewById(R.id.direccionRetiroLbl);
        direccionRetiroInput = (EditText) findViewById(R.id.direccionRetiroInput);
        descuentoActualLabel = (TextView) findViewById(R.id.descuentoActualLbl);
        descuentoActualLabel.setText(getString(R.string.descuentoActual_label, 0));

        categoriaSpinner = (Spinner) findViewById(R.id.categoriaSpinner);
        adapterCategoriaSpinner = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_dropdown_item);

        categoriaSpinner.setAdapter(adapterCategoriaSpinner);
        categoriaSpinner.setPrompt(getResources().getString(R.string.categoria_label).toUpperCase());

        descuentoSwitch = (Switch) findViewById(R.id.descuentoEnvioSwitch);
        descuentoSwitch.setOnCheckedChangeListener((boton, activo) -> {
            if (activo){
                descuentoLayout.setVisibility(View.VISIBLE);
                descuentoActualLabel.setVisibility(View.VISIBLE);
            } else {
                descuentoLayout.setVisibility(View.GONE);
                descuentoActualLabel.setVisibility(View.GONE);
            }
        });

        retiroEnPersonaCheckBox = (CheckBox) findViewById(R.id.retiroEnPersonaCheckbox);
        retiroEnPersonaCheckBox.setOnCheckedChangeListener((boton, activo) -> {
            if (activo){
                direccionRetiroLabel.setVisibility(View.VISIBLE);
                direccionRetiroInput.setVisibility(View.VISIBLE);
            } else {
                direccionRetiroLabel.setVisibility(View.GONE);
                direccionRetiroInput.setVisibility(View.GONE);
            }
        });

        publicarBtn = (Button) findViewById(R.id.publicarBtn);

        terminosCondicionesCheckBox = (CheckBox) findViewById(R.id.terminosCondicionesCheckbox);
        terminosCondicionesCheckBox.setMovementMethod(LinkMovementMethod.getInstance());
        terminosCondicionesCheckBox.setOnCheckedChangeListener((boton, activo) -> publicarBtn.setEnabled(activo));

        descuentoEnvioSeekbar = (SeekBar) findViewById(R.id.descuentoEnvioSeekbar);
        descuentoEnvioSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int valorActual, boolean b) {
                descuentoActualLabel.setText(getString(R.string.descuentoActual_label, valorActual));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}