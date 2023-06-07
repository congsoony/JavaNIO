import java.net.MalformedURLException;
import java.net.URL;

public class URLInfo {
    public static void main(String[] args) {
       if(args.length!=1) {
           System.out.println("사용법 : java WebSpider URL");
           System.exit(1);
       }
        URL url = null;
        try{
            url = new URL(args[0]);
        }catch(MalformedURLException e1){
            System.out.println("잘못된 url 형식");
            System.out.println(e1);
            System.exit(1);
        }
        System.out.println("프로토콜 : "+url.getProtocol());
        System.out.println("호스트명 : "+url.getHost());
        System.out.println("포트번호 : "+url.getPort());
        System.out.println("파일명 : "+url.getPath());
        System.out.println("사용자 쿼리 : "+url.getQuery());
    }
}
