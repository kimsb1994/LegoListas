package quim.cat.legolistas;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class PiezaActivity extends AppCompatActivity {

    List<Lego> dades2 = LegoDownloader.dades2;
    public PiezaActivity(Context context, int posicion) {
        this.context = context;
        this.posicion = posicion;
    }
    private ListView llista;
    private Context context;
    private int posicion;
    private ImageButton volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectlego);
        volver = (ImageButton) findViewById(R.id.volver);
        CatalogAdapter2 adapter = new CatalogAdapter2(context,dades2,posicion);
        llista.setAdapter(adapter);
    }
}

class CatalogAdapter2 extends BaseAdapter {
    private Context context;
    private List<Lego> dades2;
    private int posicion;


    public CatalogAdapter2(Context context, List<Lego> dades2, int posicion) {
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

        Lego lego = dades2.get(position);
        Bitmap image = lego.getBitmap();
        holder.imImage.setImageBitmap(image);
        String id = lego.getPart_name();
        holder.tvid.setText(id);
        String name = lego.getPart_name();
        holder.tvnom.setText(name);
        String color = lego.getPart_name();
        holder.tvColor.setText(color);
        String type = String.valueOf(lego.getQty());
        holder.tvType.setText(type);
        return myView;
    }
}
