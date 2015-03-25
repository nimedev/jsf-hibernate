package com.jsfhibernate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author niconator
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codEmpleado")
    private Integer codEmpleado;
    @Size(max = 50)
    @NotNull
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 50)
    @NotNull
    @Column(name = "apellidos")
    private String apellidos;
    @NotNull
    @Column(name = "sexo")
    private Character sexo;

    public Empleado() {
    }

    public Empleado(String nombres, String apellidos, Character sexo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Integer codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmpleado != null ? codEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        return !((this.codEmpleado == null && other.codEmpleado != null) || (this.codEmpleado != null && !this.codEmpleado.equals(other.codEmpleado)));
    }

    @Override
    public String toString() {
        return "com.jsfhibernate.model.Empleado[ codEmpleado=" + codEmpleado + " ]";
    }

}
