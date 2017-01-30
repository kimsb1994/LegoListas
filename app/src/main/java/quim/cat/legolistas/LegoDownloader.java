package quim.cat.legolistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LegoDownloader extends AsyncTask<Void, String, Boolean> {

    private Context context;
    public LegoDownloader(Context context) {
        this.context = context;
    }

    private OnLegoLoadedListener listener = null;
    public void setOnLegoLoadedListener(OnLegoLoadedListener listener) {
        this.listener = listener;
    }

    private ProgressDialog pDialog;

    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        /*
        pDialog.setTitle(R.string.please_wait);
        String msg = context.getResources().getString(R.string.updating_lego);
        pDialog.setMessage(msg);
        */
        pDialog.setCancelable(true);
        pDialog.show();
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        int count;
        try {
            URL url = new URL("https://rebrickable.com/api/v3/lego/colors/?key=yPI00UPOyp");
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
            File dir = context.getExternalFilesDir(null);
            if (dir == null) return false;
            File f = new File(dir, "lego.csv");
            f.delete();
            PrintWriter wr = new PrintWriter(f);
            Pattern pattern = Pattern.compile(".*<Cube time='(.*)'.*");
            Matcher matcher = pattern.matcher(xml);
            if (!matcher.find()) return false;
            String time = matcher.group(1);
            wr.println(time);
            wr.println("LEGO!");
            pattern = Pattern.compile(".*<Cube currency='(.*)' rate='(.*)'.*");
            matcher = pattern.matcher(xml);
            while (matcher.find()) {
                String currency = matcher.group(1);
                String rate = matcher.group(2);
                wr.println(currency + ":" + rate);
            }
            wr.flush();
            wr.close();
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }

        return true;
    }

    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    @Override public void onPostExecute(Boolean result) {
        pDialog.dismiss();
        if (listener != null) listener.onLegoLoaded(result);
    }
}
