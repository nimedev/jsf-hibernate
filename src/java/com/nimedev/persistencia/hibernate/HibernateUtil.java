package com.nimedev.persistencia.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author niconator
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Crea el objeto Session Factory.
     */
    public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
    }

    /**
     * Crea la nueva sesion. La sesion se almacena en la memoria privada del
     * Thread donde se esta ejecutando el codigo.
     */
    public static void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }

    /**
     * Retorna el objecto SessionFactory que se ha guardado en la variable
     * estática.
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Cierra la sesión que se creó mediante openSessionAndBindToThread().
     * Además borra la sesión de la memoria privada del thread donde se está
     * ejecutando el código.
     */
    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session != null) {
            session.close();
        }
    }

    /**
     * Cierra el objecto SessionFactory.
     */
    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }
}
