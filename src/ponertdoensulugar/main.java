/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponertdoensulugar;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Denis Parra
 */
public class main {

    public static void main(String[] argv) throws ParseException {

        try {

            Connection connection = null;
            Statement st = null;
            Statement st2 = null;
            try {

                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/log_bd", "Irene",
                        "12QWASzx");

            } catch (ClassNotFoundException e) {

                System.out.println("Where is your PostgreSQL JDBC Driver? "
                        + "Include in your library path!");
                e.printStackTrace();
                return;

            } catch (SQLException ex) {
                System.out.println("Connection Failed! Check output console");
                ex.printStackTrace();
                return;
            }
            String usuario = "juana";
            String password = "juana5";
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultadosConsulta = st.executeQuery("SELECT * FROM log_esquema.usuario WHERE nombre='" + usuario + "' AND password='" + password + "'");

            if (resultadosConsulta.first()) {
                int codSes = resultadosConsulta.getInt("id_usuario");
                st2 = connection.createStatement();
                int pid = 0;
                ResultSet resultadoFuncion = st2.executeQuery("SELECT pg_backend_pid()");
                while (resultadoFuncion.next()) {
                    pid = resultadoFuncion.getInt(1);
                }

                java.util.Date myDate = new java.util.Date();
                
                CallableStatement sentencia = connection.prepareCall("{?=call log_esquema.add_rol(?)}");//Declarar la sentencia.
                sentencia.registerOutParameter(1, Types.INTEGER);                            //Registrar el parámetro de entrada
                sentencia.setString(2, " 'g' ");                                             //Registrar el parámetro de salida
//                sentencia.executeQuery();                                                    //Realizar la llamada
//                int numero = sentencia.getInt(1);                                                  //Recoger el parámetro de salida
//                System.out.println(numero);

                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO log_esquema.sesion (id_usuario, activo, pid) "
                        + " values (?, ?, ?)");
                pstmt.setInt(1, codSes);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
//                pstmt.setDate(2, sqlDate);
                pstmt.setBoolean(2, true);
//                pstmt.setDate(3, null);
                pstmt.setInt(3, pid);
                pstmt.executeUpdate();
                System.out.println(pid);
            } else {
                System.out.println("no existe el usuario");
            }
        } catch (SQLException ex) {

            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}

//ajustes: conexiones: masRedes:ZonaWi y modem usb: zonawifi
//pulsar zonaWifi :veras contraceña 
            //ocnfigurar 
