package quim.cat.legolistas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    Lego Lego;
    final LegoDownloader ld = new LegoDownloader(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView llista1 = (ListView) findViewById(R.id.llista);
        EditText codi1 = (EditText) findViewById(R.id.codi);
        ImageButton search1 = (ImageButton) findViewById(R.id.search);
        init();

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ld.execute();
            }
        });
    }
    public void init() {
        Lego = new Lego();
    }
}




