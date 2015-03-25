package com.jsfhibernate.dao.impl;

import com.jsfhibernate.dao.UsuarioDAO;
import com.jsfhibernate.model.Usuario;
import com.nimedev.persistencia.dao.impl.GenericDAOImplHibernate;
import com.nimedev.persistencia.error.BussinessException;
import com.nimedev.persistencia.hibernate.HibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author niconator
 */
public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario, Integer>
        implements UsuarioDAO {

    private final static Logger LOGGER = Logger.getLogger(UsuarioDAOImplHibernate.class.getName());

    @Override
    public Usuario verificarDatos(Usuario usuario) throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Usuario WHERE nombre=? AND clave=?";
            Query query = session.createQuery(hql)
                    .setString(0, usuario.getNombre())
                    .setString(1, usuario.getClave());
            if (!query.list().isEmpty()) {
                return (Usuario) query.list().get(0);
            }
            return null;
        } catch (javax.validation.ConstraintViolationException cve) {
            try {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new BussinessException(cve);
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new BussinessException(cve);
        } catch (RuntimeException ex) {
            try {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    }

}
