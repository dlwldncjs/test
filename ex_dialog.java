package tableTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import java.util.Arrays;

public class ex_dialog extends JFrame implements MouseListener {
	JTable a_table;
	JTable b_table;
	DefaultTableModel a_model;
	DefaultTableModel b_model;
	JScrollPane scroll;
	String[] colNames = { "Tag ID", "VR", "VM", "Le", "Description", "Value" };
	Object data[][] = { { "a", "b", "c", "d", "e", "f" }, { "g", "h", "i", "j", "k", "k" },
			{ "l", "m", "n", "o", "p", "q" }, { "r", "s", "t", "u", "v", "w" }, { "1", "2", "3", "4", "5", "6" },
			{ "a", "b", "c", "d", "e", "f" }, { "g", "h", "i", "j", "k", "k" }, { "l", "m", "n", "o", "p", "q" },
			{ "r", "s", "t", "u", "v", "w" }, { "1", "2", "3", "4", "5", "6" } };
	Object input_data[] = new Object[6];

	public ex_dialog() {
		Dialog dialog = new Dialog(this, "Test");
		dialog.setSize(200, 200);
		a_model = new DefaultTableModel(data, colNames) {
			public boolean isCellEditable(int rowa, int cola) {
				return false;
			}
		};
		b_model = new DefaultTableModel(null, colNames) {
			public boolean isCellEditable(int rowa, int cola) {
				return false;
			}
		};
		a_table = new JTable(a_model);
		b_table = new JTable(b_model);
		scroll = new JScrollPane(a_table);
		scroll = new JScrollPane(b_table);
		a_table.setPreferredScrollableViewportSize(new Dimension(560, 830));
		b_table.setPreferredScrollableViewportSize(new Dimension(560, 830));
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JScrollPane(a_table));
		c.add(new JScrollPane(b_table));
		c.setPreferredSize(new Dimension(1200, 840));
		this.pack();
		this.setVisible(true);

		a_table.addMouseListener(this);
		b_table.addMouseListener(this);

//////////LOAD TABLE//////////
		new ex_xmlModel();
		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder xmlBuilder;
		try {
			File xmlfile = new File("C:\\Users\\JW\\Desktop\\Workspace\\xml13.xml");
			xmlBuilder = xmlFactory.newDocumentBuilder();
			Document doc = xmlBuilder.parse(xmlfile);
			Element rootElement = doc.getDocumentElement();
			NodeList list = rootElement.getChildNodes();
			xmlFactory.setIgnoringElementContentWhitespace(true);
			int m = 0;
			if (list.getLength() > 0) {
				for (int i = 0; i < list.getLength(); i++) {
					NodeList childlist = list.item(i).getChildNodes();
					if (childlist.getLength() > 0) {
						for (int u = 0; u < childlist.getLength(); u++) {
							if (Node.TEXT_NODE != childlist.item(u).getNodeType()) {
								if (childlist.item(u).getTextContent() != null) {
									input_data[m] = childlist.item(u).getTextContent();
									m++;
									if (m % 6 == 0) {
										b_model.addRow(input_data);
										m = 0;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception q) {
			q.printStackTrace();
		}

//////////SEARCH//////////
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(a_table.getModel());
		JTextField jtfFilter = new JTextField();
		a_table.setRowSorter(rowSorter);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(jtfFilter, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("error");
			}
		});

	}

//////////MOUSE ADD, DELETE//////////
	@Override
	public void mouseClicked(MouseEvent e) { ////////// ADD and SAVE
		if (e.getClickCount() == 2) {
			int a_row = a_table.getSelectedRow();
			int a_col = a_table.getColumnCount();
			for (int i = 0; i < a_col; i++) {
				input_data[i] = data[a_row][i];
			}
			b_model.addRow(input_data);
			String[] add_stringArray = Arrays.copyOf(input_data, input_data.length, String[].class);
			new ex_xmlAdd(add_stringArray);
		}
		if (e.getButton() == MouseEvent.BUTTON3) { ////////// DEL and SAVE
			int b_row = b_table.getSelectedRow();
			int a_col = a_table.getColumnCount();
			for (int i = 0; i < a_col; i++) {
				input_data[i] = b_table.getValueAt(b_row, i);
			}
			String[] del_stringArray = Arrays.copyOf(input_data, input_data.length, String[].class);
			b_model.removeRow(b_row);
			new ex_xmlDel(del_stringArray);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
