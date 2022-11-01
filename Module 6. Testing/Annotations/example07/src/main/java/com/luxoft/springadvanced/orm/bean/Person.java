package com.luxoft.springadvanced.orm.bean;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private Set<Address> addresses;
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
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
        sb.append("  firstName=" + firstName);
        sb.append("  lastName=" + lastName);
        
        sb.append("  addresses=[");
        
        if (addresses != null) {
        	for (Address address : addresses) {
        		sb.append(address.toString());
        	}
        }
        
        sb.append("]");
        
        sb.append("  created=" + created);
        
        return sb.toString();
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return id == person.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
