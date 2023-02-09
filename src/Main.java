import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Connection connection;
        Integer port = 4321;
        String response = "";
        String query = "";

        CommandHandler commandHandler = new CommandHandler();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Choose your host: 1-Client, 2-Server, 3-Exit");

        String input = reader.readLine();
        connection = new Connection();


        InetAddress localIP = InetAddress.getLocalHost();
        SocketAddress socketAddress = new InetSocketAddress(localIP, 4322);

        switch(input){
            case "1":
                Socket socket = new Socket();
                socket.connect(socketAddress);
                System.out.println("Knock knock");
                connection.sendMessage("Knock knock", socket);

                while (!query.equals("done")){
                    //connection.listen(port);
                    DataInputStream dataIn2 = new DataInputStream(socket.getInputStream());
                    query = dataIn2.readUTF();
                    if(!query.equals("done")) {
                        System.out.println(query);
                        response = commandHandler.response(query);
                        System.out.println(response);
                        connection.sendMessage(response, socket);
                    }
                }

                break;
            case "2":
                ServerSocket serverSocket = new ServerSocket(4322);

                while (true) {
                    try{
                        //System.out.println("waiting");
                        Socket respSocket = serverSocket.accept();

                        while (query != "done") {
                            DataInputStream dataIn1 = new DataInputStream(respSocket.getInputStream());
                            query = dataIn1.readUTF();
                            System.out.println(query);
                            response = commandHandler.response(query);
                            System.out.println(respSocket.isClosed());
                            connection.sendMessage(response, respSocket);
                        }
                        query = "";
                    }catch(SocketException e){

                    }
                }

                //break;
            case "3":
                break;

            default:
                System.out.println("invalid choice");

        }



    }
}