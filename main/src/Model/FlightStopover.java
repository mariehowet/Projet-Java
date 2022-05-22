package Model;

import java.util.GregorianCalendar;

public class FlightStopover {

    private int flightId;
    private String departureAirportName;
    private String arrivalAirportName;
    private GregorianCalendar departureDate;
    private GregorianCalendar arrivalDate;
    private String departureHour;
    private String arrivalHour;
    private String stopoverAirportName;
    private Integer durationStopover;

    public FlightStopover(int flightId, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, String departureHour, String arrivalHour, String stopoverAirportName, Integer durationStopover) {
        this.flightId = flightId;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
        this.stopoverAirportName = stopoverAirportName;
        this.durationStopover = durationStopover;
    }

    public FlightStopover(int flightId, String departureAirportName, String arrivalAirportName, GregorianCalendar departureDate, GregorianCalendar arrivalDate, String departureHour, String arrivalHour) {
        this.flightId = flightId;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
    }

    public void setStopoverAirportName(String stopoverAirportName) {
        this.stopoverAirportName = stopoverAirportName;
    }

    public void setDurationStopover(Integer durationStopover) {
        this.durationStopover = durationStopover;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public GregorianCalendar getDepartureDate() {
        return departureDate;
    }

    public GregorianCalendar getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public String getStopoverAirportName() {
        return stopoverAirportName;
    }

    public Integer getDurationStopover() {
        return durationStopover;
    }
}
