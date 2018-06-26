/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import Dao.AlumnosDao;
import Entidades.Alumnos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roberto
 */
public class Interfaz extends JFrame{
    public JLabel lblCodigo, lblUniversidad, lblEdad, lblEstado, lblNombre, lblApellido, lblEdads;
    
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
    public Interfaz() {
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
        container.add(lblEstado);
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
        container.add(lblEdads);
        container.add(nombre);
        container.add(apellido);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblCodigo = new JLabel("Carnet");
        lblUniversidad = new JLabel("Universidad");
        lblEdad = new JLabel("Edad");
        lblEstado = new JLabel("Estado");
        lblNombre = new JLabel("Nombre");
        lblApellido = new JLabel("Apellido");
        lblEdads = new JLabel("Edad");
        //////////////////////////////////////////////////
        lblNombre.setBounds(300,40,x,y);
        lblCodigo.setBounds(10, 10, x, y);
        lblUniversidad.setBounds(10, 60, x, y);
        lblEdad.setBounds(10, 100, x, y);
        lblEstado.setBounds(10, 140, x, y);
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
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return int.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Edad");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        AlumnosDao fd = new AlumnosDao();
        ArrayList<Alumnos> filtros = fd.readAll();

        for (Alumnos fi : filtros) {
            tm.addRow(new Object[]{fi.getCarnet(),fi.getNombres(),fi.getApellidos(),fi.getEdad(),fi.getUniversidad(),fi.isEstado()});
        }

        resultados.setModel(tm);
    }
    
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlumnosDao fd = new AlumnosDao();
                Alumnos f = new Alumnos(codigo.getText(), nombre.getText(),apellido.getText(),Integer.parseInt(edad.getText()), marca.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setEstado(false);
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
                AlumnosDao fd = new AlumnosDao();
                Alumnos f = new Alumnos(codigo.getText(), nombre.getText(),apellido.getText(),Integer.parseInt(edad.getText()), marca.getSelectedItem().toString(), true);
                if (no.isSelected()) {
                    f.setEstado(false);
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
                AlumnosDao fd = new AlumnosDao();
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
                AlumnosDao fd = new AlumnosDao();
                Alumnos f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro no encontrado");
                } else {
                    codigo.setText(f.getCarnet());
                    marca.setSelectedItem(f.getUniversidad());
                    edad.setText(Integer.toString(f.getEdad()));

                    if (f.isEstado()) {
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
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 3).toString());
                    apellido.setText(resultados.getValueAt(resultados.getSelectedRow(), 4).toString());
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
        marca.setSelectedItem("FRAM");
        edad.setText("");
    }
        
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
}
   
}

