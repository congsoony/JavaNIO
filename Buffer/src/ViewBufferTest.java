import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {
    public static void main(String[] args) {

        ByteBuffer buf = ByteBuffer.allocate(10);

        IntBuffer ib =buf.asIntBuffer();
        System.out.println("Position= " + ib.position()+ ", limit="+ib.limit()+", capacity= "+ib.capacity());
        ib.put(256).put( 256);

        System.out.println("index_0="+ib.get(0)+", index_1="+ib.get(1));

        while(buf.hasRemaining()){
            System.out.print(buf.get()+" ");
        }

    }
}
