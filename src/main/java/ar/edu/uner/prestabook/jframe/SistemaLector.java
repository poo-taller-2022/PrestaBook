package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import lombok.Getter;

public class SistemaLector extends JFrame {

    private static final long serialVersionUID = 1L;
    @Getter
    private JLabel textUsuario;

    /**
     * Create the frame.
     */

    public SistemaLector() {

        /**
         * Create components
         */

        ventana();

        JPanel contentPane = contentPane();

        JPanel panelPrestabook = panelPrestabook();
        contentPane.add(panelPrestabook);

        JLabel lblIconCerrarSesion = lblIconCerrarSesion();
        panelPrestabook.add(lblIconCerrarSesion);
        jLabelImage(lblIconCerrarSesion);

        panelPrestabook.add(lblPrestabook());

        JButton btnExit = btnExit();
        panelPrestabook.add(btnExit);

        JButton btnCerrarSesion = btnCerrarSesion();
        panelPrestabook.add(btnCerrarSesion);

        JPanel panelOpciones = panelOpciones();
        contentPane.add(panelOpciones);

        JButton btnSolicitudes = btnSolicitudes();
        panelOpciones.add(btnSolicitudes);

        panelOpciones.add(lblUsuario());
        panelOpciones.add(textUsusario());
        panelOpciones.add(panelSeparador());
        panelOpciones.add(lblOpciones());

        JButton btnConsultarObras = btnConsultarObras();
        panelOpciones.add(btnConsultarObras);

        JPanel panelConsultarObras = panelEntidades();

        JPanel panelBienvenida = panelBienvenida();
        contentPane.add(panelBienvenida);

        JLabel lblBienvenidaParte1 = lblBienvenidaParte1();
        panelBienvenida.add(lblBienvenidaParte1);

        JLabel lblIconLibreria = lblIconLibreria();
        panelBienvenida.add(lblIconLibreria);

        JLabel lblBienvenidaParte2 = lblBienvenidaParte2();
        panelBienvenida.add(lblBienvenidaParte2);

        JTextField txtIngresarAreaTematica = txtIngresarAreaTematica();
        panelConsultarObras.add(txtIngresarAreaTematica);

        /**
         * Crea el panel para administrar tipos de obras
         */

        btnConsultarObras.addActionListener(e -> {
            panelBienvenida.setVisible(false);
            panelConsultarObras.setVisible(true);

            contentPane.add(panelConsultarObras);

            panelConsultarObras.add(lblConsultarObras());

            JScrollPane scrollPane = scrollPane();
            panelConsultarObras.add(scrollPane);

            JTable tableTipoDeObras = new JTable();

            DefaultTableModel model = new DefaultTableModel();
            tableTipoDeObras.setModel(model);
            model.addColumn("");
            model.addColumn(Constants.AREA_TEMATICA);
            model.addColumn("Titulo");
            model.addColumn("Subitulo");
            model.addColumn("1° autor");
            model.addColumn("2° autor");
            model.addColumn("3° autor");
            model.addColumn("Género");
            model.addColumn(Constants.TIPO_OBRA);
            model.addColumn("Editorial");
            model.addColumn("Año de edicion");
            model.addColumn("Pais");
            model.addColumn("Idioma");
            model.addColumn("Paginas");
            model.addColumn("Volúmenes");
            model.addColumn("Formato");
            model.addColumn("Pertenece a una colección");
            model.addColumn("Nombre de coleccion");
            model.addColumn("N° ejemplares");

            tableTipoDeObras.setAutoCreateRowSorter(true);
            TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<>(model);
            tableTipoDeObras.setRowSorter(sorted);

            llenarTabla(model);
            scrollPane.setViewportView(tableTipoDeObras);

            JButton btnReservarObra = btnReservarObra();
            panelConsultarObras.add(btnReservarObra);

            /**
             * Botón con evento para agregar tipo de obra
             */

            btnReservarObra.addActionListener(b -> {

            });

            /**
             *
             */

            txtIngresarAreaTematica.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txtIngresarAreaTematica.setText("");
                }
            });

            /**
             *
             */

            txtIngresarAreaTematica.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    filtrar();
                }

                private void filtrar() {
                    sorted.setRowFilter(RowFilter.regexFilter(txtIngresarAreaTematica.getText().toLowerCase(), 1));
                }
            });

        });

        /**
         * Method created to log out and return to the "IniciarSesion" window
         */

        btnCerrarSesion.addActionListener(e -> {
            IniciarSesion login = new IniciarSesion();
            login.setVisible(true);
            SistemaLector.this.dispose();
        });

        /**
         * Created method to close window
         */

        btnExit.addActionListener(e -> System.exit(0));
    }

    /**
     * Create components
     */

    public void ventana() {
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Opciones");
        setBounds(100, 100, 1390, 811);
        setLocationRelativeTo(null);
    }

    public JPanel contentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        return contentPane;
    }

    public JPanel panelBienvenida() {
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBounds(237, 103, 1153, 708);
        panelBienvenida.setLayout(null);
        return panelBienvenida;
    }

    public JTextField txtIngresarAreaTematica() {
        JTextField txtIngresarAreaTematica = new JTextField();
        txtIngresarAreaTematica.setForeground(new Color(128, 128, 128));
        txtIngresarAreaTematica.setText("Buscar por area tematica");
        txtIngresarAreaTematica.setBounds(10, 110, 1130, 37);
        txtIngresarAreaTematica.setColumns(10);
        return txtIngresarAreaTematica;
    }

    public JLabel lblBienvenidaParte1() {
        JLabel lblBienvenidaParte1 = new JLabel("¡Bienvenido al sistema de gestión de préstamos de libros más");
        lblBienvenidaParte1.setBounds(204, 11, 775, 136);
        lblBienvenidaParte1.setForeground(Color.GRAY);
        lblBienvenidaParte1.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte1;
    }

    public JLabel lblIconLibreria() {
        JLabel lblIconLibreria = new JLabel("");
        lblIconLibreria.setBounds(194, 191, 605, 493);
        lblIconLibreria.setIcon(new ImageIcon(new File("src/main/resources/library.png").getAbsolutePath()));
        return lblIconLibreria;
    }

    public JLabel lblBienvenidaParte2() {
        JLabel lblBienvenidaParte2 = new JLabel("grande del mundo!");
        lblBienvenidaParte2.setBounds(401, 100, 369, 136);
        lblBienvenidaParte2.setForeground(Color.GRAY);
        lblBienvenidaParte2.setFont(new Font(Constants.FONT, Font.BOLD, 21));
        return lblBienvenidaParte2;
    }

    public JPanel panelPrestabook() {
        JPanel panelPrestabook = new JPanel();
        panelPrestabook.setBounds(237, 0, 1153, 103);
        panelPrestabook.setBorder(null);
        panelPrestabook.setBackground(new Color(0, 64, 128));
        panelPrestabook.setLayout(null);
        return panelPrestabook;
    }

    public JLabel lblIconCerrarSesion() {
        JLabel lblIconCerrarSesion = new JLabel("");
        lblIconCerrarSesion.setBounds(817, 5, 19, 16);
        return lblIconCerrarSesion;
    }

    public void jLabelImage(JLabel lblIconCerrarSesion) {
        ImageIcon image = new ImageIcon(new File("src/main/resources/Vector.png").getAbsolutePath());
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblIconCerrarSesion.getWidth(),
                lblIconCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
        lblIconCerrarSesion.setIcon(icon);
        this.repaint();
    }

    public JLabel lblPrestabook() {
        JLabel lblPrestabook = new JLabel("PrestaBook");
        lblPrestabook.setBounds(404, 31, 267, 42);
        lblPrestabook.setForeground(Color.WHITE);
        lblPrestabook.setFont(new Font(Constants.FONT, Font.BOLD, 32));
        return lblPrestabook;
    }

    public JButton btnExit() {
        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnExit.setBackground(new Color(255, 106, 106));
        btnExit.setBounds(1106, 0, 47, 25);
        return btnExit;
    }

    public JButton btnCerrarSesion() {
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(Constants.FONT, Font.BOLD, 11));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBounds(995, -2, 101, 23);
        btnCerrarSesion.setForeground(new Color(255, 255, 255));
        btnCerrarSesion.setBackground(new Color(0, 64, 128));
        return btnCerrarSesion;
    }

    public JPanel panelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBounds(0, 0, 237, 811);
        panelOpciones.setBackground(new Color(0, 45, 89));
        panelOpciones.setLayout(null);
        return panelOpciones;
    }

    public JLabel lblUsuario() {
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(27, 0, 122, 37);
        lblUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 17));
        lblUsuario.setForeground(new Color(255, 255, 255));
        return lblUsuario;
    }

    public JLabel textUsusario() {
        textUsuario = new JLabel("");
        textUsuario.setBackground(new Color(0, 128, 0));
        textUsuario.setBounds(124, 0, 173, 37);
        textUsuario.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        textUsuario.setForeground(new Color(255, 255, 255));
        return textUsuario;
    }

    public JButton btnSolicitudes() {
        JButton btnSolicitudes = new JButton("Solicitudes");
        btnSolicitudes.setVerifyInputWhenFocusTarget(false);
        btnSolicitudes.setForeground(new Color(0, 64, 128));
        btnSolicitudes.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnSolicitudes.setFocusPainted(false);
        btnSolicitudes.setBorderPainted(false);
        btnSolicitudes.setBorder(null);
        btnSolicitudes.setBackground(Color.WHITE);
        btnSolicitudes.setBounds(23, 74, 188, 31);
        return btnSolicitudes;
    }

    public JPanel panelSeparador() {
        JPanel panelSeparador = new JPanel();
        panelSeparador.setBounds(23, 121, 188, 3);
        return panelSeparador;
    }

    public JLabel lblOpciones() {
        JLabel lblOpciones = new JLabel("Opciones");
        lblOpciones.setForeground(new Color(255, 255, 255));
        lblOpciones.setFont(new Font(Constants.FONT, Font.BOLD, 16));
        lblOpciones.setBounds(73, 135, 105, 23);
        return lblOpciones;
    }

    public JButton btnConsultarObras() {
        JButton btnConsultarObras = new JButton("Consultar obras");
        btnConsultarObras.setFocusPainted(false);
        btnConsultarObras.setBackground(new Color(255, 255, 255));
        btnConsultarObras.setVerifyInputWhenFocusTarget(false);
        btnConsultarObras.setBorderPainted(false);
        btnConsultarObras.setBorder(null);
        btnConsultarObras.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnConsultarObras.setForeground(new Color(0, 64, 128));
        btnConsultarObras.setBounds(22, 169, 189, 31);
        return btnConsultarObras;
    }

    public JPanel panelEntidades() {
        JPanel panelEntidades = new JPanel();
        panelEntidades.setBounds(237, 103, 1153, 708);
        panelEntidades.setLayout(null);
        return panelEntidades;
    }

    public JLabel lblConsultarObras() {
        JLabel lblConsultarObras = new JLabel("Consultar obras");
        lblConsultarObras.setBounds(440, 10, 369, 136);
        lblConsultarObras.setForeground(Color.GRAY);
        lblConsultarObras.setFont(new Font(Constants.FONT, Font.BOLD, 19));
        return lblConsultarObras;
    }

    public JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 158, 1130, 300);
        return scrollPane;
    }

    private JButton btnReservarObra() {
        JButton btnReservarObra = new JButton("Reservar obra");
        btnReservarObra.setFocusPainted(false);
        btnReservarObra.setBackground(new Color(0, 64, 128));
        btnReservarObra.setForeground(new Color(255, 255, 255));
        btnReservarObra.setFont(new Font(Constants.FONT, Font.BOLD, 12));
        btnReservarObra.setBorderPainted(false);
        btnReservarObra.setBounds(440, 500, 210, 20);
        return btnReservarObra;
    }

    /**
     * @param model
     * 
     *              model.addColumn("Pais"); model.addColumn("Idioma");
     *              model.addColumn("Paginas"); model.addColumn("Volúmenes");
     *              model.addColumn("Formato"); model.addColumn("Pertenece a una
     *              colección"); model.addColumn("Nombre de coleccion");
     *              model.addColumn("N° ejemplares");
     */

    public void llenarTabla(DefaultTableModel model) {
        Integer i = 0;

        IEdicionDAO edicionDAO = DaoFactory.getEdicionDAO();
        List<Edicion> ediciones = edicionDAO.findAll();
        for (Edicion edicion : ediciones) {
            List<Object> fila = new LinkedList<>();

            IObraDAO obraDAO = DaoFactory.getObraDAO();
            Obra obra = obraDAO.findById(edicion.getIsbnObra());
            fila.add(++i);
            fila.add(obra.getArea().getNombre());
            fila.add(obra.getTitulo());
            fila.add(obra.getSubtitulo());
            fila.add(obra.getPrimerAutor());
            fila.add(obra.getSegundoAutor());
            fila.add(obra.getTercerAutor());
            fila.add(obra.getGenero());
            fila.add(obra.getTipo().getNombre());

            fila.add(edicion.getEditorial());
            fila.add(edicion.getAnio());
            fila.add(edicion.getPais());
            fila.add(edicion.getIdioma());
            fila.add(edicion.getPaginas());
            fila.add(edicion.getVolumenes());

            Set<Formato> formatos = edicion.getFormatos();
            StringBuilder concatenarFormatos = new StringBuilder();

            for (Formato formato : formatos) {
                concatenarFormatos.append(formato.getNombre() + ", ");
            }

            fila.add(concatenarFormatos.toString());

            fila.add("");
            fila.add("");

            IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
            List<Ejemplar> ejemplares = ejemplarDAO.findAll();
            Integer cantidadEjemplares = 0;
            if (ejemplares != null) {
                for (Ejemplar ejemplar : ejemplares) {

                    if (ejemplar.getIsbnObra().equals(obra.getIsbn())) {
                        cantidadEjemplares++;
                    }
                }
                fila.add(cantidadEjemplares);
            } else {
                fila.add(cantidadEjemplares);
            }

            model.addRow(new Vector<>(fila));
        }
    }
}
