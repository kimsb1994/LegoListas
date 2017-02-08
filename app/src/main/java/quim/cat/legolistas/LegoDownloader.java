package quim.cat.legolistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static quim.cat.legolistas.R.id.codi;


public class LegoDownloader extends AsyncTask<String, String, Boolean> {

    private Context context;
    public LegoDownloader(ListView ListView, Context context) {
        this.context = context;
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
    @Override protected Boolean doInBackground(String...params) {
        int count;
        try {
            URL url = new URL("http://stucom.flx.cat/lego/get_set_parts.php?set=65012-1&key=7654a5cd136677650d93cd77af591956");
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
            File f = new File(dir, "lego.csv");
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
}


