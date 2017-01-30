package quim.cat.legolistas;

/**
 * Created by DAM on 30/1/17.
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lego {

    public class Kit {
        String id;
        String name;
    }

    private String time;
    private ArrayList<Kit> legos;

    public Lego() {
        time = null;
        legos = new ArrayList<Kit>();
    }

    public String getTime() { return time; }
    public ArrayList<Kit> getKit() { return this.legos; }

    public Kit getKit(String num) {
        for(Kit k : legos) {
            if (k.id.equals(num)) return k;
        }
        return null;
    }
    public int getPosition(String id) {
        return legos.indexOf(getKit(id));
    }

    public boolean loadFromFile(Context context) {
        BufferedReader reader = null;
        try {
            File dir = context.getExternalFilesDir(null);
            if (dir == null) return false;
            File f = new File(dir, "kit.csv");
            if (!f.exists()) return false;
            reader = new BufferedReader(new FileReader(f));
            time = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length != 2) continue;
                Kit k = new Kit();
                k.id = parts[0];
                k.name = parts[1];
                legos.add(k);
            }
            return true;
        }
        catch(Exception e) {
            Log.e("flx", "ERROR : " + e);
            return false;
        }
        finally {
            if (reader != null) {
                try { reader.close(); }
                catch (IOException e) { }
            }
        }
    }
}

