import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSales = new JButton("SALES");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// destroy current gui
				// create a new order 'Order O = new Order();'
				// open up new gui
			}
		});
		btnSales.setBackground(new Color(211, 211, 211));
		btnSales.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
		btnSales.setBounds(135, 46, 166, 46);
		contentPane.add(btnSales);
		
		JButton btnReturns = new JButton("RETURNS");
		btnReturns.setBackground(new Color(211, 211, 211));
		btnReturns.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
		btnReturns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReturns.setBounds(135, 103, 166, 46);
		contentPane.add(btnReturns);
		
		JButton btnInventory = new JButton("INVENTORY");
		btnInventory.setBackground(new Color(211, 211, 211));
		btnInventory.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
		btnInventory.setBounds(135, 160, 166, 46);
		contentPane.add(btnInventory);
	}

}