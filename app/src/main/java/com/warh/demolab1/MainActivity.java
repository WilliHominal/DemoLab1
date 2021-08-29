package com.warh.demolab1;

import android.support.v4.content.ContextCompat;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner categoriaSpinner;
    ArrayAdapter<CharSequence> adapterCategoriaSpinner;

    Switch descuentoSwitch;
    CheckBox retiroEnPersonaCheckBox;
    CheckBox terminosCondicionesCheckBox;
    Button publicarBtn;
    SeekBar descuentoEnvioSeekbar;

    EditText tituloInput;
    EditText descripcionInput;
    EditText emailInput;
    EditText precioInput;
    EditText direccionRetiroInput;

    TextView tituloLabel;
    TextView descripcionLabel;
    TextView emailLabel;
    TextView precioLabel;
    TextView categoriaLabel;
    TextView direccionRetiroLabel;
    TextView descuentoActualLabel;
    LinearLayout descuentoLayout;

    Toast toastMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tituloInput = (EditText) findViewById(R.id.tituloInput);
        descripcionInput = (EditText) findViewById(R.id.descripcionInput);
        emailInput = (EditText) findViewById(R.id.correoInput);
        precioInput = (EditText) findViewById(R.id.precioInput);

        tituloLabel = (TextView) findViewById(R.id.tituloLbl);
        descripcionLabel = (TextView) findViewById(R.id.descripcionLbl);
        emailLabel = (TextView) findViewById(R.id.correoLbl);
        precioLabel = (TextView) findViewById(R.id.precioLbl);
        categoriaLabel = (TextView) findViewById(R.id.categoriaLbl);

        descuentoLayout = (LinearLayout) findViewById(R.id.descuentoEnvioLayout);
        direccionRetiroLabel = (TextView) findViewById(R.id.direccionRetiroLbl);
        direccionRetiroInput = (EditText) findViewById(R.id.direccionRetiroInput);
        descuentoActualLabel = (TextView) findViewById(R.id.descuentoActualLbl);
        descuentoActualLabel.setText(getString(R.string.descuentoActual_label, 0));
        toastMsg = Toast.makeText(this, "", Toast.LENGTH_LONG);

        categoriaSpinner = (Spinner) findViewById(R.id.categoriaSpinner);
        adapterCategoriaSpinner = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);

        categoriaSpinner.setAdapter(adapterCategoriaSpinner);

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

        publicarBtn.setOnClickListener(view -> {
            CharSequence mensaje;
            int duracion = Toast.LENGTH_SHORT;

            int validacion = validarCampos();

            switch (validacion){
                case -1: mensaje = "Los campos con asterisco (*) son obligatorios"; break;
                case -10: mensaje = "Título no puede contener caracteres especiales"; break;
                case -20: mensaje = "Descripción no puede contener caracteres especiales"; break;
                case -30: mensaje = "Correo tiene un formato inválido"; break;
                case -40: mensaje = "Precio tiene un formato inválido"; break;
                case -41: mensaje = "Precio debe ser mayor a cero (0)"; break;
                case -50:
                    mensaje = "Por favor seleccione un porcentaje de descuento mayor a cero (0) o quite la opción de ofrecer descuento de envío";
                    duracion = Toast.LENGTH_LONG;
                    break;
                default: mensaje = "Producto publicado con éxito";
            }


            Toast.makeText(getBaseContext(), mensaje, duracion).show();
        });
    }

    private int validarCampos (){
        String regexCamposTexto = "^[a-zA-ZáéíóúñÑ\\d,.\\n\\s]+$";
        String regexCamposNumero = "(^[\\d]+$)|(^[\\d]*\\.[\\d]+$)";
        String regexCorreo = "[\\w]+[@][a-zA-Z]{3,}.*";

        int numeroError = 0;

        tituloLabel.setTextColor(ContextCompat.getColor(this, R.color.black));
        descripcionLabel.setTextColor(ContextCompat.getColor(this, R.color.black));
        precioLabel.setTextColor(ContextCompat.getColor(this, R.color.black));
        categoriaLabel.setTextColor(ContextCompat.getColor(this, R.color.black));
        descuentoActualLabel.setTextColor(ContextCompat.getColor(this, R.color.black));
        direccionRetiroLabel.setTextColor(ContextCompat.getColor(this, R.color.black));

        if (tituloInput.getText().toString().isEmpty()) {
            numeroError = -1;
            tituloLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (!tituloInput.getText().toString().matches(regexCamposTexto)) {
            if (numeroError == 0) numeroError = -10;
            tituloLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (!descripcionInput.getText().toString().isEmpty() && !descripcionInput.getText().toString().matches(regexCamposTexto)) {
            if (numeroError == 0) numeroError = -20;
            descripcionLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (!emailInput.getText().toString().isEmpty() && !emailInput.getText().toString().matches(regexCorreo)) {
            if (numeroError == 0) numeroError = -30;
            emailLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (precioInput.getText().toString().isEmpty()) {
            if (numeroError == 0) numeroError = -1;
            precioLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (!precioInput.getText().toString().matches(regexCamposNumero)) {
            if (numeroError == 0) numeroError = -40;
            precioLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else if (Integer.parseInt(precioInput.getText().toString()) <= 0) {
            if (numeroError == 0) numeroError = -41;
            precioLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (categoriaSpinner.getSelectedItemPosition() == 0) {
            if (numeroError == 0) numeroError = -1;
            categoriaLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (descuentoSwitch.isChecked() && descuentoEnvioSeekbar.getProgress() == 0) {
            if (numeroError == 0) numeroError = -50;
            descuentoActualLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        if (retiroEnPersonaCheckBox.isChecked() && !direccionRetiroInput.getText().toString().matches(regexCamposTexto)) {
            if (numeroError == 0) numeroError = -1;
            direccionRetiroLabel.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        return numeroError;
    }
}