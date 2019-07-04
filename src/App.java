import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {
	private Color BG_COLOR = new Color(39, 46, 56);
	
	private JFrame frmVending;
	private JLabel lbImgSnacks;
	private JLabel lbImgBeverages;
	private JPanel snackPanel;
	private JPanel beveragePanel;
	private JPanel btnSnacksPanel;
	private JButton btnSnacks;
	private JButton btnBeverage;
	private JPanel btnBeveragePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmVending.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		// The base Frame
		frmVending = new JFrame();
		frmVending.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmVending.getContentPane().setFocusTraversalPolicyProvider(true);
		frmVending.getContentPane().setFocusCycleRoot(true);
		frmVending.getContentPane().setBackground(new Color(39, 46, 56));
		frmVending.setTitle("Vending");
		frmVending.setBounds(100, 100, 600, 600);
		frmVending.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVending.getContentPane().setLayout(null);
		
		// Top label
		JLabel lblPleaseSelect = new JLabel("Please select:");
		lblPleaseSelect.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblPleaseSelect.setForeground(Color.ORANGE);
		lblPleaseSelect.setBounds(32, 63, 251, 48);
		frmVending.getContentPane().add(lblPleaseSelect);
		
		// snack image panel
		snackPanel = new JPanel();
		snackPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		snackPanel.setBackground(BG_COLOR);
		snackPanel.setBounds(55, 143, 212, 227);
		frmVending.getContentPane().add(snackPanel);
		snackPanel.setLayout(null);
		// snack image
		lbImgSnacks = new JLabel("");
		lbImgSnacks.setBounds(6, 13, 200, 207);
		snackPanel.add(lbImgSnacks);
		lbImgSnacks.setIcon(new ImageIcon(new ImageIcon("D:\\CODE\\Vending_Machine_UI\\img\\snacks.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		
		
		// beverage image panel
		beveragePanel = new JPanel();
		beveragePanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		beveragePanel.setBackground(BG_COLOR);
		beveragePanel.setBounds(320, 143, 212, 227);
		frmVending.getContentPane().add(beveragePanel);
		beveragePanel.setLayout(null);
		// beverage image
		lbImgBeverages = new JLabel("");
		lbImgBeverages.setBounds(6, 13, 200, 207);
		beveragePanel.add(lbImgBeverages);
		lbImgBeverages.setIcon(new ImageIcon(new ImageIcon("D:\\CODE\\Vending_Machine_UI\\img\\beverages.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		
		// snack btn panel
		btnSnacksPanel = new JPanel();
		btnSnacksPanel.setBackground(BG_COLOR);
		btnSnacksPanel.setBorder(new LineBorder(Color.ORANGE, 4, true));
		btnSnacksPanel.setBounds(72, 400, 175, 65);
		frmVending.getContentPane().add(btnSnacksPanel);
		btnSnacksPanel.setLayout(null);
		// snack btn
		btnSnacks = new JButton("SNACKS");
		btnSnacks.setBounds(5, 5, 165, 55);
		btnSnacksPanel.add(btnSnacks);
		btnSnacks.setFocusPainted(false);
		btnSnacks.setOpaque(false);
		btnSnacks.setBorderPainted(false); 
		lbImgSnacks.setLabelFor(btnSnacks);
		btnSnacks.setBackground(BG_COLOR);
		btnSnacks.setContentAreaFilled(false);
		btnSnacks.setBorderPainted(false);
		btnSnacks.setForeground(Color.ORANGE);
		btnSnacks.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// onClick snack btn
		btnSnacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		// snack btn actions
		btnSnacks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSnacks.setBackground(Color.ORANGE);
				btnSnacks.setContentAreaFilled(true);
				btnSnacks.setOpaque(true);
				btnSnacks.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSnacks.setBackground(BG_COLOR);
				btnSnacks.setContentAreaFilled(false);
				btnSnacks.setForeground(Color.ORANGE);
				btnSnacks.setOpaque(false);
			}
		});
		
		// beverage btn panel
		btnBeveragePanel = new JPanel();
		btnBeveragePanel.setBorder(new LineBorder(Color.ORANGE, 4));
		btnBeveragePanel.setBounds(330, 400, 185, 65);
		btnBeveragePanel.setBackground(BG_COLOR);
		frmVending.getContentPane().add(btnBeveragePanel);
		btnBeveragePanel.setLayout(null);
		// beverage btn
		btnBeverage = new JButton("BEVERAGES");
		lbImgBeverages.setLabelFor(btnBeverage);
		btnBeverage.setBounds(5, 5, 175, 55);
		btnBeveragePanel.add(btnBeverage);
		btnBeverage.setOpaque(false);
		btnBeverage.setForeground(Color.ORANGE);
		btnBeverage.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btnBeverage.setFocusPainted(false);
		btnBeverage.setContentAreaFilled(false);
		btnBeverage.setBorderPainted(false);
		btnBeverage.setBackground(new Color(39, 46, 56));
		// onClick beverage btn
		btnBeverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// beverage btn actions
		btnBeverage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBeverage.setBackground(Color.ORANGE);
				btnBeverage.setContentAreaFilled(true);
				btnBeverage.setOpaque(true);
				btnBeverage.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBeverage.setBackground(BG_COLOR);
				btnBeverage.setContentAreaFilled(false);
				btnBeverage.setForeground(Color.ORANGE);
				btnBeverage.setOpaque(false);
			}
		});
		
	}
}