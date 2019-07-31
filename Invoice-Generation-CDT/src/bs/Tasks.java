package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Tasks extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tasks frame = new Tasks();
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
	public Tasks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 30, 900, 680);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ToolBar.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBillingSoftware = new JLabel("BHARAT HEAVY ELECTRICALS LIMITED");
		lblBillingSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillingSoftware.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBillingSoftware.setBounds(10, 0, 844, 38);
		contentPane.add(lblBillingSoftware);
		
		JLabel label = new JLabel("CORPORATE DIGIRAL TRANSFORMATIONS");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setBounds(0, 68, 864, 42);
		contentPane.add(label);
		
		JButton add_product = new JButton("Add Products");
		add_product.setBounds(189, 193, 160, 71);
		contentPane.add(add_product);
		add_product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddProduct().setVisible(true);
			}
		});
		add_product.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JButton add_party = new JButton("Add Party");
		add_party.setBounds(529, 191, 160, 71);
		contentPane.add(add_party);
		add_party.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddParty().setVisible(true);
			}
		});
		add_party.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton generate_invoice = new JButton("Generate Invoice");
		generate_invoice.setBounds(358, 289, 160, 73);
		contentPane.add(generate_invoice);
		generate_invoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Invoices().setVisible(true);
			}
		});
		generate_invoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton generate_report = new JButton("Add Transport");
		generate_report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddTransport().setVisible(true);
			}
		});
		generate_report.setBounds(189, 385, 160, 71);
		contentPane.add(generate_report);
		generate_report.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton add_Docs = new JButton("Add Documents");
		add_Docs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddDocs().setVisible(true);
			}
		});
		add_Docs.setBounds(529, 385, 160, 71);
		contentPane.add(add_Docs);
		add_Docs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JButton amtGen = new JButton("Amount Generation");
		amtGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AmountGeneration().setVisible(true);
			}
		});
		amtGen.setBounds(358, 479, 160, 71);
		contentPane.add(amtGen);
		amtGen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
	}
}
