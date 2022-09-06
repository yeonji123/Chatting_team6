
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class ServerExample4 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9002);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("클라이언트 연결 접수됨..");
                
//                BufferedReader bufferR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String nickName = bufferR.readLine();
//                
//                System.out.println("연결한 클라이언트 닉: " + nickName);
//                System.out.println("연결한 클아이언트 소켓: " + socket);
                
                Thread thread = new PerClinetThread(socket);
                thread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
