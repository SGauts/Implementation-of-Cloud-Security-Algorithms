package algo;

import javax.swing.JProgressBar;


public class Connector
{
    public int BLOCK_SIZE=16;
    public int KEY_SIZE=16;
    JProgressBar pb;

    byte[] buffer ;

    int buffered=0;
    boolean DEB=false;
    Object  sessionKey;
    int mode=-1;
    
    public Connector(JProgressBar pb1)
    {
            pb = pb1;
            pb.setValue(0);
            engineInit();
    }

    
    public byte[] decrypt(byte[] in ) throws Exception
    {
            mode = 1;
            return doFinal( in , 0 , in.length);
    }

    
    public byte[] encrypt(byte[] in ) throws Exception
    {
            mode = 0;
            return doFinal( in , 0 ,  in.length );
    }

    
    public byte[] encrypt(byte[] in, int offset, int length) throws Exception
    {
            mode = 0;
            return doFinal(in, offset, length);
    }
    
    
    public byte[] decrypt(byte[] in, int offset, int length) throws Exception
    {
            mode = 1;
            return doFinal(in, offset, length);
    }
    
    
    public byte[] doFinal (byte[] in, int inOff, int inLen)
    {
            byte[] out = this.doUpdate(in, inOff, inLen);
            return out;
    }

    
    public byte[] doUpdate(byte[] in, int inOff, int inLen)
    {
            byte[] out =  new byte[BLOCK_SIZE * ((inLen + buffered) / BLOCK_SIZE)];
            int outOff = 0;
            byte[] temp = null;
            for (int i = 0; i < inLen; i++)
            {
                    buffer[buffered++] = in[inOff++];
                    if (buffered >= BLOCK_SIZE)
                    {
                            pb.setValue( (inOff/inLen)*100 );
                            if(mode==0)
                                    temp = Algorithm.blockEncrypt(buffer, 0, sessionKey, BLOCK_SIZE);
                            else
                                    temp = Algorithm.blockDecrypt(buffer, 0, sessionKey, BLOCK_SIZE);
                            engineInit();
                            System.arraycopy(temp, 0, out, outOff, temp.length);
                            if(DEB)
                                    System.out.println(":" + new String(temp));
                            outOff += temp.length;
                    }
            }
            return out;
    }

    
    private void engineInit()
    {
            if(DEB)
                    System.out.println("Eng INIT");
            buffer = new byte[ BLOCK_SIZE] ;
            for (int i = 0; i < BLOCK_SIZE; )
                    buffer[i++] = 0;
            buffered = 0;
    }

    
    public void setKey(byte[] key) throws Exception
    {
            if( !(key.length ==16 || key.length== 24 || key.length== 32) )
                    throw new IllegalArgumentException("Invalid Key");
            sessionKey = Algorithm.makeKey(key);
    }
}
