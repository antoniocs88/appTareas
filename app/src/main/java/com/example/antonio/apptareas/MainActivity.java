package com.example.antonio.apptareas;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.antonio.apptareas.db.ControladorDB;

public class MainActivity extends AppCompatActivity {

    ControladorDB controladorDB;
    private ArrayAdapter<String> miAdapter;
    ListView listViewTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.duelodestino);
        mediaplayer.start();

        controladorDB=new ControladorDB(this);
        listViewTareas = (ListView)findViewById(R.id.listaTareas);
        actualizarUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final EditText cajaTexto=new EditText(this);
        //TO - DO
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nueva Tarea")
                .setMessage("Escribe tu nueva Tarea")
                .setView(cajaTexto)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String tarea = cajaTexto.getText().toString();
                        controladorDB.addTarea(tarea);
                        actualizarUI();

                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();



        return super.onOptionsItemSelected(item);
    }

    private void actualizarUI(){

            if(controladorDB.numeroRegistros()==0){
                listViewTareas.setAdapter(null);
            }else{
                miAdapter=new ArrayAdapter<>(this, R.layout.item_tarea, R.id.task_title, controladorDB.obtenerTareas());
                listViewTareas.setAdapter(miAdapter);
            }
    }

    public void borrarTarea (View view){

        View parent = (View) view.getParent();

        TextView tareaTextView = (TextView) parent.findViewById(R.id.task_title);

        String tarea = tareaTextView.getText().toString();

        controladorDB.borrarTarea(tarea);
        actualizarUI();

    }
}
