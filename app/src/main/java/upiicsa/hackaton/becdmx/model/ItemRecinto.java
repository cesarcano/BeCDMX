package upiicsa.hackaton.becdmx.model;

public class ItemRecinto {
    private String id;
    private String nombre;
    private String direccion;
    private double lat;
    private double lng;

    public ItemRecinto(String id, String nombre, String direccion, double lat, double lng) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
