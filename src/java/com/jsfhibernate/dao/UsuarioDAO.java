package com.jsfhibernate.dao;

import com.jsfhibernate.model.Usuario;
import com.nimedev.persistencia.dao.GenericDAO;
import com.nimedev.persistencia.error.BussinessException;

/**
 *
 * @author niconator
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {

    Usuario verificarDatos(Usuario usuario) throws BussinessException;
}
