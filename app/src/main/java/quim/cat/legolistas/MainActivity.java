package quim.cat.legolistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public class Product extends HashMap<String, Object> {
        public Product(String id, String name) {
            this.put("id", id);
            this.put("quantitat", name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView llista = (ListView) findViewById(R.id.llista);

        List<Product> dades = new ArrayList<>();/*
        dades.add(new Product("Disc dur 1TByte", 2, R.drawable.hdd));
        dades.add(new Product("Monitor 25'", 2, R.drawable.lcd));*/

        SimpleAdapter adapter = new SimpleAdapter(
                MainActivity.this,
                dades,
                R.layout.llista_item,
                new String[] { "name", "quantitat", "image" },
                new int[] { R.id.nom, R.id.quantitat, R.id.imatge }
        );
        llista.setAdapter(adapter);
    }

}




