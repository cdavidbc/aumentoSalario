package view;

import dao.EmpleadoDAO;
import model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmpleadoApp extends JFrame {
    private JTextField txtDoc, txtNombre, txtTel, txtHoras;
    private JComboBox<String> cbTipo;
    private DefaultTableModel model;

    public EmpleadoApp() {
        setTitle("Gestión de Empleados");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        txtDoc = new JTextField();
        txtNombre = new JTextField();
        txtTel = new JTextField();
        txtHoras = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"planta", "contratista", "hora"});

        panel.add(new JLabel("Documento:")); panel.add(txtDoc);
        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Teléfono:")); panel.add(txtTel);
        panel.add(new JLabel("Tipo:")); panel.add(cbTipo);
        panel.add(new JLabel("Horas (si es por hora):")); panel.add(txtHoras);

        JButton btnGuardar = new JButton("Guardar e Insertar");
        btnGuardar.addActionListener(this::guardarEmpleado);
        panel.add(btnGuardar);

        add(panel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Documento", "Nombre", "Teléfono", "Tipo", "Horas", "Salario", "Aumento"}, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        listarEmpleados();
    }

    private void guardarEmpleado(ActionEvent e) {
        String doc = txtDoc.getText();
        String nom = txtNombre.getText();
        String tel = txtTel.getText();
        String tipo = (String) cbTipo.getSelectedItem();
        int horas = tipo.equals("hora") ? Integer.parseInt(txtHoras.getText()) : 0;

        Empleado emp = new Empleado(doc, nom, tel, tipo, horas);
        double salario = EmpleadoDAO.calcularSalario(emp);
        double aumento = EmpleadoDAO.calcularAumento(emp, salario);

        EmpleadoDAO.guardarEmpleado(emp, salario, aumento);
        listarEmpleados();
        JOptionPane.showMessageDialog(this, "Empleado guardado correctamente.");
    }

    private void listarEmpleados() {
        model.setRowCount(0);
        List<String[]> lista = EmpleadoDAO.listarEmpleados();
        for (String[] fila : lista) model.addRow(fila);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmpleadoApp().setVisible(true));
    }
}