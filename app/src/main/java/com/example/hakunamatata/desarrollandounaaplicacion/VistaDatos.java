package com.example.hakunamatata.desarrollandounaaplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VistaDatos extends AppCompatActivity {

    TextView tvNombreCompleto, tvFecha, tvTelefono, tvEmail, tvDescripcion ;
    Button   btnEditarDatos ;
    Intent   intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_datos);
        inicializarVariables() ;
        // Recibir valores
        recibirDatos() ;

        btnEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(VistaDatos.this , MainActivity.class ) ;
                enviarDatos() ;
                startActivity(intent);
                finish();
            }// fin del método onClick
        }); // fin del listener


    } // fin del método onCreate

    private void enviarDatos(){
        intent.putExtra(getResources().getString(R.string.nombre_usuario) , tvNombreCompleto.getText().toString());
        intent.putExtra(getResources().getString(R.string.fecha) , tvFecha.getText().toString());
        intent.putExtra(getResources().getString(R.string.telefono) , tvTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.email) , tvEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.descripcion) , tvDescripcion.getText().toString());
    } // fin del método enviarDatos

    private void recibirDatos() {
        Bundle parametros = getIntent().getExtras();
        String nombreCompleto = parametros.getString(getResources().getString(R.string.nombre_usuario)) ;
        String fecha = parametros.getString(getResources().getString(R.string.fecha)) ;
        String telefono = parametros.getString(getResources().getString(R.string.telefono)) ;
        String email = parametros.getString(getResources().getString(R.string.email)) ;
        String descripcion = parametros.getString(getResources().getString(R.string.descripcion)) ;
        tvNombreCompleto.setText(nombreCompleto);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);
    } // fin del método recibirDatos

    private void inicializarVariables() {
        tvNombreCompleto = (TextView) findViewById(R.id.tvNombreCompleto) ;
        tvFecha          = (TextView) findViewById(R.id.tvFecha) ;
        tvTelefono       = (TextView) findViewById(R.id.tvTelefono) ;
        tvEmail          = (TextView) findViewById(R.id.tvEmail) ;
        tvDescripcion    = (TextView) findViewById(R.id.tvDescripcion) ;
        btnEditarDatos   = (Button) findViewById(R.id.btnEditarDatos) ;
    } // fin del método inicializarVariables

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            intent = new Intent(this, MainActivity.class) ;
            enviarDatos() ;
            startActivity(intent) ;
            finish();
        } // fin de if
        return super.onKeyDown(keyCode, event) ;
    } // fin del método onKeyDown

} // fin de la clase VistaDatos
