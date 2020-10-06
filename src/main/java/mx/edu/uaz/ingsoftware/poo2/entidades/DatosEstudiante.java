package mx.edu.uaz.ingsoftware.poo2.entidades;


public class DatosEstudiante {

  private String emailEstudiante;
  private String carreraEstudiante;
  private java.sql.Date fechaInicioCarrera;
  private java.sql.Date fechaEsperadaTerminacion;


  public String getEmailEstudiante() {
    return emailEstudiante;
  }

  public void setEmailEstudiante(String emailEstudiante) {
    this.emailEstudiante = emailEstudiante;
  }


  public String getCarreraEstudiante() {
    return carreraEstudiante;
  }

  public void setCarreraEstudiante(String carreraEstudiante) {
    this.carreraEstudiante = carreraEstudiante;
  }


  public java.sql.Date getFechaInicioCarrera() {
    return fechaInicioCarrera;
  }

  public void setFechaInicioCarrera(java.sql.Date fechaInicioCarrera) {
    this.fechaInicioCarrera = fechaInicioCarrera;
  }


  public java.sql.Date getFechaEsperadaTerminacion() {
    return fechaEsperadaTerminacion;
  }

  public void setFechaEsperadaTerminacion(java.sql.Date fechaEsperadaTerminacion) {
    this.fechaEsperadaTerminacion = fechaEsperadaTerminacion;
  }

}
