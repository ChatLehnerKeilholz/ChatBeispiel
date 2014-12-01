package chat;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class GUIChat extends JFrame implements ActionListener{

	//Attribute
	private JButton btnSenden;
	private JRadioButton rdbtnWortkorrektur;
	private JTextArea textAreaOben, textAreaUnten;
	private JPanel contentPane;
	private Date datum;
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	private String chatname, hostname;
	private ClientChat cc;

	/**
	 * Create the frame.
	 */
	public GUIChat() {
		
		chatname = JOptionPane.showInputDialog(null,"Bitte geben Sie Ihren Chatnamen an:");
		hostname = JOptionPane.showInputDialog(null,"Hostname des Empfängers:");
		cc = new ClientChat(hostname, chatname);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textAreaOben = new JTextArea();
		textAreaOben.setBounds(10, 11, 424, 172);
		textAreaOben.setEditable(false);
		contentPane.add(textAreaOben);
		
		textAreaUnten = new JTextArea();
		textAreaUnten.setBounds(10, 193, 286, 67);
		contentPane.add(textAreaUnten);
		
		btnSenden = new JButton("Senden");
		btnSenden.setBounds(306, 220, 128, 40);
		contentPane.add(btnSenden);
		
		rdbtnWortkorrektur = new JRadioButton("Wortkorrektur");
		rdbtnWortkorrektur.setBounds(303, 192, 109, 23);
		btnSenden.addActionListener(this);
		contentPane.add(rdbtnWortkorrektur);
		
		setResizable(false);
		setTitle("Chat (C) Keilholz & Lehner 2014");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSenden){
			String s = textAreaUnten.getText();
			datum = new Date();
			textAreaOben.append("(" + df.format(datum) + ") "+ chatname + ": " + s + "\n");
			cc.writeToSocket(s);
			textAreaUnten.setText("");
		}
	}
	
	public void setTextArea(String s){
		textAreaOben.append("(" + df.format(datum) + ") " + s + "\n");
	}
}
