package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.render.FormatoRenderer;
import ar.edu.uner.prestabook.jframe.render.ObraRenderer;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;

/**
 * Opens a window for functionality of adding an edition
 *
 */
public class AgregarEdicion extends JFrame {

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
    public AgregarEdicion(JTable table, DefaultTableModel model) {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelAgregarEdicion = panelAgregarEdicion();
        contentPane.add(panelAgregarEdicion);

        JTextField fieldPais = fieldPais();
        contentPane.add(fieldPais);

        JLabel lblPais = lblPais();
        contentPane.add(lblPais);

        JLabel lblAgregarEdicion = lblAgregarEdicion();
        panelAgregarEdicion.add(lblAgregarEdicion);

        JTextField fieldEditorial = fieldEditorial();
        contentPane.add(fieldEditorial);

        JLabel lblEditorial = lblEditorial();
        contentPane.add(lblEditorial);

        JTextField fieldVolumenes = fieldVolumenes();
        contentPane.add(fieldVolumenes);

        JLabel lblVolumenes = lblVolumenes();
        contentPane.add(lblVolumenes);

        JTextField fieldAnio = fieldAnio();
        contentPane.add(fieldAnio);

        JLabel lblAnio = lblAnio(); 
        contentPane.add(lblAnio);

        JTextField fieldPaginas = fieldPaginas();
        contentPane.add(fieldPaginas);

        JLabel lblPaginas = lblPaginas();
        contentPane.add(lblPaginas);

        JTextField fieldNumero = fieldNumero();
        contentPane.add(fieldNumero);

        JLabel lblNumero = lblNumero();
        contentPane.add(lblNumero);

        JTextField fieldIdioma = fieldIdioma();
        contentPane.add(fieldIdioma);

        JLabel lblIdioma = lblIdioma();
        contentPane.add(lblIdioma);

        JLabel lblFormato = lblFormato();
        contentPane.add(lblFormato);

        JScrollPane scrollPaneFormatos = jListFormatos();
        contentPane.add(scrollPaneFormatos);

        JButton btnAgregar = btnAgregar();
        contentPane.add(btnAgregar);

        JButton btnCancelar = btnCancelar();
        contentPane.add(btnCancelar);

        JComboBox<Obra> comboBoxObras = comboBoxObras();
        contentPane.add(comboBoxObras);

        JLabel lblObra = lblObra();
        contentPane.add(lblObra);

        btnCancelar.addActionListener(e -> this.setVisible(false));

        btnAgregar.addActionListener(e -> {
            @SuppressWarnings("unchecked")
            JList<Formato> jListFormatos = (JList<Formato>) scrollPaneFormatos.getViewport().getView();

            Boolean camposCompletos = !(fieldEditorial.getText().isBlank() || fieldPais.getText().isBlank()
                    || fieldNumero.getText().isBlank() || fieldAnio.getText().isBlank()
                    || fieldVolumenes.getText().isBlank() || fieldPaginas.getText().isBlank()
                    || fieldIdioma.getText().isBlank() || jListFormatos.isSelectionEmpty());

            if (Boolean.TRUE.equals(camposCompletos)) {

                Set<Formato> formatos = new HashSet<>(jListFormatos.getSelectedValuesList());

                Obra obra = (Obra) comboBoxObras.getSelectedItem();

                actualizarBaseDeDatos(fieldEditorial.getText(), fieldPais.getText(), fieldNumero.getText(),
                        fieldAnio.getText(), fieldVolumenes.getText(), fieldPaginas.getText(), fieldIdioma.getText(),
                        formatos, obra.getIsbn());

                Components.clearTable(table);
                Tabla.fill(model, Constants.EDICION);
                JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
                this.setVisible(false);

            } else {
                JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
            }
        });
    }

    /**
     * Updates database by inserting a new edition entity
     * 
     * @param editorial
     * @param pais
     * @param numero
     * @param anio
     * @param volumenes
     * @param paginas
     * @param idioma
     * @param formatos
     * @param isbnObra
     */
    private void actualizarBaseDeDatos(String editorial, String pais, String numero, String anio, String volumenes,
            String paginas, String idioma, Set<Formato> formatos, String isbnObra) {
        Edicion edicion = new Edicion();
        edicion.setAnio(Integer.parseInt(anio));
        edicion.setEditorial(editorial);
        edicion.setIdioma(idioma);
        edicion.setNumero(Integer.parseInt(numero));
        edicion.setPaginas(Integer.parseInt(paginas));
        edicion.setPais(pais);
        edicion.setVolumenes(Integer.parseInt(volumenes));
        edicion.setFormatos(formatos);
        edicion.setIsbnObra(isbnObra);

        IEdicionDAO e = DaoFactory.getEdicionDAO();
        e.insert(edicion);
    }

    /**
     * Creates the pane
     * 
     * @return a container
     */
    private JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new MatteBorder(3, 3, 3, 3, new Color(0, 64, 128)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    /**
     * Creates a label
     * 
     * @return a label with obra text
     */
    public JLabel lblObra() {
        JLabel lblObra = new JLabel("Obra");
        lblObra.setBounds(37, 139, 46, 14);
        return lblObra;
    }

    /**
     * Creates a button add
     * @return a button
     */
    public JButton btnAgregar() {
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(94, 454, 89, 23);
        return btnAgregar;
    }

    /**
     * Creates a button cancel
     * @return a button
     */
    public JButton btnCancelar() {
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(260, 454, 89, 23);
        return btnCancelar;
    }

    /**
     * Creates the window
     */
    private void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 439, 508);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(Constants.PRESTABOOK);
    }

    /**
     * Creates a panel
     * 
     * @return a panel add edition
     */
    public JPanel panelAgregarEdicion() {
        JPanel panelAgregarEdicion = new JPanel();
        panelAgregarEdicion.setBackground(new Color(0, 64, 128));
        panelAgregarEdicion.setBounds(0, 0, 439, 98);
        panelAgregarEdicion.setLayout(null);
        return panelAgregarEdicion;
    }

    /**
     * Creates a label
     * 
     * @return a label with add edition text
     */
    public JLabel lblAgregarEdicion() {
        JLabel lblAgregarEdicion = new JLabel("Agregar edición");
        lblAgregarEdicion.setForeground(new Color(255, 255, 255));
        lblAgregarEdicion.setBounds(136, 25, 191, 39);
        lblAgregarEdicion.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblAgregarEdicion;
    }

    /**
     * Creates a text field to editorial
     * @return a text field
     */
    public JTextField fieldEditorial() {
        JTextField fieldEditorial = new JTextField();
        fieldEditorial.setBounds(37, 219, 166, 29);
        fieldEditorial.setColumns(10);
        return fieldEditorial;
    }

    /**
     * Creates a label
     * 
     * @return a label with editorial text
     */
    public JLabel lblEditorial() {
        JLabel lblEditorial = new JLabel("Editorial");
        lblEditorial.setBounds(38, 205, 46, 14);
        return lblEditorial;
    }

    /**
     * Creates a text field to country
     * @return a text field
     */
    public JTextField fieldPais() {
        JTextField fieldPais = new JTextField();
        fieldPais.setColumns(10);
        fieldPais.setBounds(237, 154, 166, 29);
        return fieldPais;
    }

    /**
     * Creates a label
     * 
     * @return a label with country text
     */
    public JLabel lblPais() {
        JLabel lblPais = new JLabel("Pais");
        lblPais.setBounds(237, 139, 46, 14);
        return lblPais;
    }

    /**
     * Creates a text field to number
     * @return a text field
     */
    public JTextField fieldNumero() {
        JTextField fieldNumero = new JTextField();
        fieldNumero.setColumns(10);
        fieldNumero.setBounds(37, 332, 166, 29);
        fieldNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldNumero.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldNumero;
    }

    /**
     * Creates a label
     * 
     * @return a label with number text
     */
    public JLabel lblNumero() {
        JLabel lblNumero = new JLabel("Numero");
        lblNumero.setBounds(37, 314, 73, 14);
        return lblNumero;
    }

    /**
     * Creates a text field to year
     * @return a text field
     */
    public JTextField fieldAnio() {
        JTextField fieldAnio = new JTextField();
        fieldAnio.setColumns(10);
        fieldAnio.setBounds(37, 274, 166, 29);
        fieldAnio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldAnio.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldAnio;
    }

    /**
     * Creates a label
     * 
     * @return a label with year text
     */
    public JLabel lblAnio() {
        JLabel lblAnio = new JLabel("Año");
        lblAnio.setBounds(39, 256, 83, 14);
        return lblAnio;
    }

    /**
     * Creates a text field to volumenes
     * @return a text field
     */
    public JTextField fieldVolumenes() {
        JTextField fieldVolumenes = new JTextField();
        fieldVolumenes.setColumns(10);
        fieldVolumenes.setBounds(237, 219, 166, 29);
        fieldVolumenes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldVolumenes.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldVolumenes;
    }

    /**
     * Creates a label
     * 
     * @return a label with volumenes text
     */
    public JLabel lblVolumenes() {
        JLabel lblVolumenes = new JLabel("Volúmenes");
        lblVolumenes.setBounds(237, 205, 112, 14);
        return lblVolumenes;
    }

    /**
     * Creates a text field to pages
     * @return a text field
     */
    public JTextField fieldPaginas() {
        JTextField fieldPaginas = new JTextField();
        fieldPaginas.setColumns(10);
        fieldPaginas.setBounds(237, 274, 166, 29);
        fieldPaginas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                fieldPaginas.setEditable(!Character.isLetter(ke.getKeyChar()));
            }
        });
        return fieldPaginas;
    }

    /**
     * Creates a label
     * 
     * @return a label with pages text
     */
    public JLabel lblPaginas() {
        JLabel lblPaginas = new JLabel("Páginas");
        lblPaginas.setBounds(237, 256, 83, 14);
        return lblPaginas;
    }

    /**
     * Creates a text field to language
     * @return a text field
     */
    public JTextField fieldIdioma() {
        JTextField fieldIdioma = new JTextField();
        fieldIdioma.setColumns(10);
        fieldIdioma.setBounds(37, 387, 166, 29);
        return fieldIdioma;
    }

    /**
     * Creates a label
     * 
     * @return a label with language text
     */
    public JLabel lblIdioma() {
        JLabel lblIdioma = new JLabel("Idioma");
        lblIdioma.setBounds(37, 372, 64, 14);
        return lblIdioma;
    }

    /**
     * Creates a label
     * 
     * @return a label with add formats text
     */
    public JLabel lblFormato() {
        JLabel lblFormato = new JLabel("Formatos");
        lblFormato.setBounds(237, 314, 83, 14);
        return lblFormato;
    }

    /**
     * Creates a combo box of Obras
     * @return a combo box
     */
    public JComboBox<Obra> comboBoxObras() {
        JComboBox<Obra> comboBoxObra = new JComboBox<>(new Vector<>(DaoFactory.getObraDAO().findAll()));
        comboBoxObra.setBounds(37, 154, 166, 29);
        comboBoxObra.setRenderer(new ObraRenderer());
        comboBoxObra.setSelectedItem(null);
        return comboBoxObra;
    }

    /**
     * Creates a scroll pane
     * @return a scroll pane
     */
    public JScrollPane jListFormatos() {
        JList<Formato> jListFormatos = new JList<>(new Vector<>(DaoFactory.getFormatoDAO().findAll()));
        jListFormatos.setCellRenderer(new FormatoRenderer());
        jListFormatos.setVisibleRowCount(2);
        jListFormatos.setSelectionModel(new DefaultListSelectionModel() {

            private static final long serialVersionUID = 1L;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(jListFormatos);
        scrollPane.setBounds(237, 332, 166, 84);
        return scrollPane;
    }
}
