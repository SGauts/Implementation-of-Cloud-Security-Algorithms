package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.util.Scanner;
import javax.xml.crypto.dsig.keyinfo.KeyName;

public class UploadObject {
    
   
	  static String  bucketName     = "encryptstore";
	   static String  keyName; //="he.jpg";
       // static String u2="C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\1.jpg";
	  static String uploadFileName ;//= "C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\New folder\\img1jpg.jpg";
	
            Scanner sc=new Scanner(System.in); 
            
            AWSCredentials credentials = new BasicAWSCredentials(
				"********************",  "****************************************");
       
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

     
     public UploadObject(String k,String u) throws IOException
     {
      keyName=new String(k);
         uploadFileName=new String(u);   
         
          }
          
	void uploadfile()
	{ 
         
            //System.out.println("Enter the name for the object to be stored on cloud: ");
             //keyName=sc.nextLine();
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		try {
            System.out.println("Uploading a new object to S3 from a file\n");
            
           // System.out.println("File to be uploaded: ");
            
            File file = new File(uploadFileName);
            s3client.putObject(new PutObjectRequest(
            		                 bucketName, keyName, file));
            
            System.out.println(uploadFileName+" \nsuccessfully uploaded to bucket: "+bucketName);
            
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
		
		void deletefile()
		{
			AmazonS3 s3client = new AmazonS3Client(credentials);
			 try {
				 s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
		        } catch (AmazonServiceException ase) {
		            System.out.println("Caught an AmazonServiceException.");
		            System.out.println("Error Message:    " + ase.getMessage());
		            System.out.println("HTTP Status Code: " + ase.getStatusCode());
		            System.out.println("AWS Error Code:   " + ase.getErrorCode());
		            System.out.println("Error Type:       " + ase.getErrorType());
		            System.out.println("Request ID:       " + ase.getRequestId());
		        } catch (AmazonClientException ace) {
		            System.out.println("Caught an AmazonClientException.");
		            System.out.println("Error Message: " + ace.getMessage());
		        }
		    }

		void downloadfile(String dname) throws IOException
		{
			AmazonS3 s3client = new AmazonS3Client(credentials);
			try {
	            System.out.println("Downloading an object");
	           /*
                    S3Object s3object = s3client.getObject(new GetObjectRequest(
	            		bucketName, keyName));
	          */
                   
                   
                S3Object fetchFile = s3client.getObject(new GetObjectRequest(bucketName,keyName));
                final BufferedInputStream i = new BufferedInputStream(fetchFile.getObjectContent());
                InputStream objectData = fetchFile.getObjectContent();
                Files.copy(objectData, new File(dname).toPath()); //location to local path
                objectData.close();
                  
                System.out.println("Object Downloaded");
                   
                   
                 /* 
                   System.out.println("Content-Type: "  + getObjectMetadata().getContentType());	 
	            InputStream input = S3Object.getObjectContent();
	            
	           BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	           while (true) {
	               String line = reader.readLine();
	               if (line == null) break;

	               System.out.println("    " + line);
	           }
	           System.out.println();
                   */
	        } 
                        catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which" +
	            		" means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } 
                        catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means"+
	            		" the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
	    }
             
                //UploadObject s3client = new UploadObject(keyName,uploadFileName);
                

	public static void main(String args[]) throws IOException       {
		AWSCredentials Credentials = new BasicAWSCredentials(       "********************",  "****************************************");
	    
                   
                UploadObject u2=new UploadObject("fdnbh.jpg","C:\\Users\\ACER\\Documents\\NetBeansProjects\\JavaApplication17\\New folder\\ga.jpg" );
                 u2.uploadfile();
    
               // System.out.println("upload name:"+args);
	        //s3client.deletefile(Credentials);
	     // s3client.downloadfile(Credentials);

        }                                               
               
                     
}
