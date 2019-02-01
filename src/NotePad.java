import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NotePad extends JFrame {

	private JPanel contentPane;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("note.png"));
					NotePad frame = new NotePad();
					frame.setVisible(true);
					frame.setIconImage(icon);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							int returnvalue = JOptionPane.showConfirmDialog(frame, "Before closing, do you want to save your document?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
						    if (returnvalue == JOptionPane.YES_OPTION) {
						    	JFileChooser c = new JFileChooser();
								c.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
								c.setAcceptAllFileFilterUsed(false);
								c.showSaveDialog(scrollPane);
								
								try {
									BufferedWriter bw = new BufferedWriter(new FileWriter(c.getSelectedFile() + ".txt"));
									bw.append(textArea.getText());
									bw.close();
									JOptionPane.showMessageDialog(scrollPane, "Saved correctly!");
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								System.exit(0);
						    	
						    }
						    else if (returnvalue == JOptionPane.NO_OPTION) {
						    	System.exit(0);
						    }
						}
						
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotePad() {
		setTitle("Simple Java Blackboard");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 720, 529);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		mnFile.add(mntmClear);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter txt = new FileNameExtensionFilter("Text File","txt");
				filechooser.addChoosableFileFilter(txt);
				filechooser.setAcceptAllFileFilterUsed(false);
				filechooser.showSaveDialog(scrollPane);
				try {
					BufferedWriter bw = new BufferedWriter (new FileWriter (filechooser.getSelectedFile() + ".txt"));
					bw.append(textArea.getText());
					bw.close();
					JOptionPane.showMessageDialog(scrollPane, "Saved correctly!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
				filechooser.setAcceptAllFileFilterUsed(true);
				filechooser.showOpenDialog(scrollPane);
				try {
					BufferedReader br = new BufferedReader (new FileReader(filechooser.getSelectedFile()));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						textArea.append(line + "\n");	
					}
					br.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmOpen);
		mnFile.add(mntmSave);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textArea.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}				
			}
		});
		mnFile.add(mntmPrint);
		
		JMenu mnFormat = new JMenu("Format");
		mnFormat.setFont(new Font("Dialog", Font.BOLD, 12));
		menuBar.add(mnFormat);
		
		JMenu mnFont = new JMenu("Set Font Size");
		mnFormat.add(mnFont);
		
		JMenuItem menuItem_1 = new JMenuItem("13");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fontstyle = textArea.getFont().getStyle();
				textArea.setFont(new Font("Dialog", fontstyle, 13));
			}
		});
		mnFont.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("14");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fontstyle = textArea.getFont().getStyle();
				textArea.setFont(new Font("Dialog", fontstyle, 14));
			}
		});
		mnFont.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("15");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fontstyle = textArea.getFont().getStyle();
				textArea.setFont(new Font("Dialog", fontstyle, 15));
			}
		});
		mnFont.add(menuItem_3);
		
		JMenu mnSetWrap = new JMenu("Set Wrap");
		mnFormat.add(mnSetWrap);
		
		JMenuItem mntmActivate = new JMenuItem("Activate");
		mntmActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
			}
		});
		mnSetWrap.add(mntmActivate);
		
		JMenuItem mntmDisactivate = new JMenuItem("Deactivate");
		mntmDisactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setLineWrap(false);
				textArea.setWrapStyleWord(false);
			}
		});
		mnSetWrap.add(mntmDisactivate);
		
		JMenuItem mntmSetBold = new JMenuItem("Set Bold");
		mntmSetBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fontsize = textArea.getFont().getSize();
				textArea.setFont(new Font("Dialog", Font.BOLD, fontsize));
			}
		});
		mnFormat.add(mntmSetBold);
		
		JMenuItem mntmSetPlain = new JMenuItem("Set Plain");
		mntmSetPlain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fontsize = textArea.getFont().getSize();
				textArea.setFont(new Font("Dialog", Font.PLAIN, fontsize));
			}
		});
		mnFormat.add(mntmSetPlain);
		
		JMenu mnTheme = new JMenu("Theme");
		menuBar.add(mnTheme);
		
		JMenuItem mntmWhiteTheme = new JMenuItem("White Theme");
		mntmWhiteTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.WHITE);
				scrollPane.setBackground(Color.WHITE);
				textArea.setBackground(Color.WHITE);
				textArea.setForeground(Color.BLACK);
				textArea.setCaretColor(Color.BLACK);
			}
		});
		
		JMenuItem mntmDefaultTheme = new JMenuItem("Default Theme");
		mntmDefaultTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.DARK_GRAY);
				scrollPane.setBackground(Color.DARK_GRAY);
				textArea.setBackground(Color.DARK_GRAY);
				textArea.setForeground(Color.WHITE);
				textArea.setCaretColor(Color.WHITE);
			}
		});
		mnTheme.add(mntmDefaultTheme);
		mnTheme.add(mntmWhiteTheme);
		
		JMenuItem mntmGreenDosTheme = new JMenuItem("Green Dos Theme");
		mntmGreenDosTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(new Color(154, 205, 50));
				scrollPane.setBackground(new Color(154, 205, 50));
				textArea.setBackground(new Color(154, 205, 50));
				textArea.setForeground(new Color(210, 105, 30));
				textArea.setCaretColor(new Color(210, 105, 30));
			}
		});
		mnTheme.add(mntmGreenDosTheme);
		
		JMenuItem mntmLinuxTerminalTheme = new JMenuItem("Linux Terminal Theme");
		mntmLinuxTerminalTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.BLACK);
				scrollPane.setBackground(Color.BLACK);
				textArea.setBackground(Color.BLACK);
				textArea.setForeground(Color.GREEN);
				textArea.setCaretColor(Color.GREEN);
			}
		});
		mnTheme.add(mntmLinuxTerminalTheme);
		
		JMenuItem mntmUbuntuTerminalTheme = new JMenuItem("Ubuntu Terminal Theme");
		mntmUbuntuTerminalTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(new Color(153, 0, 102));
				scrollPane.setBackground(new Color(153, 0, 102));
				textArea.setBackground(new Color(153, 0, 102));
				textArea.setForeground(Color.WHITE);
				textArea.setCaretColor(Color.WHITE);
			}
		});
		mnTheme.add(mntmUbuntuTerminalTheme);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JMenuItem mntmCredits = new JMenuItem("Credits");
		mntmCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(scrollPane, "Simple Java Blackboard v0.02a\nDeveloped by Simone Mallia\nThanks for using my application :)", "Credits", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnInfo.add(mntmCredits);
		
		JMenuItem mntmGoIntoWebsite = new JMenuItem("Go into website");
		mntmGoIntoWebsite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URI url = new URI("https://github.com/simonemallia");
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(url);
					}
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mnInfo.add(mntmGoIntoWebsite);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.DARK_GRAY);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setCaretColor(Color.WHITE);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(null);
		textArea.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(textArea);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);
		
		JMenuItem menuItem = new JMenuItem("Clear");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		popupMenu.add(menuItem);
		
		JMenuItem mntmOpen_1 = new JMenuItem("Open");
		mntmOpen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
				filechooser.setAcceptAllFileFilterUsed(true);
				filechooser.showOpenDialog(scrollPane);
				try {
					BufferedReader br = new BufferedReader (new FileReader(filechooser.getSelectedFile()));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						textArea.append(line + "\n");	
					}
					br.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		popupMenu.add(mntmOpen_1);
		
		JMenuItem mntmSave_1 = new JMenuItem("Save");
		mntmSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter txt = new FileNameExtensionFilter("Text File","txt");
				filechooser.addChoosableFileFilter(txt);
				filechooser.setAcceptAllFileFilterUsed(false);
				filechooser.showSaveDialog(scrollPane);
				try {
					BufferedWriter bw = new BufferedWriter (new FileWriter (filechooser.getSelectedFile() + ".txt"));
					bw.append(textArea.getText());
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		popupMenu.add(mntmSave_1);
		
		JMenuItem mntmPrint_1 = new JMenuItem("Print");
		popupMenu.add(mntmPrint_1);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
