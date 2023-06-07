import java.io.*;

public class SmallBuffer {
    public static void main(String[] args) {
        try{
            long startTime = System.currentTimeMillis();
            copy("abcd.exe","abcd2.exe");//적당한 파일크기 10mb이상되는것 아무거나 파일복사
            long endTime =System.currentTimeMillis();
            System.out.println("SmallBuffer 처리시간 : "+(endTime-startTime) + " milli seconds");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void copy(String filefrom,String fileTo)throws IOException{
        InputStream inbuffer =null;
        OutputStream outbuffer = null;
        try{
            InputStream in = new FileInputStream(filefrom);
            inbuffer = new BufferedInputStream(in,2048);
            OutputStream out = new FileOutputStream(fileTo);
            outbuffer = new BufferedOutputStream(out,2048);

            while(true){
                int bytedata = inbuffer.read();
                if(bytedata!=-1)break;
                out.write(bytedata);
            }
        }finally {
            if(inbuffer!=null)inbuffer.close();
            if(outbuffer!=null)outbuffer.close();
        }
    }
}
