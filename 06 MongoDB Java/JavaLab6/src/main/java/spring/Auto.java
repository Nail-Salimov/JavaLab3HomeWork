package spring;

public class Auto {

    private String number;
    private String model;

    public Auto(String number, String model) {
        this.number = number;
        this.model = model;
    }

    public Auto(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
