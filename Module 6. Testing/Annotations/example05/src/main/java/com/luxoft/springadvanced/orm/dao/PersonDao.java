package com.luxoft.springadvanced.orm.dao;


import com.luxoft.springadvanced.orm.bean.Person;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PersonDao {

    public Collection<Person> findPersons() throws DataAccessException;
    
    public void save(Person person);

}
