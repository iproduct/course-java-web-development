package chat.multicasting;

// ChatGUI.java
// The GUI for the chat client
// (c) Copyright IPT - Intellectual Products & Technologies Ltd., 2004-2006.
// All rights reserved. This software program can be compiled and modified only as a part of the 
// "Programming in Java" course provided by IPT - Intellectual Products & Technologies Ltd.,
// for educational purposes only, and provided that this copyright notice is kept unchanged 
// with the program. The program is provided "as is", without express or implied warranty of any 
// kind, including any implied warranty of merchantability, fitness for a particular purpose or 
// non-infringement. Should the Source Code or any resulting software prove defective, the user
// assumes the cost of all necessary servicing, repair, or correction. In no event shall 
// IPT - Intellectual Products & Technologies Ltd. be liable to any party under any legal theory 
// for direct, indirect, special, incidental, or consequential damages, including lost profits, 
// business interruption, loss of business information, or any other pecuniary loss, or for
// personal injuries, arising out of the use of this source code and its documentation, or arising 
// out of the inability to use any resulting program, even if IPT - Intellectual Products & 
// Technologies Ltd. has been advised of the possibility of such damage. 
// Contact information: www.iproduct.org, e-mail: office@iproduct.org 

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ChatGUI extends JFrame {
  private ChatClientInterface client;
  private JLabel jl1 = new JLabel("Messages:");
  private JTextArea jta1 = new JTextArea(15, 40);
  private JLabel jl2 = new JLabel("Input your message:");
  private JTextArea jta2 = new JTextArea(2, 40);
  private JButton
    b = new JButton("Send Message"),
    c = new JButton("Clear Message");
  public ChatGUI(ChatClientInterface cl) {
	client = cl;
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  if (client.getUID() == null)
    		  new ChatNameDialog(client);
    	  client.newMessageEntered(jta2.getText());
          jta2.setText("");
      }
    });
    c.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jta2.setText("");
      }
    });
    Box box = Box.createVerticalBox();
    Box box2 = Box.createHorizontalBox();
    box2.add(Box.createRigidArea(new Dimension(10,30)));
    box2.add(jl1);
    box2.add(Box.createHorizontalGlue());
    box.add(box2);
    jta1.setEditable(false);
    box.add(new JScrollPane(jta1));

    Box box3 = Box.createHorizontalBox();
    box3.add(Box.createRigidArea(new Dimension(10,30)));
    box3.add(jl2);
    box3.add(Box.createHorizontalGlue());
    box.add(box3);
    box.add(new JScrollPane(jta2));

    Box box4 = Box.createHorizontalBox();
    box4.add(Box.createRigidArea(new Dimension(100,80)));
    box4.add(Box.createHorizontalGlue());
    box4.add(b);
    box4.add(Box.createHorizontalGlue());
    box4.add(c);
    box4.add(Box.createHorizontalGlue());
    box4.add(Box.createRigidArea(new Dimension(100,80)));
    box.add(box4);
    Container cp = getContentPane();
    cp.add(box);
    setSize(700, 500);
    jta2.addKeyListener(new KeyAdapter() {
    	public void keyReleased(KeyEvent ke) {
    		if (ke.getKeyCode() == 10 ) {
    			System.out.println(ke);
    			 if (client.getUID() == null)
    	    		  new ChatNameDialog(client);
    	    	  client.newMessageEntered(jta2.getText());
    	          jta2.setText("");
    		}
    	}
    });
  }
  public void addMessage(String message) {
	  jta1.append(message+"\n");
  }
}