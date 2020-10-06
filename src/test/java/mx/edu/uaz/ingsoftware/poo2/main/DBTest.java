package mx.edu.uaz.ingsoftware.poo2.main;

import junit.framework.TestCase;
import mx.edu.uaz.ingsoftware.poo2.basedatos.DaoInstitucion;
import mx.edu.uaz.ingsoftware.poo2.entidades.Institucion;
import org.dbunit.*;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DBTest extends TestCase {
    private static IDatabaseTester databaseTester;
    private final static String driverName="com.mysql.cj.jdbc.Driver";
    private final static String url="jdbc:mysql://localhost/controlconcursos";
    private static List<Institucion> datosEsperados;
    private static IDatabaseConnection conndbunit;
    private static double calificacion;
    private final static double CALIF_CLASE_ENTIDAD=10;
    private final static double CALIF_OBTENER=7.5;
    private final static double CALIF_AGREGAR=7.5;
    private final static double CALIF_UPDATE=7.5;
    private final static double CALIF_DELETE=7.5;

    private static void iniciaDatosLista() throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("datosinst.tsv"), StandardCharsets.UTF_8));
        String linea=in.readLine();
        datosEsperados=new ArrayList<>();
        while (linea!=null) {
            String[] p=linea.split("\t");
            Institucion inst=new Institucion(Long.parseLong(p[0]),
                    p[1],p[2],p[3],p[4],Long.parseLong(p[6]),
                    Long.parseLong(p[7]));
            inst.setColoniaInstitucion(p[5]);
            inst.setCodpostalInstitucion(p[8]);
            inst.setTelefonoInstitucion(p[9]);
            datosEsperados.add(inst);
            linea=in.readLine();
        }
    }


    @BeforeAll
    public static void inicializa() throws Exception {
        System.out.println("Setup..");
        iniciaDatosLista();
        databaseTester=new JdbcDatabaseTester(driverName,url,
                "IngSW","UAZsw2020");
        databaseTester.setOperationListener(new CustomConfigurationOperationListener());
        conndbunit=databaseTester.getConnection();
        DatabaseConfig config=conndbunit.getConfig();
        config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS,true);
        System.out.println(config.getProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS));
        //databaseTester=new JdbcDatabaseTester(driverName,url);
        IDataSet dataSet=new FlatXmlDataSetBuilder().build(new FileInputStream("concursos.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    @AfterAll
    public static void termina() throws Exception {
        System.out.println("Teardown..");
        databaseTester.setTearDownOperation(DatabaseOperation.REFRESH);
        databaseTester.onTearDown();
        System.out.printf("Calificacion:%.2f/%.2f\n",calificacion,100.0);
    }

    @Test
    @Order(1)
    public void testClaseEntidad() {
        long id=5;
        String nom="Universidad Tecnologica Estado de Zacatecas";
        String nomcorto="UTZAC";
        String url="http://www.utzac.edu.mx";
        String calle="Carretera Zacatecas, Cd Cuauhtemoc km. 5";
        String colonia="Ejido Cieneguitas";
        long idmun=32017;
        long ident=32;
        String codpostal="98601";
        String tel="4929276180";
        Institucion inst_agregar = new Institucion(id, nom, nomcorto, url,
                calle, idmun, ident);
        inst_agregar.setColoniaInstitucion(colonia);
        inst_agregar.setCodpostalInstitucion(codpostal);
        inst_agregar.setTelefonoInstitucion(tel);
        assertEquals(id, inst_agregar.getIdInstitucion());
        assertEquals(nom, inst_agregar.getNombreInstitucion());
        assertEquals(nomcorto, inst_agregar.getNombreCortoInstitucion());
        assertEquals(url, inst_agregar.getUrlInstitucion());
        assertEquals(calle, inst_agregar.getCalleNumInstitucion());
        assertEquals(colonia, inst_agregar.getColoniaInstitucion());
        assertEquals(idmun, inst_agregar.getIdMunicipioInstitucion());
        assertEquals(ident, inst_agregar.getIdEntidadInstitucion());
        assertEquals(codpostal, inst_agregar.getCodpostalInstitucion());
        assertEquals(tel, inst_agregar.getTelefonoInstitucion());
        assertEquals(nom, inst_agregar.toString());
        calificacion += CALIF_CLASE_ENTIDAD;
    }

    private void comparaInst(Institucion expected,
                             Institucion actual) {
        assertEquals(expected.getIdInstitucion(),
                actual.getIdInstitucion());
        assertEquals(expected.getNombreInstitucion(),
                actual.getNombreInstitucion());
        assertEquals(expected.getNombreCortoInstitucion(),
                actual.getNombreCortoInstitucion());
        assertEquals(expected.getUrlInstitucion(),
                actual.getUrlInstitucion());
        assertEquals(expected.getCalleNumInstitucion(),
                actual.getCalleNumInstitucion());
        assertEquals(expected.getColoniaInstitucion(),
                actual.getColoniaInstitucion());
        assertEquals(expected.getIdMunicipioInstitucion(),
                actual.getIdMunicipioInstitucion());
        assertEquals(expected.getIdEntidadInstitucion(),
                actual.getIdEntidadInstitucion());
        assertEquals(expected.getCodpostalInstitucion(),
                actual.getCodpostalInstitucion());
        assertEquals(expected.getTelefonoInstitucion(),
                actual.getTelefonoInstitucion());
    }

    @Test
    @Order(2)
    public void testObtenInstExistente() throws Exception {
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        Long idABuscar=1L;
        Optional<Institucion> actual = daoInst.get(idABuscar);
        assertTrue(actual.isPresent());
        comparaInst(datosEsperados.get(0),actual.get());
        calificacion += CALIF_OBTENER;
    }

    @Test
    @Order(3)
    public void testObtenInstInexistente() throws Exception {
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        Long idABuscar=100L;
        Optional<Institucion> actual = daoInst.get(idABuscar);
        assertFalse(actual.isPresent());
        calificacion += CALIF_OBTENER;
    }

    @Test
    @Order(4)
    public void testObtenInstAll() throws Exception {
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        List<Institucion> actual= daoInst.getAll();
        assertEquals(actual.size(),datosEsperados.size());
        for (int i=0; i<actual.size(); i++) {
            comparaInst(datosEsperados.get(i), actual.get(i));
        }
        calificacion += CALIF_OBTENER;
    }

    @Test
    @Order(5)
    public void testAgregarInstValida() throws Exception {
        long id=5;
        String nom="Universidad Tecnologica Estado de Zacatecas";
        String nomcorto="UTZAC";
        String url="http://www.utzac.edu.mx";
        String calle="Carretera Zacatecas, Cd Cuauhtemoc km. 5";
        String colonia="Ejido Cieneguitas";
        long idmun=32017;
        long ident=32;
        String codpostal="98601";
        String tel="4929276180";
        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        boolean resultado=daoInst.save(inst);
        assertTrue(resultado);


        ITable actualTable=conndbunit.createQueryTable("institucion",
                "SELECT * FROM institucion WHERE id_institucion>4");

        IDataSet expectedDataSet=new FlatXmlDataSetBuilder().build(new File("inst_add.xml"));
        ITable expectedTable=expectedDataSet.getTable("institucion");

        Assertion.assertEquals(expectedTable,actualTable);
        calificacion += CALIF_AGREGAR;
    }

    @Test
    @Order(6)
    public void testAgregarInstDuplicada() throws Exception {
        long id=5;
        String nom="Universidad Tecnologica Estado de Zacatecas";
        String nomcorto="UTZAC";
        String url="http://www.utzac.edu.mx";
        String calle="Carretera Zacatecas, Cd Cuauhtemoc km. 5";
        String colonia="Ejido Cieneguitas";
        long idmun=32017;
        long ident=32;
        String codpostal="98601";
        String tel="4929276180";

        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        try {
            boolean resultado=daoInst.save(inst);
            assertFalse(resultado);
        }
        catch (Exception ex) {
        }
        calificacion += CALIF_AGREGAR;
    }

    @Test
    @Order(7)
    public void testAgregarInstInvalida() throws Exception {
        long id=6;
        String nom="Universidad Con un Nombre Extremadamente Largo para el Limite que tiene el campo y que por tanto no deberia de pasar Tecnologica Estado de Zacatecas";
        String nomcorto="UniversidadTecnologicadelEstadodeZacatecas";
        String url="Este URL es extremadamente largo y por tanto no deberia de pasar ";
        url += url;
        url += url;
        url += url;
        String calle="Carretera Zacatecas, Cd Cuauhtemoc km. 5";
        calle += calle;
        calle += calle;
        String colonia="Ejido Cieneguitas";
        colonia += colonia; colonia += colonia; colonia += colonia; colonia += colonia; colonia += colonia;
        long idmun=32100;
        long ident=35;
        String codpostal="198601";
        String tel="49292762180";
        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        boolean resultado=true;
        try {
            resultado=daoInst.save(inst);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        assertFalse(resultado);
        calificacion += CALIF_AGREGAR;
    }

    @Test
    @Order(8)
    public void testUpdateInstValida() throws Exception {
        System.out.println("Entra a testUpdate");

        long id=5;
        String nom="Universidad Tecnologica de Aguascalientes";
        String nomcorto="UTAGS";
        String url="http://www.utags.edu.mx";
        String calle="Blvd. Juan Pablo II 1302";
        String colonia="Canteras de San Agustin";
        long idmun=1001;
        long ident=1;
        String codpostal="20200";
        String tel="4499105000";
        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        boolean resultado=daoInst.update(inst);
        assertTrue(resultado);

        ITable actualTable=conndbunit.createQueryTable("institucion",
                "SELECT * FROM institucion WHERE id_institucion>4");

        IDataSet expectedDataSet=new FlatXmlDataSetBuilder().build(new File("inst_upd.xml"));
        ITable expectedTable=expectedDataSet.getTable("institucion");
        Assertion.assertEquals(expectedTable,actualTable);
        calificacion += CALIF_UPDATE;
    }

    @Test
    @Order(9)
    public void testUpdateInstInexistente() throws Exception {
        System.out.println("Entra a testUpdateInexistente");

        long id=16;
        String nom="Universidad Tecnologica de Aguascalientes";
        String nomcorto="UTAGS";
        String url="http://www.utags.edu.mx";
        String calle="Blvd. Juan Pablo II 1302";
        String colonia="Canteras de San Agustin";
        long idmun=1001;
        long ident=1;
        String codpostal="20200";
        String tel="4499105000";
        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        boolean resultado=true;
        try {
            resultado=daoInst.update(inst);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        assertFalse(resultado);
        calificacion += CALIF_UPDATE;
    }

    @Test
    @Order(10)
    public void testUpdateInstInvalida() throws Exception {
        System.out.println("Entra a testUpdateInvalida");

        long id=5;
        String nom="Universidad Con un Nombre Extremadamente Largo para el Limite que tiene el campo y que por tanto no deberia de pasar Tecnologica Estado de Zacatecas";
        String nomcorto="UniversidadTecnologicadelEstadodeZacatecas";
        String url="Este URL es extremadamente largo y por tanto no deberia de pasar ";
        url += url;
        url += url;
        url += url;
        String calle="Carretera Zacatecas, Cd Cuauhtemoc km. 5";
        calle += calle;
        calle += calle;
        String colonia="Ejido Cieneguitas";
        colonia += colonia; colonia += colonia; colonia += colonia; colonia += colonia; colonia += colonia;
        long idmun=32100;
        long ident=35;
        String codpostal="198601";
        String tel="49292762180";
        Institucion inst= new Institucion(id,nom,nomcorto,url,
                calle,idmun,ident);
        inst.setColoniaInstitucion(colonia);
        inst.setCodpostalInstitucion(codpostal);
        inst.setTelefonoInstitucion(tel);

        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        boolean resultado=true;
        try {
            resultado=daoInst.update(inst);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        assertFalse(resultado);
        calificacion += CALIF_UPDATE;
    }

    @Test
    @Order(11)
    public void testDeleteInstInexistente() throws Exception {
        Long id=16L;
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        try {
            boolean resultado=daoInst.delete(id);
            assertFalse(resultado);
        }
        catch (Exception ex) {
        }
        calificacion += CALIF_DELETE;
    }

    @Test
    @Order(12)
    public void testDeleteInstExistente() throws Exception {
        Long id=10L;
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        try {
            boolean resultado=daoInst.delete(id);
            assertTrue(resultado);
            id=5L;
            daoInst.delete(id); // Se borra la 5 agregada en las pruebas
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ITable actualTable=conndbunit.createTable("institucion");

        IDataSet expectedDataSet=new FlatXmlDataSetBuilder().build(new File("inst_del.xml"));
        ITable expectedTable=expectedDataSet.getTable("institucion");
        Assertion.assertEquals(expectedTable,actualTable);
        calificacion += CALIF_DELETE;
    }

    @Test
    @Order(13)
    public void testDeleteInstInvalida() throws Exception {
        Long id=1L;
        DaoInstitucion daoInst=new DaoInstitucion(conndbunit.getConnection());
        try {
            boolean resultado=daoInst.delete(id);
            assertFalse(resultado);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        calificacion += CALIF_DELETE;
    }

    public static class CustomConfigurationOperationListener extends DefaultOperationListener implements IOperationListener {
        @Override
        public void connectionRetrieved(IDatabaseConnection iDatabaseConnection) {
            super.connectionRetrieved(iDatabaseConnection);
            iDatabaseConnection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        }
    }
}
