import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) {
        FileChannel channel = null;
        try{
            File file = new File("C:/test.doc");
            channel = new RandomAccessFile(file,"rw").getChannel();
            FileLock lock = channel.lock(0,Long.MAX_VALUE,true);
            try{
                boolean isShared = lock.isShared();
                System.out.println("Is shared lock? : "+isShared);
            }finally {
                lock.release();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(channel!=null){
                try{
                    channel.close();
                }catch (IOException ex){
                    
                }
            }
        }
    }
}
