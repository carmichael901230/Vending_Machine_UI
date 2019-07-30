
public class Item {
	private String name;
	private int code;
	private double price;
	private int fontSize;
	
	public Item(String name, int code, double price, int size) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.fontSize = size;
	}
	
	public String getName() {
		return this.name;
	}
	public int getCode() {
		return this.code;
	}
	public double getPrice() {
		return this.price;
	}
	public int getFontSize() {
		return this.fontSize;
	}
}
