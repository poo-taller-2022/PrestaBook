package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.render.EdicionRenderer;
import ar.edu.uner.prestabook.jframe.render.ObraRenderer;
import ar.edu.uner.prestabook.jframe.utils.DateSettings;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;


/**
 * Opens a window for add a new copy
 *
 */

public class AgregarEjemplar extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates the frame.
     * 
     * @param model model that stores the values of the table this frame will insert
     *              entities to
     * @param table table that displays the model
     */
    public AgregarEjemplar(JTable table, DefaultTableModel model) {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelAgregarEjemplar = panelAgregarEjemplar();
        contentPane.add(panelAgregarEjemplar);

        JLabel lblAgregarEjemplar = lblAgregarEjemplar();
        panelAgregarEjemplar.add(lblAgregarEjemplar);

        JTextField fieldFormaAdquisicion = fieldFormaAdquisicion();
        contentPane.add(fieldFormaAdquisicion);

        DatePicker calendarFechaAdquisicion = calendarFechaAdquisicion();
        contentPane.add(calendarFechaAdquisicion);

        JLabel lblFormaAdquisicion = lblFormaAdquisicion();
        contentPane.add(lblFormaAdquisicion);

        JLabel lblFechaAdquisicion = lblFechaAdquisicion();
        contentPane.add(lblFechaAdquisicion);

        JTextField fieldObservaciones = fieldObservaciones();
        contentPane.add(fieldObservaciones);

        JLabel lblObservaciones = lblObservaciones();
        contentPane.add(lblObservaciones);

        JButton btnAgregar = btnAgregar();
        contentPane.add(btnAgregar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        JTextField fieldPasillo = fieldPasillo();
        contentPane.add(fieldPasillo);

        JTextField fieldEstanteria = fieldEstanteria();
        contentPane.add(fieldEstanteria);

        JTextField fieldEstante = fieldEstante();
        contentPane.add(fieldEstante);

        JLabel lblPasillo = lblPasillo();
        contentPane.add(lblPasillo);

        JLabel lblEstanteria = lblEstanteria();
        contentPane.add(lblEstanteria);

        JLabel lblEstante = lblEstante();
        contentPane.add(lblEstante);

        JLabel lblObra = lblObra();
        contentPane.add(lblObra);

        JLabel lblEdicion = lblEdicion();
        contentPane.add(lblEdicion);

        JComboBox<Edicion> comboBoxEdiciones = comboBoxEdiciones();
        contentPane.add(comboBoxEdiciones);

        JComboBox<Obra> comboBoxObras = comboBoxObras();

        comboBoxObras.addItemListener(e -> {
            Obra obra = (Obra) e.getItem();
            comboBoxEdiciones.removeAllItems();
            List<Edicion> ediciones = DaoFactory.getEdicionDAO().findByAllObraIsbn(obra.getIsbn());
            for (Edicion edicion : ediciones) {

                comboBoxEdiciones.addItem(edicion);
            }
        });
        contentPane.add(comboBoxObras);

        btnCancelar.addActionListener(e -> this.setVisible(false));

        btnAgregar.addActionListener(e -> {
            Boolean camposCompletos = !(fieldFormaAdquisicion.getText().isBlank()
                    || fieldObservaciones.getText().isBlank() || fieldPasillo.getText().isBlank()
                    || fieldEstanteria.getText().isBlank() || fieldEstante.getText().isBlank())
                    && calendarFechaAdquisicion.getDate() != null && comboBoxEdiciones.getSelectedItem() != null;

            if (Boolean.TRUE.equals(camposCompletos)) {

                Obra itemObra = (Obra) comboBoxObras.getSelectedItem();

                Edicion itemEdicion = (Edicion) comboBoxEdiciones.getSelectedItem();

                actualizarBaseDeDatos(fieldFormaAdquisicion.getText(), calendarFechaAdquisicion.getDate().toString(),
                        fieldObservaciones.getText(), fieldPasillo.getText(), fieldEstanteria.getText(),
                        fieldEstante.getText(), itemObra.getIsbn(), itemEdicion.getId());

                Components.clearTable(table);
                Tabla.fill(model, Constants.EJEMPLAR);
                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
            }
        });
    }

    /**
     * Updates database by inserting a new ejemplar entity
     * 
     * @param formaAdquisicion
     * @param fechaAdquisicion
     * @param observaciones
     * @param pasillo
     * @param estanteria
     * @param estante
     * @param isbnObra
     * @param idEdicion
     */
    private void actualizarBaseDeDatos(String formaAdquisicion, String fechaAdquisicion, String observaciones,
            String pasillo, String estanteria, String estante, String isbnObra, Long idEdicion) {
        IObraDAO o = DaoFactory.getObraDAO();
        Obra obra = o.findById(isbnObra);

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setIsbnObra(obra.getIsbn());
        ejemplar.setTitulo(obra.getTitulo());
        ejemplar.setSubtitulo(obra.getSubtitulo());
        ejemplar.setPrimerAutor(obra.getPrimerAutor());
        ejemplar.setSegundoAutor(obra.getSegundoAutor());
        ejemplar.setTercerAutor(obra.getTercerAutor());
        ejemplar.setGenero(obra.getGenero());
        ejemplar.setTipo(new TipoObra(obra.getTipo().getId(), obra.getTipo().getNombre()));
        ejemplar.setFormaAdquisicion(formaAdquisicion);
        ejemplar.setFechaAdquisicion(fechaAdquisicion);
        ejemplar.setObservaciones(observaciones);
        ejemplar.setIdEdicion(idEdicion);

        CodigoIdentificatorio codigoIden = new CodigoIdentificatorio();
        codigoIden.setEstante(Integer.parseInt(estante));
        codigoIden.setEstanteria(Integer.parseInt(estanteria));
        codigoIden.setPasillo(Integer.parseInt(pasillo));

        ICodigoIdentificatorioDAO co = DaoFactory.getCodigoIdentificatorioDAO();
        co.insert(codigoIden);
        ejemplar.setCodigoIdentificatorio(codigoIden);

        IEjemplarDAO ej = DaoFactory.getEjemplarDAO();
        ej.insert(ejemplar);
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 448);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(Constants.PRESTABOOK);
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    public JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new MatteBorder(3, 3, 3, 3, new Color(0, 64, 128)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    /**
     * Creates a text field to pasillo
     */
    public JTextField fieldPasillo() {
        JTextField fieldPasillo = new JTextField();
        fieldPasillo.setColumns(10);
        fieldPasillo.setBounds(446, 233, 166, 29);
        fieldPasillo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldPasillo.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldPasillo;
    }

    /**
     * Creates a text field 
     * @return a text field to shelves book
     * 
     */
    public JTextField fieldEstanteria() {
        JTextField fieldEstanteria = new JTextField();
        fieldEstanteria.setColumns(10);
        fieldEstanteria.setBounds(147, 297, 166, 29);
        fieldEstanteria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldEstanteria.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldEstanteria;
    }

    /**
     * Creates a text field 
     * @return a text field to shelve book
     */
    public JTextField fieldEstante() {
        JTextField fieldEstante = new JTextField();
        fieldEstante.setColumns(10);
        fieldEstante.setBounds(353, 297, 166, 29);
        fieldEstante.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldEstante.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldEstante;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of hall text
     */
    public JLabel lblPasillo() {
        JLabel lblPasillo = new JLabel("Pasillo");
        lblPasillo.setBounds(446, 220, 133, 14);
        return lblPasillo;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of shelves book text
     */
    public JLabel lblEstanteria() {
        JLabel lblEstanteria = new JLabel("Estantería");
        lblEstanteria.setBounds(147, 285, 133, 14);
        return lblEstanteria;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of shelve book text
     */
    public JLabel lblEstante() {
        JLabel lblEstante = new JLabel("Estante");
        lblEstante.setBounds(353, 285, 133, 14);
        return lblEstante;
    }

    /**
     * Creates a button 
     * @return a button to add
     */
    public JButton btnAgregar() {
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(198, 376, 89, 23);
        return btnAgregar;
    }

    /**
     * Creates a button 
     * @return a button to cancel
     */
    public JButton btnCancelar() {
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(390, 376, 89, 23);
        return btnCancelar;
    }

    /**
     * Creates a panel
     * 
     * @return a panel to add copy
     */
    public JPanel panelAgregarEjemplar() {
        JPanel panelAgregarEjemplar = new JPanel();
        panelAgregarEjemplar.setBackground(new Color(0, 64, 128));
        panelAgregarEjemplar.setBounds(0, 0, 655, 98);
        panelAgregarEjemplar.setLayout(null);
        return panelAgregarEjemplar;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of add copy text
     */
    public JLabel lblAgregarEjemplar() {
        JLabel lblAgregarEjemplar = new JLabel("Agregar Ejemplar");
        lblAgregarEjemplar.setForeground(new Color(255, 255, 255));
        lblAgregarEjemplar.setBounds(252, 30, 276, 39);
        lblAgregarEjemplar.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblAgregarEjemplar;
    }

    /**
     * Creates a text field 
     * @return a text field to acquisition way
     */
    public JTextField fieldFormaAdquisicion() {
        JTextField fieldFormaAdquisicion = new JTextField();
        fieldFormaAdquisicion.setColumns(10);
        fieldFormaAdquisicion.setBounds(41, 233, 166, 29);
        return fieldFormaAdquisicion;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of acquisition way text
     */
    public JLabel lblFormaAdquisicion() {
        JLabel lblFormaAdquisicion = new JLabel("Forma de adquisición");
        lblFormaAdquisicion.setBounds(41, 220, 127, 14);
        return lblFormaAdquisicion;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of date of  acquisition text
     */
    public JLabel lblFechaAdquisicion() {
        JLabel lblFechaAdquisicion = new JLabel("Fecha de adquisición");
        lblFechaAdquisicion.setBounds(446, 149, 133, 14);
        return lblFechaAdquisicion;
    }

    /**
     * Creates a text field 
     * @return a text field to observations
     */
    public JTextField fieldObservaciones() {
        JTextField fieldObservaciones = new JTextField();
        fieldObservaciones.setColumns(10);
        fieldObservaciones.setBounds(241, 233, 166, 29);
        return fieldObservaciones;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of observations text
     */
    public JLabel lblObservaciones() {
        JLabel lblObservaciones = new JLabel("Observaciones");
        lblObservaciones.setBounds(241, 220, 133, 14);
        return lblObservaciones;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of book text
     */
    public JLabel lblObra() {
        JLabel lblObra = new JLabel("Obra");
        lblObra.setBounds(41, 149, 46, 14);
        return lblObra;
    }

    /**
     * Creates a combo box of type Obras
     * @return a combo box with all Obra
     */
    public JComboBox<Obra> comboBoxObras() {
        JComboBox<Obra> comboBoxObras = new JComboBox<>(new Vector<>(DaoFactory.getObraDAO().findAll()));
        comboBoxObras.setRenderer(new ObraRenderer());
        comboBoxObras.setBounds(41, 163, 166, 29);
        comboBoxObras.setSelectedItem(null);
        return comboBoxObras;
    }

    /**
     * Creates a date picker
     * @return a date to acquisition
     */
    public DatePicker calendarFechaAdquisicion() {
        DatePicker calendarFechaAdquisicion = new DatePicker();
        calendarFechaAdquisicion.setBounds(446, 163, 166, 29);
        calendarFechaAdquisicion.setSettings(DateSettings.getDatePickerSettings());
        return calendarFechaAdquisicion;
    }

    /**
     * Creates a label
     * 
     * @return a label with title of edition text
     */
    public JLabel lblEdicion() {
        JLabel lblEdicion = new JLabel("Edicion");
        lblEdicion.setBounds(241, 149, 72, 14);
        return lblEdicion;
    }

    /**
     * Creates a combo box of type  Edicion
     * @return a combo box with all editions
     */
    public JComboBox<Edicion> comboBoxEdiciones() {
        JComboBox<Edicion> comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.setBounds(241, 163, 166, 29);
        comboBoxEdiciones.setRenderer(new EdicionRenderer());
        return comboBoxEdiciones;
    }
}
