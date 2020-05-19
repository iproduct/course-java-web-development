package chat.multicasting;

// ChatNameDialog.java
// Dialog for entering the nam of the user in the chat
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

import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ChatNameDialog extends JDialog 
{
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JButton jButton1 = new JButton();
  private final ChatClientInterface client;

  public ChatNameDialog(ChatClientInterface cl)
  {
    this(cl, null, "", true);
  }

  public ChatNameDialog(ChatClientInterface cl, Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
	client = cl; 
    try
    {
    	   this.setSize(new Dimension(400, 252));
    	    this.getContentPane().setLayout(null);
    	    this.setTitle("Enter User Name Dialog");
    	    jLabel1.setText("Type your name in the chat:");
    	    jLabel1.setBounds(new Rectangle(50, 45, 320, 25));
    	    jTextField1.setBounds(new Rectangle(55, 80, 255, 25));
    	    jButton1.setText("Go");
    	    jButton1.setBounds(new Rectangle(140, 135, 100, 25));
    	    jButton1.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	      	  client.setUID(getUserName());
    	      	  dispose();
    	        }
    	      });
    	    this.getContentPane().add(jButton1, null);
    	    this.getContentPane().add(jTextField1, null);
    	    this.getContentPane().add(jLabel1, null);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    setVisible(true);
  }

  public String getUserName()
  {
    return jTextField1.getText();
  }
}