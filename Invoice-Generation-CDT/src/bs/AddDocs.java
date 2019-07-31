package bs;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddDocs extends JFrame {

	private JPanel contentPane;
	private JComboBox invoice_num;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JRadioButton radio5;
	private JRadioButton radio6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDocs frame = new AddDocs();
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
	
	public void invoiceDetail()
	{
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			System.out.print("Connection Successful before invoice no extraction");
//			JOptionPane.showMessageDialog(null, "invoice details added");
			
			String ptname = invoice_num.getSelectedItem().toString();
			commonMethodForSt("select INVOICE_NO from INVOICE_TABLE");
			while(set_com.next()) {
//				System.out.print(set_com);
				//textField_1.setText(set_com.getString("GST"));
				//textField_3.setText(set_com.getString("APPROVEDAMOUNT"));
				//textField_5.setText(set_com.getString("SUBMITTED"));
				invoice_num.addItem(set_com.getString(1));
				
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
	
	
	public AddDocs() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 30, 900, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBillingSoftware = new JLabel("BHARAT HEAVY ELECTRICALS LIMITED");
		lblBillingSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillingSoftware.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBillingSoftware.setBounds(10, 0, 844, 38);
		contentPane.add(lblBillingSoftware);
		
		JLabel lblAddDocs = new JLabel("Document Checking");
		lblAddDocs.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAddDocs.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDocs.setBounds(0, 57, 884, 70);
		contentPane.add(lblAddDocs);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(e -> this.dispose());
		btnNewButton.setBounds(778, 15, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblInvoice = new JLabel("Invoice No. :");
		lblInvoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoice.setBounds(10, 139, 95, 24);
		contentPane.add(lblInvoice);
		
		invoice_num = new JComboBox();
		invoice_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		invoice_num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceDetail();
			}
		});
		invoice_num.setEditable(true);
		invoice_num.setBounds(115, 139, 100, 25);
		contentPane.add(invoice_num);
		
		JLabel lblIns = new JLabel("Installation Certificate");
		lblIns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIns.setHorizontalAlignment(SwingConstants.CENTER);
		lblIns.setBounds(10, 183, 167, 24);
		contentPane.add(lblIns);
		
		radio1=new JRadioButton();
		radio1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio1.setBounds(187, 183, 55, 24);
		contentPane.add(radio1);
		
		JLabel lblNew = new JLabel("Newness Certificate");
		lblNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblNew.setBounds(252, 183, 167, 24);
		contentPane.add(lblNew);
		
		radio2=new JRadioButton();
		radio2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio2.setBounds(429, 183, 55, 24);
		contentPane.add(radio2);
		
		
		JLabel lblDown = new JLabel("DownTime Report");
		lblDown.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblDown.setBounds(10, 251, 167, 24);
		contentPane.add(lblDown);
		
		radio3=new JRadioButton();
		radio3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio3.setBounds(187, 251, 55, 24);
		contentPane.add(radio3);
		
		JLabel lblSLA = new JLabel("SLA Report");
		lblSLA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSLA.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLA.setBounds(252, 251, 167, 24);
		contentPane.add(lblSLA);
		
		radio4=new JRadioButton();
		radio4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio4.setBounds(429, 251, 55, 24);
		contentPane.add(radio4);
		
		JLabel lblInsure = new JLabel("Insurance Copy");
		lblInsure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInsure.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsure.setBounds(10, 319, 167, 24);
		contentPane.add(lblInsure);
		
		radio5=new JRadioButton();
		radio5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio5.setBounds(187, 319, 55, 24);
		contentPane.add(radio5);
		
		JLabel lblInvoicec = new JLabel("Invoice Copy");
		lblInvoicec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInvoicec.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoicec.setBounds(252, 319, 167, 24);
		contentPane.add(lblInvoicec);
		
		radio6=new JRadioButton();
		radio6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radio6.setBounds(429, 319, 55, 24);
		contentPane.add(radio6);
		
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int c = 0;
				int flag=0;
				try {
					Class.forName("org.h2.Driver");
					connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
					System.out.print("Connection Successful for document checking ");
					String addData = "insert into DOCS values(?,?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(addData);
					
					int inserted;
//					if(inserted>0) {
//						System.out.println("Done");
//					}
					ps.setString(1, invoice_num.getSelectedItem().toString());
					if(radio1.isSelected())
					{
						ps.setString(2, "yes");
					}
					else
					{
						ps.setString(2, "no");
						flag++;
						try {
							JOptionPane.showMessageDialog(null, "Documents are not complete!!");
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
					}
					
					if(radio2.isSelected())
					{
						ps.setString(3, "yes");
					}
					else
					{
						ps.setString(3, "no");
						flag++;
						try {
							JOptionPane.showMessageDialog(null, "Documents are not complete!!");
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
					}
					
					if(radio3.isSelected())
					{
						ps.setString(4, "yes");
					}
					else
					{
						ps.setString(4, "no");
						flag++;
						try {
							JOptionPane.showMessageDialog(null, "Documents are not complete!!");
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
					}
					
					if(radio5.isSelected())
					{
						ps.setString(5, "yes");
					}
					else
					{
						ps.setString(5, "no");
						flag++;
						try {
							JOptionPane.showMessageDialog(null, "Documents are not complete!!");
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
					}
					
					if(radio6.isSelected())
					{
						ps.setString(6, "yes");
					}
					else
					{
						ps.setString(6, "no");
						flag++;
						try {
							JOptionPane.showMessageDialog(null, "Documents are not complete!!");
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
					}
					c++;
					inserted = ps.executeUpdate();
					
					if(flag>0)
					{
						String updateData = "update INVOICE_TABLE set DOCS_CHECKED='no' where INVOICE_NO=?";
						PreparedStatement ps1 = connection.prepareStatement(updateData);
						
						int inserted1;
						ps1.setString(1, invoice_num.getSelectedItem().toString());
						
						inserted1=ps1.executeUpdate();
					}
				}catch (Exception e3) {
					e3.printStackTrace();
				}finally {
					try {
						JOptionPane.showMessageDialog(null, c+" Rows Added");
						connection.close();
					} catch (Exception e4) {
						// TODO: handle exception
					}
				}
				
				
//				JOptionPane.showMessageDialog(null, "Connection Successful");
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(365, 540, 126, 35);
		contentPane.add(btnAdd);
	}

}