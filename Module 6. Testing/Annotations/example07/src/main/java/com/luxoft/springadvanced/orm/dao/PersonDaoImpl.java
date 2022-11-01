package com.luxoft.springadvanced.orm.dao;

import java.util.Collection;

import com.luxoft.springadvanced.orm.bean.Person;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class PersonDaoImpl implements PersonDao {

    private HibernateTemplate template;

    public void setSessionFactory(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public Collection<Person> findPersons() throws DataAccessException {
        return (Collection<Person>) template.find("from Person");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Person person) {
        template.saveOrUpdate(person);
    }

}
