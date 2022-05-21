package View;

import Model.FlightOption;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class FlightOptionsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<FlightOption> contents;

    public FlightOptionsModel (ArrayList<FlightOption> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Type de siège");
        columnNames.add("Prix du type de siège");
        columnNames.add("Poids des bagages");
        columnNames.add("Prix du poids des bagages");
        columnNames.add("Prix Total (€)");
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
        FlightOption flightOption = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flightOption.getSeatType();
            case 1:
                return flightOption.getPriceSeatType();
            case 2:
                return flightOption.getLuggageWeight();
            case 3:
                return flightOption.getPriceLuggageWeight();
            case 4:
                return flightOption.getTotalPrice();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 0:
            case 2: c = String.class;
                break;
            default: c = Double.class;
        }
        return c;
    }
}
