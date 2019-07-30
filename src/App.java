// TODO 
// insert money by pressing btns
// implement back btn
// cancel of first page
// cancel of second page


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

public class App {
	private Color orange = new Color(255, 142, 35);
	
	private Item[][] itemList;
	private int codeCnt;
	private ArrayList<Integer> code;
	private StringBuilder codeStr;
	private double cashInserted;
	private Item chosenItem;
	
	
	private JFrame frame;
	private JPanel selectPanel;
	private JPanel payPanel;
	private JLabel lbCode;
	private JButton btnNext;
	private JButton btnCancel_1;
	private JLabel lbCollect;
	
	private JLabel lbPrice;
	private JLabel label_17;
	private JLabel lbInserted_1;
	private JLabel lbInserted_2;
	private JButton btnItem;
	private JLabel lblCollectChange;
	private JLabel lblCashInserted;
	private JButton btnBuy;
	private JPanel panel_9;
	private JPanel panel_10;
	
	private JButton btnBack;
	private JButton btnCancel_2;
	
	private JButton btnCashFive;
	private JButton btnCashOne;
	private JButton btnCashQuarter;
	private JButton btnCashDime;
	private JButton btnCashNickle;
	private JButton btnCashPenny;
	
	private JLabel lblOne;
	private JLabel lblQuarter;
	private JLabel lblDime;
	private JLabel lblNickel;
	private JLabel lblPenny;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void updateCode() {
		for(int i=0; i<code.size(); i++) {
			if(code.get(i)==-1)
				codeStr.setCharAt(i*2, '_');
			else
				codeStr.setCharAt(i*2, code.get(i).toString().charAt(0));
		}
		lbCode.setText(codeStr.toString());
	}
	
	private void enableCashPanel(boolean on) {
		Component[] comp = panel_9.getComponents();
		if (on) {
			for (int i=0; i<comp.length; i++) {
				if (comp[i] instanceof  JButton) {
					comp[i].setEnabled(true);
				}
			}
		}
		else {
			for (int i=0; i<comp.length; i++) {
				if (comp[i] instanceof  JButton) {
					comp[i].setEnabled(false);
				}
			}
		}
	}
	
	
	private int[] calChange(double change) {
		int ones = (int) (change / 1.0);
		change -= ones;
		int qt = (int) (change / 0.25);
		change -= qt*0.25;
		int dime = (int) (change / 0.1);
		change -= dime*0.1;
		int nickle = (int) (change / 0.05);
		change -= nickle*0.05;
		int penny = (int) (change / 0.01);
		int[] changes = {ones, qt, dime, nickle, penny};
		return changes;
	}
	
	private void reset() {
		payPanel.setVisible(false);
		selectPanel.setVisible(true);
		cashInserted = 0;
		codeCnt = 0;
		code = new ArrayList<>(Arrays.asList(-1,-1,-1));	
		updateCode();
		label_17.setVisible(false);
		lbInserted_1.setVisible(false);
		btnCancel_1.setEnabled(true);
		btnNext.setEnabled(true);
		btnNext.setBorder(new LineBorder(Color.CYAN, 3, true));
		btnCancel_1.setBorder(new LineBorder(Color.RED, 3, true));
		btnCancel_1.setForeground(Color.RED);
		lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
		lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
		lbInserted_1.setVisible(true);
		lbCollect.setText("Collect Item Here");
		lbCollect.setForeground(Color.WHITE);
		lblCollectChange.setVisible(false);
		lblOne.setText("$1: ");
		lblQuarter.setText("$0.25: ");
		lblDime.setText("$0.1: ");
		lblNickel.setText("$0.05: " );
		lblPenny.setText("$0.01: ");
		lblOne.setVisible(false);
		lblQuarter.setVisible(false);
		lblDime.setVisible(false);
		lblNickel.setVisible(false);
		lblPenny.setVisible(false);
		panel_9.setVisible(false);
		lbInserted_1.setVisible(false);
		btnBuy.setEnabled(false);
		btnCancel_2.setEnabled(true);
		btnBack.setEnabled(true);
		btnBuy.setForeground(Color.CYAN);
		btnBuy.setBorder(new LineBorder(Color.GRAY, 3, true));
		btnCancel_2.setBorder(new LineBorder(Color.RED, 3, true));
		btnCancel_2.setForeground(Color.RED);
		btnBack.setBorder(new LineBorder(Color.ORANGE, 3, true));
		panel_10.setBackground(Color.ORANGE);
		panel_10.setBorder(new LineBorder(Color.ORANGE, 3, true));
		enableCashPanel(true);
	}
	
	
	private void displayChange(int[] changes) {
		lblCollectChange.setVisible(true);
		lblOne.setVisible(true);
		lblQuarter.setVisible(true);
		lblDime.setVisible(true);
		lblNickel.setVisible(true);
		lblPenny.setVisible(true);
		lblOne.setText(lblOne.getText()+changes[0]);
		lblQuarter.setText(lblQuarter.getText()+changes[1]);
		lblDime.setText(lblDime.getText()+changes[2]);
		lblNickel.setText(lblNickel.getText()+changes[3]);
		lblPenny.setText(lblPenny.getText()+changes[4]);
		System.out.println("Changes: ");
		System.out.println("Ones: "+changes[0]);
		System.out.println("Quarter: "+changes[1]);
		System.out.println("Dime: "+changes[2]);
		System.out.println("Nickle: "+changes[3]);
		System.out.println("Penny: "+changes[4]);
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
		codeCnt = 0;
		code = new ArrayList<>(Arrays.asList(-1,-1,-1));
		codeStr = new StringBuilder("_ _ _");
		itemList = new Item[][]{
			{new Item("energy bar", 100, 2.00, 22), new Item("nuts", 101, 1.50, 25), new Item("chips", 102, 1.00, 25), new Item("cooikes", 103, 1.50, 25)},
			{new Item("soda", 200, 1.00, 25), new Item("energy drink", 201, 2.50, 18), new Item("juice", 202, 1.50, 25), new Item("mineral water", 203, 1.00, 17)}
		};
		cashInserted = 0.0;
		
		frame = new JFrame();
		frame.setTitle("VENDING");
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(0, 0, 590, 790);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(0, 0, 400, 650);
		itemPanel.setBackground(Color.ORANGE);
		frame.getContentPane().add(itemPanel);
		itemPanel.setLayout(new GridLayout(4, 2,2,2));
		
		JPanel energybarPanel = new JPanel();
		energybarPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(energybarPanel);
		SpringLayout sl_energybarPanel = new SpringLayout();
		energybarPanel.setLayout(sl_energybarPanel);
		
		JPanel energybarBpanel = new JPanel();
		energybarBpanel.setBackground(Color.ORANGE);
		sl_energybarPanel.putConstraint(SpringLayout.WEST, energybarBpanel, 10, SpringLayout.WEST, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.EAST, energybarBpanel, -10, SpringLayout.EAST, energybarPanel);
		energybarBpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		energybarPanel.add(energybarBpanel);
		
		JButton btnEnergybar = new JButton("ENERGY BAR");
		btnEnergybar.setMargin(new Insets(2, 0, 2, 0));
		btnEnergybar.setBorderPainted(false);
		btnEnergybar.setFocusPainted(false);
		btnEnergybar.setContentAreaFilled(false);
		btnEnergybar.setRequestFocusEnabled(false);
		energybarBpanel.add(btnEnergybar);
		btnEnergybar.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnEnergybar.setForeground(Color.BLACK);
		
		JLabel lbEnergybarCode = new JLabel("100");
		lbEnergybarCode.setForeground(Color.WHITE);
		sl_energybarPanel.putConstraint(SpringLayout.WEST, lbEnergybarCode, 63, SpringLayout.WEST, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.NORTH, lbEnergybarCode, 115, SpringLayout.NORTH, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.SOUTH, lbEnergybarCode, -10, SpringLayout.SOUTH, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.EAST, lbEnergybarCode, -60, SpringLayout.EAST, energybarPanel);
		lbEnergybarCode.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbEnergybarCode.setHorizontalAlignment(SwingConstants.CENTER);
		lbEnergybarCode.setFont(new Font("Arial Black", Font.PLAIN, 20));
		energybarPanel.add(lbEnergybarCode);
		
		JLabel lbEnergybarPrice = new JLabel("$2.00");
		lbEnergybarPrice.setForeground(Color.ORANGE);
		sl_energybarPanel.putConstraint(SpringLayout.SOUTH, energybarBpanel, -6, SpringLayout.NORTH, lbEnergybarPrice);
		sl_energybarPanel.putConstraint(SpringLayout.NORTH, lbEnergybarPrice, 70, SpringLayout.NORTH, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.WEST, lbEnergybarPrice, 12, SpringLayout.WEST, energybarPanel);
		sl_energybarPanel.putConstraint(SpringLayout.EAST, lbEnergybarPrice, -10, SpringLayout.EAST, energybarPanel);
		lbEnergybarPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbEnergybarPrice.setFont(new Font("Arial Black", Font.PLAIN, 25));
		energybarPanel.add(lbEnergybarPrice);
		
		JPanel nutsPanel = new JPanel();
		nutsPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(nutsPanel);
		SpringLayout sl_nutsPanel = new SpringLayout();
		nutsPanel.setLayout(sl_nutsPanel);
		
		JPanel nutsBpanel = new JPanel();
		sl_nutsPanel.putConstraint(SpringLayout.NORTH, nutsBpanel, 10, SpringLayout.NORTH, nutsPanel);
		sl_nutsPanel.putConstraint(SpringLayout.SOUTH, nutsBpanel, 63, SpringLayout.NORTH, nutsPanel);
		nutsBpanel.setBackground(Color.ORANGE);
		sl_nutsPanel.putConstraint(SpringLayout.WEST, nutsBpanel, 10, SpringLayout.WEST, nutsPanel);
		nutsBpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		nutsPanel.add(nutsBpanel);
		nutsBpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNuts = new JButton("NUTS");
		btnNuts.setForeground(Color.BLACK);
		btnNuts.setMargin(new Insets(2, 0, 2, 0));
		btnNuts.setRequestFocusEnabled(false);
		btnNuts.setOpaque(false);
		btnNuts.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnNuts.setFocusPainted(false);
		btnNuts.setContentAreaFilled(false);
		btnNuts.setBorderPainted(false);
		nutsBpanel.add(btnNuts);
		
		JLabel lbNutsPrice = new JLabel("$1.50");
		sl_nutsPanel.putConstraint(SpringLayout.NORTH, lbNutsPrice, 6, SpringLayout.SOUTH, nutsBpanel);
		lbNutsPrice.setForeground(Color.ORANGE);
		sl_nutsPanel.putConstraint(SpringLayout.EAST, nutsBpanel, 0, SpringLayout.EAST, lbNutsPrice);
		sl_nutsPanel.putConstraint(SpringLayout.WEST, lbNutsPrice, 12, SpringLayout.WEST, nutsPanel);
		sl_nutsPanel.putConstraint(SpringLayout.EAST, lbNutsPrice, -10, SpringLayout.EAST, nutsPanel);
		lbNutsPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbNutsPrice.setFont(new Font("Arial Black", Font.PLAIN, 25));
		nutsPanel.add(lbNutsPrice);
		
		JLabel label = new JLabel("101");
		label.setForeground(Color.WHITE);
		sl_nutsPanel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.SOUTH, lbNutsPrice);
		sl_nutsPanel.putConstraint(SpringLayout.WEST, label, -140, SpringLayout.EAST, nutsPanel);
		sl_nutsPanel.putConstraint(SpringLayout.SOUTH, label, -10, SpringLayout.SOUTH, nutsPanel);
		sl_nutsPanel.putConstraint(SpringLayout.EAST, label, -59, SpringLayout.EAST, nutsPanel);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		nutsPanel.add(label);
		
		JPanel chipsPanel = new JPanel();
		chipsPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(chipsPanel);
		SpringLayout sl_chipsPanel = new SpringLayout();
		chipsPanel.setLayout(sl_chipsPanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		sl_chipsPanel.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, chipsPanel);
		sl_chipsPanel.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, chipsPanel);
		sl_chipsPanel.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, chipsPanel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		chipsPanel.add(panel);
		
		JButton btnChips = new JButton("CHIPS");
		btnChips.setForeground(Color.BLACK);
		btnChips.setRequestFocusEnabled(false);
		btnChips.setOpaque(false);
		btnChips.setMargin(new Insets(2, 0, 2, 0));
		btnChips.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnChips.setFocusPainted(false);
		btnChips.setContentAreaFilled(false);
		btnChips.setBorderPainted(false);
		panel.add(btnChips);
		
		JLabel label_1 = new JLabel("$1.00");
		label_1.setForeground(Color.ORANGE);
		sl_chipsPanel.putConstraint(SpringLayout.NORTH, label_1, 6, SpringLayout.SOUTH, panel);
		sl_chipsPanel.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, chipsPanel);
		sl_chipsPanel.putConstraint(SpringLayout.EAST, label_1, -10, SpringLayout.EAST, chipsPanel);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 25));
		chipsPanel.add(label_1);
		
		JLabel label_2 = new JLabel("102");
		label_2.setForeground(Color.WHITE);
		sl_chipsPanel.putConstraint(SpringLayout.NORTH, label_2, 5, SpringLayout.SOUTH, label_1);
		sl_chipsPanel.putConstraint(SpringLayout.WEST, label_2, -139, SpringLayout.EAST, chipsPanel);
		sl_chipsPanel.putConstraint(SpringLayout.SOUTH, label_2, -10, SpringLayout.SOUTH, chipsPanel);
		sl_chipsPanel.putConstraint(SpringLayout.EAST, label_2, -60, SpringLayout.EAST, chipsPanel);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chipsPanel.add(label_2);
		
		JPanel cookiesPanel = new JPanel();
		cookiesPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(cookiesPanel);
		SpringLayout sl_cookiesPanel = new SpringLayout();
		cookiesPanel.setLayout(sl_cookiesPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		sl_cookiesPanel.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, cookiesPanel);
		sl_cookiesPanel.putConstraint(SpringLayout.WEST, panel_1, 8, SpringLayout.WEST, cookiesPanel);
		sl_cookiesPanel.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, cookiesPanel);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		cookiesPanel.add(panel_1);
		
		JButton btnCookies = new JButton("COOKIES");
		btnCookies.setForeground(Color.BLACK);
		btnCookies.setRequestFocusEnabled(false);
		btnCookies.setOpaque(false);
		btnCookies.setMargin(new Insets(2, 0, 2, 0));
		btnCookies.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnCookies.setFocusPainted(false);
		btnCookies.setContentAreaFilled(false);
		btnCookies.setBorderPainted(false);
		panel_1.add(btnCookies);
		
		JLabel label_3 = new JLabel("$1.50");
		label_3.setForeground(Color.ORANGE);
		sl_cookiesPanel.putConstraint(SpringLayout.NORTH, label_3, 6, SpringLayout.SOUTH, panel_1);
		sl_cookiesPanel.putConstraint(SpringLayout.EAST, label_3, 0, SpringLayout.EAST, panel_1);
		sl_cookiesPanel.putConstraint(SpringLayout.WEST, label_3, 8, SpringLayout.WEST, cookiesPanel);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Arial Black", Font.PLAIN, 25));
		cookiesPanel.add(label_3);
		
		JLabel label_4 = new JLabel("103");
		label_4.setForeground(Color.WHITE);
		sl_cookiesPanel.putConstraint(SpringLayout.NORTH, label_4, 5, SpringLayout.SOUTH, label_3);
		sl_cookiesPanel.putConstraint(SpringLayout.WEST, label_4, 59, SpringLayout.WEST, cookiesPanel);
		sl_cookiesPanel.putConstraint(SpringLayout.SOUTH, label_4, -10, SpringLayout.SOUTH, cookiesPanel);
		sl_cookiesPanel.putConstraint(SpringLayout.EAST, label_4, 142, SpringLayout.WEST, cookiesPanel);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cookiesPanel.add(label_4);
		
		JPanel sodePanel = new JPanel();
		sodePanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(sodePanel);
		SpringLayout sl_sodePanel = new SpringLayout();
		sodePanel.setLayout(sl_sodePanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		sl_sodePanel.putConstraint(SpringLayout.NORTH, panel_2, 10, SpringLayout.NORTH, sodePanel);
		sl_sodePanel.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, sodePanel);
		sl_sodePanel.putConstraint(SpringLayout.EAST, panel_2, -10, SpringLayout.EAST, sodePanel);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		sodePanel.add(panel_2);
		
		JButton btnSoda = new JButton("SODA");
		btnSoda.setForeground(Color.BLACK);
		btnSoda.setRequestFocusEnabled(false);
		btnSoda.setOpaque(false);
		btnSoda.setMargin(new Insets(2, 0, 2, 0));
		btnSoda.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnSoda.setFocusPainted(false);
		btnSoda.setContentAreaFilled(false);
		btnSoda.setBorderPainted(false);
		panel_2.add(btnSoda);
		
		JLabel label_5 = new JLabel("$1.00");
		label_5.setForeground(Color.ORANGE);
		sl_sodePanel.putConstraint(SpringLayout.NORTH, label_5, 6, SpringLayout.SOUTH, panel_2);
		sl_sodePanel.putConstraint(SpringLayout.WEST, label_5, 0, SpringLayout.WEST, panel_2);
		sl_sodePanel.putConstraint(SpringLayout.EAST, label_5, 0, SpringLayout.EAST, panel_2);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Arial Black", Font.PLAIN, 25));
		sodePanel.add(label_5);
		
		JLabel label_6 = new JLabel("200");
		label_6.setForeground(Color.WHITE);
		sl_sodePanel.putConstraint(SpringLayout.NORTH, label_6, 6, SpringLayout.SOUTH, label_5);
		sl_sodePanel.putConstraint(SpringLayout.WEST, label_6, 59, SpringLayout.WEST, sodePanel);
		sl_sodePanel.putConstraint(SpringLayout.SOUTH, label_6, -10, SpringLayout.SOUTH, sodePanel);
		sl_sodePanel.putConstraint(SpringLayout.EAST, label_6, 143, SpringLayout.WEST, sodePanel);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sodePanel.add(label_6);
		
		JPanel energydrinkPanel = new JPanel();
		energydrinkPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(energydrinkPanel);
		SpringLayout sl_energydrinkPanel = new SpringLayout();
		energydrinkPanel.setLayout(sl_energydrinkPanel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		sl_energydrinkPanel.putConstraint(SpringLayout.NORTH, panel_3, 10, SpringLayout.NORTH, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.WEST, panel_3, 10, SpringLayout.WEST, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.SOUTH, panel_3, 70, SpringLayout.NORTH, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.EAST, panel_3, -10, SpringLayout.EAST, energydrinkPanel);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		energydrinkPanel.add(panel_3);
		
		JButton btnEnergyDrink = new JButton("ENERGY DRINK");
		btnEnergyDrink.setForeground(Color.BLACK);
		btnEnergyDrink.setRequestFocusEnabled(false);
		btnEnergyDrink.setOpaque(false);
		btnEnergyDrink.setMargin(new Insets(8, 0, 8, 0));
		btnEnergyDrink.setFont(new Font("Arial Black", Font.PLAIN, 18));
		btnEnergyDrink.setFocusPainted(false);
		btnEnergyDrink.setContentAreaFilled(false);
		btnEnergyDrink.setBorderPainted(false);
		panel_3.add(btnEnergyDrink);
		
		JLabel label_7 = new JLabel("$2.50");
		label_7.setForeground(Color.ORANGE);
		sl_energydrinkPanel.putConstraint(SpringLayout.NORTH, label_7, 6, SpringLayout.SOUTH, panel_3);
		sl_energydrinkPanel.putConstraint(SpringLayout.WEST, label_7, 10, SpringLayout.WEST, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.EAST, label_7, -16, SpringLayout.EAST, energydrinkPanel);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Arial Black", Font.PLAIN, 25));
		energydrinkPanel.add(label_7);
		
		JLabel label_8 = new JLabel("201");
		label_8.setForeground(Color.WHITE);
		sl_energydrinkPanel.putConstraint(SpringLayout.NORTH, label_8, 4, SpringLayout.SOUTH, label_7);
		sl_energydrinkPanel.putConstraint(SpringLayout.WEST, label_8, 57, SpringLayout.WEST, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.SOUTH, label_8, -10, SpringLayout.SOUTH, energydrinkPanel);
		sl_energydrinkPanel.putConstraint(SpringLayout.EAST, label_8, 134, SpringLayout.WEST, energydrinkPanel);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_8.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		energydrinkPanel.add(label_8);
		
		JPanel juicePanel = new JPanel();
		juicePanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(juicePanel);
		SpringLayout sl_juicePanel = new SpringLayout();
		juicePanel.setLayout(sl_juicePanel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.ORANGE);
		sl_juicePanel.putConstraint(SpringLayout.NORTH, panel_4, 10, SpringLayout.NORTH, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.WEST, panel_4, 10, SpringLayout.WEST, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.EAST, panel_4, -10, SpringLayout.EAST, juicePanel);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		juicePanel.add(panel_4);
		
		JButton btnJuice = new JButton("JUICE");
		btnJuice.setForeground(Color.BLACK);
		btnJuice.setRequestFocusEnabled(false);
		btnJuice.setOpaque(false);
		btnJuice.setMargin(new Insets(2, 0, 2, 0));
		btnJuice.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnJuice.setFocusPainted(false);
		btnJuice.setContentAreaFilled(false);
		btnJuice.setBorderPainted(false);
		panel_4.add(btnJuice);
		
		JLabel label_9 = new JLabel("$1.50");
		sl_juicePanel.putConstraint(SpringLayout.NORTH, label_9, 71, SpringLayout.NORTH, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.WEST, label_9, 10, SpringLayout.WEST, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.EAST, label_9, -10, SpringLayout.EAST, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.SOUTH, panel_4, -7, SpringLayout.NORTH, label_9);
		label_9.setForeground(Color.ORANGE);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Arial Black", Font.PLAIN, 25));
		juicePanel.add(label_9);
		
		JLabel label_10 = new JLabel("202");
		label_10.setForeground(Color.WHITE);
		sl_juicePanel.putConstraint(SpringLayout.NORTH, label_10, 6, SpringLayout.SOUTH, label_9);
		sl_juicePanel.putConstraint(SpringLayout.WEST, label_10, -138, SpringLayout.EAST, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.SOUTH, label_10, -6, SpringLayout.SOUTH, juicePanel);
		sl_juicePanel.putConstraint(SpringLayout.EAST, label_10, -54, SpringLayout.EAST, juicePanel);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_10.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		juicePanel.add(label_10);
		
		JPanel waterPanel = new JPanel();
		waterPanel.setBackground(Color.DARK_GRAY);
		itemPanel.add(waterPanel);
		SpringLayout sl_waterPanel = new SpringLayout();
		waterPanel.setLayout(sl_waterPanel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.ORANGE);
		sl_waterPanel.putConstraint(SpringLayout.NORTH, panel_5, 10, SpringLayout.NORTH, waterPanel);
		sl_waterPanel.putConstraint(SpringLayout.WEST, panel_5, 10, SpringLayout.WEST, waterPanel);
		sl_waterPanel.putConstraint(SpringLayout.EAST, panel_5, -10, SpringLayout.EAST, waterPanel);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		waterPanel.add(panel_5);
		
		JButton btnMineralWater = new JButton("MINERAL WATER");
		btnMineralWater.setForeground(Color.BLACK);
		btnMineralWater.setRequestFocusEnabled(false);
		btnMineralWater.setOpaque(false);
		btnMineralWater.setMargin(new Insets(8, 0, 8, 0));
		btnMineralWater.setFont(new Font("Arial Black", Font.PLAIN, 17));
		btnMineralWater.setFocusPainted(false);
		btnMineralWater.setContentAreaFilled(false);
		btnMineralWater.setBorderPainted(false);
		panel_5.add(btnMineralWater);
		
		JLabel label_11 = new JLabel("$1.00");
		label_11.setForeground(Color.ORANGE);
		sl_waterPanel.putConstraint(SpringLayout.NORTH, label_11, 7, SpringLayout.SOUTH, panel_5);
		sl_waterPanel.putConstraint(SpringLayout.WEST, label_11, 0, SpringLayout.WEST, panel_5);
		sl_waterPanel.putConstraint(SpringLayout.EAST, label_11, 0, SpringLayout.EAST, panel_5);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Arial Black", Font.PLAIN, 25));
		waterPanel.add(label_11);
		
		JLabel label_12 = new JLabel("203");
		label_12.setForeground(Color.WHITE);
		sl_waterPanel.putConstraint(SpringLayout.NORTH, label_12, 1, SpringLayout.SOUTH, label_11);
		sl_waterPanel.putConstraint(SpringLayout.WEST, label_12, -136, SpringLayout.EAST, waterPanel);
		sl_waterPanel.putConstraint(SpringLayout.SOUTH, label_12, -10, SpringLayout.SOUTH, waterPanel);
		sl_waterPanel.putConstraint(SpringLayout.EAST, label_12, -54, SpringLayout.EAST, waterPanel);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_12.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		waterPanel.add(label_12);
		
		JPanel tapPanel = new JPanel();
		tapPanel.setBounds(402, 0, 180, 650);
		tapPanel.setBackground(Color.ORANGE);
		frame.getContentPane().add(tapPanel);
		tapPanel.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(0, 0, 180, 100);
		tapPanel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblVending = new JLabel("<html>VENDING<br />MACHINE</html>");
		lblVending.setBackground(Color.DARK_GRAY);
		lblVending.setForeground(Color.WHITE);
		lblVending.setBounds(0, 0, 180, 100);
		lblVending.setHorizontalAlignment(SwingConstants.CENTER);
		lblVending.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 25));
		panel_6.add(lblVending);
		
		selectPanel = new JPanel();
		selectPanel.setBackground(Color.DARK_GRAY);
		selectPanel.setBounds(0, 102, 180, 436);
		tapPanel.add(selectPanel);
		selectPanel.setLayout(null);
		
		JPanel codeDispalyPanel = new JPanel();
		codeDispalyPanel.setBackground(Color.DARK_GRAY);
		codeDispalyPanel.setBounds(14, 30, 152, 30);
		selectPanel.add(codeDispalyPanel);
		codeDispalyPanel.setLayout(null);
		
		lbCode = new JLabel(codeStr.toString());
		lbCode.setForeground(Color.WHITE);
		lbCode.setBounds(0, 0, 152, 30);
		codeDispalyPanel.add(lbCode);
		lbCode.setFont(new Font("Arial Black", Font.BOLD, 30));
		lbCode.setHorizontalAlignment(SwingConstants.CENTER);
		
		label_17 = new JLabel("Cash Inserted:");
		label_17.setForeground(Color.ORANGE);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Arial Black", Font.PLAIN, 20));
		label_17.setBounds(0, 293, 180, 18);
		label_17.setVisible(false);
		selectPanel.add(label_17);

		lbInserted_1 = new JLabel("$0.00");
		lbInserted_1.setForeground(Color.WHITE);
		lbInserted_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbInserted_1.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lbInserted_1.setBounds(6, 311, 160, 29);
		lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
		lbInserted_1.setVisible(false);
		selectPanel.add(lbInserted_1);
		
		btnNext = new JButton("NEXT");
		btnNext.setForeground(Color.CYAN);
		btnNext.setFocusPainted(false);
		btnNext.setContentAreaFilled(false);
		btnNext.setBorder(new LineBorder(Color.CYAN, 3, true));
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					chosenItem = itemList[code.get(0)-1][code.get(1)*10+code.get(2)];
					// System.out.println(item);
					
					selectPanel.setVisible(false);
					payPanel.setVisible(true);
					lbPrice.setText("$"+String.format("%.2f", chosenItem.getPrice()));
					btnItem.setText(chosenItem.getName().toUpperCase());
					btnItem.setFont(new Font("Arial Black", Font.PLAIN, chosenItem.getFontSize()));	
					panel_9.setVisible(true);
					if (cashInserted>=chosenItem.getPrice()) {
						btnBuy.setEnabled(true);
						btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
						enableCashPanel(false);
					}
					if (cashInserted<chosenItem.getPrice()) {
						btnBuy.setEnabled(false);
						btnBuy.setBorder(new LineBorder(Color.GRAY, 3, true));
						enableCashPanel(true);
					}
				}
				catch(IndexOutOfBoundsException err) {
					lbCode.setFont(new Font("Arial Black", Font.BOLD, 20));
					lbCode.setForeground(Color.RED);
					lbCode.setText("Invalid Code");
						
					TimerTask restore = new TimerTask() {
						public void run() {
							lbCode.setFont(new Font("Arial Black", Font.BOLD, 30));
							lbCode.setForeground(Color.WHITE);
							code = new ArrayList<>(Arrays.asList(-1,-1,-1));
							codeStr = new StringBuilder("_ _ _");
							codeCnt = 0;
							updateCode();
						}
					};
					Timer timer = new Timer("Delay");
					timer.schedule(restore, 1000L);
					// System.out.println(code.get(0) + " " + code.get(1) + " " + code.get(2));
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnNext.isEnabled()) {
					btnNext.setBackground(Color.CYAN);
					btnNext.setContentAreaFilled(true);
					btnNext.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (btnNext.isEnabled()) {
					btnNext.setContentAreaFilled(false);
					btnNext.setForeground(Color.CYAN);
				}
			}

		});
		
				btnNext.setFont(new Font("Arial Black", Font.PLAIN, 15));
				btnNext.setBounds(14, 353, 152, 30);
				selectPanel.add(btnNext);
		
		JPanel keyPanel = new JPanel();
		keyPanel.setBackground(Color.DARK_GRAY);
		keyPanel.setBounds(14, 80, 152, 211);
		selectPanel.add(keyPanel);
		keyPanel.setLayout(new GridLayout(4, 3, 2, 2));
		
		JButton button_1 = new JButton("1");
		button_1.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_1.setFocusPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setForeground(Color.ORANGE);
		button_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 1);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setBackground(Color.ORANGE);
				button_1.setContentAreaFilled(true);
				button_1.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setContentAreaFilled(false);
				button_1.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.setBorder(new LineBorder(new Color(255, 200, 0), 2, true));
		button_2.setFocusPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setForeground(Color.ORANGE);
		button_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 2);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_2.setBackground(Color.ORANGE);
				button_2.setContentAreaFilled(true);
				button_2.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setContentAreaFilled(false);
				button_2.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.setForeground(Color.ORANGE);
		button_3.setFocusPainted(false);
		button_3.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_3.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_3.setContentAreaFilled(false);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 3);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_3.setBackground(Color.ORANGE);
				button_3.setContentAreaFilled(true);
				button_3.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_3.setContentAreaFilled(false);
				button_3.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.setForeground(Color.ORANGE);
		button_4.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_4.setFocusPainted(false);
		button_4.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_4.setContentAreaFilled(false);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 4);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_4.setBackground(Color.ORANGE);
				button_4.setContentAreaFilled(true);
				button_4.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_4.setContentAreaFilled(false);
				button_4.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.setForeground(Color.ORANGE);
		button_5.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_5.setFocusPainted(false);
		button_5.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_5.setContentAreaFilled(false);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 5);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_5.setBackground(Color.ORANGE);
				button_5.setContentAreaFilled(true);
				button_5.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_5.setContentAreaFilled(false);
				button_5.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.setForeground(Color.ORANGE);
		button_6.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_6.setFocusPainted(false);
		button_6.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_6.setContentAreaFilled(false);
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 6);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_6.setBackground(Color.ORANGE);
				button_6.setContentAreaFilled(true);
				button_6.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_6.setContentAreaFilled(false);
				button_6.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.setForeground(Color.ORANGE);
		button_7.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_7.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_7.setFocusPainted(false);
		button_7.setContentAreaFilled(false);
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 7);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_7.setBackground(Color.ORANGE);
				button_7.setContentAreaFilled(true);
				button_7.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_7.setContentAreaFilled(false);
				button_7.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.setForeground(Color.ORANGE);
		button_8.setFocusPainted(false);
		button_8.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_8.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_8.setContentAreaFilled(false);
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 8);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_8.setBackground(Color.ORANGE);
				button_8.setContentAreaFilled(true);
				button_8.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_8.setContentAreaFilled(false);
				button_8.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.setForeground(Color.ORANGE);
		button_9.setFocusPainted(false);
		button_9.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_9.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_9.setContentAreaFilled(false);
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 9);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_9.setBackground(Color.ORANGE);
				button_9.setContentAreaFilled(true);
				button_9.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_9.setContentAreaFilled(false);
				button_9.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_9);
		
		JButton button_null = new JButton("");
		keyPanel.add(button_null);
		button_null.setBorderPainted(false);
		button_null.setContentAreaFilled(false);
		button_null.setFocusPainted(false);
		
		JButton button_0 = new JButton("0");
		button_0.setForeground(Color.ORANGE);
		button_0.setFocusPainted(false);
		button_0.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_0.setFont(new Font("Arial Black", Font.BOLD, 20));
		button_0.setContentAreaFilled(false);
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(codeCnt==3) {
					
				}
				else {
					code.set(codeCnt, 0);
					codeCnt++;
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_0.setBackground(Color.ORANGE);
				button_0.setContentAreaFilled(true);
				button_0.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_0.setContentAreaFilled(false);
				button_0.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_0);
		
		JButton button_bk = new JButton("");
		button_bk.setBorder(new LineBorder(Color.ORANGE, 2, true));
		button_bk.setBackground(Color.DARK_GRAY);
		button_bk.setFocusPainted(false);
		URL imgURL = getClass().getResource("/images/backspace_orange.png");
		// System.out.println(imgURL);
		ImageIcon bkImage = new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT));
		button_bk.setContentAreaFilled(false);
		button_bk.setIcon(bkImage);
		button_bk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (codeCnt==0) {
					
				}
				else {
					code.set(--codeCnt, -1);
					updateCode();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_bk.setBackground(Color.ORANGE);
				button_bk.setContentAreaFilled(true);
				button_bk.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_bk.setContentAreaFilled(false);
				button_bk.setForeground(Color.ORANGE);
			}
		});
		keyPanel.add(button_bk);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Item Code:");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(14, 0, 152, 30);
		selectPanel.add(lblNewLabel_1);
		
		btnCancel_1 = new JButton("Cancel");
		btnCancel_1.setForeground(Color.RED);
		btnCancel_1.setContentAreaFilled(false);
		btnCancel_1.setFocusPainted(false);
		btnCancel_1.setBorder(new LineBorder(Color.RED, 3, true));
		btnCancel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cashInserted>0) {
					TimerTask resetFrame = new TimerTask() {
						public void run() {
							reset();
						}
					};
					Timer timer = new Timer("Delay");
					timer.schedule(resetFrame, 5000L);
					int[] changes = calChange(cashInserted);
					displayChange(changes);
					label_17.setVisible(false);
					lbInserted_1.setVisible(false);
					btnCancel_1.setEnabled(false);
					btnCancel_1.setContentAreaFilled(false);
					btnNext.setContentAreaFilled(false);
					btnNext.setBorder(new LineBorder(Color.GRAY, 3, true));
					btnCancel_1.setBorder(new LineBorder(Color.GRAY, 3, true));
					btnNext.setEnabled(false);
				}
				else {
					label_17.setVisible(false);
					lbInserted_1.setVisible(false);
					reset();
					btnCancel_1.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnCancel_1.isEnabled()) {
					btnCancel_1.setBackground(Color.RED);
					btnCancel_1.setContentAreaFilled(true);
					btnCancel_1.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (btnCancel_1.isEnabled()) {
					btnCancel_1.setContentAreaFilled(false);
					btnCancel_1.setForeground(Color.RED);
				}
			}
		});
		btnCancel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnCancel_1.setBounds(14, 396, 152, 30);
		selectPanel.add(btnCancel_1);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBackground(Color.DARK_GRAY);
		insertPanel.setBounds(0, 541, 180, 109);
		tapPanel.add(insertPanel);
		insertPanel.setLayout(null);
		
		panel_9 = new JPanel();
		panel_9.setBackground(Color.DARK_GRAY);
		panel_9.setBounds(0, 0, 180, 109);
		insertPanel.add(panel_9);
		panel_9.setVisible(false);
		
		btnCashFive = new JButton("$5");
		btnCashFive.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashFive.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashFive.setContentAreaFilled(false);
		btnCashFive.setForeground(Color.WHITE);
		btnCashFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cashInserted+=5;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					enableCashPanel(false);
				}
			}
		});
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		panel_9.add(btnCashFive);
		
		btnCashOne = new JButton("$1");
		btnCashOne.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashOne.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashOne.setForeground(Color.WHITE);
		btnCashOne.setContentAreaFilled(false);
		btnCashOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCashOne.isEnabled()) {return;}
				cashInserted+=1;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					btnCashOne.setEnabled(false);
					enableCashPanel(false);
				}
			}
		});
		panel_9.add(btnCashOne);
		
		btnCashQuarter = new JButton("$0.25");
		btnCashQuarter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashQuarter.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashQuarter.setForeground(Color.WHITE);
		btnCashQuarter.setContentAreaFilled(false);
		btnCashQuarter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCashQuarter.isEnabled()) {return;}
				cashInserted+=0.25;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					enableCashPanel(false);
				}
			}
		});
		panel_9.add(btnCashQuarter);
		
		btnCashDime = new JButton("$0.1");
		btnCashDime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashDime.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashDime.setForeground(Color.WHITE);
		btnCashDime.setContentAreaFilled(false);
		btnCashDime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCashDime.isEnabled()) {return;}
				cashInserted+=0.1;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					enableCashPanel(false);
				}
			}
		});
		panel_9.add(btnCashDime);
		
		btnCashNickle = new JButton("$0.05");
		btnCashNickle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashNickle.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashNickle.setForeground(Color.WHITE);
		btnCashNickle.setContentAreaFilled(false);
		btnCashNickle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCashNickle.isEnabled()) {return;}
				cashInserted+=0.05;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					enableCashPanel(false);
				}
			}
		});
		panel_9.add(btnCashNickle);
		
		btnCashPenny = new JButton("$0.01");
		btnCashPenny.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCashPenny.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnCashPenny.setForeground(Color.WHITE);
		btnCashPenny.setContentAreaFilled(false);
		btnCashPenny.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCashPenny.isEnabled()) {return;}
				cashInserted+=0.01;
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
				if (cashInserted>=chosenItem.getPrice()) {
					btnBuy.setEnabled(true);
					btnBuy.setBorder(new LineBorder(Color.CYAN, 3, true));
					enableCashPanel(false);
				}
			}
		});
		panel_9.add(btnCashPenny);
		
		JPanel delivPanel = new JPanel();
		delivPanel.setBounds(0, 652, 400, 100);
		delivPanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(delivPanel);
		
		
		payPanel = new JPanel();
		payPanel.setBackground(Color.DARK_GRAY);
		payPanel.setBounds(0, 101, 180, 436);
		tapPanel.add(payPanel);
		payPanel.setLayout(null);
		payPanel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Please Pay:");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(10, 10, 160, 29);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel.setAutoscrolls(true);
		payPanel.add(lblNewLabel);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_8.setBounds(20, 52, 130, 40);
		payPanel.add(panel_8);
		panel_8.setLayout(null);
		
		lbPrice = new JLabel("$0.00");
		lbPrice.setBackground(Color.ORANGE);
		lbPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbPrice.setBounds(0, 3, 130, 35);
		panel_8.add(lbPrice);
		lbPrice.setFont(new Font("Arial Black", Font.BOLD, 30));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 153, 0));
		panel_7.setBounds(0, 104, 180, 58);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		payPanel.add(panel_7);
		
		btnItem = new JButton("Item");
		btnItem.setBorder(null);
		btnItem.setForeground(Color.WHITE);
		btnItem.setRequestFocusEnabled(false);
		btnItem.setOpaque(false);
		btnItem.setMargin(new Insets(2, 0, 2, 0));
		btnItem.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 25));
		btnItem.setFocusPainted(false);
		btnItem.setContentAreaFilled(false);
		btnItem.setBorderPainted(false);
		panel_7.add(btnItem);
		
		JLabel lblPleaseInsertCash = new JLabel("Insert Cash");
		lblPleaseInsertCash.setForeground(Color.ORANGE);
		lblPleaseInsertCash.setBounds(15, 400, 160, 22);
		lblPleaseInsertCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseInsertCash.setFont(new Font("Arial Black", Font.BOLD, 15));
		payPanel.add(lblPleaseInsertCash);
		
		JLabel lbCashArrow_1 = new JLabel("");
		
//		URL imgURL = getClass().getResource("/images/backspace_orange.png");
//		ImageIcon bkImage = new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT));
//		button_bk.setIcon(bkImage);
//		
		URL arrImgURL = getClass().getResource("/images/orange_arrow.png");
		lbCashArrow_1.setIcon(new ImageIcon(new ImageIcon(arrImgURL).getImage().getScaledInstance(20, 15, Image.SCALE_DEFAULT)));
		lbCashArrow_1.setBounds(42, 420, 20, 15);
		payPanel.add(lbCashArrow_1);
		
		JLabel lbCashArrow_2 = new JLabel("");
		lbCashArrow_2.setIcon(new ImageIcon(new ImageIcon(arrImgURL).getImage().getScaledInstance(20, 15, Image.SCALE_DEFAULT)));
		lbCashArrow_2.setBounds(86, 420, 20, 15);
		payPanel.add(lbCashArrow_2);
		
		JLabel lbCashArrow_3 = new JLabel("");
		lbCashArrow_3.setIcon(new ImageIcon(new ImageIcon(arrImgURL).getImage().getScaledInstance(20, 15, Image.SCALE_DEFAULT)));
		lbCashArrow_3.setBounds(130, 420, 20, 15);
		payPanel.add(lbCashArrow_3);
		
		lblCashInserted = new JLabel("Cash Inserted:");
		lblCashInserted.setForeground(Color.ORANGE);
		lblCashInserted.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashInserted.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblCashInserted.setBounds(0, 200, 180, 18);
		payPanel.add(lblCashInserted);
		
		lbInserted_2 = new JLabel("$0.00");
		lbInserted_2.setForeground(Color.WHITE);
		lbInserted_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbInserted_2.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lbInserted_2.setBounds(10, 220, 160, 29);
		payPanel.add(lbInserted_2);
		
		btnCancel_2 = new JButton("Cancel Purchase");
		btnCancel_2.setForeground(Color.RED);
		btnCancel_2.setContentAreaFilled(false);
		btnCancel_2.setBorder(new LineBorder(Color.RED, 3, true));
		btnCancel_2.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnCancel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCancel_2.isEnabled()) {return;}
				btnBuy.setEnabled(false);
				btnCancel_2.setEnabled(false);
				btnBack.setEnabled(false);
				TimerTask resetFrame = new TimerTask() {
					public void run() {
						reset();
					}
				};
				Timer timer = new Timer("Delay");
				timer.schedule(resetFrame, 5000L);
				btnBuy.setEnabled(false);
				btnBuy.setContentAreaFilled(false);
				btnCancel_2.setContentAreaFilled(false);
				btnBuy.setBorder(new LineBorder(Color.GRAY, 3, true));
				btnCancel_2.setBorder(new LineBorder(Color.GRAY, 3, true));
				btnBack.setBorder(new LineBorder(Color.GRAY, 3, true));
				btnBack.setEnabled(false);
				btnCancel_2.setEnabled(false);
				int[] changes = calChange(cashInserted);
				displayChange(changes);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnCancel_2.isEnabled()) {
					btnCancel_2.setBackground(Color.RED);
					btnCancel_2.setContentAreaFilled(true);
					btnCancel_2.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (btnCancel_2.isEnabled()) {
					btnCancel_2.setContentAreaFilled(false);
					btnCancel_2.setForeground(Color.RED);
				}
			}
		});
		btnCancel_2.setBounds(10, 357, 160, 30);
		payPanel.add(btnCancel_2);
		
		btnBuy = new JButton("BUY");
		btnBuy.setForeground(Color.CYAN);
		btnBuy.setBorder(new LineBorder(Color.GRAY, 3, true));
		btnBuy.setContentAreaFilled(false);
		btnBuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnBuy.isEnabled()) {return;}
				else if (cashInserted>=chosenItem.getPrice()) {
					// sufficient cash
					lbCollect.setText(chosenItem.getName().toUpperCase() + " Delievered");
					lbCollect.setForeground(Color.RED);
					panel_10.setBackground(Color.WHITE);
					panel_10.setBorder(new LineBorder(Color.WHITE, 3, true));
					btnBack.setEnabled(false);
					btnCancel_2.setEnabled(false);
					enableCashPanel(false);
					TimerTask resetFrame = new TimerTask() {
						public void run() {
							reset();
						}
					};
					Timer timer = new Timer("Delay");
					timer.schedule(resetFrame, 5000L);
					btnBuy.setEnabled(false);
					btnBuy.setContentAreaFilled(false);
					btnBuy.setBorder(new LineBorder(Color.GRAY, 3, true));
					btnCancel_2.setBorder(new LineBorder(Color.GRAY, 3, true));
					btnBack.setBorder(new LineBorder(Color.GRAY, 3, true));
					int[] changes = calChange(cashInserted-chosenItem.getPrice());
					displayChange(changes);
				}
				else {
					lbInserted_2.setText("INSUFFICIENT");
					lbInserted_2.setForeground(Color.RED);
					lbInserted_2.setFont(new Font("Arial Black", Font.PLAIN, 20));
					TimerTask insufficient = new TimerTask() {
						public void run() {
							lbInserted_2.setText("$"+String.format("%.2f", cashInserted));
							lbInserted_2.setForeground(Color.WHITE);
							lbInserted_2.setFont(new Font("Arial Black", Font.PLAIN, 25));
						}
					};
					Timer timer = new Timer("Delay");
					timer.schedule(insufficient, 3000L);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(cashInserted>=chosenItem.getPrice() && btnBuy.isEnabled()) {
					btnBuy.setBackground(Color.CYAN);
					btnBuy.setContentAreaFilled(true);
					btnBuy.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (cashInserted>=chosenItem.getPrice() && btnBuy.isEnabled()) {
					btnBuy.setContentAreaFilled(false);
					btnBuy.setForeground(Color.CYAN);
				}
			}
		});
		btnBuy.setEnabled(false);
		btnBuy.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnBuy.setBounds(10, 269, 160, 30);
		payPanel.add(btnBuy);
		
		btnBack = new JButton("BACK");
		btnBack.setForeground(Color.ORANGE);
		btnBack.setBorder(new LineBorder(Color.ORANGE, 3, true));
		btnBack.setContentAreaFilled(false);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnBack.isEnabled()) {return;}
				selectPanel.setVisible(true);
				payPanel.setVisible(false);
				label_17.setVisible(true);
				lbInserted_1.setText("$"+String.format("%.2f", cashInserted));
				lbInserted_1.setVisible(true);
				codeCnt = 0;
				code = new ArrayList<>(Arrays.asList(-1,-1,-1));	
				updateCode();
				panel_9.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnBack.isEnabled()) {
					btnBack.setBackground(Color.ORANGE);
					btnBack.setContentAreaFilled(true);
					btnBack.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (btnBack.isEnabled()) {
					btnBack.setContentAreaFilled(false);
					btnBack.setForeground(Color.ORANGE);
				}
			}
		});
		btnBack.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnBack.setBounds(10, 314, 160, 30);
		payPanel.add(btnBack);
		delivPanel.setLayout(null);
		
		panel_10 = new JPanel();
		panel_10.setBackground(Color.ORANGE);
		panel_10.setBorder(new LineBorder(new Color(255, 200, 0), 2, true));
		panel_10.setBounds(14, 30, 372, 45);
		delivPanel.add(panel_10);
		panel_10.setLayout(null);
		
		
		lbCollect = new JLabel("Collect Item Here");
		lbCollect.setBackground(Color.DARK_GRAY);
		lbCollect.setOpaque(true);
		lbCollect.setBounds(4, 4, 364, 37);
		panel_10.add(lbCollect);
		lbCollect.setForeground(Color.WHITE);
		lbCollect.setHorizontalAlignment(SwingConstants.CENTER);
		lbCollect.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		
		JPanel changePanel = new JPanel();
		changePanel.setBounds(402, 652, 180, 100);
		changePanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(changePanel);
		SpringLayout sl_changePanel = new SpringLayout();
		changePanel.setLayout(sl_changePanel);
		
		lblCollectChange = new JLabel("Collect Change:");
		lblCollectChange.setForeground(Color.ORANGE);
		lblCollectChange.setFont(new Font("Arial Black", Font.PLAIN, 15));
		sl_changePanel.putConstraint(SpringLayout.NORTH, lblCollectChange, 0, SpringLayout.NORTH, changePanel);
		sl_changePanel.putConstraint(SpringLayout.WEST, lblCollectChange, 0, SpringLayout.WEST, changePanel);
		lblCollectChange.setVisible(false);
		changePanel.add(lblCollectChange);
		
		JPanel coinPanel = new JPanel();
		coinPanel.setBackground(Color.DARK_GRAY);
		sl_changePanel.putConstraint(SpringLayout.NORTH, coinPanel, 6, SpringLayout.SOUTH, lblCollectChange);
		sl_changePanel.putConstraint(SpringLayout.WEST, coinPanel, 0, SpringLayout.WEST, lblCollectChange);
		sl_changePanel.putConstraint(SpringLayout.SOUTH, coinPanel, 78, SpringLayout.SOUTH, lblCollectChange);
		sl_changePanel.putConstraint(SpringLayout.EAST, coinPanel, 180, SpringLayout.WEST, changePanel);
		changePanel.add(coinPanel);
		
		lblOne = new JLabel("$1: ");
		lblOne.setForeground(Color.WHITE);
		lblOne.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblOne.setVisible(false);
		coinPanel.setLayout(new GridLayout(0, 2, 0, 0));
		coinPanel.add(lblOne);
		
		lblQuarter = new JLabel("$0.25: ");
		lblQuarter.setForeground(Color.WHITE);
		lblQuarter.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblQuarter.setVisible(false);
		coinPanel.add(lblQuarter);
		
		lblDime = new JLabel("$0.1: ");
		lblDime.setForeground(Color.WHITE);
		lblDime.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblDime.setVisible(false);
		coinPanel.add(lblDime);
		
		lblNickel = new JLabel("$0.05: ");
		lblNickel.setForeground(Color.WHITE);
		lblNickel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNickel.setVisible(false);
		coinPanel.add(lblNickel);
		
		lblPenny = new JLabel("$0.01: ");
		lblPenny.setForeground(Color.WHITE);
		lblPenny.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblPenny.setVisible(false);
		coinPanel.add(lblPenny);
		
		JLabel label_14 = new JLabel("");
		label_14.setForeground(Color.WHITE);
		coinPanel.add(label_14);
	}
}
