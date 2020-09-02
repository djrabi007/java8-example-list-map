package com.rabi.java8.stream;

public class Car {

	int id;
	String make;
	double price;
	String color;

	public Car(int id, String make, double price, String color) {
		super();
		this.id = id;
		this.make = make;
		this.price = price;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", price=" + price + ", color=" + color + "]";
	}

}
