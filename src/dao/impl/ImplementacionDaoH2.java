package dao.impl;

import dao.BD;
import dao.IDAO;
import modelo.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementacionDaoH2 implements IDAO<Odontologo> {
    private Map<Integer,Odontologo> bdMemoria;

    public ImplementacionDaoH2() {
        this.bdMemoria = new HashMap<>();
    }

    private static final Logger logger = Logger.getLogger(ImplementacionDaoH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try {

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ODONTOLOGO (" +
                            "NOMBRE, APELLIDO, NUMERO_MATRICULA) VALUES " +
                            "(?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getNumeroMatricula());

            preparedStatement.execute();

            //guardé el odontologo y se generó el id

            ResultSet rs = preparedStatement.getGeneratedKeys();


            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
                logger.info("Se guardó el odontologo con nombre " +
                        odontologo.getNombre());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;

        List<Odontologo> odontologoList = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ODONTOLOGO"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));

                odontologoList.add(odontologo);

                logger.info(odontologo.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologoList;
    }

    @Override
    public List<Odontologo> listarOdontologos() {

        return null;
    }

    @Override
    public Odontologo guardarOdontologos(Odontologo odontologo) {
        odontologo.setId((bdMemoria.size() + 1));
        return null;
    }

    @Override
    public Odontologo consultarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ODONTOLOGO WHERE ID = ?"
            );

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));

                logger.info(odontologo.toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void eliminarPorId(Integer id) {
        Connection connection = null;
        boolean isDelete = false;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM ODONTOLOGO WHERE ID = ?"
            );

            preparedStatement.setInt(1, id);

            int rowsAffected= preparedStatement.executeUpdate();

            if (rowsAffected > 0 ){
                isDelete = true;
                logger.info("El odontologo se eliminó correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ODONTOLOGO SET NOMBRE=?, APELLIDO=?,NUMERO_MATRICULA=? WHERE ID=?"
            );

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getNumeroMatricula());
            preparedStatement.setInt(4,    odontologo.getId());

            preparedStatement.execute();

            logger.info("Este es el nuevo nombre del Odontologo " + odontologo.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologo;
    }
}
