package ar.edu.uner.prestabook.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.persistence.PersistenceException;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.jframe.render.AreaTematicaRenderer;
import ar.edu.uner.prestabook.jframe.render.ColeccionRenderer;
import ar.edu.uner.prestabook.jframe.render.TipoObraRenderer;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Coleccion;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IObraDAO;

public class AgregarObra extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AgregarObra() {
		ventana();
		JPanel contentPane = contentPane();

		JPanel panelAgregarObra = panelAgregarObra();
		contentPane.add(panelAgregarObra);

		JLabel lblAgregarObra = lblAgregarObra();
		panelAgregarObra.add(lblAgregarObra);

		JTextField fieldIsbn = fieldIsbn();
		contentPane.add(fieldIsbn);

		JLabel lblIsbn = lblIsbn();
		contentPane.add(lblIsbn);

		JTextField fieldTitulo = fieldTitulo();
		contentPane.add(fieldTitulo);

		JLabel lblTitulo = lblTitulo();
		contentPane.add(lblTitulo);

		JTextField fieldSubtitulo = fieldSubtitulo();
		contentPane.add(fieldSubtitulo);

		JLabel lblSubtitulo = lblSubtitulo();
		contentPane.add(lblSubtitulo);

		JTextField fieldPrimerAutor = fieldPrimerAutor();
		contentPane.add(fieldPrimerAutor);

		JLabel lblPrimerAutor = lblPrimerAutor();
		contentPane.add(lblPrimerAutor);

		JTextField fieldSegundoAutor = fieldSegundoAutor();
		contentPane.add(fieldSegundoAutor);

		JLabel lblSegundoAutor = lblSegundoAutor();
		contentPane.add(lblSegundoAutor);

		JTextField fieldTercerAutor = fieldTercerAutor();
		contentPane.add(fieldTercerAutor);

		JLabel lblTercerAutor = lblTercerAutor();
		contentPane.add(lblTercerAutor);

		JTextField fieldGenero = fieldGenero();
		contentPane.add(fieldGenero);

		JLabel lblGenero = lblGenero();
		contentPane.add(lblGenero);

		JLabel lblTipoObra = lblTipoObra();
		contentPane.add(lblTipoObra);

		JLabel lblAreaTematica = lblAreaTematica();
		contentPane.add(lblAreaTematica);

		JButton btnAgregar = btnAgregar();
		contentPane.add(btnAgregar);

		JButton btnCancelar = btnCancelar();
		contentPane.add(btnCancelar);

		JComboBox<TipoObra> comboBoxTipoObra = comboBoxTipoObra();
		contentPane.add(comboBoxTipoObra);
		
		JScrollPane scrollPaneAreaTematica = jListAreaTematica();
        contentPane.add(scrollPaneAreaTematica);

		JCheckBox checkBoxColeccion = checkBoxColeccion();
		contentPane.add(checkBoxColeccion);

		JLabel lblIsbnDeColeccion = lblIsbnDeColeccion();
		contentPane.add(lblIsbnDeColeccion);

		JComboBox<Coleccion> comboBoxIsbnColeccion = comboBoxIsbnColeccion();
		contentPane.add(comboBoxIsbnColeccion);

		checkBoxColeccion.addActionListener(e -> {
			if (checkBoxColeccion.isSelected()) {
				lblIsbnDeColeccion.setEnabled(true);
				comboBoxIsbnColeccion.setEnabled(true);
			} else {
				lblIsbnDeColeccion.setEnabled(false);
				comboBoxIsbnColeccion.setEnabled(false);
			}
		});

		btnAgregar.addActionListener(e -> {
			
			@SuppressWarnings("unchecked")
            JList<AreaTematica> jListAreaTematica = (JList<AreaTematica>) scrollPaneAreaTematica.getViewport().getView();

			Boolean camposCompletos = !(fieldIsbn.getText().isBlank() || fieldTitulo.getText().isBlank()
					|| fieldSubtitulo.getText().isBlank() || fieldPrimerAutor.getText().isBlank()
					|| fieldSegundoAutor.getText().isBlank() || fieldTercerAutor.getText().isBlank()
					|| fieldGenero.getText().isBlank() || jListAreaTematica.isSelectionEmpty()) && comboBoxTipoObra.getSelectedItem() != null;
			
			if (Boolean.TRUE.equals(camposCompletos)) {

				TipoObra itemTipoObra = (TipoObra) comboBoxTipoObra.getSelectedItem();

                Set<AreaTematica> areasTematicas = new HashSet<>(jListAreaTematica.getSelectedValuesList());

				try {
					actualizarBaseDeDatos(fieldIsbn.getText(), fieldTitulo.getText(), fieldSubtitulo.getText(),
							fieldPrimerAutor.getText(), fieldSegundoAutor.getText(), fieldTercerAutor.getText(),
							fieldGenero.getText(), itemTipoObra.getNombre(), itemTipoObra.getId(),
							areasTematicas, checkBoxColeccion,
							comboBoxIsbnColeccion);

					JOptionPane.showInternalMessageDialog(null, "Datos guardados correctamente");
					this.setVisible(false);
				} catch (PersistenceException exception) {
					HibernateConnection.getCurrentSession().getTransaction().rollback();
					JOptionPane.showInternalMessageDialog(null, "Ya existe una obra con ese ISBN", "Obra repetida",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showInternalMessageDialog(null, "Debe completar todos los campos");
			}
		});

		btnCancelar.addActionListener(e -> this.setVisible(false));

	}

	private void actualizarBaseDeDatos(String isbn, String titulo, String subtitulo, String primerAutor,
			String segundoAutor, String tercerAutor, String genero, String tipoObra, Integer idTipoObra,
			Set<AreaTematica> areasTematicas, JCheckBox checkBoxColeccion,
			JComboBox<Coleccion> comboBoxIsbnColeccion) {

		Obra obra = new Obra();
		obra.setIsbn(isbn);
		obra.setTitulo(titulo);
		obra.setSubtitulo(subtitulo);
		obra.setPrimerAutor(primerAutor);
		obra.setSegundoAutor(segundoAutor);
		obra.setTercerAutor(tercerAutor);
		obra.setGenero(genero);
		obra.setTipo(new TipoObra(idTipoObra, tipoObra));
		obra.setArea(areasTematicas);
		if (checkBoxColeccion.isSelected()) {
			Coleccion itemColeccion = (Coleccion) comboBoxIsbnColeccion.getSelectedItem();
			obra.setIsbnColeccion(itemColeccion.getIsbn());
		}
		IObraDAO o = DaoFactory.getObraDAO();
		o.insert(obra);
	}

	public void ventana() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 449);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JPanel contentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 64, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}

	public JCheckBox checkBoxColeccion() {
		JCheckBox checkBoxColeccion = new JCheckBox("Pertenece a una colección");
		checkBoxColeccion.setBounds(157, 346, 192, 23);
		return checkBoxColeccion;
	}

	public JLabel lblIsbnDeColeccion() {
		JLabel lblIsbnDeColeccion = new JLabel("Isbn de colección");
		lblIsbnDeColeccion.setEnabled(false);
		lblIsbnDeColeccion.setBounds(375, 323, 114, 14);
		return lblIsbnDeColeccion;
	}

	public JButton btnAñadirEdicion() {
		JButton btnAñadirEdicion = new JButton("Añadir edición");
		btnAñadirEdicion.setBounds(166, 338, 127, 23);
		return btnAñadirEdicion;
	}

	public JPanel panelAgregarObra() {
		JPanel panelAgregarObra = new JPanel();
		panelAgregarObra.setBackground(new Color(0, 64, 128));
		panelAgregarObra.setBounds(0, 0, 655, 98);
		panelAgregarObra.setLayout(null);
		return panelAgregarObra;
	}

	public JLabel lblAgregarObra() {
		JLabel lblAgregarObra = new JLabel("Agregar Obra");
		lblAgregarObra.setForeground(new Color(255, 255, 255));
		lblAgregarObra.setBounds(240, 31, 191, 39);
		lblAgregarObra.setFont(new Font("Verdana", Font.BOLD, 20));
		return lblAgregarObra;
	}

	public JTextField fieldIsbn() {
		JTextField fieldIsbn = new JTextField();
		fieldIsbn.setBounds(37, 134, 166, 29);
		fieldIsbn.setColumns(10);
		fieldIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				fieldIsbn.setEditable(!Character.isLetter(ke.getKeyChar()));
			}
		});
		return fieldIsbn;
	}

	public JLabel lblIsbn() {
		JLabel lblIsbn = new JLabel("Isbn de obra");
		lblIsbn.setBounds(37, 120, 105, 14);
		return lblIsbn;
	}

	public JTextField fieldTitulo() {
		JTextField fieldTitulo = new JTextField();
		fieldTitulo.setColumns(10);
		fieldTitulo.setBounds(235, 134, 166, 29);
		return fieldTitulo;
	}

	public JLabel lblTitulo() {
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(237, 120, 46, 14);
		return lblTitulo;
	}

	public JTextField fieldSubtitulo() {
		JTextField fieldSubtitulo = new JTextField();
		fieldSubtitulo.setColumns(10);
		fieldSubtitulo.setBounds(437, 134, 166, 29);
		return fieldSubtitulo;
	}

	public JLabel lblSubtitulo() {
		JLabel lblSubtitulo = new JLabel("Subtítulo");
		lblSubtitulo.setBounds(436, 120, 73, 14);
		return lblSubtitulo;
	}

	public JTextField fieldPrimerAutor() {
		JTextField fieldPrimerAutor = new JTextField();
		fieldPrimerAutor.setColumns(10);
		fieldPrimerAutor.setBounds(37, 197, 166, 29);
		return fieldPrimerAutor;
	}

	public JLabel lblPrimerAutor() {
		JLabel lblPrimerAutor = new JLabel("Primer autor");
		lblPrimerAutor.setBounds(37, 183, 83, 14);
		return lblPrimerAutor;
	}

	public JTextField fieldSegundoAutor() {
		JTextField fieldSegundoAutor = new JTextField();
		fieldSegundoAutor.setColumns(10);
		fieldSegundoAutor.setBounds(235, 197, 166, 29);
		return fieldSegundoAutor;
	}

	public JLabel lblSegundoAutor() {
		JLabel lblSegundoAutor = new JLabel("Segundo autor");
		lblSegundoAutor.setBounds(237, 183, 112, 14);
		return lblSegundoAutor;
	}

	public JTextField fieldTercerAutor() {
		JTextField fieldTercerAutor = new JTextField();
		fieldTercerAutor.setColumns(10);
		fieldTercerAutor.setBounds(437, 197, 166, 29);
		return fieldTercerAutor;
	}

	public JLabel lblTercerAutor() {
		JLabel lblTercerAutor = new JLabel("Tercer autor");
		lblTercerAutor.setBounds(436, 183, 83, 14);
		return lblTercerAutor;
	}

	public JTextField fieldGenero() {
		JTextField fieldGenero = new JTextField();
		fieldGenero.setColumns(10);
		fieldGenero.setBounds(37, 265, 166, 29);
		return fieldGenero;
	}

	public JLabel lblGenero() {
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(37, 249, 64, 14);
		return lblGenero;
	}

	public JLabel lblTipoObra() {
		JLabel lblTipoObra = new JLabel("Tipo obra");
		lblTipoObra.setBounds(235, 249, 83, 14);
		return lblTipoObra;
	}

	public JComboBox<Coleccion> comboBoxIsbnColeccion() {
		JComboBox<Coleccion> comboBoxIsbnColeccion = new JComboBox<>(
				new Vector<>(DaoFactory.getColeccionDAO().findAll()));
		comboBoxIsbnColeccion.setBounds(374, 343, 166, 29);
		comboBoxIsbnColeccion.setRenderer(new ColeccionRenderer());
		comboBoxIsbnColeccion.setSelectedItem(null);
		return comboBoxIsbnColeccion;
	}

	public JComboBox<TipoObra> comboBoxTipoObra() {
		JComboBox<TipoObra> comboBoxObra = new JComboBox<>(new Vector<>(DaoFactory.getTipoObraDAO().findAll()));
		comboBoxObra.setBounds(235, 268, 166, 29);
		comboBoxObra.setRenderer(new TipoObraRenderer());
		comboBoxObra.setSelectedItem(null);
		return comboBoxObra;
	}

	public JLabel lblAreaTematica() {
		JLabel lblAreaTematica = new JLabel("Área temática");
		lblAreaTematica.setBounds(437, 249, 89, 14);
		return lblAreaTematica;
	}

	public JButton btnAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(194, 404, 89, 23);
		return btnAgregar;
	}

	public JButton btnCancelar() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(353, 404, 89, 23);
		return btnCancelar;
	}
	
	public JScrollPane jListAreaTematica() {
        JList<AreaTematica> jListAreaTematica = new JList<>(new Vector<>(DaoFactory.getAreaTematicaDAO().findAll()));
        jListAreaTematica.setCellRenderer(new AreaTematicaRenderer());
        jListAreaTematica.setVisibleRowCount(2);
        jListAreaTematica.setSelectionModel(new DefaultListSelectionModel() {
   
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
        JScrollPane scrollPane = new JScrollPane(jListAreaTematica);
        scrollPane.setBounds(437, 268, 166, 59);
        return scrollPane;
    }
}
