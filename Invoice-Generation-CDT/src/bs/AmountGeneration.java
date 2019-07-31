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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AmountGeneration extends JFrame {

	private JPanel contentPane;
	private JComboBox invoice_num;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox submitted;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AmountGeneration frame = new AmountGeneration();
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
			commonMethodForSt("select * from INVOICE_TABLE where INVOICE_NO='"+ptname+"'");
			if(set_com.next()) {
//				System.out.print(set_com);
				textField_1.setText(set_com.getString("GST"));
				textField_3.setText(set_com.getString("APPROVEDAMOUNT"));
				//textField_5.setText(set_com.getString("SUBMITTED"));
				
				
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
	
	
	public AmountGeneration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblAddParty = new JLabel("Amount Generation");
		lblAddParty.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddParty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddParty.setBounds(0, 56, 884, 70);
		contentPane.add(lblAddParty);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(e -> this.dispose());
		btnNewButton.setBounds(776, 13, 89, 23);
		contentPane.add(btnNewButton);
		
		
		JLabel lblGstNo = new JLabel("Invoice No");
		lblGstNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGstNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGstNo.setBounds(28, 138, 125, 24);
		contentPane.add(lblGstNo);
		
		invoice_num = new JComboBox();
		invoice_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		invoice_num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceDetail();
			}
		});
		invoice_num.setEditable(true);
		invoice_num.setBounds(163, 138, 100, 25);
		contentPane.add(invoice_num);
		
		
		JLabel lblPartyName = new JLabel("GST Applied:");
		lblPartyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartyName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPartyName.setBounds(28, 182, 255, 24);
		contentPane.add(lblPartyName);
		
		textField_1=new JTextField();
		textField_1.setBounds(293, 182, 125, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblAddress = new JLabel("TDS Applied:");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(28, 226, 255, 24);
		contentPane.add(lblAddress);
		
		textField_2=new JTextField();
		textField_2.setBounds(293, 226, 125, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblState = new JLabel("Approved Amount:");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(28, 260, 255, 24);
		contentPane.add(lblState);
		
		textField_3=new JTextField();
		textField_3.setBounds(293, 260, 125, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblContact = new JLabel("Generated Amount:");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContact.setBounds(28, 294, 255, 24);
		contentPane.add(lblContact);
		
		textField_4=new JTextField();
		textField_4.setBounds(293, 294, 125, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblSub = new JLabel("Submitted To:");
		lblSub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSub.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSub.setBounds(28, 328, 255, 24);
		contentPane.add(lblSub);
		
		submitted=new JComboBox();
		submitted.setBounds(293, 328, 125, 24);
		contentPane.add(submitted);
		submitted.addItem("FINANCE");
		submitted.addItem("CDT");
		
		JButton cal=new JButton("CALCULATE");
		cal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int gst=(Integer.parseInt(textField_3.getText())*Integer.parseInt(textField_1.getText()))/100;
					int app=gst+(Integer.parseInt(textField_3.getText()));
					textField_4.setText(String.valueOf(app));
				
				
				}});
		cal.setBounds(429, 294, 125, 35);
		contentPane.add(cal);
		
		JButton button = new JButton("ADD TO DATABASE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int c = 0;
				try {
					Class.forName("org.h2.Driver");
					connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
					System.out.print("Connection Successful for amountGeneration ");
					String addData = "update INVOICE_TABLE set Approved=? where INVOICE_NO=?;";
					PreparedStatement ps = connection.prepareStatement(addData);
					
					int inserted;
//					if(inserted>0) {
//						System.out.println("Done");
//					}
						ps.setString(1, textField_4.getText());
						ps.setString(2, invoice_num.getSelectedItem().toString());
						c++;
						inserted = ps.executeUpdate();
				
				String updateData = "update INVOICE_TABLE set SUBMITTED=? where INVOICE_NO=?;";
				PreparedStatement ps1 = connection.prepareStatement(updateData);
				
				int inserted1;
//				if(inserted>0) {
//					System.out.println("Done");
//				}
					ps1.setString(1, submitted.getSelectedItem().toString());
					ps1.setString(2, invoice_num.getSelectedItem().toString());
					c++;
					inserted1 = ps.executeUpdate();
				}
					
				catch (Exception e2) {
					e2.printStackTrace();
				}finally {
					try {
						JOptionPane.showMessageDialog(null, c+" Rows Updated");
						connection.close();
					} catch (Exception e3) {
						// TODO: handle exception
					}
				}
				
				
//				JOptionPane.showMessageDialog(null, "Connection Successful");
				
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(359, 553, 220, 35);
		contentPane.add(button);
	}

}
