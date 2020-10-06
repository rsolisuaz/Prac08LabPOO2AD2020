package mx.edu.uaz.ingsoftware.poo2.entidades;


public class Concurso {

  private long idConcurso;
  private String nombreConcurso;
  private java.sql.Date fechaConcurso;
  private java.sql.Date fechaInicioRegistro;
  private java.sql.Date fechaFinRegistro;


  public long getIdConcurso() {
    return idConcurso;
  }

  public void setIdConcurso(long idConcurso) {
    this.idConcurso = idConcurso;
  }


  public String getNombreConcurso() {
    return nombreConcurso;
  }

  public void setNombreConcurso(String nombreConcurso) {
    this.nombreConcurso = nombreConcurso;
  }


  public java.sql.Date getFechaConcurso() {
    return fechaConcurso;
  }

  public void setFechaConcurso(java.sql.Date fechaConcurso) {
    this.fechaConcurso = fechaConcurso;
  }


  public java.sql.Date getFechaInicioRegistro() {
    return fechaInicioRegistro;
  }

  public void setFechaInicioRegistro(java.sql.Date fechaInicioRegistro) {
    this.fechaInicioRegistro = fechaInicioRegistro;
  }


  public java.sql.Date getFechaFinRegistro() {
    return fechaFinRegistro;
  }

  public void setFechaFinRegistro(java.sql.Date fechaFinRegistro) {
    this.fechaFinRegistro = fechaFinRegistro;
  }

}
