package servicio;

import dao.IDAO;
import dao.impl.ImplementacionDaoH2;
import modelo.Odontologo;

import java.util.List;

public class OdontologoServicio {
    private IDAO<Odontologo> interfazDAO;

    public OdontologoServicio() {
        this.interfazDAO = new ImplementacionDaoH2();
    }

    public Odontologo guardar(Odontologo paciente) {
        return interfazDAO.guardar(paciente);
    }

    public void eliminar(Integer id){
        interfazDAO.eliminarPorId(id);
    }

    public List<Odontologo> listarTodos() {
        return interfazDAO.listarTodos();
    }

    public Odontologo buscarPorId(Integer id) {
        return interfazDAO.consultarPorId(id);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return interfazDAO.actualizar(odontologo);
    }

}
