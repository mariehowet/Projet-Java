package View;

import Model.Stopover;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StopoversModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<Stopover> contents;

    public StopoversModel(ArrayList<Stopover> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Aéroport d'escale");
        columnNames.add("Durée de l'escale");
        this.contents = contents;
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public String getColumnName (int column) { return columnNames.get(column);}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stopover stopover = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stopover.getAirport();
            case 1:
                return stopover.getDuration();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 1:
                c = int.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
