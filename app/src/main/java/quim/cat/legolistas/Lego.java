package quim.cat.legolistas;

import android.graphics.Bitmap;

//Part_id /Qty //Ldraw_color_id/Type/Part_name/Color_name/Part_img_url/Element_id/Element_img_url/Rb_color_id/Part_type_id

public class Lego {
    private Bitmap image;
    private String part_id;
    private String qty;
    private String ldraw_color_id;
    private String type;
    private String part_name;
    private String color_name;
    private String part_img_url;
    private String element_id;
    private String element_img_url;
    private String rb_color_id;
    private String part_type_id;
    private Bitmap bitmap;



    public Lego() {
    }
    public Lego(String part_id) {
        this.part_id = part_id;
    }

    public Lego(String part_id, String qty, String ldraw_color_id, String type, String part_name, String color_name, String part_img_url, String element_id, String element_img_url, String rb_color_id, String part_type_id, Bitmap bitmap) {
        this.part_id = part_id;
        this.qty = qty;
        this.ldraw_color_id = ldraw_color_id;
        this.type = type;
        this.part_name = part_name;
        this.color_name = color_name;
        this.part_img_url = part_img_url;
        this.element_id = element_id;
        this.element_img_url = element_img_url;
        this.rb_color_id = rb_color_id;
        this.part_type_id = part_type_id;
        this.bitmap = bitmap;
    }
    public Lego(String part_name,String qty,String part_img_url){
        this.part_name = part_name;
        this.qty = qty;
        this.part_img_url = part_img_url;
    }
    public Lego (String part_name,String qty,String part_img_url, Bitmap image){
        this.part_name=part_name;
        this.qty = qty;
        this.part_img_url = part_img_url;
        this.image= image;
    }

    public Lego(String id, String name, String type, String color, String image) {
        this.part_id = part_id;
        this.part_name = part_name;
        this.type = type;
        this.rb_color_id = rb_color_id;
        this.element_img_url = element_img_url;

    }

    public Bitmap getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getLdraw_color_id() {
        return ldraw_color_id;
    }

    public void setLdraw_color_id(String ldraw_color_id) {
        this.ldraw_color_id = ldraw_color_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getColor_name(int position) {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getPart_img_url() {
        return part_img_url;
    }

    public void setPart_img_url(String part_img_url) {
        this.part_img_url = part_img_url;
    }

    public String getElement_id() {
        return element_id;
    }

    public void setElement_id(String element_id) {
        this.element_id = element_id;
    }

    public String getElement_img_url() {
        return element_img_url;
    }

    public void setElement_img_url(String element_img_url) {
        this.element_img_url = element_img_url;
    }

    public String getRb_color_id() {
        return rb_color_id;
    }

    public void setRb_color_id(String rb_color_id) {
        this.rb_color_id = rb_color_id;
    }

    public String getPart_type_id() {
        return part_type_id;
    }

    public void setPart_type_id(String part_type_id) {
        this.part_type_id = part_type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lego lego = (Lego) o;

        if (image != null ? !image.equals(lego.image) : lego.image != null) return false;
        if (part_id != null ? !part_id.equals(lego.part_id) : lego.part_id != null) return false;
        if (qty != null ? !qty.equals(lego.qty) : lego.qty != null) return false;
        if (ldraw_color_id != null ? !ldraw_color_id.equals(lego.ldraw_color_id) : lego.ldraw_color_id != null)
            return false;
        if (type != null ? !type.equals(lego.type) : lego.type != null) return false;
        if (part_name != null ? !part_name.equals(lego.part_name) : lego.part_name != null)
            return false;
        if (color_name != null ? !color_name.equals(lego.color_name) : lego.color_name != null)
            return false;
        if (part_img_url != null ? !part_img_url.equals(lego.part_img_url) : lego.part_img_url != null)
            return false;
        if (element_id != null ? !element_id.equals(lego.element_id) : lego.element_id != null)
            return false;
        if (element_img_url != null ? !element_img_url.equals(lego.element_img_url) : lego.element_img_url != null)
            return false;
        if (rb_color_id != null ? !rb_color_id.equals(lego.rb_color_id) : lego.rb_color_id != null)
            return false;
        return part_type_id != null ? part_type_id.equals(lego.part_type_id) : lego.part_type_id == null;

    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (part_id != null ? part_id.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (ldraw_color_id != null ? ldraw_color_id.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (part_name != null ? part_name.hashCode() : 0);
        result = 31 * result + (color_name != null ? color_name.hashCode() : 0);
        result = 31 * result + (part_img_url != null ? part_img_url.hashCode() : 0);
        result = 31 * result + (element_id != null ? element_id.hashCode() : 0);
        result = 31 * result + (element_img_url != null ? element_img_url.hashCode() : 0);
        result = 31 * result + (rb_color_id != null ? rb_color_id.hashCode() : 0);
        result = 31 * result + (part_type_id != null ? part_type_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lego{" +
                "part_id='" + part_id + '\'' +
                ", qty='" + qty + '\'' +
                ", ldraw_color_id='" + ldraw_color_id + '\'' +
                ", type='" + type + '\'' +
                ", part_name='" + part_name + '\'' +
                ", color_name='" + color_name + '\'' +
                ", part_img_url='" + part_img_url + '\'' +
                ", element_id='" + element_id + '\'' +
                ", element_img_url='" + element_img_url + '\'' +
                ", rb_color_id='" + rb_color_id + '\'' +
                ", part_type_id='" + part_type_id + '\'' +
                '}';
    }



}

