package dao;

import model.Empleado;

import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public static double calcularSalario(Empleado emp) {
        return switch (emp.getTipo()) {
            case "planta" -> 800000;
            case "contratista" -> 600000;
            case "hora" -> emp.getHorasTrabajadas() * 20000;
            default -> 0;
        };
    }

    public static double calcularAumento(Empleado emp, double salario) {
        return switch (emp.getTipo()) {
            case "planta" -> salario * 0.15;
            case "contratista" -> salario * 0.10;
            case "hora" -> salario * 0.05;
            default -> 0;
        };
    }

    public static void guardarEmpleado(Empleado emp, double salario, double aumento) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO empleados (documento, nombre, telefono, tipo, horas, salario, aumento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getDocumento());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getTelefono());
            ps.setString(4, emp.getTipo());
            ps.setInt(5, emp.getHorasTrabajadas());
            ps.setDouble(6, salario);
            ps.setDouble(7, aumento);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> listarEmpleados() {
        List<String[]> lista = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM empleados")) {
            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("tipo"),
                        String.valueOf(rs.getInt("horas")),
                        String.valueOf(rs.getDouble("salario")),
                        String.valueOf(rs.getDouble("aumento"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}