import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class CharBufferViewTest2 {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(100);

        CharBuffer cbuf = buf.asCharBuffer();

        cbuf.put("Hello World!");
        cbuf.flip(); //limit는 pos위치로 pos =0 으로 mark =-1 로 초기화

        String s = cbuf.toString();
        System.out.println("Data : "+s);
        System.out.println("Buffer Postion : "+cbuf.position());

        int start = 6;
        int end = 12;
        CharSequence sub = cbuf.subSequence(start,end);
        System.out.println(sub.toString());
    }
}
