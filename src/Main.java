import dao.BD;
import modelo.Odontologo;
import org.apache.log4j.Logger;
import servicio.OdontologoServicio;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        BD.createTable(); //la tabla se va a crear una sola vez

        Odontologo odontologo = new Odontologo("Yei","Toro","BVM11E");
        OdontologoServicio servicio = new OdontologoServicio();

        servicio.guardar(odontologo);

        logger.info("Este es el odontologo que sabe");
        logger.info(odontologo.toString());

        odontologo.setNombre("Daniela");
        servicio.actualizar(odontologo);

        logger.info("Este es el odontologo modificado");
        logger.info(odontologo.toString());

        Odontologo odontologo2 = new Odontologo("Daniela","Anttury","HML11A");

        Odontologo odontologo3 = new Odontologo("Nicolas","Duran","COPABF0605E");

        servicio.guardar(odontologo2);
        servicio.guardar(odontologo3);

        servicio.listarTodos();

    }
}