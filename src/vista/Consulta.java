/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import dao.FiltroDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Inscripcion;
/**
 *
 * @author LN710Q
 */

public class Consulta extends JFrame {

    public JLabel lblCodigo, lblUniversidad, lblEdad, estado, lblNombre, lblApellido, edads;

    public JTextField codigo, descripcion, edad, nombre, apellido;
    public JComboBox marca;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int x = 130, y = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblUniversidad);
        container.add(lblEdad);
        container.add(estado);
        container.add(codigo);
        container.add(marca);
        container.add(edad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        container.add(lblNombre);
        container.add(edad);
        container.add(lblApellido);
        container.add(edads);
        container.add(nombre);
        container.add(apellido);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblCodigo = new JLabel("Carnet");
        lblUniversidad = new JLabel("Universidad");
        lblEdad = new JLabel("Edad");
        //estado = new JLabel("Estado");
        lblNombre = new JLabel("Nombre");
        lblApellido = new JLabel("Apellido");
        edads = new JLabel("Edad");
        //////////////////////////////////////////////////
        lblNombre.setBounds(300,40,x,y);
        lblCodigo.setBounds(10, 10, x, y);
        lblUniversidad.setBounds(10, 60, x, y);
        lblEdad.setBounds(10, 100, x, y);
        //estado.setBounds(10, 140, x, y);
        lblApellido.setBounds(300, 90, x, y);
    }

    public final void formulario() {
        codigo = new JTextField();
        nombre = new JTextField();
        apellido = new JTextField();
        marca = new JComboBox();
        edad = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        marca.addItem("UCA");
        marca.addItem("UDB");
        marca.addItem("UFG");
        marca.addItem("UGB");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);

        codigo.setBounds(140, 10, x, y);
        marca.setBounds(140, 60, x, y);
        edad.setBounds(140, 100, x, y);
        si.setBounds(140, 140, 50, y);
        no.setBounds(210, 140, 50, y);
        nombre.setBounds(300, 70, x, y);
        apellido.setBounds(300, 120, x, y);

        buscar.setBounds(300, 10, x, y);
        insertar.setBounds(10, 210, x, y);
        actualizar.setBounds(150, 210, x, y);
        eliminar.setBounds(300, 210, x, y);
        limpiar.setBounds(450, 210, x, y);
        resultados = new JTable();
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return int.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Universidad");

        FiltroDao fd = new FiltroDao();
        ArrayList<Inscripcion> filtros = fd.readAll();

        for (Inscripcion fi : filtros) {
            tm.addRow(new Object[]{fi.getId(), fi.getNombres(), fi.getEdad(), fi.getApellidos()});
        }

        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Inscripcion f = new Inscripcion(codigo.getText(), marca.getSelectedItem().toString(),
                        Integer.parseInt(edad.getText()), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Inscripcion f = new Inscripcion(codigo.getText(), marca.getSelectedItem().toString(),
                        Integer.parseInt(edad.getText()), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas al querer modificar el filtro");

                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Inscripcion f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro no encontrado");
                } else {
                    codigo.setText(f.getApellidos());
                    marca.setSelectedItem(f.getNombres());
                    edad.setText(Integer.toString(f.getEdad()));

                    if (f.isExistencia()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    codigo.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    marca.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    edad.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
                    if (resultados.getValueAt(resultados.getSelectedRow(), 3).toString() == "false") {
                        no.setSelected(true);
                    } else {
                        si.setSelected(true);
                    }
                }
            }
        });
    }

    public void limpiarCampos() {
        codigo.setText("");
        marca.setSelectedItem("UCA");
        edad.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}

