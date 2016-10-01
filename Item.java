package homework;


public class Item {

	//private static int count = 0;

	int id;
	String name;
	String description;
	int quantity;
	double price;

	public Item(int id, String name, String description, int quantity, double price) {
		super();
		//this.id = count++;
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(){
		this.id = id;
	}
	public String toString(){
		return name;
	}




}