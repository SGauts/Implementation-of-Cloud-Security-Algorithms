package algo;

 

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;

 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

 

import javax.swing.border.Border;




public class MsgBox extends JDialog
{
    Border border1;
    JButton jbOk = new JButton();
    JTextArea jtaMessage = new JTextArea();
    
    public static void show(String s)
    {
        MsgBox m = new MsgBox();
        m.setSize(340,200);
        m.jtaMessage.setText(s);
        m.setModal(true);
        m.setVisible(true);
    }
    public MsgBox()
    {
        setResizable(false);
        int x=0,y=0;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        x = d.width /2 - 340/2;
        y = d.height /2 - 200/2;
        setLocation( x,y);
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception
    {
        border1 = BorderFactory.createLineBorder(SystemColor.controlText,1);
        this.setTitle("Message ");
        this.getContentPane().setLayout(null);
        jbOk.setText("OK");
        jbOk.setBounds(new Rectangle(107, 122, 90, 25));
        jbOk.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        jtaMessage.setLineWrap(true);
        jtaMessage.setBackground(SystemColor.control);
        jtaMessage.setEditable(false);
        jtaMessage.setBounds(new Rectangle(15, 15, 300, 88));
        this.getContentPane().add(jbOk, null);
        this.getContentPane().add(jtaMessage, null);
    }
}