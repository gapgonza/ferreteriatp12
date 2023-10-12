
package ferreteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;


public class Ferreteria {

   
    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String URL = "jdbc:mariadb://localhost:3306/ferreteria";
            String USUARIO="root";
            String PASSWORD ="";
            Connection conn=DriverManager.getConnection(URL,USUARIO,PASSWORD);
            
           
            String sqlInsertEmpleado = "INSERT INTO empleado (id_empleado, dni, apellido, nombre, acceso, estado)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmtInsertEmpleado = conn.prepareStatement(sqlInsertEmpleado);
            //primer empleado
            pstmtInsertEmpleado.setInt(1, 1);
            pstmtInsertEmpleado.setLong(2, 35767572L);
            pstmtInsertEmpleado.setString(3, "Peñiñorey");
            pstmtInsertEmpleado.setString(4, "Alan Gonzalo");
            pstmtInsertEmpleado.setInt(5, 1);
            pstmtInsertEmpleado.setInt(6, 1);
            pstmtInsertEmpleado.executeUpdate();
            
            // Segundo empleado
            pstmtInsertEmpleado.setInt(1, 2);
            pstmtInsertEmpleado.setLong(2, 23456789L);
            pstmtInsertEmpleado.setString(3, "Castro");
            pstmtInsertEmpleado.setString(4, "Sebastian");
            pstmtInsertEmpleado.setInt(5, 2);
            pstmtInsertEmpleado.setInt(6, 1);
            pstmtInsertEmpleado.executeUpdate();

// Tercer empleado
            pstmtInsertEmpleado.setInt(1, 3);
            pstmtInsertEmpleado.setLong(2, 34567890L);
            pstmtInsertEmpleado.setString(3, "Aguero");
            pstmtInsertEmpleado.setString(4, "Juliana");
            pstmtInsertEmpleado.setInt(5, 3);
            pstmtInsertEmpleado.setInt(6, 1);
            
            
            int filas = pstmtInsertEmpleado.executeUpdate();
            if(filas>0){
                JOptionPane.showMessageDialog(null, "Trabajador agregado ");
            }
            //---------agregar 2 herramientaas
            String sqlInsertHerramienta = "INSERT INTO herramienta (nombre, descripcion, stock, estado)"
                    + " VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmtInsertHerramienta = conn.prepareStatement(sqlInsertHerramienta);
            
           pstmtInsertHerramienta.setString(1, "RotoMartillo");
            pstmtInsertHerramienta.setString(2, "electrico");
            pstmtInsertHerramienta.setInt(3, 20);
            pstmtInsertHerramienta.setInt(4, 1);
            pstmtInsertHerramienta.executeUpdate();
            
            pstmtInsertHerramienta.setString(1, "Motosierra");
            pstmtInsertHerramienta.setString(2, "combustible");
            pstmtInsertHerramienta.setInt(3, 20);
            pstmtInsertHerramienta.setInt(4, 1);
            pstmtInsertHerramienta.executeUpdate();
            
            
            //Listar las herramientas
            String sqlListarHerramientas = "SELECT * FROM herramienta WHERE stock > 10";
            Statement stmtListarHerramientas = conn.createStatement();
            ResultSet rsListarHerramientas = stmtListarHerramientas.executeQuery(sqlListarHerramientas);
            while (rsListarHerramientas.next()) {
                System.out.println(rsListarHerramientas.getString("nombre"));
            }
            
            // Dar de baja al primer empleado ingresado a la base de datos
            String sqlDarDeBajaEmpleado = "UPDATE empleado SET estado = 0 WHERE id_empleado = 1";
            Statement stmtDarDeBajaEmpleado = conn.createStatement();
            
            stmtDarDeBajaEmpleado.executeUpdate(sqlDarDeBajaEmpleado);
            
            
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }catch(SQLException sql){
            System.out.println(sql.getErrorCode());
            if(sql.getErrorCode() == 1062){
                JOptionPane.showMessageDialog(null, "Ya exite un duplicado de ese DNI");
            }else if(sql.getErrorCode() ==1049){
                JOptionPane.showMessageDialog(null, "La base de datos ya existe");
            }else
            JOptionPane.showMessageDialog(null,"Error al cargar el db");
        }
    }
    
}
