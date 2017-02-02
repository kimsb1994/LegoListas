package quim.cat.legolistas;

/**
 * Created by DAM on 30/1/17.
 */


public class Lego {
    private long id;
    private String id_piece;
    private String nombre;
    private String image;
    private int cantidad;

    public Lego() {
    }

    public Lego(long id, String id_piece, String nombre, String image, int cantidad) {
        this.id = id;
        this.id_piece = id_piece;
        this.nombre = nombre;
        this.image = image;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_piece() {
        return id_piece;
    }

    public void setId_piece(String id_piece) {
        this.id_piece = id_piece;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Lego{" +
                "id=" + id +
                ", id_piece='" + id_piece + '\'' +
                ", nombre='" + nombre + '\'' +
                ", image='" + image + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}

