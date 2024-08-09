package test;

import dao.BD;
import dao.IDAO;
import dao.impl.ImplementacionDaoH2;
import modelo.Odontologo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class OdontologoTest {
    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
       private IDAO<Odontologo> odontologoDAO;

    @Before
    public void setUp() throws Exception {
        odontologoDAO = new ImplementacionDaoH2();
        // Limpiar la base de datos antes de cada prueba
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE IF EXISTS odontologos");
            stmt.execute("CREATE TABLE IF NOT EXISTS odontologos (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "numeroMatricula INT UNIQUE, " +
                    "nombre VARCHAR(50), " +
                    "apellido VARCHAR(50))");
        }
    }


    @Test
    public void testGuardarYListar() {
        // Crear y guardar un nuevo odontólogo
        Odontologo odontologo = new Odontologo("Yei","Toro","BVM11E");
        odontologoDAO.guardar(odontologo);

        // Listar todos los odontólogos
        List<Odontologo> odontologos = odontologoDAO.listarTodos();
        assertFalse(odontologos.isEmpty());
        assertEquals(1, odontologos.size());

        // Verificar que los datos sean correctos
        Odontologo odontologoGuardado = odontologos.get(0);
        assertNotNull(odontologoGuardado.getId());
        assertEquals("BVM11E", odontologoGuardado.getNumeroMatricula());
        assertEquals("Yei", odontologoGuardado.getNombre());
        assertEquals("Toro", odontologoGuardado.getApellido());
    }

    @Test
    public void testActualizar() {
        // Crear y guardar un odontólogo
        Odontologo odontologo = new Odontologo("Yei","Toro","BVM11E");
        odontologoDAO.guardar(odontologo);

        // Actualizar los datos del odontólogo
        odontologo.setNombre("Daniela");
        odontologo.setApellido("Anttury");
        odontologoDAO.guardar(odontologo);

        // Listar todos los odontólogos y verificar la actualización
        List<Odontologo> odontologos = odontologoDAO.listarTodos();
        assertFalse(odontologos.isEmpty());
        assertEquals(1, odontologos.size());

        Odontologo odontologoActualizado = odontologos.get(0);
        assertEquals("Daniela", odontologoActualizado.getNombre());
        assertEquals("Anttury", odontologoActualizado.getApellido());
    }

//    @Test
//    public void testEliminar() {
//        // Crear y guardar un odontólogo
//        Odontologo odontologo = new Odontologo(5,"Yei","Toro","BVM11E");
//        odontologoDAO.guardar(odontologo);
//
//        // Verificar que la lista de odontólogos esté vacía
//        List<Odontologo> odontologos = odontologoDAO.listarTodos();
//        assertTrue(odontologos.isEmpty());
//    }
}