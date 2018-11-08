/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Luis Fuentes <luisfuau10@gmail.com>
 */
public class Estado {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString()
    {
        return this.nombre;
    }
    
    public Vector <Estado> mostrarEstados()
    {
        PreparedStatement ps;
        ResultSet rs;
        ConexionGeneral conexion = new ConexionGeneral("localhost", "3306", "mexico", "root", "1234");
        Connection con = conexion.getConnection();
        Vector <Estado> datos = new Vector<Estado>();
        Estado data = null;
        try
        {
            String sql = "SELECT * FROM t_estado";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            data = new Estado();
            data.setId(0);
            data.setNombre("Selecciona estado");
            datos.add(data);
            
            while(rs.next())
            {
                data = new Estado();
                data.setId(rs.getInt("id_estado"));
                data.setNombre(rs.getString("estado"));
                datos.add(data);
            }
            
            rs.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        
        return datos;
    }
}
