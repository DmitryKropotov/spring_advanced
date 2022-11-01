package com.luxoft.springadvanced.transactions.data.repositories;

import com.luxoft.springadvanced.transactions.orm.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<Log, Integer>, LogDaoCustom {


}
