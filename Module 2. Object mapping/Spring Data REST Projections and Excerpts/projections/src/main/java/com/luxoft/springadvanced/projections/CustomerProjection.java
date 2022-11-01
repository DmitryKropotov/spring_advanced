package com.luxoft.springadvanced.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "myprojection", types = Customer.class)
public interface CustomerProjection {

	String getFirstname();

	String getLastname();

	@Value("#{target.address.toString()}")
	String getAddress();
}
