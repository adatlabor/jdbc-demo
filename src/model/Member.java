package model;

/**
 * Model class for storing data for the 5th class.
 *
 */
public class Member {
	private String name;
	private String address;

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

	@Override
	public String toString() {
		return "Member [name=" + name + ", address=" + address + "]";
	}

}
