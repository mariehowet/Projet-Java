package View;

import Model.FlightOfDepartureAirport;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightsOfDepartureAirportModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<FlightOfDepartureAirport> contents;

    public FlightsOfDepartureAirportModel (ArrayList<FlightOfDepartureAirport> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro vol");
        columnNames.add("Aéroport arrivée");
        columnNames.add("Date départ");
        columnNames.add("Date arrivée");
        columnNames.add("Nombre de places restantes");
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
        FlightOfDepartureAirport flightOfDepartureAirport = contents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flightOfDepartureAirport.getFlightId();
            case 1:
                return flightOfDepartureAirport.getArrivalAirportName();
            case 2:
                return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(flightOfDepartureAirport.getDepartureDate().getTime());
            case 3:
                return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(flightOfDepartureAirport.getArrivalDate().getTime());
            case 4:
                return flightOfDepartureAirport.getRemainingSeats();
            default:
                return null;
        }
    }

    public Class getColumClass(int column) {
        Class c;
        switch(column) {
            case 0:
            case 4:
                c = int.class;
                break;
            case 2:
            case 3:
                c = Date.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
