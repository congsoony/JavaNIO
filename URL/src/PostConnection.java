import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class PostConnection {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("사용법 : java PostConnection id password");
            System.exit(1);
        }
        try{
            String id = URLEncoder.encode(args[0]);
            String passwd = URLEncoder.encode(args[1]);

            String query = "id="+id+"&passwd="+passwd;

            String u ="http://sunny.sarang.net/jnet/post.php";
            System.out.println(u+query);
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            HttpURLConnection hurlc = (HttpURLConnection) connection;
            hurlc.setRequestMethod("POST");
            hurlc.setDoOutput(true);
            hurlc.setDoInput(true);
            hurlc.setUseCaches(false);
            hurlc.setDefaultUseCaches(false);

            PrintWriter out = new PrintWriter(hurlc.getOutputStream());
            out.println(query);
            out.close();

            BufferedReader in =new BufferedReader(new InputStreamReader(hurlc.getInputStream()));
            String inputLine = null;
            while((inputLine = in.readLine())!=null){
                System.out.println(inputLine);
            }
            in.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
