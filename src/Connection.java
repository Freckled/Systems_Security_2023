import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Connection {
    public ServerSocket serverSocket;
    public Socket socket;

    public Connection() {

    }

    public Socket connect(InetAddress address, int port) throws IOException {
        if (socket != null){
            return socket;
        }

        SocketAddress socketAddress = new InetSocketAddress(address, port);
        SocketAddress localAddress = new InetSocketAddress(address, 4322);
        Socket socket = new Socket();
        //socket.bind(localAddress);
        socket.connect(socketAddress);
        return socket;
    }


    public Socket listen(int port) throws IOException {
        if (serverSocket != null){
            return serverSocket.accept();
        }
        serverSocket = new ServerSocket(port);
        return serverSocket.accept();
        //do stuff
    }

    public void sendMessage(String message, Socket socket) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        dataOut.writeUTF(message);
        dataOut.flush();
    }
}
