package com.nimedev.persistencia.dao.impl;

import com.nimedev.persistencia.error.BussinessException;
import com.nimedev.persistencia.error.BussinessMessage;
import com.nimedev.persistencia.dao.GenericDAO;
import com.nimedev.persistencia.hibernate.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author niconator
 * @param <T>
 * @param <ID>
 */
public class GenericDAOImplHibernate<T, ID extends Serializable>
        implements GenericDAO<T, ID> {

    protected SessionFactory sessionFactory;

    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());

    public GenericDAOImplHibernate() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(T entity) throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
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

    @Override
    public T read(ID id) throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            session.getTransaction().commit();

            return entity;
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

    @Override
    public void update(T entity) throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
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

    @Override
    public void delete(ID id) throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            if (entity == null) {
                throw new BussinessException(new BussinessMessage(null, "Los datos a borrar ya no existen"));
            }
            session.delete(entity);
            session.getTransaction().commit();
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
        } catch (BussinessException ex) {
            try {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw ex;
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

    @Override
    public List<T> findAll() throws BussinessException {
        Session session = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
            List<T> entities = query.list();

            return entities;
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

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
