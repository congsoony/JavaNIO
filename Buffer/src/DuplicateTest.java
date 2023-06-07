import java.nio.ByteBuffer;

public class DuplicateTest {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(10);
        for(int i=0;i<10;i++)buf.put((byte)i);
        buf.position(3);
        buf.limit(9);
        buf.mark();

        ByteBuffer buf2 = buf.duplicate();
        System.out.println("1) Position: "+buf2.position() + ", Limit: "+buf2.limit()+", Capacity: "+buf2.capacity());
        buf2.position(7);
        buf2.reset();
        System.out.println("reset() 호출 후 Position : "+buf2.position());

        buf2.clear();
        System.out.println("2) Position: "+buf2.position() + ", Limit: "+buf2.limit()+", Capacity: "+buf2.capacity());

        while(buf2.hasRemaining()){
            System.out.print(buf2.get()+" ");
        }
        buf.put(0,(byte) 10);
        System.out.println("\n"+" => buf의 0값을 10으로 바꿈");

        System.out.println("Original Buffer Value : "+buf.get(0));
        System.out.println("DuplicateBuffer Value : "+buf2.get(0));

        buf.put(1,(byte)11);
        System.out.println("=> buf2의 1값을 12로 바꿈");
        System.out.println("Original Buffer Value : "+buf.get(1));
        System.out.println("DuplicateBuffer Value : "+buf2.get(1));

    }
}
