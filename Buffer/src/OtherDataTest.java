import java.nio.Buffer;
import java.nio.ByteBuffer;

public class OtherDataTest {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(10);
        print(buf);
        buf.putInt(100);
        print(buf);
    }
    public static void print(Buffer buf){
        System.out.println("position= "+buf.position() + ", limit= "+buf.limit()+ ", capacity= "+buf.capacity());
    }
}
