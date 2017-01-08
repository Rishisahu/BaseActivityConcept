package com.baseactivityconcept.Wrappers;

/**
 * Created by Rishi sahu on 1/8/2017.
 */

public class MenuDrawer_Wrapper {

    private String id;

    public MenuDrawer_Wrapper(String id, String name, int drawable_icon)
    {
        this.id = id;
        Name = name;
        this.drawable_icon = drawable_icon;
    }

    public MenuDrawer_Wrapper(String id, String name, String contact_no, String email_id, String address, String img, String PAY_MODE, String min, String max, String imgs) {
        this.id = id;
        Name = name;
        this.contact_no = contact_no;
        Email_id = email_id;
        Address = address;
        this.img = img;
        this.PAY_MODE = PAY_MODE;
        this.min = min;
        this.max = max;
        this.imgs = imgs;
    }

    private String Name;
    private String contact_no;
    private String Email_id;
    private String Address;
    private String img;
    private String PAY_MODE;
    private String min;
    private String max;
    private String imgs;

    private int drawable_icon;

    public int getDrawable_icon() {
        return drawable_icon;
    }

    public void setDrawable_icon(int drawable_icon) {
        this.drawable_icon = drawable_icon;
    }





    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPAY_MODE() {
        return PAY_MODE;
    }

    public void setPAY_MODE(String PAY_MODE) {
        this.PAY_MODE = PAY_MODE;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }


}
