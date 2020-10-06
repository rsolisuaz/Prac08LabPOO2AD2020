package mx.edu.uaz.ingsoftware.poo2.basedatos;

import mx.edu.uaz.ingsoftware.poo2.entidades.Institucion;
import mx.edu.uaz.ingsoftware.poo2.interfaces.Dao;
import mx.edu.uaz.ingsoftware.poo2.interfaces.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoInstitucion implements Dao<Institucion> {
    private final Statement stmt;
    private final PreparedStatement stmtGetOne;
    private final PreparedStatement stmtInsert;
    private final PreparedStatement stmtUpdate;
    private final PreparedStatement stmtDelete;

    public DaoInstitucion(Connection con) throws DaoException {
        if (con==null) {
            throw new DaoException("Conector recibido es nulo");
        }
        try {
            stmt= con.createStatement();
            stmtGetOne= con.prepareStatement("SELECT * FROM institucion WHERE id_institucion=?");
            stmtInsert= con.prepareStatement("INSERT INTO institucion VALUES(?,?,?,?,?,?,?,?,?,?)");
            stmtUpdate= con.prepareStatement("UPDATE institucion SET nombre_institucion=?,nombre_corto_institucion=?,url_institucion=?,calle_num_institucion=?,colonia_institucion=?,id_municipio_institucion=?,id_entidad_institucion=?,codpostal_institucion=?,telefono_institucion=? WHERE id_institucion=?");
            stmtDelete= con.prepareStatement("DELETE FROM institucion WHERE id_institucion=?");
        }
        catch (SQLException exsql) {
            throw new DaoException(exsql.getMessage(), exsql.getCause());
        }
    }

    @Override
    public Optional<Institucion> get(Object id) {
        Optional<Institucion> resultado=Optional.empty();
        if (id instanceof Long) {
            long idinst=(Long)id;
            try {
                //String sql="SELECT * FROM institucion WHERE id_institucion="+id;
                stmtGetOne.setLong(1,idinst);
                ResultSet rs= stmtGetOne.executeQuery(); //stmt.executeQuery(sql);
                if (rs.next()) {
                    String nom=rs.getString("nombre_institucion");
                    String nomcorto=rs.getString("nombre_corto_institucion");
                    String url=rs.getString("url_institucion");
                    String calle=rs.getString("calle_num_institucion");
                    String colonia=rs.getString("colonia_institucion");
                    long idmun=rs.getLong("id_municipio_institucion");
                    long ident=rs.getLong("id_entidad_institucion");
                    String cp=rs.getString("codpostal_institucion");
                    String tel=rs.getString("telefono_institucion");
                    Institucion inst= new Institucion(idinst,nom,nomcorto,
                            url,calle,idmun,ident);
                    inst.setColoniaInstitucion(colonia);
                    inst.setCodpostalInstitucion(cp);
                    inst.setTelefonoInstitucion(tel);
                    resultado=Optional.of(inst);
                }
            }
            catch (SQLException exsql) {
                throw new DaoException(exsql.getMessage(), exsql.getCause());
            }
        }
        return resultado;
    }

    @Override
    public List<Institucion> getAll() {
        List<Institucion> datos=new ArrayList<>();
        try {
            String sql="SELECT * FROM institucion";
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()) {
                long idinst=rs.getLong("id_institucion");
                String nom=rs.getString("nombre_institucion");
                String nomcorto=rs.getString("nombre_corto_institucion");
                String url=rs.getString("url_institucion");
                String calle=rs.getString("calle_num_institucion");
                String colonia=rs.getString("colonia_institucion");
                long idmun=rs.getLong("id_municipio_institucion");
                long ident=rs.getLong("id_entidad_institucion");
                String cp=rs.getString("codpostal_institucion");
                String tel=rs.getString("telefono_institucion");
                Institucion inst= new Institucion(idinst,nom,nomcorto,
                        url,calle,idmun,ident);
                inst.setColoniaInstitucion(colonia);
                inst.setCodpostalInstitucion(cp);
                inst.setTelefonoInstitucion(tel);
                datos.add(inst);
            }
        }
        catch (SQLException exsql) {
            throw new DaoException(exsql.getMessage(), exsql.getCause());
        }
        return datos;
    }

    @Override
    public boolean save(Institucion dato) {
        boolean resultado=false;
        try {
            stmtInsert.setLong(1,dato.getIdInstitucion());
            stmtInsert.setString(2, dato.getNombreInstitucion());
            stmtInsert.setString(3,dato.getNombreCortoInstitucion());
            stmtInsert.setString(4,dato.getUrlInstitucion());
            stmtInsert.setString(5,dato.getCalleNumInstitucion());
            stmtInsert.setLong(7,dato.getIdMunicipioInstitucion());
            stmtInsert.setLong(8,dato.getIdEntidadInstitucion());
            String valor = dato.getColoniaInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtInsert.setString(6,valor);
            }
            else {
                stmtInsert.setNull(6,Types.VARCHAR);
            }
            valor = dato.getCodpostalInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtInsert.setString(9,valor);
            }
            else {
                stmtInsert.setNull(9,Types.CHAR);
            }
            valor = dato.getTelefonoInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtInsert.setString(10,valor);
            }
            else {
                stmtInsert.setNull(10,Types.CHAR);
            }
            int numafectados=stmtInsert.executeUpdate();
            if (numafectados==1) {
                resultado=true;
            }
        }
        catch (SQLException exsql) {
            throw new DaoException(exsql.getMessage(), exsql.getCause());
        }
        return resultado;
    }

    @Override
    public boolean update(Institucion dato) {
        boolean resultado=false;
        try {
            stmtUpdate.setString(1, dato.getNombreInstitucion());
            stmtUpdate.setString(2,dato.getNombreCortoInstitucion());
            stmtUpdate.setString(3,dato.getUrlInstitucion());
            stmtUpdate.setString(4,dato.getCalleNumInstitucion());
            stmtUpdate.setLong(6,dato.getIdMunicipioInstitucion());
            stmtUpdate.setLong(7,dato.getIdEntidadInstitucion());
            String valor = dato.getColoniaInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtUpdate.setString(5,valor);
            }
            else {
                stmtUpdate.setNull(5,Types.VARCHAR);
            }
            valor = dato.getCodpostalInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtUpdate.setString(8,valor);
            }
            else {
                stmtUpdate.setNull(8,Types.CHAR);
            }
            valor = dato.getTelefonoInstitucion();
            if (valor!=null && !valor.isEmpty()) {
                stmtUpdate.setString(9,valor);
            }
            else {
                stmtUpdate.setNull(9,Types.CHAR);
            }
            stmtUpdate.setLong(10,dato.getIdInstitucion());
            int numafectados=stmtUpdate.executeUpdate();
            if (numafectados==1) {
                resultado=true;
            }
        }
        catch (SQLException exsql) {
            throw new DaoException(exsql.getMessage(), exsql.getCause());
        }
        return resultado;
    }

    @Override
    public boolean delete(Object id) {
        boolean resultado=false;
        if (id instanceof Long) {
            Long idInst = (Long) id;
            try {
                stmtDelete.setLong(1, idInst);
                int numafectados = stmtDelete.executeUpdate();
                if (numafectados == 1) {
                    resultado = true;
                }
            } catch (SQLException exsql) {
                throw new DaoException(exsql.getMessage(), exsql.getCause());
            }
        }
        return resultado;
    }
}
