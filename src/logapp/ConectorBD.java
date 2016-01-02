package logapp;

import entidades.Usuario;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ConectorBD {

    private Session sesion;
    private Transaction tx;

    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public void desconectar() {
//        registrarSecion(false, -1, getUser());
        sesion.close();
    }

    public int registrarSesion(Usuario usuario) throws HibernateException {
        int id = 0;

        try {
            iniciarOperacion();
            id = (int) sesion.save(usuario);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    public Usuario obtenUsuario(int idContacto) throws HibernateException {
        Usuario contacto = null;
        try {
//            iniciaOperacion();
            contacto = (Usuario) sesion.get(Usuario.class, idContacto);
        } finally {
            sesion.close();
        }

        return contacto;
    }

    public List<Usuario> obtenListaContactos() throws HibernateException {
        List<Usuario> listaContactos = null;

        try {
//            iniciaOperacion();
            listaContactos = sesion.createQuery("from Contacto").list();

//            System.out.println(sesion.createQuery("select pg_backend_pid()"));no funciona hibernate al parecer no lo recnonoce :(
        } finally {
            sesion.close();
        }

        return listaContactos;
    }

    public int autenticarUsuario(String nombre, String password) {
        boolean isValid = false;
        int userRol = -1;
        isValid = (userRol != -1);
        if (isValid) {
            JOptionPane.showMessageDialog(null, "Bien Benid@", "los datos ingresados son correctos",
                    JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "your password our name entered is incorrect",
                    "no es usuario valido", JOptionPane.INFORMATION_MESSAGE);
        }

        return userRol;
    }

//    public int getUserId() {
//        int result = -1;
//        try {
//            String name = getUser();
//            CallableStatement editStatement = coneccion.prepareCall("{? = call log_esquema.verificar_id(?)}");
//            editStatement.registerOutParameter(1, Types.INTEGER);
//            editStatement.setString(2, name);
//            editStatement.execute();
//
//            result = editStatement.getInt(1);
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public String getUserDates(int id) {
//        String datos = "";
//        try {
//            CallableStatement datosStatement = coneccion.prepareCall("{? = call log_esquema.datosDeUsuario(?)}");
//            datosStatement.registerOutParameter(1, Types.VARCHAR);
//            datosStatement.setInt(2, id);
//            datosStatement.execute();
//            datos = datosStatement.getString(1);
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return datos;
//    }
//
//    public void editarSalarios(int id_usuario, Date fechaI, int bono, int descuentos, int total) {
//        try {
//            CallableStatement editStatement = coneccion.prepareCall("{call log_esquema.add_sueldo(?, ?, ?, ?, ?)}");
//            editStatement.setDate(1, fechaI);
//            editStatement.setInt(2, id_usuario);
//            editStatement.setInt(3, descuentos);
//            editStatement.setInt(4, bono);
//            editStatement.setInt(5, total);
//            editStatement.execute();
//
//            String nuevo = String.format("actual monto total es:%d para el usuario" + getUserDates(id_usuario), total);
//            registrarInsercion(nuevo);
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void registrarUpdate(String antiguo, String nuevo, int ci_usuario) {
//        try {
//            PreparedStatement prepareStatement = coneccion.prepareCall("INSERT INTO log_esquema.bitacora"
//                    + " (accion, valor_antiguo, valor_nuevo, ci_usuario )"
//                    + "   VALUES (?, ?, ?, ?)");
//            prepareStatement.setString(1, "insertar");
//            prepareStatement.setString(2, antiguo);
//            prepareStatement.setString(3, nuevo);
//            prepareStatement.setString(4, getUserId() + " : " + getUser());
//            prepareStatement.executeUpdate();
//            Instant ahora = now();
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void registrarInsercion(String nuevo) {
//        try {
//            CallableStatement statement = coneccion.prepareCall("{call log_esquema.registrar_insersion(?)}");
//            statement.setString(1, nuevo);
//            statement.execute();
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public int getIdOfRol(String sentencia) {
//        int result = -1;
//        ResultSet resultOfSerch;
//        try {
//            resultOfSerch = searchUserStatement.executeQuery(sentencia);
//            if (resultOfSerch.first()) {
//                result = resultOfSerch.getInt("id_rol");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public int getUserId(String userName) {
//        int id = -1;
//        try {
//            CallableStatement statement = coneccion.prepareCall("{? = call log_esquema.verificar_id(?)}");
//            statement.registerOutParameter(1, Types.INTEGER);
//            statement.setString(2, userName);
//            statement.execute();
//            id = statement.getInt(1);
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
//
//    public List<Salario> getHistorialDeSalario() {
//        ResultSet historial = null;
//        try {
//            searchUserStatement = coneccion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
//            historial = searchUserStatement.executeQuery("SELECT log_esquema.sueldo.id_usuario,log_esquema.sueldo.mes, log_esquema.sueldo.descuentos,"
//                    + " log_esquema.sueldo.bonos, log_esquema.sueldo.total_monto_salarial"
//                    + " FROM log_esquema.sueldo WHERE id_usuario = " + getUserId());
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return getHistoryListSalari(historial);
//    }
//
//    public List<Salario> getHistorialDeSueldosDeEmpleados() {
//        ResultSet historial = null;
//        try {
//            searchUserStatement = coneccion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
//            historial = searchUserStatement.executeQuery("SELECT log_esquema.sueldo.id_usuario, log_esquema.sueldo.mes, log_esquema.sueldo.descuentos,"
//                    + " log_esquema.sueldo.bonos, log_esquema.sueldo.total_monto_salarial"
//                    + " FROM log_esquema.sueldo ");
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return getHistorialDeSlariosDeUsuariosi(historial);
//    }
//
//    private List<Salario> getHistoryListSalari(ResultSet historial) {
//        List<Salario> resultado = new ArrayList<>();
//        try {
//            int userId = getUserId(getUser());
//            while (historial.next()) {
//                resultado.add(new Salario(historial.getInt(1), historial.getDate("mes"), historial.getInt("descuentos"), historial.getInt("bonos"), historial.getInt("total_monto_salarial")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return resultado;
//    }
//
//    private List<Salario> getHistorialDeSlariosDeUsuariosi(ResultSet historial) {
//        List<Salario> resultado = new ArrayList<>();
//        try {
//            while (historial.next()) {
//                resultado.add(new Salario(historial.getInt(1), historial.getDate(2), historial.getInt(3), historial.getInt(4), historial.getInt(5)));
////                historial.deleteRow();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return resultado;
//    }
//
//    public int getId_usuario() {
//        return getUserId(getUser());
//    }
    private ArrayList<Integer> getFormularyCredencials(ResultSet rolList) {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            while (rolList.next()) {
                result.add(rolList.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
