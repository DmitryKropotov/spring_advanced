package com.luxoft.springadvanced.orm.dao;


import java.util.Collection;

import com.luxoft.springadvanced.orm.bean.Person;
import org.springframework.dao.DataAccessException;

public interface PersonDao {

    public Collection<Person> findPersons() throws DataAccessException;
    
    public void save(Person person);

}
