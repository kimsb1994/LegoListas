package quim.cat.legolistas;

import android.database.MatrixCursor;

import java.util.List;

public class SpinnerCursor extends MatrixCursor {

	public static final String[] COLNAMES = { "_id","part_id"};

	public SpinnerCursor(List<Lego> LegoIdLista) {
		super(COLNAMES);
		int n = 0;
		for (Lego c : LegoIdLista) {
			String[] row = new String[2];
			row[0] = String.valueOf(++n);
			row[1] = c.getPart_id();
			this.addRow(row);
		}
	}
}
