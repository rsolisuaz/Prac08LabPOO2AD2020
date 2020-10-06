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

    /**
     * Constructor vacio que creara una conexion en base
     * a los datos por default
     * @throws DaoException  Si no se puede crear la conexion
     */
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

    /**
     * Constructor que recibe el objeto conexion desde fuera
     * y con el cual se establecera la comunicacion con MySQL
     * @param con  Objeto conexion creado desde fuera
     * @throws DaoException  En caso de que el objeto conexion no sea valido
     */
    public DaoConcursosImpl(Connection con) throws DaoException {
        if (conn==null) {
            throw new DaoException("Conector recibido es nulo");
        }
        conn=con;
        inicializaDaos(conn);
    }

    /**
     * Este metodo inicializara los Dao necesarios
     * pasandoles el objeto de conexion
     * @param conn Objeto de conexion a MySQL
     */
    private void inicializaDaos(Connection conn) {

    }

}
