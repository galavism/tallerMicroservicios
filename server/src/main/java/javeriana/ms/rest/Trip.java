package javeriana.ms.rest;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Trip {

    int id;
    String name;
    String departureLocation;
    String arrivalLocation;
    String date;

    //Constructor
    public Trip() {
    }

    public Trip(int id, String name, String departureLocation, String arrivalLocation, String date) {
        this.id = id;
        this.name = name;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.date = date;
    }

    //Get and Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //toString
    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
