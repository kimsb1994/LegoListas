package quim.cat.legolistas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CatalogAdapter extends BaseAdapter {
    private Context context;
    private List<Lego> dades2;


    public CatalogAdapter(Context context, List<Lego> dades2) {
        this.context = context;
        this.dades2 = dades2;
    }

    @Override public int getCount() { return dades2.size(); }
    @Override public Object getItem(int position) { return dades2.get(position); }
    @Override public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        public TextView tvNom;
        public TextView tvQuantity;
        public ImageView imImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (myView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView = inflater.inflate(R.layout.llista_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.tvNom = (TextView) myView.findViewById(R.id.nom);
            holder.tvQuantity = (TextView) myView.findViewById(R.id.quantity);
            holder.imImage = (ImageView) myView.findViewById(R.id.imatge);
            myView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) myView.getTag();

        Lego lego = dades2.get(position);
        Bitmap image = lego.getBitmap();
        holder.imImage.setImageBitmap(image);
        String name = lego.getPart_name();
        holder.tvNom.setText(name);
        String quantity = String.valueOf(lego.getQty());
        holder.tvQuantity.setText(quantity);
        return myView;
    }
}