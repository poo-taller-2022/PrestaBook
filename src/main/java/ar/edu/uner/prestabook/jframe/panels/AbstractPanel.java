package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.jframe.common.Components;
import ar.edu.uner.prestabook.jframe.tablemodel.NonEditableTableModel;

/**
 * Abstract Panel for the interface system. Though it is thought to be used to
 * display entities, it can also use as a welcome screen.
 *
 */
public abstract class AbstractPanel {

	/**
	 * Class construct
	 */
	public AbstractPanel() {
		
	}
	
    JPanel panel = Components.panelEntities();
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    /**
     * Hides this panel
     */
    public void hide() {
        panel.setVisible(false);
        model = null;
        table = null;
        scrollPane = null;
    }

    /**
     * Sets the general configuration for the panel, such as the layout, bounds,
     * table, model, and so on. It also makes it visible.
     */
    public void prepare() {
        model = new NonEditableTableModel();
        table = new JTable();
        scrollPane = Components.scrollPane();
        panel.setVisible(true);
        panel.add(scrollPane);
        table.setSize(Components.PANEL_WIDTH, 1600);
        table.setModel(model);
        scrollPane.setViewportView(table);
        panel.add(Components.lblPanelTitle(getPanelName()));
        Components.clearTable(table);
        setModelColumns();
    }

    /**
     * Initializes this panel.
     * @return a panel
     */
    public abstract JPanel init();

    /**
     * Sets the model columns to be displayed on the table.
     */
    public abstract void setModelColumns();

    /**
     * get the name of this panel
     * @return the name of this panel.
     */
    public abstract String getPanelName();

}
