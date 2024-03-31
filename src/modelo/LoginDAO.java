
package modelo;

//importo las librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    //creo las variables necesarias para las conexiones
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionSV cn = new ConexionSV();
    
    //creo el metodo que va ha iniciar el login, revisando la base de datos la tabla usuarios
    public mLogin log(String correo, String password) {
        mLogin l = new mLogin();
        String sql = "select * from usuarios where correo = ? and password = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
}
