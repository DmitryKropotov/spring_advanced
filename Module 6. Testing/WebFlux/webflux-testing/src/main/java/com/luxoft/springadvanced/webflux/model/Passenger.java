package com.luxoft.springadvanced.webflux.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class Passenger implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
    private int id;
	private String name;
	private long coveredDistance;
 
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCoveredDistance() {
		return coveredDistance;
	}

	public void setCoveredDistance(long coveredDistance) {
		this.coveredDistance = coveredDistance;
	}

	@Override
	public String toString() {
		return "Passenger{" +
				"id=" + id +
				", name='" + name + '\'' +
				", coveredDistance=" + coveredDistance +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Passenger passenger = (Passenger) o;
		return id == passenger.id &&
				coveredDistance == passenger.coveredDistance &&
				Objects.equals(name, passenger.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, coveredDistance);
	}
}
