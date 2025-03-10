package com.example.SpringRestExempel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {


    @NotBlank(message = "name cannot be blank")
    @Size(min = 2, max = 16, message = "name must be between 2 and 16 characters long")
    private String name;


    @Min(value = 0, message = "price cannot be negativ")
    private double price;


    private long id;

    @Min(value = 0, message = "quantity cannot be negative")
    private int quantity;

    public Product(long id, String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
