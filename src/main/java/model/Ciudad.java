package model;

public class Ciudad {
    private String name;
    private double lat;
    private double lon;
    private String imagen;

    public Ciudad(String name, double lat, double lon, String imagen) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.imagen = imagen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return  name ;

    }
}
