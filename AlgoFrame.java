package algo;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import database_encr.fetch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
 
                          
public class AlgoFrame extends JFrame
{
    String key;

    
    
    String  bucketName     = "encryptstore";
	  String  keyName="";
         
	  String uploadFileName = new String(); // "C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\New folder\\img1jpg.jpg";
	
            Scanner sc=new Scanner(System.in); 
            
            AWSCredentials credentials = new BasicAWSCredentials(
				"********************",  "****************************************");
       
            
    String initDir = null;
    String output_path= new String();
    private JPanel jPanel1 = new JPanel();
    JTextField jtfInputFile = new JTextField();
    JTextField jtfOutputFile = new JTextField();
    private JLabel jlInFile = new JLabel();
    private JLabel jlOutFile = new JLabel();
    private JButton jbInFile = new JButton();
    private JButton jbOutFile = new JButton();
    private JButton jbProperties = new JButton();
    private JButton jbOk = new JButton();
    private JButton jbExit = new JButton();
    private JButton jbCancel = new JButton();
    private Border border1;
    private Border border2;
    private Border border3;
    private Border border4;
    private Border border5;
    private Border border6;
    private Border border7;
    private Border border8;
    private CheckboxGroup cbgOperation = new CheckboxGroup();
    Checkbox cbEncrypt = new Checkbox();
    Checkbox cbDecrypt = new Checkbox();
    int BLOCK_SIZE=128;
    int KEY_SIZE=128;
    JProgressBar pbPosition = new JProgressBar();
    
        public AlgoFrame()
    {
        pbPosition.setVisible(false);
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args)
    {
        AlgoFrame algoFrame1 = new AlgoFrame();
        algoFrame1.setSize(550,400);
        algoFrame1.setResizable(false);
        algoFrame1.setTitle("Algo..." );
        int x=0,y=0;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        x = d.width /2 - 550/2;
        y = d.height /2 - 400/2;
        algoFrame1.setLocation( x,y);
        algoFrame1.show();
    }

    
    private void jbInit() throws Exception
    {
        border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
        border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(142, 142, 142));
        border3 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(148, 145, 140),new Color(103, 101, 98));
        border4 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
        border5 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
        border6 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
        border7 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(142, 142, 142),new Color(99, 99, 99));
        border8 = BorderFactory.createLineBorder(SystemColor.windowBorder,2);
        jPanel1.setBackground(SystemColor.control);
        jPanel1.setForeground(Color.red);
        jPanel1.setBorder(border8);
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);
        jtfInputFile.setFont(new java.awt.Font("SansSerif", 1, 12));
        jtfInputFile.setCaretColor(Color.red);
        jtfInputFile.setText("Select Input File");
        jtfInputFile.setBounds(new Rectangle(108, 57, 200, 30));
        jtfOutputFile.setFont(new java.awt.Font("SansSerif", 1, 12));
        jtfOutputFile.setCaretColor(Color.red);
        jtfOutputFile.setText("Select Output File");
        jtfOutputFile.setBounds(new Rectangle(107, 117, 203, 30));
        jbInFile.setBorder(border1);
        jbInFile.setToolTipText("Select In File");
        jbInFile.setText("...");
        jbInFile.setBounds(new Rectangle(423, 56, 79, 27));
        jbInFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                inFileActionPerformed(e);
            }
        });
        jbOutFile.setBounds(new Rectangle(423, 121, 79, 27));
        jbOutFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                outFileActionPerformed(e);
            }
        });
        jbOutFile.setBorder(border7);
        jbOutFile.setToolTipText("Select  Out File");
        jbOutFile.setText("...");
        jbProperties.setFont(new java.awt.Font("SansSerif", 1, 10));
        jbProperties.setBorder(border2);
        jbProperties.setText("Adjust Properties");
        jbProperties.setBounds(new Rectangle(388, 166, 113, 30));
        jbProperties.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                propertiesActionPerformed(e);
            }
        });
        jlInFile.setForeground(Color.black);
        jlInFile.setText("Input");
        jlInFile.setBounds(new Rectangle(8, 60, 96, 28));
        jlOutFile.setForeground(Color.black);
        jlOutFile.setText("Output");
        jlOutFile.setBounds(new Rectangle(11, 126, 92, 22));
        jbOk.setBorder(border4);
        jbOk.setText("OK");
        jbOk.setBounds(new Rectangle(81, 285, 86, 28));
        jbOk.addActionListener(new java.awt.event.ActionListener()
        {
    public void actionPerformed(ActionEvent e) {
    okActionPerformed(e);
    }
    });
    jbExit.setBounds(new Rectangle(294, 286, 86, 28));
    jbExit.addActionListener(new java.awt.event.ActionListener() {

    public void actionPerformed(ActionEvent e) {
    exitActionPerformed(e);
    }
    });
    jbExit.setBorder(border6);
    jbExit.setText("Exit");
    jbCancel.setBounds(new Rectangle(188, 285, 86, 28));
    jbCancel.addActionListener(new java.awt.event.ActionListener() {

    public void actionPerformed(ActionEvent e) {
    cancelActionPerformed(e);
    }
    });
    jbCancel.setBorder(border5);
    jbCancel.setText("Cancel");
    cbEncrypt.setBackground(SystemColor.control);
    cbEncrypt.setBounds(new Rectangle(124, 195, 79, 21));
    cbEncrypt.setFont(new java.awt.Font("Serif", 1, 12));
    cbEncrypt.setForeground(Color.black);
    cbEncrypt.setCheckboxGroup(cbgOperation);
    cbEncrypt.setLabel("Encrypt");
    cbEncrypt.setState(true);
    cbDecrypt.setBackground(SystemColor.control);
    cbDecrypt.setBounds(new Rectangle(124, 224, 80, 23));
    cbDecrypt.setFont(new java.awt.Font("Serif", 1, 12));
    cbDecrypt.setForeground(Color.black);
    cbDecrypt.setCheckboxGroup(cbgOperation);
    cbDecrypt.setLabel("Decrypt");
    this.setResizable(false);
    pbPosition.setBackground(Color.white);
    pbPosition.setForeground(Color.red);
    pbPosition.setStringPainted(true);
    pbPosition.setBounds(new Rectangle(162, 317, 148, 22));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jbInFile, null);
    jPanel1.add(jtfInputFile, null);
    jPanel1.add(jtfOutputFile, null);
    jPanel1.add(jbOutFile, null);
    jPanel1.add(jlInFile, null);
    jPanel1.add(jlOutFile, null);
    jPanel1.add(jbProperties, null);
   // jPanel1.add(jbCancel, null);
    jPanel1.add(jbExit, null);
    jPanel1.add(jbOk, null);
    jPanel1.add(cbEncrypt, null);
    jPanel1.add(cbDecrypt, null);
    jPanel1.add(pbPosition, null);
    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    SwingUtilities.updateComponentTreeUI(this);

    }

    
    private void okActionPerformed(ActionEvent ae)
    {
                       pbPosition.setValue(0);
            algo.PasswordDialog d = new algo.PasswordDialog(this) ;
            d.show();
            String pass="0";
            if(!d.cancel)
                    pass = d.tfPassword.getText() ;
            else
                    return;
            for(int i=pass.length() ; i < KEY_SIZE/8;i++ )
                    pass += "0";
            pbPosition.setVisible(true);
            algo.Connector spi = new algo.Connector(pbPosition);
            try
            {
                    spi.setKey(pass.getBytes());
                    File f = new File(jtfInputFile.getText());
                    
                    byte[] in = null;
                    byte pad=0;
                    if(cbEncrypt.getState())
                    {
            
                            if(f.length() % BLOCK_SIZE != 0)
                            {
                                    pad = (byte) (BLOCK_SIZE - (f.length() % BLOCK_SIZE));
                                    in = new byte[(int) f.length() + pad +1];
                                    FileInputStream fin = new FileInputStream(f);
                                    fin.read(in , 1 , (int)f.length());
                                    fin.close();
                            }
                            else
                            {
                                    in = new byte[(int) f.length() + 1];
                                    FileInputStream fin = new FileInputStream( f);
                                    fin.read( in,1,(int)f.length());
                                    fin.close();
                            }
                            byte[] out = spi.encrypt(in, 1, in.length-1);
                            byte[] out1 = new byte[out.length + 1];
                            System.arraycopy(out,0,out1,1,out.length);
                            out1[0] = pad;
                            f = new File(jtfOutputFile.getText());
                            FileOutputStream fout = new FileOutputStream(f);
                            fout.write(out1);
                               fout.close();
                           output_path=jtfOutputFile.getText();
                           
                          
                           fetch obj=new fetch();
                          //System.out.println("Enter the path of file to be uploaded"); 
                          //output_path=sc.nextLine();
                         keyName= obj.data_entry(output_path); //"j.jpg";
                           //UploadObject u2=new UploadObject("fdnbh.jpg","C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\New folder\\ga.jpg" );
                           UploadObject u2=new UploadObject(keyName,output_path);
                           
                           u2.uploadfile();
                    
                           System.out.println("key="+keyName+" filename= "+output_path);
                           //UploadObject obj2= new UploadObject();
                           //obj2.uploadfile("jn","C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\1.jpg");
                           //System.out.println(u.uploadFileName+ " will be saved as \n "+u.keyName+" in the bucket");
            
                          
                               
                   }

                        else
                        {

                            jtfInputFile.setText(output_path);
                            System.out.println("Enter the path of the file to be downloaded");
                            String output_path_dec=sc.nextLine();
                            
                            
                            UploadObject u1=new UploadObject(keyName,output_path_dec);
                           u1.downloadfile(output_path_dec);
 
                    jtfOutputFile.setText(output_path_dec);
                           
                             
                                in = new byte[(int)f.length()];
                                FileInputStream fin = new FileInputStream( f);
                                fin.read( in);
                                pad = in[0];
                byte[] in1 = new byte[ in.length - 1];
                System.arraycopy(in,1,in1,0,in1.length );
                byte[] out = spi.decrypt(in1);
                byte[] out1 = new byte[ out.length-pad];
                System.arraycopy(  out , 0,out1,0,out1.length );
                f = new File( jtfOutputFile.getText());
                FileOutputStream fout = new FileOutputStream(f);
                fout.write( out1);
                fout.close();
              }
              algo.MsgBox.show("Operation Completed");

                      }catch(Exception e1){
                 algo.MsgBox.show("ERROR :" + e1.getMessage());
                 e1.printStackTrace();
             }
              pbPosition.setVisible(false);
          }

        public String fetch_data()
        {

            return output_path;

        }


        private void cancelActionPerformed(ActionEvent ae)
        {

        }


        private void exitActionPerformed(ActionEvent ae)
        {
            System.exit(0);
        }


        private void propertiesActionPerformed(ActionEvent ae)
        {
            algo.PropertiesDialog d = new algo.PropertiesDialog(this);
            d.setVisible(true);
            BLOCK_SIZE = d.BLOCK_SIZE ;
            KEY_SIZE = d.KEY_SIZE ;
        }


        private void inFileActionPerformed(ActionEvent ae)
        {   
            if(initDir == null)
                initDir = System.getProperty("user.dir","C:\\");
            javax.swing.JFileChooser f = new javax.swing.JFileChooser();
            f.setCurrentDirectory(new File(initDir));
            f.showOpenDialog( this);
            initDir = f.getCurrentDirectory().getAbsolutePath();
            jtfInputFile.setText( f.getSelectedFile().getAbsolutePath());
        }


        private void outFileActionPerformed(ActionEvent ae)
        {
            if(initDir == null)
                initDir = System.getProperty("user.dir","C:\\");
            javax.swing.JFileChooser f = new javax.swing.JFileChooser();
            f.setCurrentDirectory(new File(initDir));
            f.showSaveDialog(this);
            initDir = f.getCurrentDirectory().getAbsolutePath();
            jtfOutputFile.setText(f.getSelectedFile().getAbsolutePath());
        }






                /*
                public void set_uploadFileName(String u)
            {
                uploadFileName=u;
            }
            public void set_keyName(String u)
            {
                keyName=u;
            }
            */
         /*
         public UploadObject()
         {

         }
           */     
            void uploadfile() throws InterruptedException
            {
                 //System.out.println("Enter the name for the object to be stored on cloud: ");
                 //keyName=sc.nextLine();
                    AmazonS3 s3client = new AmazonS3Client(credentials);

                    try {
                //System.out.println("Preparing Upload to S3 from a file\n");

                    //    TimeUnit.SECONDS.sleep(3);
                System.out.println("Uploading a new object to S3 from a file\n");
              //  output_path="C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\1.jpg";
                File file = new File(output_path);
                System.out.println("File to be uploaded: "+output_path);
                s3client.putObject(new PutObjectRequest(
                                             bucketName, keyName, file));

                System.out.println(output_path+" \nsuccessfully uploaded to bucket: "+bucketName);

            }
                   
                    catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which " +
                            "means your request made it " +
                        "to Amazon S3, but was rejected with an error response" +
                        " for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            } 
                    catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, which " +
                            "means the client encountered " +
                        "an internal error while trying to " +
                        "communicate with S3, " +
                        "such as not being able to access the network.");
     System.out.println("Error Message:            System.out.println(\"Error Type:       \" + ase.getErrorType());\n" +
    " " + ace.getMessage());
            }
                    
        }
          
    }
