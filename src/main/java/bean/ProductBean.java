package bean;

import java.io.Serializable;

public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String supplier;
    private String id;
    private String name;
    private int stock;
    private double cost;
    private double price;
	private String image;

	public ProductBean() {}

	public ProductBean(String type, String supplier, String id, String name, int stock, double cost, double price, String image) {
	    this.type = type;
	    this.supplier = supplier;
	    this.id = id;
	    this.name = name;
	    this.stock = stock;
	    this.cost = cost;
	    this.price = price;
	    this.image = image;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
	
	