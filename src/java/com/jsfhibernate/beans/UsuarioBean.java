package com.jsfhibernate.beans;

import com.jsfhibernate.dao.UsuarioDAO;
import com.jsfhibernate.dao.impl.UsuarioDAOImplHibernate;
import com.jsfhibernate.model.Usuario;
import com.nimedev.persistencia.error.BussinessException;
import com.nimedev.persistencia.hibernate.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author niconator
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String verificarDatos() throws BussinessException {
        UsuarioDAO usuDAO = new UsuarioDAOImplHibernate();
        Usuario us;
        String resultado;

        // Encripta la clave
        String encript = DigestUtils.sha1Hex(this.usuario.getClave());
        this.usuario.setClave(encript);

        System.out.println("Clave:" + encript);

        us = usuDAO.verificarDatos(this.usuario);
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("usuario", us);
            resultado = "exito";
        } else {
            resultado = "error";
        }
        return resultado;
    }

    public boolean verificarSesion() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario") != null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "index?faces-redirect=true";
    }
}
