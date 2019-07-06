import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Snack {
	private Color BG_COLOR = new Color(39, 46, 56);
	private JFrame snackFrame;
	
	public JPanel snackPane;
	
	private JPanel doritosPanel;
	private JLabel lbImgDoritos;
	private JButton doritosBtn;
	private JPanel doritosBtnPanel;
	
	private JPanel cheezitPanel;
	private JLabel lbImgCheezit;
	private JButton cheezitBtn;
	private JPanel cheezitBtnPanel;
	
	private JPanel oreoPanel;
	private JLabel lbImgOreo;
	private JButton oreoBtn;
	private JPanel oreoBtnPanel;
	
	private JPanel cheetosPanel;
	private JLabel lbImgCheetos;
	private JButton cheetosBtn;
	private JPanel cheetosBtnPanel;
	
	private JPanel backPanel;
	private JButton backBtn;
	private JPanel panel;
	private JButton button;
	private JPanel panel_1;
	private JButton button_1;
	private JPanel panel_2;
	private JButton button_2;
	private JPanel panel_3;
	private JButton button_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Snack frame = new Snack();
					frame.snackFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Snack() {
		snackFrame = new JFrame();
		snackFrame.setResizable(false);
		snackFrame.setVisible(true);
		snackFrame.setTitle("Vending");
		snackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snackFrame.setBounds(100, 100, 600, 800);
		snackPane = new JPanel();
		snackPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		snackPane.setBackground(BG_COLOR);
		snackFrame.setContentPane(snackPane);
		snackPane.setLayout(null);
		
		// Top label
		JLabel lblPleaseSelect = new JLabel("Please select:");
		lblPleaseSelect.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblPleaseSelect.setForeground(Color.ORANGE);
		lblPleaseSelect.setBounds(32, 30, 251, 48);
		snackPane.add(lblPleaseSelect);
		
		// DORITOS image panel
		doritosPanel = new JPanel();
		doritosPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		doritosPanel.setBackground(BG_COLOR);
		doritosPanel.setBounds(102, 112, 135, 135);
		snackPane.add(doritosPanel);
		doritosPanel.setLayout(null);
		// DORITOS image
		lbImgDoritos = new JLabel("");
		lbImgDoritos.setBounds(5, 5, 125, 125);
		doritosPanel.add(lbImgDoritos);
		lbImgDoritos.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\img\\snacks\\doritos.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
		// DORITOS btn panel
		doritosBtnPanel = new JPanel();
		doritosBtnPanel.setBackground(BG_COLOR);
		doritosBtnPanel.setBorder(new LineBorder(Color.ORANGE, 4, true));
		doritosBtnPanel.setBounds(102, 313, 135, 45);
		snackPane.add(doritosBtnPanel);
		doritosBtnPanel.setLayout(null);
		// DORITOS btn
		doritosBtn = new JButton("BUY");
		doritosBtn.setBounds(5, 5, 125, 35);
		doritosBtnPanel.add(doritosBtn);
		doritosBtn.setFocusPainted(false);
		doritosBtn.setOpaque(false);
		doritosBtn.setBorderPainted(false); 
		lbImgDoritos.setLabelFor(doritosBtn);
		doritosBtn.setBackground(BG_COLOR);
		doritosBtn.setContentAreaFilled(false);
		doritosBtn.setBorderPainted(false);
		doritosBtn.setForeground(Color.ORANGE);
		doritosBtn.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// DORITOS btn action
		doritosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		doritosBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				doritosBtn.setBackground(Color.ORANGE);
				doritosBtn.setContentAreaFilled(true);
				doritosBtn.setOpaque(true);
				doritosBtn.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				doritosBtn.setBackground(BG_COLOR);
				doritosBtn.setContentAreaFilled(false);
				doritosBtn.setForeground(Color.ORANGE);
				doritosBtn.setOpaque(false);
			}
		});
		
		// CHEEZ-IT image panel
		cheezitPanel = new JPanel();
		cheezitPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		cheezitPanel.setBackground(BG_COLOR);
		cheezitPanel.setBounds(348, 112, 135, 135);
		snackPane.add(cheezitPanel);
		cheezitPanel.setLayout(null);
		// CHEEZ-IT image
		lbImgCheezit = new JLabel("");
		lbImgCheezit.setBounds(5, 5, 125, 125);
		cheezitPanel.add(lbImgCheezit);
		lbImgCheezit.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\img\\snacks\\cheezit.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
		// CHEEZ-IT btn panel
		cheezitBtnPanel = new JPanel();
		cheezitBtnPanel.setLayout(null);
		cheezitBtnPanel.setBorder(new LineBorder(Color.ORANGE, 4, true));
		cheezitBtnPanel.setBackground(new Color(39, 46, 56));
		cheezitBtnPanel.setBounds(348, 313, 135, 45);
		snackPane.add(cheezitBtnPanel);
		// CHEEZ-IT btn
		cheezitBtn = new JButton("BUY");
		cheezitBtn.setOpaque(false);
		cheezitBtn.setForeground(Color.ORANGE);
		cheezitBtn.setFont(new Font("Arial Black", Font.PLAIN, 15));
		cheezitBtn.setFocusPainted(false);
		cheezitBtn.setContentAreaFilled(false);
		cheezitBtn.setBorderPainted(false);
		cheezitBtn.setBackground(new Color(39, 46, 56));
		cheezitBtn.setBounds(5, 5, 125, 35);
		cheezitBtnPanel.add(cheezitBtn);
		// CHEEZ-IT btn actions
		cheezitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cheezitBtn.setBackground(Color.ORANGE);
				cheezitBtn.setContentAreaFilled(true);
				cheezitBtn.setOpaque(true);
				cheezitBtn.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cheezitBtn.setBackground(BG_COLOR);
				cheezitBtn.setContentAreaFilled(false);
				cheezitBtn.setForeground(Color.ORANGE);
				cheezitBtn.setOpaque(false);
			}
		});
		
		// OREO image panel
		oreoPanel = new JPanel();
		oreoPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		oreoPanel.setBackground(BG_COLOR);
		oreoPanel.setBounds(102, 400, 135, 135);
		snackPane.add(oreoPanel);
		oreoPanel.setLayout(null);
		// OREO image
		lbImgOreo = new JLabel("");
		lbImgOreo.setBounds(5, 5, 125, 125);
		oreoPanel.add(lbImgOreo);
		
		// OREO btn panel
		lbImgOreo.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\img\\snacks\\oreo.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
		oreoBtnPanel = new JPanel();
		oreoBtnPanel.setLayout(null);
		oreoBtnPanel.setBorder(new LineBorder(Color.ORANGE, 4, true));
		oreoBtnPanel.setBackground(new Color(39, 46, 56));
		oreoBtnPanel.setBounds(102, 600, 135, 45);
		snackPane.add(oreoBtnPanel);
		//OREO btn
		oreoBtn = new JButton("BUY");
		oreoBtn.setOpaque(false);
		oreoBtn.setForeground(Color.ORANGE);
		oreoBtn.setFont(new Font("Arial Black", Font.PLAIN, 15));
		oreoBtn.setFocusPainted(false);
		oreoBtn.setContentAreaFilled(false);
		oreoBtn.setBorderPainted(false);
		oreoBtn.setBackground(new Color(39, 46, 56));
		oreoBtn.setBounds(5, 5, 125, 35);
		oreoBtnPanel.add(oreoBtn);
		// OREO btn actions
		oreoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oreoBtn.setBackground(Color.ORANGE);
				oreoBtn.setContentAreaFilled(true);
				oreoBtn.setOpaque(true);
				oreoBtn.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				oreoBtn.setBackground(BG_COLOR);
				oreoBtn.setContentAreaFilled(false);
				oreoBtn.setForeground(Color.ORANGE);
				oreoBtn.setOpaque(false);
			}
		});
		
		// CHEETOS image panel
		cheetosPanel = new JPanel();
		cheetosPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5, true));
		cheetosPanel.setBackground(BG_COLOR);
		cheetosPanel.setBounds(348, 400, 135, 135);
		snackPane.add(cheetosPanel);
		cheetosPanel.setLayout(null);
		// CHEETOS image
		lbImgCheetos = new JLabel("");
		lbImgCheetos.setBounds(5, 5, 125, 125);
		cheetosPanel.add(lbImgCheetos);
		lbImgCheetos.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\img\\snacks\\cheetos.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
		// CHEETOS btn panel
		cheetosBtnPanel = new JPanel();
		cheetosBtnPanel.setLayout(null);
		cheetosBtnPanel.setBorder(new LineBorder(Color.ORANGE, 4, true));
		cheetosBtnPanel.setBackground(new Color(39, 46, 56));
		cheetosBtnPanel.setBounds(348, 600, 135, 45);
		snackPane.add(cheetosBtnPanel);
		// CHEETOS btn
		cheetosBtn = new JButton("BUY");
		cheetosBtn.setOpaque(false);
		cheetosBtn.setForeground(Color.ORANGE);
		cheetosBtn.setFont(new Font("Arial Black", Font.PLAIN, 15));
		cheetosBtn.setFocusPainted(false);
		cheetosBtn.setContentAreaFilled(false);
		cheetosBtn.setBorderPainted(false);
		cheetosBtn.setBackground(new Color(39, 46, 56));
		cheetosBtn.setBounds(5, 5, 125, 35);
		cheetosBtnPanel.add(cheetosBtn);
		// CHEETOS btn action
		cheetosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cheetosBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cheetosBtn.setBackground(Color.ORANGE);
				cheetosBtn.setContentAreaFilled(true);
				cheetosBtn.setOpaque(true);
				cheetosBtn.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cheetosBtn.setBackground(BG_COLOR);
				cheetosBtn.setContentAreaFilled(false);
				cheetosBtn.setForeground(Color.ORANGE);
				cheetosBtn.setOpaque(false);
			}
		});
				
		
		
		// BACK btn panel
		backPanel = new JPanel();
		backPanel.setBorder(new LineBorder(new Color(255, 0, 0), 4, true));
		backPanel.setBounds(32, 700, 120, 48);
		backPanel.setBackground(BG_COLOR);
		snackPane.add(backPanel);
		backPanel.setLayout(null);
		// BACK btn
		backBtn = new JButton("BACK");
		backBtn.setBounds(5, 5, 110, 38);
		backPanel.add(backBtn);
		backBtn.setOpaque(false);
		backBtn.setForeground(Color.RED);
		backBtn.setFont(new Font("Arial Black", Font.PLAIN, 15));
		backBtn.setFocusPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBackground(BG_COLOR);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, Color.CYAN));
		panel.setBackground(new Color(39, 46, 56));
		panel.setBounds(102, 548, 135, 45);
		snackPane.add(panel);
		
		button = new JButton("$1.50");
		button.setOpaque(false);
		button.setForeground(Color.CYAN);
		button.setFont(new Font("Arial Black", Font.PLAIN, 15));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(39, 46, 56));
		button.setBounds(5, 5, 125, 35);
		panel.add(button);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, Color.CYAN));
		panel_1.setBackground(new Color(39, 46, 56));
		panel_1.setBounds(348, 548, 135, 45);
		snackPane.add(panel_1);
		
		button_1 = new JButton("$1.00");
		button_1.setOpaque(false);
		button_1.setForeground(Color.CYAN);
		button_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		button_1.setFocusPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBackground(new Color(39, 46, 56));
		button_1.setBounds(5, 5, 125, 35);
		panel_1.add(button_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, Color.CYAN));
		panel_2.setBackground(new Color(39, 46, 56));
		panel_2.setBounds(102, 260, 135, 45);
		snackPane.add(panel_2);
		
		button_2 = new JButton("$1.50");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.setOpaque(false);
		button_2.setForeground(Color.CYAN);
		button_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		button_2.setFocusPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBackground(new Color(39, 46, 56));
		button_2.setBounds(5, 5, 125, 35);
		panel_2.add(button_2);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, Color.CYAN));
		panel_3.setBackground(new Color(39, 46, 56));
		panel_3.setBounds(348, 260, 135, 45);
		snackPane.add(panel_3);
		
		button_3 = new JButton("$2.00");
		button_3.setOpaque(false);
		button_3.setForeground(Color.CYAN);
		button_3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		button_3.setFocusPainted(false);
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setBackground(new Color(39, 46, 56));
		button_3.setBounds(5, 5, 125, 35);
		panel_3.add(button_3);
		// BACK btn action
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snackFrame.dispose();
				new App();
			}
		});
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setBackground(Color.RED);
				backBtn.setContentAreaFilled(true);
				backBtn.setOpaque(true);
				backBtn.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setBackground(BG_COLOR);
				backBtn.setContentAreaFilled(false);
				backBtn.setForeground(Color.RED);
				backBtn.setOpaque(false);
			}
		});
	}
}
