/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_encr;
import java.util.Date;
import algo.AlgoFrame;
import algo.UploadObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;  
import java.text.SimpleDateFormat;

public class fetch{
    String path_file,file_name;
public String data_entry(String s) throws FileNotFoundException, IOException, SQLException{  

int i=0,count=0;
AlgoFrame obj= new AlgoFrame();

    try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");  

Statement fetch1=con.createStatement();  
ResultSet rs2=fetch1.executeQuery("select * from filestorage");  
while(rs2.next())  
{
 //System.out.println(rs2.getTimestamp(1)+"  "+rs2.getString(2)+"  "+rs2.getString(3));  
}
//insert data
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from filestorage ");  


path_file=s;

        String[] split = s.split("\\\\");
        file_name=split[split.length-1];
        
String timeStamp;
    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

PreparedStatement ps=con.prepareStatement("insert into filestorage values(?,?,?)");
ps.setString(1,timeStamp);
ps.setString(3,path_file);
ps.setString(2,file_name);


//FileInputStream fin=new FileInputStream("d:\\c3.jpg");  
//ps.setBinaryStream(2,fin,fin.available());  
 i=ps.executeUpdate();  
count+=i;
//fin=new FileInputStream("d:\\c2.jpg");  
//ps.setBinaryStream(2,fin,fin.available());  






System.out.println("Database Updated");


// retreival image


/*
PreparedStatement ret=con.prepareStatement("select * from emp");  
ResultSet rs2=ret.executeQuery();  
int j=0;
FileOutputStream fout= new FileOutputStream("d:\\sonoo.jpg");
while(rs2.next())
{
    
//if(rs2.next())//now on 1st row  
              
Blob b=rs2.getBlob(2);//2 means 2nd column data  
byte barr[]=b.getBytes(1,(int)b.length());//1 means first image  
              
fout=new FileOutputStream("d:\\"+rs2.getString(1)+".jpg");  
j++;
fout.write(barr);  


fout.close();  

}
   */
//System.out.print(j+" records retrived");
con.close();
    
}catch(Exception e){ System.out.println(e);}  
/*
   if(count==1)
      {
System.out.println(count+" record updated");
      }
      else if(i==0)
      {
System.out.println("No record affected");
          
      }
      else if(i>1)
      {
          System.out.println(count+" records affected");
      }
count=0;   
*/
return file_name;
}



    public static void main(String[] args) throws IOException, FileNotFoundException, SQLException {
        fetch obj=new fetch();
        
//        obj.data_entry();
    }

     }
