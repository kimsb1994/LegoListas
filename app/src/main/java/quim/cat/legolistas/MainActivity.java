package quim.cat.legolistas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Lego Lego;
    ListView llista1;
    EditText codi1;
    ImageButton search1;
    LegoDownloader ld;
    SpinnerDownloader sd;
    PiezaActivity pa;
    ImageView imatge2;
    Spinner spinner;
    List<Lego> listaSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        llista1 = (ListView) findViewById(R.id.llista);
        codi1 = (EditText) findViewById(R.id.codi);
        search1 = (ImageButton) findViewById(R.id.search);
        imatge2 = (ImageView) findViewById(R.id.imatge);
        imatge2 = (ImageView) findViewById(R.id.volver);
        init();
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textCodi = codi1.getText().toString();
                downloaderPack(textCodi,llista1);
            }
        });
        downloadSpinner();

        llista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PiezaActivity.class);

                List<Lego> dades2 = ld.getDades2();
                intent.putExtra("id", dades2.get(position).getPart_id());
                intent.putExtra("name", dades2.get(position).getPart_name());
                intent.putExtra("type", dades2.get(position).getType());
                intent.putExtra("color", dades2.get(position).getLdraw_color_id());
                intent.putExtra("image", dades2.get(position).getPart_img_url());
                startActivity(intent);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listaSpinner = sd.getDadesId();
                String codigo = listaSpinner.get(i).getPart_id();
                downloaderPack(codigo,llista1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void downloaderPack(String codi, ListView llisteta) {
        ld = new LegoDownloader(this, codi, llisteta);
        ld.execute();
    }
    public void downloadSpinner() {
        sd = new SpinnerDownloader(this,spinner);
        sd.execute();
    }
    public void init() {
        Lego = new Lego();
    }
}




