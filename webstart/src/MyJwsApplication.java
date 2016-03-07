/**
 * This skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students. Though we were trying to
 * create a good example application here, the code is probably not suitable for
 * a real life application.
 * 
 * Written by
 * 	Gergely J. Horváth
 * 	Richárd Milanovits
 * Based on the previous version by
 * 	Ádám Kollár
 * Revised by
 * 	Roland Kamaras
 */

import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

// Application class
public class MyJwsApplication extends javax.swing.JFrame {

	// As suggested by the Swing model, we'll have a GUI + controllers class
	// (this one, that also includes the entry point) and a model.
	protected MyJwsApplicationModel model;

	// GUI components

	// Connection panel
	protected JPanel connectionPanel;
	protected JTextField userNameField;
	protected JTextField passwordField;
	protected JButton connectButton;
	protected JLabel connectionStateLabel;

	// Tabbed pane
	protected JTabbedPane tabbedPane;

	// Log tab
	protected JPanel logTab;
	protected JScrollPane logScrollPanel;
	protected JTextArea logTextArea;

	// Search tab
	protected JPanel searchTab;
	protected JPanel searchInputPanel;
	protected JTextField keywordField;
	protected JButton searchButton;
	protected JScrollPane searchScrollPanel;
	protected JTable searchTable;

	// Class constructor
	public MyJwsApplication() {
		model = new MyJwsApplicationModel(this);
	}

	// Entry point for the application
	public static void main(String[] args) {

		// Create a new instance of the Runnable class and show it
		SwingUtilities.invokeLater(new 

			// Create an embedded Runnable class
			Runnable() {

				// Display GUI window
				public void run() {
					MyJwsApplication instance = new MyJwsApplication();

					// Populate the JFrame with a nice GUI
					instance.createGUI();

					// Make it reasonably big
					instance.setSize(600, 500);

					// Center the window and show it
					instance.setLocationRelativeTo(null);
					instance.setVisible(true);
				}

			}

		);
	}

	// Create the GUI
	protected void createGUI() {

		// Create all the GUI components

		// Connection panel
		connectionPanel = new JPanel();
		userNameField = new JTextField();
		passwordField = new JTextField();
		connectButton = new JButton();
		connectionStateLabel = new JLabel();

		// Tabbed pane
		tabbedPane = new JTabbedPane();

		// Log tab
		logTab = new JPanel();
		logScrollPanel = new JScrollPane();
		logTextArea = new JTextArea();

		// Search tab
		searchTab = new JPanel();
		searchInputPanel = new JPanel();
		keywordField = new JTextField();
		searchButton = new JButton();
		searchScrollPanel = new JScrollPane();
		searchTable = new JTable();

		// Set the CloseOperation to call dispose() on close
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Set the minimum window size
		setMinimumSize(new java.awt.Dimension(600, 500));
		setPreferredSize(new java.awt.Dimension(600, 500));

		// Placing GUI elements

		// This is the panel for the connection options
		connectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		connectionPanel.setLayout(new BoxLayout(connectionPanel, BoxLayout.LINE_AXIS));

		// Username field
		userNameField.setMaximumSize(new java.awt.Dimension(200, 27));
		userNameField.setMinimumSize(new java.awt.Dimension(100, 27));
		userNameField.setPreferredSize(new java.awt.Dimension(150, 27));

		userNameField.setText("");

		// Add username field to the connection panel
		connectionPanel.add(userNameField);

		// Password field
		passwordField.setMaximumSize(new java.awt.Dimension(200, 27));
		passwordField.setMinimumSize(new java.awt.Dimension(100, 27));
		passwordField.setPreferredSize(new java.awt.Dimension(150, 27));

		passwordField.setText("");

		// Add password field to the connection panel
		connectionPanel.add(passwordField);

		// Connect button
		connectButton.setMnemonic('c');
		connectButton.setText("Connect");

		// Connect button action listener
		connectButton.addActionListener(new java.awt.event.ActionListener() {

			// Action performed method
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				connectButtonActionPerformed(evt);
			}
		});

		// Add connect button to the connection panel
		connectionPanel.add(connectButton);

		// Little label to show the connection status
		connectionStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		connectionStateLabel.setText("<html>Connection: <font color=\"red\">disconnected</font>");
		connectionStateLabel.setMinimumSize(new java.awt.Dimension(200, 17));
		connectionStateLabel.setPreferredSize(new java.awt.Dimension(200, 17));

		// Add connection status label to the connection panel
		connectionPanel.add(connectionStateLabel);

		// Finally put the connection panel on the window
		getContentPane().add(connectionPanel, java.awt.BorderLayout.PAGE_START);

		// Now we create a tabbed pane for the tabs
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// First a nice tab for logging
		logTab.setLayout(new java.awt.BorderLayout());

		// Log scroll panel
		logScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Log text area
		logTextArea.setEditable(false);
		logTextArea.setColumns(20);
		logTextArea.setRows(5);
		logTextArea.setTabSize(4);

		// Locate the tab
		logScrollPanel.setViewportView(logTextArea);
		logTab.add(logScrollPanel, java.awt.BorderLayout.CENTER);
		tabbedPane.addTab("Log", logTab);

		// Then a search tab for searching
		searchTab.setLayout(new BoxLayout(searchTab, BoxLayout.PAGE_AXIS));

		// Search input panel
		searchInputPanel.setLayout(new BoxLayout(searchInputPanel, BoxLayout.LINE_AXIS));

		// Search keyword field
		searchInputPanel.add(keywordField);

		// Search button
		searchButton.setMnemonic('k');
		searchButton.setText("Search");

		// Search button action listener
		searchButton.addActionListener(new java.awt.event.ActionListener() {

			// Action performed method
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				searchButtonActionPerformed(evt);
			}
		});

		// Add search button to the input panel
		searchInputPanel.add(searchButton);

		// Add search input panel to the search tab
		searchTab.add(searchInputPanel);

		// Search table
		searchTable.setModel(new DefaultTableModel(
				new Object [][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null}
				},
				new String [] {
					"Title 1", "Title 2", "Title 3", "Title 4"
				}
			)
			{
				// Is cell editable
				@Override
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			}
		);

		// Locate the tab
		searchScrollPanel.setViewportView(searchTable);
		searchTab.add(searchScrollPanel);
		tabbedPane.addTab("Search", searchTab);

		// !TODO: This is the place you should add your other tabs (like edit)

		// Finally add the tabbed pane to the window and pack the layout
		getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);
		pack();

		// Here you could set the look and feel, etc.
	}

	/**
	 * This is called whenever the connect button is pressed.
	 * @param event Contains details about the AWT event.
	 */
	protected void connectButtonActionPerformed(java.awt.event.ActionEvent event) {

		try {

			// The model's connect method will do everything for us, just call it
			model.connect(userNameField.getText(), passwordField.getText());
			connectionStateLabel.setText("<html>Connection: <font color=\"green\">created</font>");

			// Test the connection
			if (model.testConnection()) {
				log("Connection seems to be working.");
			} else {
				log("It's a TRAP!");
			}

		} catch (SQLException e) {

			// !TODO: More user friendly error handling
			log(e.toString());

		} catch (ClassNotFoundException e) {

			// !TODO: More user friendly error handling
			log(e.toString());

		}

	}

	/**
	 * This is called whenever the search button is pressed.
	 * @param event Contains details about the AWT event.
	 */
	protected void searchButtonActionPerformed(java.awt.event.ActionEvent event) {

		// Let's grab the TableModel
		DefaultTableModel tableModel = (DefaultTableModel) searchTable.getModel();

		// Clear all the rows
		tableModel.setRowCount(0);

		// Then we can add the new ones like this
		tableModel.addRow(new Object[] {null,null,null,null});
	}

	/**
	 * Appends the message (with a line break added) to the log.
	 * @param message The message to be logged.
	 */
	protected void log(String message) {
		logTextArea.append(message + "\n");
	}

	// Dispose method
	@Override
	public void dispose() {
		super.dispose();
	}

}
