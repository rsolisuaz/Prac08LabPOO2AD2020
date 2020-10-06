package mx.edu.uaz.ingsoftware.poo2.basedatos;

import mx.edu.uaz.ingsoftware.poo2.entidades.*;
import mx.edu.uaz.ingsoftware.poo2.interfaces.DAOConcursos;
import mx.edu.uaz.ingsoftware.poo2.interfaces.Dao;
import mx.edu.uaz.ingsoftware.poo2.interfaces.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DaoConcursosImpl  implements DAOConcursos {
    private Connection conn;
    Dao<Entidad> daoEntidad;

    public DaoConcursosImpl() throws DaoException {
        String url="jdbc:mysql://localhost/controlconcursos?characterEncoding=utf8";
        try {
            conn= DriverManager.getConnection(url,
                    "IngSW","UAZsw2020");
            inicializaDaos(conn);
        }
        catch (SQLException exsql) {
            throw new DaoException(exsql.getMessage(), exsql.getCause());
        }
    }

    public DaoConcursosImpl(Connection conn) throws DaoException {
        if (conn==null) {
            throw new DaoException("Conector recibido es nulo");
        }
        inicializaDaos(conn);
    }

    private void inicializaDaos(Connection conn) {
        daoEntidad = new DaoEntidad(conn);
    }

    @Override
    public List<Entidad> obtenEntidades() {
        return daoEntidad.getAll();
    }


    @Override
    public List<Municipio> obtenMunicipios(long idEntidad) {
        return null;
    }

    @Override
    public List<Institucion> obtenInstituciones() {
        return null;
    }

    @Override
    public boolean agregaInstitucion(Institucion dato) {
        return false;
    }

    @Override
    public boolean eliminaInstitucion(long idInstitucion) {
        return false;
    }

    @Override
    public boolean actualizaInstitucion(Institucion dato) {
        return false;
    }

    @Override
    public List<Persona> obtenPersonas() {
        return null;
    }

    @Override
    public boolean agregaPersona(Persona dato) {
        return false;
    }

    @Override
    public boolean eliminaPersona(String emailPersona) {
        return false;
    }

    @Override
    public boolean actualizaPersona(Persona dato) {
        return false;
    }

    @Override
    public DatosEstudiante obtenDatosEstudiante(String emailEstudiante) {
        return null;
    }

    @Override
    public boolean agregaDatosEstudiante(DatosEstudiante dato) {
        return false;
    }

    @Override
    public boolean eliminaDatosEstudiante(String emailEstudiante) {
        return false;
    }

    @Override
    public boolean actualizaDatosEstudiante(DatosEstudiante dato) {
        return false;
    }

    @Override
    public List<Sede> obtenSedes() {
        return null;
    }

    @Override
    public boolean agregaSede(Sede dato) {
        return false;
    }

    @Override
    public boolean eliminaSede(long idSede) {
        return false;
    }

    @Override
    public boolean actualizaSede(Sede dato) {
        return false;
    }

    @Override
    public List<Concurso> obtenConcursos() {
        return null;
    }

    @Override
    public boolean agregaConcurso(Concurso dato) {
        return false;
    }

    @Override
    public boolean eliminaConcurso(long idConcurso) {
        return false;
    }

    @Override
    public boolean actualizaConcurso(Concurso dato) {
        return false;
    }

    @Override
    public List<Equipo> obtenEquipos() {
        return null;
    }

    @Override
    public boolean agregaEquipo(Equipo dato) {
        return false;
    }

    @Override
    public boolean eliminaEquipo(long idEquipo) {
        return false;
    }

    @Override
    public boolean actualizaEquipo(Equipo dato) {
        return false;
    }

    @Override
    public List<String> obtenCorreosDeInstitucion(long idInstitucion, String tipo) {
        return null;
    }

    @Override
    public List<SedeConcursoExtendida> obtenSedesDisponibles(long idConcurso) {
        return null;
    }

    @Override
    public List<SedeConcursoExtendida> obtenSedesAsignadas(long idConcurso) {
        return null;
    }

    @Override
    public boolean agregaSedeConcurso(SedeConcurso dato) {
        return false;
    }

    @Override
    public boolean eliminaSedeConcurso(long idSedeConcurso) {
        return false;
    }

    @Override
    public List<EquiposSedeConcursoExtendida> obtenEquiposRegistrados(long idSedeConcurso, long idInstitucion) {
        return null;
    }

    @Override
    public List<Equipo> obtenEquiposDisponibles(long idConcurso, long idInstitucion) {
        return null;
    }

    @Override
    public boolean registrarEquipoSedeConcurso(EquiposSedeConcurso dato) {
        return false;
    }

    @Override
    public boolean cancelarEquipoSedeConcurso(long idEquipoSedeConcurso) {
        return false;
    }
}
