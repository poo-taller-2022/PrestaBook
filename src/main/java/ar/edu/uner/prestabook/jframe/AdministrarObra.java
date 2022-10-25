package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.render.EdicionRenderer;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;
import ar.edu.uner.prestabook.persistence.IReservaDAO;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdministrarObra extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public AdministrarObra(Obra obra) {
        ventana();
        JPanel contentPane = contentPane();

        JPanel panelAdministrarObra = panelAdministrarObra();
        contentPane.add(panelAdministrarObra);

        JLabel lblAdministrarObra = lblAdministrarObra();
        panelAdministrarObra.add(lblAdministrarObra);

        JScrollPane scrollPane = scrollPane();
        contentPane.add(scrollPane);

        JTextArea textArea = textArea();
        scrollPane.setViewportView(textArea);

        JLabel lblEdicion = lblEdicion();
        contentPane.add(lblEdicion);

        JTextField fieldIsbnObra = fieldIsbnObra();
        fieldIsbnObra.setText(obra.getTitulo());
        contentPane.add(fieldIsbnObra);

        JLabel lblTituloObra = lblTituloObra();
        contentPane.add(lblTituloObra);

        JLabel lblEjemplaresDisponibles = lblEjemplaresDisponibles();
        contentPane.add(lblEjemplaresDisponibles);

        JComboBox<Edicion> comboBoxEdiciones = comboBoxEdiciones();
        contentPane.add(comboBoxEdiciones);

        JTextField fieldEjemplaresDisponibles = fieldEjemplaresDisponibles();
        contentPane.add(fieldEjemplaresDisponibles);

        JButton btnSolicitarPrestamo = Components.btnGeneric("Solicitar préstamo", "Center");
        JButton btnReservarObra = Components.btnGeneric("Reservar Obra", "Left");
        JButton btnRegresar = Components.btnGeneric("Regresar", "Right");
        
        contentPane.add(btnRegresar);
        contentPane.add(btnSolicitarPrestamo);
        contentPane.add(btnReservarObra);

        List<Edicion> ediciones = DaoFactory.getEdicionDAO().findByAllObraIsbn(obra.getIsbn());
        if (ediciones.isEmpty()) {
            comboBoxEdiciones.setEnabled(false);
        } else {
            for (Edicion edicion : ediciones) {
                comboBoxEdiciones.addItem(edicion);
            }
        }

        comboBoxEdiciones.addActionListener(e -> {
            textArea.setText(cargarObra(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).toString());
            fieldEjemplaresDisponibles
                    .setText(cantidadDeEjemplares(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).toString());
            if (cantidadDeEjemplares(obra, (Edicion) comboBoxEdiciones.getSelectedItem()) == 0) {
                btnReservarObra.setEnabled(true);
                btnSolicitarPrestamo.setEnabled(false);
            } else {
                btnReservarObra.setEnabled(false);
                btnSolicitarPrestamo.setEnabled(true);
            }
        });

        textArea.setText(cargarObra(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).toString());

        btnRegresar.addActionListener(e -> this.setVisible(false));

        btnReservarObra.addActionListener(b -> {
            new ReservarEjemplar(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).setVisible(true);
            this.setVisible(false);
        });

        btnSolicitarPrestamo.addActionListener(b -> {
            new PedirPrestamo(obra, (Edicion) comboBoxEdiciones.getSelectedItem()).setVisible(true);
            this.setVisible(false);
        });

    }

    /**
     * 
     * Concatenates all information of an obra
     * Used to displays more information of an obra
     * @param obra
     * @param edicion
     * @return String with the information
     */
    public StringBuilder cargarObra(Obra obra, Edicion edicion) {
        StringBuilder informacionObra = new StringBuilder();
        informacionObra.append(" Isbn de obra: " + obra.getIsbn() +
                "\n Área temática: " + contatenarAreas(obra) +
                "\n Titulo de obra: " + obra.getTitulo() +
                "\n Subtitulo de obra: " + obra.getSubtitulo() +
                "\n Primer autor: " + obra.getPrimerAutor() +
                "\n Segundo autor: " + obra.getSegundoAutor() +
                "\n Tercer autor: " + obra.getTercerAutor() +
                "\n Género: " + obra.getGenero() +
                "\n Tipo obra: " + obra.getTipo().getNombre() +
                "\n Editorial: " + edicion.getEditorial() +
                "\n Año de edicion: " + edicion.getAnio().toString() +
                "\n Pais de edicion: " + edicion.getPais() +
                "\n Idioma: " + edicion.getIdioma() +
                "\n Numero de edicion: " + edicion.getNumero().toString() +
                "\n Paginas: " + edicion.getPaginas().toString() +
                "\n Volumenes: " + edicion.getVolumenes().toString() +
                "\n Formato: " + contatenarFormatos(edicion.getFormatos()).toString() +
                "\n Pertenece a una colección: " + (obra.getIsbnColeccion() == null ? "No" : "Si") +
                "\n Nombre de colección: " + (obra.getIsbnColeccion() == null ? "No corresponde"
                        : DaoFactory.getColeccionDAO().findById(obra.getIsbnColeccion()).getTitulo()));

        return informacionObra;
    }
    
    /**
     * 
     * Concatenates thematic areas in one string
     * Used to displays more then one area in the GUI
     */
    public StringBuilder contatenarAreas(Obra obra) {
        Set<AreaTematica> areas = obra.getArea();
        StringBuilder contatenarAreas = new StringBuilder();

        for (AreaTematica area : areas) {
            contatenarAreas.append(area.getNombre() + ", ");
        }
        contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);
        return contatenarAreas;
    }

    /**
     * 
     * Concatenates formats in one string
     * Used to displays more then one format in the GUI
     */
    public StringBuilder contatenarFormatos(Set<Formato> formatos) {
        StringBuilder contatenarFormatos = new StringBuilder();

        for (Formato formato : formatos) {
            contatenarFormatos.append(formato.getNombre() + ", ");
        }
        contatenarFormatos = contatenarFormatos.deleteCharAt(contatenarFormatos.length() - 2);
        return contatenarFormatos;
    }

    /**
     * 
     * Count all available ejemplares
     * @param obra
     * @param edicion
     * @return number of available ejemplares
     */
    public Integer cantidadDeEjemplares(Obra obra, Edicion edicion) {
        IPrestamoDAO prestamoDAO = DaoFactory.getPrestamoDAO();
        IReservaDAO reservaDAO = DaoFactory.getReservaDAO();
        List<Ejemplar> ejemplares = DaoFactory.getEjemplarDAO().findAll();
        Integer cantidadEjemplares = 0;
        if (ejemplares != null) {
            for (Ejemplar ejemplar : ejemplares) {
                if (Objects.equals(ejemplar.getIsbnObra(), obra.getIsbn()) && ejemplar.getMotivoBaja() == null
                        && Objects.equals(ejemplar.getIdEdicion(), edicion.getId())
                        && prestamoDAO.findByIdEjemplar(ejemplar.getId()) == null
                        && reservaDAO.findByIdEjemplar(ejemplar.getId()) == null) {
                    cantidadEjemplares++;
                }
            }
            return cantidadEjemplares;
        } else {
            return 0; 
        }
    }

    /**
     * Creates the window
     */
    public void ventana() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 664, 583);
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
     * Creates a label
     * 
     * @return a label with ejemplaresDisponibles text 
     */
    public JLabel lblEjemplaresDisponibles() {
        JLabel lblEjemplaresDisponibles = new JLabel("Ejemplares disponibles");
        lblEjemplaresDisponibles.setBounds(446, 121, 152, 14);
        return lblEjemplaresDisponibles;
    }

    /**
     * Crates a comboBox of available ejemplares
     */
    public JTextField fieldEjemplaresDisponibles() {
        JTextField fieldEjemplaresDisponibles = new JTextField();
        fieldEjemplaresDisponibles.setText((String) null);
        fieldEjemplaresDisponibles.setEditable(false);
        fieldEjemplaresDisponibles.setColumns(10);
        fieldEjemplaresDisponibles.setBounds(446, 139, 152, 29);
        return fieldEjemplaresDisponibles;
    }

    /**
     * Creates a text field of isbnObra
     */
    public JTextField fieldIsbnObra() {
        JTextField fieldIsbnObra = new JTextField();
        fieldIsbnObra.setEditable(false);
        fieldIsbnObra.setBounds(67, 139, 166, 29);
        fieldIsbnObra.setColumns(10);
        return fieldIsbnObra;
    }

    /**
     * Creates a label
     * 
     * @return a label with edicion text
     */
    public JLabel lblEdicion() {
        JLabel lblEdicion = new JLabel("Edicion");
        lblEdicion.setBounds(264, 121, 46, 14);
        return lblEdicion;
    }
    
    /**
     * Creates a label
     * 
     * @return a label with title of obra text
     */
    public JLabel lblTituloObra() {
        JLabel lblTituloObra = new JLabel("Obra");
        lblTituloObra.setBounds(67, 120, 119, 14);
        return lblTituloObra;
    }

    /**
     * Creates a scrollPane
     */
    public JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(67, 181, 531, 310);
        return scrollPane;
    }

    /**
     * Creates a text
     */
    public JTextArea textArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 10, 400, 100);
        return textArea;
    }

    /**
     * Creates a panel
     * 
     * @return adminObra panel
     */
    public JPanel panelAdministrarObra() {
        JPanel panelAdministrarObra = new JPanel();
        panelAdministrarObra.setBackground(new Color(0, 64, 128));
        panelAdministrarObra.setBounds(0, 0, 664, 98);
        panelAdministrarObra.setLayout(null);
        return panelAdministrarObra;
    }

    /**
     * Creates a label
     * 
     * @return a label with administrar obra text
     */
    public JLabel lblAdministrarObra() {
        JLabel lblAdministrarObra = new JLabel("Administrar obra");
        lblAdministrarObra.setForeground(new Color(255, 255, 255));
        lblAdministrarObra.setBounds(252, 30, 276, 39);
        lblAdministrarObra.setFont(new Font("Verdana", Font.BOLD, 20));
        return lblAdministrarObra;
    }

    /**
     * Creates a Combo Box with all Editions
     */
    public JComboBox<Edicion> comboBoxEdiciones() {
        JComboBox<Edicion> comboBoxEdiciones = new JComboBox<>();
        comboBoxEdiciones.setBounds(260, 139, 166, 29);
        comboBoxEdiciones.setRenderer(new EdicionRenderer());
        comboBoxEdiciones.setSelectedItem(null);
        return comboBoxEdiciones;
    }
}
