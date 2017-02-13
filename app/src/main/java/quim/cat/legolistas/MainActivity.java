package quim.cat.legolistas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Lego Lego;
    ListView llista1;
    EditText codi1;
    ImageButton search1;
    LegoDownloader ld;
    ImageView imatge2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llista1 = (ListView) findViewById(R.id.llista);
        codi1 = (EditText) findViewById(R.id.codi);
        search1 = (ImageButton) findViewById(R.id.search);
        imatge2 = (ImageView) findViewById(R.id.imatge);
        imatge2 = (ImageView) findViewById(R.id.volver);
        init();
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloaderPack(codi1,llista1);
            }
        });
        llista1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Click en " + id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Click fora", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void downloaderPack(EditText codi, ListView llisteta) {
        String textCodi = codi.getText().toString();
        ld = new LegoDownloader(this, textCodi, llisteta);
        ld.execute();
    }



    public void init() {
        Lego = new Lego();
    }
}




