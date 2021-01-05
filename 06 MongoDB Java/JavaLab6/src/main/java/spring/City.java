package spring;

public class City {

    private String _id;
    private String city;


    public City() {
    }

    public City(String _id, String city) {
        this._id = _id;
        this.city = city;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
