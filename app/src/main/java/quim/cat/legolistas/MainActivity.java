package quim.cat.legolistas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;



public class MainActivity extends AppCompatActivity {

    Lego Lego;
    ListView llista1;
    EditText codi1;
    ImageButton search1;
    LegoDownloader ld;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llista1 = (ListView) findViewById(R.id.llista);
        codi1 = (EditText) findViewById(R.id.codi);
        search1 = (ImageButton) findViewById(R.id.search);
        init();
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloaderPack(codi1,llista1);
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




