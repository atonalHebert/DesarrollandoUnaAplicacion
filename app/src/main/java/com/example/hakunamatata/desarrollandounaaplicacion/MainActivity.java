package com.example.hakunamatata.desarrollandounaaplicacion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilNombreCompleto , tilFecha, tilTelefono, tilEmail, tilDescripcion ;
    private EditText        edtNombreCompleto , edtFecha, edtTelefono, edtEmail, edtDescripcion ;
    private Button          btnSiguiente ;
    // variables para capturar día, mes y año
    private int sAnio, sMes, sDia, hAnio, hMes, hDia ;
    static final int DATE_ID = 0 ;
    Calendar C = Calendar.getInstance() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main) ;
        inicializarVariablesReferencia() ;
        // se recibe la fecha del día actual, de hoy
        hAnio = C.get(Calendar.YEAR) ;
        hMes = C.get(Calendar.MONTH) ;
        hDia = C.get(Calendar.DAY_OF_MONTH) ;
        // Si el usuario está en VistaDatos, y da click en botón back, VistaDatos envia la información
        // y se recibe aquí en parámetros
        Bundle parametros = getIntent().getExtras();
        if(parametros!=null){
            String nombreCompleto = parametros.getString(getResources().getString(R.string.nombre_usuario)) ;
            String fecha = parametros.getString(getResources().getString(R.string.fecha)) ;
            String telefono = parametros.getString(getResources().getString(R.string.telefono)) ;
            String email = parametros.getString(getResources().getString(R.string.email)) ;
            String descripcion = parametros.getString(getResources().getString(R.string.descripcion)) ;
            edtNombreCompleto.setText(nombreCompleto);
            edtFecha.setText(fecha);
            edtTelefono.setText(telefono);
            edtEmail.setText(email);
            edtDescripcion.setText(descripcion);
        }// fin de if

        // Listener que estará atento al EditText de fecha
        edtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        // listener que estará atento al botón
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarValores()){
                    Intent intent = new Intent(MainActivity.this , VistaDatos.class ) ;
                    intent.putExtra(getResources().getString(R.string.nombre_usuario) , edtNombreCompleto.getText().toString());
                    intent.putExtra(getResources().getString(R.string.fecha) , edtFecha.getText().toString());
                    intent.putExtra(getResources().getString(R.string.telefono) , edtTelefono.getText().toString());
                    intent.putExtra(getResources().getString(R.string.email) , edtEmail.getText().toString());
                    intent.putExtra(getResources().getString(R.string.descripcion) , edtDescripcion.getText().toString());
                    startActivity(intent);
                    finish();
                }// fin de if
            }// fin del método onClick
        }); // fin del listener
    } // fin del método onCreate

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            sAnio = year ;
            sMes = month ;
            sDia = dayOfMonth ;
            colocarFecha() ;
        }
    };

    private void colocarFecha() {
        edtFecha.setText(sDia + "/" + (sMes+1)+"/" +sAnio);
    } // fin del método colocarFecha


    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_ID:
                return new DatePickerDialog(this, onDateSetListener, hAnio, hMes, hDia) ;
        }

        return null ;
    } // fin del método onCreateDialog

    private void inicializarVariablesReferencia() {
        tilNombreCompleto = (TextInputLayout) findViewById(R.id.tilNombreCompleto) ;
        tilFecha          = (TextInputLayout) findViewById(R.id.tilFecha) ;
        tilTelefono       = (TextInputLayout) findViewById(R.id.tilTelefono) ;
        tilEmail          = (TextInputLayout) findViewById(R.id.tilEmail) ;
        tilDescripcion    = (TextInputLayout) findViewById(R.id.tilDescripcion) ;
        edtNombreCompleto = (EditText) findViewById(R.id.edtNombreCompleto) ;
        edtFecha          = (EditText) findViewById(R.id.edtFecha) ;
        edtTelefono       = (EditText) findViewById(R.id.edtTelefono) ;
        edtEmail          = (EditText) findViewById(R.id.edtEmail) ;
        edtDescripcion    = (EditText) findViewById(R.id.edtDescripcion) ;
        btnSiguiente      = (Button) findViewById(R.id.btnSiguiente) ;
    } // fin del método inicializarVariablesReferencia

    private boolean validarValores(){

        boolean validado = true ;
        // valida nombre
        if (edtNombreCompleto.getText().toString().isEmpty()) {
            tilNombreCompleto.setError(getResources().getString(R.string.error_vacio));
            validado = false;
        } else
            tilNombreCompleto.setErrorEnabled(false);
        // valida fecha
        if (edtFecha.getText().toString().isEmpty()) {
            tilFecha.setError(getResources().getString(R.string.error_vacio));
            validado = false;
        } else
            tilFecha.setErrorEnabled(false);
        // valida telefono
        if (edtTelefono.getText().toString().isEmpty()) {
            tilTelefono.setError(getResources().getString(R.string.error_vacio));
            validado = false;
        } else
            tilTelefono.setErrorEnabled(false);
        // valida correo electrónico
        if (edtEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getResources().getString(R.string.error_vacio));
            validado = false;
        } else
            tilEmail.setErrorEnabled(false);
        // valida descripción
        if (edtDescripcion.getText().toString().isEmpty()) {
            tilDescripcion.setError(getResources().getString(R.string.error_vacio));
            validado = false;
        } else
            tilDescripcion.setErrorEnabled(false);
        // devuelve resultado de validación
        return validado ;
    }// fin del método validarValores


} // fin de la clase MainActivity
