package mx.edu.uaz.ingsoftware.poo2.basedatos;

import mx.edu.uaz.ingsoftware.poo2.entidades.Institucion;
import mx.edu.uaz.ingsoftware.poo2.interfaces.Dao;
import mx.edu.uaz.ingsoftware.poo2.interfaces.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoInstitucion implements Dao<Institucion> {
    private Connection conn;
    /**
     * Constructor para inicializar la conexion
     * la cual es recibida desde fuera
     * @param con Objeto conexion a MySQL a usar
     * @throws DaoException En caso de que el objeto de conexion no sea valido
     */
    public DaoInstitucion(Connection con) throws DaoException {
        if (con==null) {
            throw new DaoException("Conector recibido es nulo");
        }
        conn=con;
    }


}
