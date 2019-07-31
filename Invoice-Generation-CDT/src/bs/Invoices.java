package bs;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Invoices extends JFrame {
	private JTextField invoice_date;
	private JTextField receiving_date;
	private JTextField vendorName;
	private JTextField work_Des;
	private JTextField rateField;
	private JTextField qtyField;
	private JTextField po_number;
	private JTextField po_date;
	private JTextField vendor;
	private JComboBox docs_checked;
	private JTextField party_gst;
	private JComboBox fin_tds;
	private JTextField amt28;
	private JTextField amt18;
	private JTextField amt12;
	private JTextField tax12;
	private JTextField tax5;
	private JTextField tax18;
	private JTextField tax28;
	private JTextField tax0;
	private JTextField gstTotal;
	private JTextField igstField;
	private JTextField sgstField;
	private JTextField cgstField;
	private JTextField subTotal;
	private JTextField taxTotal;
	private JTextField amount_claimed;
	private JTextField amt0;
	private JTextField amt5;
	
	private JTextArea party_address;
	
	private int srno = 1;
	
	private JComboBox productField;
	private JComboBox invoice_num;
	private JComboBox party_name;
	private JComboBox gstCombo;
	private JComboBox transportCombo;
	private JComboBox payment_period;
	private JComboBox nop;
	private JComboBox submitted;
	
	private JScrollPane scrollPane;
	private DefaultTableModel model_sale = new DefaultTableModel();
	private JTable table_sale = new JTable();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoices frame = new Invoices();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	ResultSet set_com;
	
	public void commonMethodForSt(String query) {
		try {
			Statement st = connection.createStatement();
			set_com = st.executeQuery(query);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}	
	}
	/*public void partyDropDown() {
		party_name.addItem("");
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful for party dropdown");
//			JOptionPane.showMessageDialog(null, "Connection Successful");
			
			commonMethodForSt("select NAME from PARTY");
			while(set_com.next()) {
				party_name.addItem(set_com.getString(1));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
		}
		party_name.getEditor().getEditorComponent().addKeyListener(new ComboKeyHandler(party_name));
	}
	*/
	public void productDropDown() {
		productField.addItem("");
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful for product dropdown");
//			JOptionPane.showMessageDialog(null, "Product Connection Successful");
			
			commonMethodForSt("select PRODUCT_NAME from PRODUCT");
			while(set_com.next()) {
				productField.addItem(set_com.getString(1));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				connection.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
		}			
		productField.getEditor().getEditorComponent().addKeyListener(new ComboKeyHandler(productField));
	}
	
	/*public void transportDropDown() {
		transportCombo.addItem("");
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful for transport dropdown");
//			JOptionPane.showMessageDialog(null, "Transport Connection Successful");
			
			commonMethodForSt("select TRANPORT_NAME from TRANSPORT");
			while(set_com.next()) {
				transportCombo.addItem(set_com.getString(1));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
		}
		transportCombo.getEditor().getEditorComponent().addKeyListener(new ComboKeyHandler(transportCombo));
	}*/
	
	
	public void productDetails() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful for displaying product details");
//			JOptionPane.showMessageDialog(null, "product detail displaying");
			
			String pname = productField.getSelectedItem().toString();
			commonMethodForSt("select * from PRODUCT where PRODUCT_NAME='"+pname+"'");
			if(set_com.next()) {
//				System.out.print(set_com);
				rateField.setText(set_com.getString("RATE"));
				fin_tds.getSelectedItem().toString();
				gstCombo.setSelectedItem(set_com.getString("TAX_RATE"));
			}
		}catch (Exception e) {
//			e.printStackTrace();
			
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				 
			}
		}
		
	}
	
	public void partyDetails() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful for party details adding");
//			JOptionPane.showMessageDialog(null, "party details added");
			
			String ptname = party_name.getSelectedItem().toString();
			commonMethodForSt("select * from PARTY where NAME='"+ptname+"'");
			if(set_com.next()) {
//				System.out.print(set_com);
				party_gst.setText(set_com.getString("GST_NO"));
				po_number.setText(set_com.getString("PO_NUMBER"));
				po_date.setText(set_com.getString("PO_DETAILS"));
				vendor.setText(set_com.getString("VENDOR"));
				work_Des.setText(set_com.getString("WORK"));
				party_address.setText(set_com.getString("ADDRESS"));
				//party_state.setText(set_com.getString("STATE"));
			}
		}catch (Exception e) {
//			e.printStackTrace();
			
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				
			}
		}
		
	}
	public void invoiceNODetail() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful before invoice no extraction");
//			JOptionPane.showMessageDialog(null, "invoice details added");
			
			commonMethodForSt("SELECT max(INVOICE_NO) FROM INVOICE_TABLE ");
			if(set_com.next()) {
//				System.out.print(set_com);
				int billNO = Integer.parseInt(set_com.getString("MAX(INVOICE_NO)"));
				billNO +=1;
				invoice_num.setSelectedItem((Integer.toString(billNO)));;
			}
		}catch (Exception e) {
			
//			e.printStackTrace();
			
			
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				
			}
		}
		
	}
	
	public void invoiceDetail() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful before invoice no extraction");
//			JOptionPane.showMessageDialog(null, "invoice details added");
			
			String ptname = invoice_num.getSelectedItem().toString();
			commonMethodForSt("select * from INVOICE_TABLE where INVOICE_NO='"+ptname+"'");
			if(set_com.next()) {
//				System.out.print(set_com);
				invoice_date.setText(set_com.getString("INVOICE_DATE"));
				productField.getSelectedItem().toString();
				receiving_date.setText(set_com.getString("RECEIVING_DATE"));
				rateField.setText(set_com.getString("RATE"));
				gstCombo.getSelectedItem().toString();
				qtyField.setText(set_com.getString("QTY"));
				igstField.setText(set_com.getString("IGST"));
				vendor.setText(set_com.getString("VENDOR"));
				po_number.setText(set_com.getString("PO_NUMBER"));
				po_date.setText(set_com.getString("PO_DATE"));
				docs_checked.getSelectedItem().toString();
				payment_period.getSelectedItem().toString();
				nop.getSelectedItem().toString();
				work_Des.setText(set_com.getString("WORKDES"));
				submitted.getSelectedItem().toString();
				
				
			}
		}catch (Exception e) {
			
//			e.printStackTrace();
			
			
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				
			}
		}
		
	}
	
	public void footerFieldData() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful before footer details calculated");
//			JOptionPane.showMessageDialog(null, "invoice details added");
			
			int billNo = Integer.parseInt(invoice_num.getSelectedItem().toString());
			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total from invoice_table where GST=0 and invoice_no ="+billNo);
			if(set_com.next()) {
				System.out.println(set_com);
				amt0.setText(set_com.getString("approved"));
				tax0.setText(set_com.getString("tax"));
				if(set_com.getString("tax") == null) {
					amt0.setText("0.0");
					tax0.setText("0.0");
				}
			}
			
			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total from invoice_table where GST=5 and invoice_no ="+billNo);
			if(set_com.next()) {
				amt5.setText(set_com.getString("approved"));
				tax5.setText(set_com.getString("tax"));
				if(set_com.getString("tax") == null) {
					amt5.setText("0.0");
					tax5.setText("0.0");
				}
			}

			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total from invoice_table where GST=12 and invoice_no ="+billNo);
			if(set_com.next()) {
				amt12.setText(set_com.getString("approved"));
				tax12.setText(set_com.getString("tax"));
				if(set_com.getString("tax") == null) {
					amt12.setText("0.0");
					tax12.setText("0.0");
				}
			}

			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total from invoice_table where GST=18 and invoice_no ="+billNo);
			if(set_com.next()) {
				amt18.setText(set_com.getString("approved"));
				tax18.setText(set_com.getString("tax"));
				if(set_com.getString("tax") == null) {
					amt18.setText("0.0");
					tax18.setText("0.0");
				}
			}
			
			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total from invoice_table where GST=28 and invoice_no ="+billNo);
			if(set_com.next()) {
				amt28.setText(set_com.getString("total"));
				tax28.setText(set_com.getString("tax"));
				if(set_com.getString("tax") == null) {
					amt28
					.setText("0.0");
					tax28.setText("0.0");
				}
			}
			
			commonMethodForSt("select sum(qty*rate*gst/100) as tax, sum(rate*qty) as total,igst from invoice_table where invoice_no ="+billNo);
			if(set_com.next()) {
				subTotal.setText(set_com.getString("total"));
				gstTotal.setText(set_com.getString("tax"));
				taxTotal.setText(set_com.getString("tax"));
				amount_claimed.setText(""+(Float.parseFloat(set_com.getString("tax"))+Float.parseFloat(set_com.getString("total"))));
				if(Integer.parseInt(set_com.getString("igst")) == 1) {
					float tax = Float.parseFloat(set_com.getString("tax"));
					cgstField.setText("0.0");
					sgstField.setText("0.0");
					igstField.setText(""+tax);
				}
				else {
					float tax = Float.parseFloat(set_com.getString("tax"));
					cgstField.setText(""+(tax/2));
					sgstField.setText(""+(tax/2));
					igstField.setText("0.0");
				}
			}
		
			
		}catch (Exception e) {

//			e.printStackTrace();
			
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				
			}
		}
		
	}
	
	
	
	public Invoices() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 30, 900, 980);
		getContentPane().setLayout(null);
		
		JLayeredPane menuLayeredPane = new JLayeredPane();
		menuLayeredPane.setBounds(10, 11, 884, 38);
		getContentPane().add(menuLayeredPane);
		
		JLabel lblBillingSoftware = new JLabel("BHARAT HEAVY ELECTRICALS LIMITED");
		lblBillingSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillingSoftware.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBillingSoftware.setBounds(10, 0, 844, 38);
		menuLayeredPane.add(lblBillingSoftware);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(e -> this.dispose());
		btnNewButton.setBounds(765, 7, 89, 23);
		menuLayeredPane.add(btnNewButton);
		
		JLayeredPane workspaceLayeredPane = new JLayeredPane();
		workspaceLayeredPane.setBounds(10, 53, 864, 617);
		getContentPane().add(workspaceLayeredPane);
		workspaceLayeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		workspaceLayeredPane.add(panel, "name_195379362278888");
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(10, 299, 844, 211);
		panel.add(scrollPane);
		scrollPane.setViewportView(table_sale);
		table_sale.setModel(model_sale);
		
		Object column_names[] = {"Sr No.", "Product Description", "PO Number","PO Date","Vendor","Nature","Period", "Quantity", "Rate", "Amount", "GST RATE", "CGST", "SGST", "IGST", "TDS","DOCS CHECK" }; 
		
		model_sale.setColumnIdentifiers(column_names);
		
		
		JLayeredPane invoiceItemlayeredPane = new JLayeredPane();
		invoiceItemlayeredPane.setBounds(10, 11, 864, 275);
		panel.add(invoiceItemlayeredPane);
		invoiceItemlayeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel itemFieldPanel = new JPanel();
		invoiceItemlayeredPane.add(itemFieldPanel, "name_174238840517892");
		itemFieldPanel.setLayout(null);
		
		JLabel lblCDT = new JLabel("CORPORATE DIGITAL TRANSFORMATIONS");
		lblCDT.setHorizontalAlignment(SwingConstants.CENTER);
		lblCDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCDT.setBounds(10, 20, 844, 18);
		itemFieldPanel.add(lblCDT);
		
		JLabel labelInvoiceDate = new JLabel("Invoice Date :");
		labelInvoiceDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelInvoiceDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInvoiceDate.setBounds(598, 44, 99, 25);
		itemFieldPanel.add(labelInvoiceDate);
		
		JLabel labelInvoiceNo = new JLabel("Invoice No. :");
		labelInvoiceNo.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInvoiceNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelInvoiceNo.setBounds(598, 11, 99, 25);
		itemFieldPanel.add(labelInvoiceNo);
		
		/*invoice_no = new JTextField();
		invoice_no.setFont(new Font("Tahoma", Font.PLAIN, 15));
		invoice_no.setBounds(707, 11, 127, 25);
		itemFieldPanel.add(invoice_no);
		invoice_no.setColumns(10);*/
		
		invoice_num = new JComboBox();
		invoice_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		invoice_num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceDetail();
			}
		});
		invoice_num.setEditable(true);
		invoice_num.setBounds(707, 11, 99, 25);
		itemFieldPanel.add(invoice_num);
		
		invoice_date = new JTextField();
		invoice_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		invoice_date.setColumns(10);
		invoice_date.setBounds(707, 46, 127, 25);
		itemFieldPanel.add(invoice_date);
		
		productField = new JComboBox();
		productField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		productField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productDetails();
			}
		});
		productField.setEditable(true);
		productField.setBounds(10, 189, 270, 25);
		itemFieldPanel.add(productField);
		
		rateField = new JTextField();
		rateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rateField.setHorizontalAlignment(SwingConstants.TRAILING);
		rateField.setText("0");
		rateField.setBounds(399, 189, 99, 25);
		itemFieldPanel.add(rateField);
		rateField.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProductName.setBounds(10, 159, 146, 25);
		itemFieldPanel.add(lblProductName);
		
		
		JLabel lblGst = new JLabel("GST %");
		lblGst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGst.setBounds(617, 159, 53, 25);
		itemFieldPanel.add(lblGst);
		
		gstCombo = new JComboBox();
		gstCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gstCombo.setEditable(true);
		gstCombo.setBounds(617, 189, 53, 25);
		itemFieldPanel.add(gstCombo);
		gstCombo.addItem("0");
		gstCombo.addItem("5");
		gstCombo.addItem("12");
		gstCombo.addItem("18");
		gstCombo.addItem("28");
		
		
		payment_period = new JComboBox();
		payment_period.setFont(new Font("Tahoma", Font.PLAIN, 15));
		payment_period.setEditable(true);
		payment_period.setBounds(10, 245, 109, 25);
		itemFieldPanel.add(payment_period);
		payment_period.addItem("monthly");
		payment_period.addItem("one-time");
		payment_period.addItem("Jab-Mar");
		payment_period.addItem("Apr-Jun");
		payment_period.addItem("Jul-Sep");
		payment_period.addItem("Oct-Dec");
		
		JLabel lblPay = new JLabel("Payment Period");
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPay.setBounds(10, 219, 109, 25);
		itemFieldPanel.add(lblPay);
		
		
		nop= new JComboBox();
		nop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nop.setEditable(true);
		nop.setBounds(125, 245, 109, 25);
		itemFieldPanel.add(nop);
		nop.addItem("quaterly");
		nop.addItem("annually");
		
		JLabel lblNop = new JLabel("Nature :");
		lblNop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNop.setBounds(125, 219, 109, 25);
		itemFieldPanel.add(lblNop);
		
		docs_checked=new JComboBox();
		docs_checked.setFont(new Font("Tahoma", Font.PLAIN, 15));
		docs_checked.setEditable(true);
		docs_checked.setBounds(240, 245, 109, 25);
		itemFieldPanel.add(docs_checked);
		docs_checked.addItem("yes");
		docs_checked.addItem("no");
		
		JLabel lblDocs = new JLabel("Documents Checked");
		lblDocs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDocs.setBounds(240, 219, 109, 25);
		itemFieldPanel.add(lblDocs);
		
		
		fin_tds=new JComboBox();
		fin_tds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fin_tds.setEditable(true);
		fin_tds.setBounds(355, 245, 109, 25);
		itemFieldPanel.add(fin_tds);
		fin_tds.addItem("LD deducted");
		fin_tds.addItem("LD not deducted");
		
		
		JLabel lblLd = new JLabel("LD :");
		lblLd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLd.setBounds(355, 219, 109, 25);
		itemFieldPanel.add(lblLd);
		
		JLabel lblsub = new JLabel("Submitted To :");
		lblsub.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblsub.setBounds(474, 219, 109, 25);
		itemFieldPanel.add(lblsub);
		
		submitted=new JComboBox();
		submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submitted.setEditable(true);
		submitted.setBounds(474, 245, 109, 25);
		itemFieldPanel.add(submitted);
		submitted.addItem("FINANCE");
		submitted.addItem("CDT");
		
		JLabel statusLabel=new JLabel();
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(593, 219, 109, 25);
		itemFieldPanel.add(statusLabel);
		JFileChooser  fileDialog = new JFileChooser();
	      JButton showFileDialogButton = new JButton("Open File");
	      
	      showFileDialogButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            int returnVal = fileDialog.showOpenDialog(itemFieldPanel);
	            
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               statusLabel.setText("File Selected :" + file.getName());
	            } else {
	               statusLabel.setText("Open command cancelled by user." );           
	            }      
	         }
	      });
	      showFileDialogButton.setBounds(593, 245, 129, 25);
		  itemFieldPanel.add(showFileDialogButton);
		
		
		qtyField = new JTextField();
		qtyField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		qtyField.setText("0");
		qtyField.setHorizontalAlignment(SwingConstants.TRAILING);
		qtyField.setColumns(10);
		qtyField.setBounds(508, 189, 99, 25);
		itemFieldPanel.add(qtyField);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(508, 159, 99, 25);
		itemFieldPanel.add(lblQuantity);
		
		vendor = new JTextField();
		vendor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		vendor.setHorizontalAlignment(SwingConstants.TRAILING);
		vendor.setColumns(10);
		vendor.setBounds(290, 189, 99, 25);
		itemFieldPanel.add(vendor);
		
		JLabel label = new JLabel("Vendor :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(290, 159, 109, 25);
		itemFieldPanel.add(label);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate.setBounds(419, 159, 109, 25);
		itemFieldPanel.add(lblRate);
		
		/*JLabel lblMs = new JLabel("To :");
		lblMs.setVerticalAlignment(SwingConstants.TOP);
		lblMs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMs.setBounds(30, 13, 54, 23);
		itemFieldPanel.add(lblMs);*/
		
		/*party_name = new JComboBox();
		party_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		party_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partyDetails();
			}
		});
		party_name.setEditable(true);
		party_name.setBounds(94, 13, 270, 25);
		itemFieldPanel.add(party_name);*/
		
		JLabel lblNumber = new JLabel("PO Number :");
		lblNumber.setVerticalAlignment(SwingConstants.TOP);
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumber.setBounds(7, 46, 90, 25);
		itemFieldPanel.add(lblNumber);
		
		po_number = new JTextField();
		po_number.setFont(new Font("Tahoma", Font.PLAIN, 15));
		po_number.setHorizontalAlignment(SwingConstants.TRAILING);
		po_number.setColumns(10);
		po_number.setBounds(126, 46, 109, 25);
		itemFieldPanel.add(po_number);
		
		JLabel lblDate=new JLabel("PO Date :");
		lblDate.setVerticalAlignment(SwingConstants.TOP);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(7, 81, 80, 25);
		itemFieldPanel.add(lblDate);
		
		po_date = new JTextField();
		po_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		po_date.setHorizontalAlignment(SwingConstants.TRAILING);
		po_date.setColumns(10);
		po_date.setBounds(126, 81, 109, 25);
		itemFieldPanel.add(po_date);
		
		
		/*JLabel lblState = new JLabel("State :");
		lblState.setVerticalAlignment(SwingConstants.TOP);
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(10, 115, 74, 25);
		itemFieldPanel.add(lblState);*/
		
		JLabel lblRec = new JLabel("Receiving Date:");
		lblRec.setVerticalAlignment(SwingConstants.TOP);
		lblRec.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRec.setBounds(7, 116, 109, 25);
		itemFieldPanel.add(lblRec);
		
		receiving_date = new JTextField();
		receiving_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		receiving_date.setHorizontalAlignment(SwingConstants.TRAILING);
		receiving_date.setColumns(10);
		receiving_date.setBounds(126, 116, 109, 25);
		itemFieldPanel.add(receiving_date);
		
		
		JLabel lblPartyGst = new JLabel("Party GST No. :");
		lblPartyGst.setVerticalAlignment(SwingConstants.TOP);
		lblPartyGst.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPartyGst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPartyGst.setBounds(463, 115, 116, 25);
		itemFieldPanel.add(lblPartyGst);
		
		party_gst = new JTextField();
		party_gst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		party_gst.setColumns(10);
		party_gst.setBounds(589, 115, 245, 25);
		itemFieldPanel.add(party_gst);
		
		
		JLabel lblwork = new JLabel("Work Des :");
		lblwork.setVerticalAlignment(SwingConstants.TOP);
		lblwork.setHorizontalAlignment(SwingConstants.RIGHT);
		lblwork.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblwork.setBounds(463, 81, 116, 25);
		itemFieldPanel.add(lblwork);
		
		work_Des= new JTextField();
		work_Des.setFont(new Font("Tahoma", Font.PLAIN, 15));
		work_Des.setColumns(10);
		work_Des.setBounds(589, 81, 245, 23);
		itemFieldPanel.add(work_Des);
		/*JLabel lblTransport = new JLabel("Transport :");
		lblTransport.setVerticalAlignment(SwingConstants.TOP);
		lblTransport.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTransport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTransport.setBounds(463, 81, 116, 25);
		itemFieldPanel.add(lblTransport);
		
		transportCombo = new JComboBox();
		transportCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		transportCombo.setEditable(true);
		transportCombo.setBounds(589, 81, 245, 23);
		itemFieldPanel.add(transportCombo);
		
		party_address = new JTextArea();
		party_address.setBorder(UIManager.getBorder("TextField.border"));
		party_address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		party_address.setTabSize(4);
		party_address.setLineWrap(true);
		party_address.setBounds(94, 44, 270, 62);
		itemFieldPanel.add(party_address);*/
		
		JCheckBox igst_check = new JCheckBox("IGST");
		igst_check.setFont(new Font("Tahoma", Font.PLAIN, 14));
		igst_check.setBounds(684, 188, 55, 23);
		itemFieldPanel.add(igst_check);
		
		JButton addProduct = new JButton("ADD");
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					Class.forName("org.h2.Driver");
					connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
					System.out.print("Connection Successful before adding to invoice table");
//					JOptionPane.showMessageDialog(null, "Connection Successful");
					
					String addData = "insert into INVOICE_TABLE values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(addData);
					ps.setString(1,invoice_num.getSelectedItem().toString());
					ps.setString(2,invoice_date.getText());
					ps.setString(3,productField.getSelectedItem().toString());
					ps.setString(4,receiving_date.getText());
					ps.setString(5,rateField.getText() );
					ps.setString(6,gstCombo.getSelectedItem().toString());
					ps.setString(7,qtyField.getText());
					if(igst_check.isSelected()) {
						ps.setString(8,"1");
					}
					else {
						ps.setString(8,"0");
					}
					//ps.setString(9, party_name.getSelectedItem().toString());
					ps.setString(9, vendor.getText());
					ps.setString(10, po_number.getText());
					ps.setString(11, po_date.getText());
					ps.setString(12, docs_checked.getSelectedItem().toString());
					ps.setString(13, payment_period.getSelectedItem().toString());
					ps.setString(14, nop.getSelectedItem().toString());
					ps.setString(15, work_Des.getText());
					ps.setString(16, submitted.getSelectedItem().toString());
					ps.setString(17, "null");
					ps.setString(18, "null");
					
					int inserted = ps.executeUpdate();
					
					if(inserted>0) {
//						JOptionPane.showMessageDialog(null, "data added successful");
						System.out.print("data added successfully to invoice table");
						
						Object row[] = new Object[40]; 
						
						row[0] = srno;
						row[1] = productField.getSelectedItem().toString();
						row[2] = po_number.getText();
						row[3] = po_date.getText();
						row[4] = vendor.getText();
						row[5] = nop.getSelectedItem().toString();
						row[6] = payment_period.getSelectedItem().toString();
						row[7] = qtyField.getText();
						row[8] = rateField.getText();
						float amt = Integer.parseInt(qtyField.getText())*Float.parseFloat(rateField.getText());
						row[9] = ""+amt;
						row[10] = gstCombo.getSelectedItem().toString();
						float tax = amt*Integer.parseInt(gstCombo.getSelectedItem().toString())/100;
						if(igst_check.isSelected()) {
							row[11] = "0.0";
							row[12] = "0.0";
							row[13] = ""+tax;
						}
						else {
							row[11] = ""+(tax/2);
							row[12] = ""+(tax/2);
							row[13] = "0.0";
						}
						//row[14] = ""+(amt+tax);
						row[14] = fin_tds.getSelectedItem().toString();
						row[15] = docs_checked.getSelectedItem().toString();
						row[16] = submitted.getSelectedItem().toString();
						
						model_sale.addRow(row);
							
						footerFieldData();
						srno++;
						
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}	
				finally {
					try {
						connection.close();
					} catch (Exception e2) {
						
					}
				}
				productField.setSelectedItem("");
				rateField.setText("0");
				gstCombo.setSelectedIndex(0);
				qtyField.setText("0");
//				JOptionPane.showMessageDialog(null, "Cleared");
				System.out.print("product details cleared");
			}
		});
		addProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addProduct.setBounds(745, 188, 89, 27);
		itemFieldPanel.add(addProduct);
					
		JLabel label_1 = new JLabel("0 % :");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(19, 535, 46, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("18 % :");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(19, 595, 46, 18);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("28 % :");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(19, 615, 46, 18);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("12 % :");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(19, 575, 46, 18);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("5 % :");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_5.setBounds(19, 555, 46, 18);
		panel.add(label_5);
		
		amt28 = new JTextField();
		amt28.setText("0.0");
		amt28.setHorizontalAlignment(SwingConstants.TRAILING);
		amt28.setEditable(false);
		amt28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amt28.setColumns(10);
		amt28.setBounds(75, 615, 86, 18);
		panel.add(amt28);
		
		amt18 = new JTextField();
		amt18.setText("0.0");
		amt18.setHorizontalAlignment(SwingConstants.TRAILING);
		amt18.setEditable(false);
		amt18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amt18.setColumns(10);
		amt18.setBounds(75, 595, 86, 18);
		panel.add(amt18);
		
		amt12 = new JTextField();
		amt12.setHorizontalAlignment(SwingConstants.RIGHT);
		amt12.setText("0.0");
		amt12.setEditable(false);
		amt12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amt12.setColumns(10);
		amt12.setBounds(75, 575, 86, 18);
		panel.add(amt12);
		
		tax12 = new JTextField();
		tax12.setText("0.0");
		tax12.setHorizontalAlignment(SwingConstants.TRAILING);
		tax12.setEditable(false);
		tax12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tax12.setColumns(10);
		tax12.setBounds(255, 575, 86, 18);
		panel.add(tax12);
		
		tax5 = new JTextField();
		tax5.setText("0.0");
		tax5.setHorizontalAlignment(SwingConstants.TRAILING);
		tax5.setEditable(false);
		tax5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tax5.setColumns(10);
		tax5.setBounds(255, 555, 86, 18);
		panel.add(tax5);
		
		tax18 = new JTextField();
		tax18.setText("0.0");
		tax18.setHorizontalAlignment(SwingConstants.TRAILING);
		tax18.setEditable(false);
		tax18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tax18.setColumns(10);
		tax18.setBounds(255, 595, 86, 18);
		panel.add(tax18);
		
		tax28 = new JTextField();
		tax28.setText("0.0");
		tax28.setHorizontalAlignment(SwingConstants.TRAILING);
		tax28.setEditable(false);
		tax28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tax28.setColumns(10);
		tax28.setBounds(255, 615, 86, 18);
		panel.add(tax28);
		
		tax0 = new JTextField();
		tax0.setText("0.0");
		tax0.setHorizontalAlignment(SwingConstants.TRAILING);
		tax0.setEditable(false);
		tax0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tax0.setColumns(10);
		tax0.setBounds(255, 535, 86, 18);
		panel.add(tax0);
		
		JLabel lblAmounts = new JLabel("Amounts");
		lblAmounts.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAmounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmounts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmounts.setBounds(75, 516, 86, 24);
		panel.add(lblAmounts);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTax.setHorizontalAlignment(SwingConstants.CENTER);
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTax.setBounds(255, 511, 86, 24);
		panel.add(lblTax);
		
		JLabel lblCgst = new JLabel("CGST :");
		lblCgst.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCgst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCgst.setBounds(421, 525, 46, 18);
		panel.add(lblCgst);
		
		JLabel lblSgst = new JLabel("SGST :");
		lblSgst.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSgst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSgst.setBounds(421, 544, 46, 18);
		panel.add(lblSgst);
		
		JLabel lblIgst = new JLabel("IGST :");
		lblIgst.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIgst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIgst.setBounds(421, 563, 46, 18);
		panel.add(lblIgst);
		
		JLabel lblGstTotal = new JLabel("GST Total :");
		lblGstTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGstTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGstTotal.setBounds(393, 582, 74, 18);
		panel.add(lblGstTotal);
		
		gstTotal = new JTextField();
		gstTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		gstTotal.setText("0.0");
		gstTotal.setEditable(false);
		gstTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gstTotal.setColumns(10);
		gstTotal.setBounds(477, 582, 86, 18);
		panel.add(gstTotal);
		
		igstField = new JTextField();
		igstField.setHorizontalAlignment(SwingConstants.TRAILING);
		igstField.setText("0.0");
		igstField.setEditable(false);
		igstField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		igstField.setColumns(10);
		igstField.setBounds(477, 563, 86, 18);
		panel.add(igstField);
		
		sgstField = new JTextField();
		sgstField.setHorizontalAlignment(SwingConstants.TRAILING);
		sgstField.setText("0.0");
		sgstField.setEditable(false);
		sgstField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sgstField.setColumns(10);
		sgstField.setBounds(477, 544, 86, 18);
		panel.add(sgstField);
		
		cgstField = new JTextField();
		cgstField.setHorizontalAlignment(SwingConstants.TRAILING);
		cgstField.setText("0.0");
		cgstField.setEditable(false);
		cgstField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cgstField.setColumns(10);
		cgstField.setBounds(477, 525, 86, 18);
		panel.add(cgstField);
		
		JLabel lblSubTotal = new JLabel("Sub Total :");
		lblSubTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubTotal.setBounds(604, 525, 64, 18);
		panel.add(lblSubTotal);
		
		subTotal = new JTextField();
		subTotal.setText("0.0");
		subTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		subTotal.setEditable(false);
		subTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subTotal.setColumns(10);
		subTotal.setBounds(687, 525, 86, 18);
		panel.add(subTotal);
		
		taxTotal = new JTextField();
		taxTotal.setText("0.0");
		taxTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		taxTotal.setEditable(false);
		taxTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taxTotal.setColumns(10);
		taxTotal.setBounds(687, 544, 86, 18);
		panel.add(taxTotal);
		
		JLabel lblGstTotal_1 = new JLabel("GST Total :");
		lblGstTotal_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGstTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGstTotal_1.setBounds(594, 544, 74, 18);
		panel.add(lblGstTotal_1);
		
		JLabel lblTotalAmount = new JLabel("Approved :");
		lblTotalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalAmount.setBounds(582, 563, 86, 18);
		panel.add(lblTotalAmount);
		
		amount_claimed = new JTextField();
		amount_claimed.setText("0.0");
		amount_claimed.setHorizontalAlignment(SwingConstants.TRAILING);
		amount_claimed.setEditable(false);
		amount_claimed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amount_claimed.setColumns(10);
		amount_claimed.setBounds(687, 563, 86, 18);
		panel.add(amount_claimed);
						
		amt0 = new JTextField();
		amt0.setEditable(false);
		amt0.setText("0.0");
		amt0.setHorizontalAlignment(SwingConstants.TRAILING);
		amt0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amt0.setColumns(10);
		amt0.setBounds(75, 535, 86, 18);
		panel.add(amt0);
		
		amt5 = new JTextField();
		amt5.setHorizontalAlignment(SwingConstants.TRAILING);
		amt5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amt5.setText("0.0");
		amt5.setEditable(false);
		amt5.setBounds(75, 555, 86, 18);
		panel.add(amt5);
		amt5.setColumns(10);
		
		JLabel label_6 = new JLabel("0 % :");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_6.setBounds(199, 535, 46, 18);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("5 % :");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_7.setBounds(199, 555, 46, 18);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("12 % :");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_8.setBounds(199, 575, 46, 18);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("18 % :");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_9.setBounds(199, 595, 46, 18);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("28 % :");
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_10.setBounds(199, 615, 46, 18);
		panel.add(label_10);
		
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					try {
						Class.forName("org.h2.Driver");
						connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
						System.out.print("Connection Successful for jasper");
					}catch (Exception e2) {
					
					}
					JasperDesign jd;
					if(igst_check.isSelected()) {
						jd = JRXmlLoader.load(new File("").getAbsoluteFile()+"/src/Bill1.jrxml");
					}
					else
					{
						jd = JRXmlLoader.load(new File("").getAbsoluteFile()+"/src/Bill2.jrxml");
					}
					HashMap param = new HashMap();
					//param.put("party_name",party_name.getSelectedItem().toString());
					//param.put("party_address",party_address.getText());
					//param.put("party_state",party_state.getText());
					param.put("invoice_no", invoice_num.getSelectedItem().toString());
					param.put("invoice_date", invoice_date.getText());
					param.put("party_gst", party_gst.getText());
					//param.put("transport", transportCombo.getSelectedItem().toString());
					param.put("igst", igstField.getText());
					param.put("cgst", cgstField.getText());
					param.put("sgst", sgstField.getText());
					param.put("igst", igstField.getText());
					param.put("Receiving Date",receiving_date.getText());
					param.put("PO Number",po_number.getText() );
					param.put("PO Date", po_date.getText());
					param.put("Vendor", vendor.getText());
					param.put("Work des", work_Des.getText());
					param.put("Docs checked", docs_checked.getSelectedItem().toString());
					param.put("Payment period", payment_period.getSelectedItem().toString());
					param.put("Nature Of Payment", nop.getSelectedItem().toString());
					param.put("Submitted to",submitted.getSelectedItem().toString());
					param.put("subTotal", subTotal.getText());
					param.put("totalAmount1",amount_claimed.getText());
					int amt = Math.round(Float.parseFloat(amount_claimed.getText()));
					param.put("totalAmount2",""+amt);
					BigDecimal numberVariable =  new BigDecimal(""+amt);
					String numberInWords = NumberToWords.convertNumberToWords(numberVariable); 
					param.put("inWords",numberInWords+" Only");
					
					
					JasperReport jr = JasperCompileManager.compileReport(jd);
					JasperPrint jp = JasperFillManager.fillReport(jr,param,connection);
					JasperViewer jv = new JasperViewer(jp,false);
					jv.setVisible(true);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(745, 245, 86, 19);
		itemFieldPanel.add(btnPrint);
		
		//transportDropDown();
		productDropDown();
		//partyDropDown();
		invoiceNODetail();
		srno = 1;
		
	}
}
