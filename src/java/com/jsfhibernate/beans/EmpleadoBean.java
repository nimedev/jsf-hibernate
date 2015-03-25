package com.jsfhibernate.beans;

import com.jsfhibernate.dao.EmpleadoDAO;
import com.jsfhibernate.dao.impl.EmpleadoDAOImplHibernate;
import com.jsfhibernate.model.Empleado;
import com.nimedev.persistencia.error.BussinessException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author niconator
 */
@ManagedBean
@SessionScoped
public class EmpleadoBean {

    private Empleado empleado = new Empleado();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAOImplHibernate();
    private List<Empleado> lstEmpleados;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoDAO getEmpleadoDAO() {
        return empleadoDAO;
    }

    public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public List<Empleado> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleado> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }

    public void registrar() throws BussinessException {
        empleadoDAO.create(empleado);

    }

    public void eliminar(Empleado emp) throws BussinessException {
        int id = emp.getCodEmpleado();
        empleadoDAO.delete(id);

    }

    public String leer(Empleado emp) {
        this.empleado = emp;
        return "editar";
    }

    public String modificar() throws BussinessException {
        empleadoDAO.update(empleado);
        return "exito";
    }

    public void listar() throws BussinessException {
        this.lstEmpleados = empleadoDAO.findAll();
    }
}
