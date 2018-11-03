import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JLabel lblPassword;
	private JLabel lblUsername;

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
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername, "name_989977588628496");
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "name_989977597256729");
		txtUsername.setColumns(10);
		
		JButton btnLogine = new JButton("Login");
		btnLogine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("UserName " + txtUsername.getText() );
				System.out.println("Password " + pwdPassword.getText() );
				
				Employee newE = new Employee(txtUsername.getText());
				Boolean pwdcheck = newE.checkPassword(pwdPassword.getText());
				System.out.println("Password check is " + pwdcheck );
				if(pwdcheck)
				{
					
				}
				
			}
		});
		
		lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "name_989977609670974");
		
		pwdPassword = new JPasswordField();
		contentPane.add(pwdPassword, "name_989977619294232");
		contentPane.add(btnLogine, "name_989977629030487");
	}
	
	

}
