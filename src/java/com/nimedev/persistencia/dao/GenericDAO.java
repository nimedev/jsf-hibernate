package com.nimedev.persistencia.dao;

import com.nimedev.persistencia.error.BussinessException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author niconator
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {

    void create(T entity) throws BussinessException;

    T read(ID id) throws BussinessException;
    
    void update(T entity) throws BussinessException;

    void delete(ID id) throws BussinessException;

    List<T> findAll() throws BussinessException;
}
