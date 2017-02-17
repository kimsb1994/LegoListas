package quim.cat.legolistas;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class PiezaActivity extends AppCompatActivity {
    public PiezaActivity() {
    }
    private ListView llista;
    private Context context;
    private int posicion;
    private ImageButton volver;
    Intent in = this.getIntent();
    String id = in.getStringExtra("id");
    String name = in.getStringExtra("name");
    String type = getIntent().getStringExtra("type");
    String color = getIntent().getStringExtra("color");
    String image = getIntent().getStringExtra("image");

    List<String> dades2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectlego);
        dades2.add(id);
        dades2.add(name);
        dades2.add(type);
        dades2.add(color);
        dades2.add(image);


        volver = (ImageButton) findViewById(R.id.volver);
        CatalogAdapter2 adapter = new CatalogAdapter2(context,dades2,posicion);
        llista.setAdapter(adapter);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("parametro", "string");
                startActivity(intent);
            }
        });
    }
}
class CatalogAdapter2 extends BaseAdapter {
    private Context context;
    private List<String> dades2;
    private int posicion;


    public CatalogAdapter2(Context context, List<String> dades2, int posicion) {
        this.context = context;
        this.dades2 = dades2;
        this.posicion = posicion;
    }

    @Override public int getCount() { return dades2.size(); }
    @Override public Object getItem(int position) { return dades2.get(posicion); }
    @Override public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        public TextView tvid;
        public TextView tvnom;
        public TextView tvColor;
        public TextView tvType;
        public ImageView imImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (myView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView = inflater.inflate(R.layout.selectlego, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.imImage = (ImageView) myView.findViewById(R.id.imatge);
            holder.tvid = (TextView) myView.findViewById(R.id.id);
            holder.tvnom = (TextView) myView.findViewById(R.id.nom);
            holder.tvColor = (TextView) myView.findViewById(R.id.type);
            holder.tvType = (TextView) myView.findViewById(R.id.color);
            myView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) myView.getTag();
        Bitmap loadedImage = null;
        try {
            URL imageUrl = new URL(dades2.get(5));
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap image = loadedImage;
        holder.imImage.setImageBitmap(image);
        String id = dades2.get(1);
        holder.tvid.setText(id);
        String name = dades2.get(2);
        holder.tvnom.setText(name);
        String color = dades2.get(4);
        holder.tvColor.setText(color);
        String type = dades2.get(3);
        holder.tvType.setText(type);
        return myView;
    }
}