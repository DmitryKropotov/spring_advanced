package com.luxoft.springadvanced.orm.bean;

import java.util.Date;
import java.util.Objects;

public class Address {

    private int id;
    private String address;
    private String city;
    private String state;
    private Date created;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  addresss=" + address);
        sb.append("  city=" + city);
        sb.append("  state=" + state);
        sb.append("  created=" + created);
        
        return sb.toString();
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return id == address.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
