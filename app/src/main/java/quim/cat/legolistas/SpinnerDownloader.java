package quim.cat.legolistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static quim.cat.legolistas.R.id.imatge;


public class SpinnerDownloader extends AsyncTask<Void, String, Boolean> {
    private Context context;
    private Spinner spinner;
    public static List<Lego> dadesId = new ArrayList<>();
    public SpinnerDownloader(Context context,Spinner spinner) {
        this.context = context;
        this.spinner = spinner;
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
            URL url = new URL("http://stucom.flx.cat/lego/search.php?query=&key=7654a5cd136677650d93cd77af591956");
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
            /* FINAL DEL CONNECT*/
            String xml = new String(output.toByteArray());
            BufferedReader reader = null;
            reader = new BufferedReader(new StringReader(xml));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");
                for(String string :parts){
                    String [] parts2 =line.split("\t");
                    if(!parts2[0].equalsIgnoreCase("part_id")) {
                        Lego l1 = new Lego(parts2[0]);
                        dadesId.add(l1);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }
        return true;
    }

    public static List<Lego> getDadesId() {
        return dadesId;
    }

    @Override public void onPostExecute(Boolean result) {
        updateSpinners();
        pDialog.dismiss();

    }


    public void updateSpinners() {
        SpinnerCursor cursor = new SpinnerCursor(dadesId);
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(
                context,
                R.layout.spinner_item,
                cursor,
                new String[]{"part_id"},
                new int[]{R.id.textView1},
                0);
        spinner.setAdapter(adapter);
    }
}


