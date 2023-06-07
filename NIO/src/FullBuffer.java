import java.io.*;

public class FullBuffer
{
    public static void main(String[] args) {
        try{
            long startTime = System.currentTimeMillis();
            copy("abcd.exe","abcd2.exe");//적당한 파일크기 10mb이상되는것 아무거나 파일복사
            long endTime =System.currentTimeMillis();
            System.out.println("FullBuffer 처리시간 : "+(endTime-startTime) + " milli seconds");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void copy(String filefrom,String fileTo)throws IOException{
        InputStream in = null;
        OutputStream out = null;
        try{
            in = new FileInputStream(filefrom);
            out = new FileOutputStream(fileTo);

            int availableLength = in.available();
            byte[] buffer = new byte[availableLength];
            System.out.println("버퍼 사이즈 : "+availableLength);
            int bytedata = in.read(buffer);
            out.write(buffer);
        }finally {
            if(in!=null)in.close();
            if(out!=null)out.close();
        }
    }
}
