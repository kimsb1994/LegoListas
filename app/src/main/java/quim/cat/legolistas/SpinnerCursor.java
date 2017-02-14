package quim.cat.legolistas;

import android.database.MatrixCursor;

import java.util.List;

public class SpinnerCursor extends MatrixCursor {

	public static final String[] COLNAMES = { "id",};

	public SpinnerCursor(List<Lego> LegoIdLista) {
		super(COLNAMES);
		int n = 0;
		for (Lego c : LegoIdLista) {
			String[] row = new String[0];
			row[0] = c.getPart_id();
			this.addRow(row);
		}
	}
}
