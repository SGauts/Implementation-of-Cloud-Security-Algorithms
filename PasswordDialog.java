
package algo;

 

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;

 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PasswordDialog extends JDialog
{
    private JLabel jlPassword = new JLabel();
    private JLabel jlConfirmPassword = new JLabel();
    private Button butOk = new Button();
    private Button butCancel = new Button();
    public TextField tfPassword = new TextField();
    public TextField tfConfirmPassword = new TextField();
    public boolean cancel = true;

    public PasswordDialog(JFrame f)
    {
    super(f,true) ;
    setResizable(false);
    setSize(300,200);
    int x=0,y=0;
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
x = d.width /2 - 300/2;
y = d.height /2 - 200/2;

setLocation( x,y);
try {
  jbInit();
}
catch(Exception e) {
  e.printStackTrace();
}
}
 
        
    private void jbInit() throws Exception
    {
    this.getContentPane().setLayout(null);
    jlPassword.setText("Password");
    jlPassword.setBounds(new Rectangle(37, 23, 72, 25));
    jlConfirmPassword.setBounds(new Rectangle(11, 52, 116, 31));
    jlConfirmPassword.setText("Confirm Password");
    butOk.setBounds(new Rectangle(65, 98, 57, 21));
    butOk.setLabel("OK");
    butOk.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        butOkActionPerformed(e);
      }
    });
    butCancel.setBounds(new Rectangle(134, 98, 69, 20));
    butCancel.setLabel("Cancel");
    butCancel.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        butCancelActionPerformed(e);
      }
    });
    tfPassword.setBackground(Color.white);
    tfPassword.setBounds(new Rectangle(125, 26, 153, 21));
    tfPassword.setEchoChar('?');
    tfConfirmPassword.setBackground(SystemColor.controlLtHighlight);
    tfConfirmPassword.setBounds(new Rectangle(125, 55, 153, 21));
    tfConfirmPassword.setEchoChar('?');
    this.setTitle("JFSecurity Password");
    this.getContentPane().add(jlPassword, null);
    this.getContentPane().add(jlConfirmPassword, null);
    this.getContentPane().add(butOk, null);
    this.getContentPane().add(butCancel, null);
    this.getContentPane().add(tfPassword, null);
    this.getContentPane().add(tfConfirmPassword, null);
    }

    
    private void butOkActionPerformed(ActionEvent e)
    {
        if( tfPassword.getText().compareTo(tfConfirmPassword.getText())==0)
        {
            cancel = false;
            setVisible(false);
            return;
        }
        algo.MsgBox.show("Invalid Confirmation");
        tfConfirmPassword.selectAll();
        cancel = true;
    }
    
    
    private void butCancelActionPerformed(ActionEvent e)
    {
        tfPassword.setText("");
        tfConfirmPassword.setText("");
        cancel = true;
        dispose();
    }
}