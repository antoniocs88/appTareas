package com.example.antonio.apptareas;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.respiracion);
        mediaplayer.start();

        Typeface miFuente = Typeface.createFromAsset(getAssets(), "Starjedi.ttf");

        TextView titulo2= (TextView)findViewById(R.id.titulo);

        titulo2.setTypeface(miFuente);

    }

    public void crearCuenta(View view){

        Toast toast = Toast.makeText(this, "Funcion no disponible", Toast.LENGTH_LONG);
        toast.show();

    }

    public void login (View view){

        TextInputEditText user = (TextInputEditText)findViewById(R.id.cajaUser);
        TextInputEditText pass = (TextInputEditText)findViewById(R.id.cajaPass);

        if(user.getText().toString().equals("antoniocs88") && pass.getText().toString().equals("1234")){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast toast2 = Toast.makeText(this, "Contraseña o usuario incorrecto", Toast.LENGTH_LONG);
            toast2.show();
        }

    }
}
