package quim.cat.legolistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class LegoDownloader extends AsyncTask<Void, String, Boolean> {

    private String textCodi;
    private Context context;
    private Spinner llista;
    public LegoDownloader(Context context, String textCodi,Spinner llista) {
        this.context = context;
        this.textCodi = textCodi;
        this.llista = llista;

    }

   private ProgressDialog pDialog;



    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle(R.string.please_wait);
        String msg = context.getResources().getString(R.string.updating_lego);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    @Override protected Boolean doInBackground(Void... params) {
        int count;
        try {
            Log.e("CODIGO: ",textCodi);
            URL url = new URL("http://stucom.flx.cat/lego/get_set_parts.php?set="+textCodi+"&key=7654a5cd136677650d93cd77af591956");
            URLConnection connection = url.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            pDialog.setMax(lengthOfFile);
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                output.write(data, 0, count);
            }
            input.close();
            output.flush();
            String xml = new String(output.toByteArray());
            Log.e("xml: ", xml);
            File dir = context.getExternalFilesDir(null);
            if (dir == null) return false;
            File f = new File(dir, "currencies.csv");
            if (!f.exists()) return false;
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(f));
            String line;
            List<Lego> dades = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");
                for(String string :parts){
                    String [] parts2 =line.split("\t");
                        Lego l = new Lego(parts2[0], parts2[1], parts2[2], parts2[3], parts2[4], parts2[5], parts2[6], parts2[7], parts2[8], parts2[9], parts2[10]);
                        if(!parts2[0].equalsIgnoreCase("part_id")){
                            dades.add(l);
                        }
                }
            }
            llenarLista(dades);
            f = new File(dir, "lego.csv");
            f.delete();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }
        return true;
    }
    @Override public void onPostExecute(Boolean result) {
        pDialog.dismiss();
    }

    private void llenarLista(List<Lego> dades){

        SimpleAdapter adapter = new SimpleAdapter(
                this.context,
                dades,
                R.layout.llista_item,
                new String[] { "part_name", "qty", "part_img_url" },
                new int[] { R.id.nom, R.id.quantity, R.id.image }
        );
        llista.setAdapter(adapter);
    }




}


