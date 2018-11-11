package upiicsa.hackaton.becdmx.model;

public class ItemSabias {
    private String img_url;
    private String nombre;

    public ItemSabias(String img_url, String nombre) {
        this.img_url = img_url;
        this.nombre = nombre;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
