/*
 * Licensed under GPL.
 */
package model;


public class Phone {

    private int weight;

    private String brand;

    private String model;

    private double price;

    public Phone(int weight, String brand, String model, double price) {
        this.weight = weight;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
