import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class WinChatClient extends Frame implements ActionListener {
    private TextField idTF = null;
    private TextField input = null;
    private TextArea display =null;
    private CardLayout cardLayout = null;

    private BufferedReader br = null;
    private PrintWriter pw = null;
    private Socket sock = null;

    public WinChatClient(String ip) {
        super("채팅 클라이언트");
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        Panel loginPanel = new Panel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add("North",new Label("아이디를 입력하여 주신 후 엔터 키를 입력해서 주세요"));
        idTF = new TextField(20);
        idTF.addActionListener(this);
        Panel c =new Panel();
        c.add(idTF);

        loginPanel.add("Center",c);
        add("login",loginPanel);
        Panel main = new Panel();
        main.setLayout(new BorderLayout());
        input = new TextField();
        input.addActionListener(this);
        display = new TextArea();
        display.setEditable(false);
        main.add("Center",display);
        main.add("South",input);
        add("main",main);

        try{
            sock = new Socket(ip,10001);
            pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }catch (Exception ex){
            System.out.println("서버와 접속시 오류가 발생");
            System.out.println(ex);
            System.exit(1);
        }

        setSize(500,500);
        cardLayout.show(this,"login");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pw.println("/quit");
                pw.flush();

                try{
                    sock.close();
                }catch (Exception ex){

                }
                System.out.println("종료합니다.");
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("사용법 : java WinChatClient ip");
            System.exit(1);
        }
        new WinChatClient(args[0]);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == idTF){
            String id = idTF.getText();
            if(id==null || id.trim().equals("")){
                System.out.println("아이디를 다시 입력해서 주세요");
                return;
            }
            pw.println(id.trim());
            pw.flush();
            WinInputThread wit = new WinInputThread(sock,br);
            wit.start();
            cardLayout.show(this,"main");
            input.requestFocus();
        }else if(e.getSource()==input){
            String msg = input.getText();
            pw.println(msg);
            pw.flush();
            if(msg.equals("/quit")){
                try{
                    sock.close();
                }catch (Exception ex){}
                System.out.println("종료합니다. ");
                System.exit(1);
            }
            input.setText("");
            input.requestFocus();
        }
    }
    class WinInputThread extends Thread{
        private Socket sock =null;
        private BufferedReader br = null;

        public WinInputThread(Socket sock, BufferedReader br) {
            this.sock = sock;
            this.br = br;
        }

        @Override
        public synchronized void start() {
            try{
                String line =null;
                while((line=br.readLine())!=null){
                    display.append(line+"\n");
                }
            }catch (Exception ex){

            }finally {
                try{
                    if(br!=null)br.close();
                }catch (Exception ex){}
                try{
                    if(sock!=null)sock.close();
                }catch (Exception ex){}

            }
        }
    }
}
