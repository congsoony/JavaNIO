import java.io.*;

public class NonBuffer {
    public static void main(String[] args) {
        try{
            long startTime = System.currentTimeMillis();
            copy("abc.exe","abc2.exe");//적당한 파일크기 10mb이상되는것 아무거나 파일복사
            long endTime =System.currentTimeMillis();
            System.out.println("NonBuffer 처리시간 : "+(endTime-startTime) + " milli seconds");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void copy(String filefrom,String fileTo)throws IOException{
        InputStream in =null;
        OutputStream out = null;
        try{
            in = new FileInputStream(filefrom);
            out  = new FileOutputStream(fileTo);
            while(true){
                int bytedata = in.read();//1byte씩 읽고
                if(bytedata==-1)break;
                out.write(bytedata);//1byte씩 쓰기
            }
        }finally {
            if(in!=null)in.close();
            if(out!=null)out.close();
        }
    }
}
