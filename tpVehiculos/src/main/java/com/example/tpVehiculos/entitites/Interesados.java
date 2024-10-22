package com.example.tpVehiculos.entitites;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "interesados")
public class Interesados {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "restringido")
    private Boolean restringido;

    @Column(name = "nro_licencia")
    private String nroLicencia;

    @Column(name = "fecha_vencimiento_licencia")
    private String fechaVencimientoLicencia;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getRestringido() {
        return restringido;
    }

    public void setRestringido(Boolean restringido) {
        this.restringido = restringido;
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(String nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public String getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(String fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interesados interesados)) return false;
        return Objects.equals(id, interesados.id) && Objects.equals(tipoDocumento, interesados.tipoDocumento) && Objects.equals(documento, interesados.documento) && Objects.equals(nombre, interesados.nombre) && Objects.equals(apellido, interesados.apellido) && Objects.equals(restringido, interesados.restringido) && Objects.equals(nroLicencia, interesados.nroLicencia) && Objects.equals(fechaVencimientoLicencia, interesados.fechaVencimientoLicencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoDocumento, documento, nombre, apellido, restringido, nroLicencia, fechaVencimientoLicencia);
    }

    @Override
    public String toString() {
        return "Interesados{" +
                "id=" + id +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", restringido=" + restringido +
                ", nroLicencia='" + nroLicencia + '\'' +
                ", fechaVencimientoLicencia='" + fechaVencimientoLicencia + '\'' +
                '}';
    }

    public Interesados() {}

    public Interesados(Long id, String tipoDocumento, String documento, String nombre, String apellido, Boolean restringido, String nroLicencia, String fechaVencimientoLicencia) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.restringido = restringido;
        this.nroLicencia = nroLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }
}
