package algo;



import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;


public class PropertiesDialog extends JDialog
{
    private Label lblBlockSize = new Label();
    private Label lblKeySize = new Label();
    private Button butOk = new Button();
    private Button butCancel = new Button();
    Choice algorithm = new Choice();
    Choice chKeySize = new Choice();
    public int BLOCK_SIZE=128;
    public int KEY_SIZE=128;

    public PropertiesDialog(javax.swing.JFrame f) 
    {
        super(f,true);
        setResizable(false);
        setSize(300,200);
        algorithm.add("DES");
        algorithm.add("AES");
        algorithm.add("BLOWFISH");
        chKeySize.add("128");
        chKeySize.add("192");
        chKeySize.add("256");
        int x=0,y=0;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        x = d.width /2 - 300/2;
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
        lblBlockSize.setBounds(new Rectangle(23, 17, 65, 29));
        lblBlockSize.setFont(new java.awt.Font("Cambria", 1, 12));
        
        lblBlockSize.setText("Algorithm");
        this.setTitle("Algo Encryption Settings");
        this.getContentPane().setLayout(null);
        lblKeySize.setText("Key Size");
        lblKeySize.setBounds(new Rectangle(22, 54, 65, 29));
        lblKeySize.setFont(new java.awt.Font("Cambria", 1, 12));
        butOk.setBounds(new Rectangle(92, 125, 71, 31));
        butOk.setLabel("OK");
        butOk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button1_actionPerformed(e);
            }
        });
        butCancel.setLabel("Close");
        butCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button2_actionPerformed(e);
            }
        });
        butCancel.setBounds(new Rectangle(173, 126, 71, 31));
        algorithm.setBounds(new Rectangle(94, 20, 111, 21));
        algorithm.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                choice1_itemStateChanged(e);
            }
        });
        chKeySize.setBounds(new Rectangle(93, 62, 111, 22));
        chKeySize.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                choice2_itemStateChanged(e);
            }
        });
        this.getContentPane().add(lblBlockSize, null);
        this.getContentPane().add(lblKeySize, null);
        this.getContentPane().add(butOk, null);
        this.getContentPane().add(butCancel, null);
        this.getContentPane().add(algorithm, null);
        this.getContentPane().add(chKeySize, null);
    }
    
    
    void button1_actionPerformed(ActionEvent e)
    {
        if(algorithm.getSelectedIndex() >0)
            BLOCK_SIZE = 128;
        if(chKeySize.getSelectedIndex() >0)
            KEY_SIZE = Integer.parseInt( chKeySize.getItem(chKeySize.getSelectedIndex() ) );
        this.dispose();
    }


    
    void button2_actionPerformed(ActionEvent e) 
    {
      this.dispose();
    }

    
    void choice1_itemStateChanged(ItemEvent e) 
    {
        BLOCK_SIZE =128;
    }
    
    
    void choice2_itemStateChanged(ItemEvent e) 
    {
        KEY_SIZE = Integer.parseInt( chKeySize.getSelectedItem());
    }
}