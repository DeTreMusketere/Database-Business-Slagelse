package model.data;

import abstracts.Data;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class Store extends Data {

    private String name;
    private String address;
    private String phone;
    private Picture picture;
    private Dealer parentDealer;

    public Store(int id, String name, String address, String phone, Picture picture, Dealer parentDealer, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.parentDealer = parentDealer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Dealer getParent() {
        return parentDealer;
    }

    public void setParent(Dealer parentDealer) {
        this.parentDealer = parentDealer;
    }

    @Override
    public String toString() {
        return "name: " + name + " address: " + address + " phone: " + phone + " picture: " + picture + " parent dealer: " + parentDealer.getName();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", name);
        obj.put("address", address);
        obj.put("phone", phone);
        try {
            obj.put("picture", picture.getId());
        } catch (NullPointerException ex) {
            obj.put("picture", -1);
        }
        obj.put("parentdealer", parentDealer.getId());
        obj.put("updatenumber", getUpdateNumber());
        return obj;
    }

}
