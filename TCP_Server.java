import java.io.*;
import java.net.*;

class TCP_Server{
    public static void main(String argv[]) throws Exception{
        String clientSentence,capitalizedSentence;
        String request;
        String response = "",header = "";
        ServerSocket welcomeSocket = new ServerSocket(8808);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            //SAMPLE CODE
            // clientSentence = inFromClient.readLine();
            // capitalizedSentence = clientSentence.toUpperCase() + '\n';
            // outToClient.writeBytes(capitalizedSentence);

            request = inFromClient.readLine();
            

            switch(request){
                case "GET /index.html HTTP/1.1":
                    response = "C:/Users/Dylan/Documents/BradleySchoolWork/CIS430/Project1/index.html";
                    header = "HTTP /1.1 200 OK";
                    break;
                case "GET /abc.html HTTP/1.1":
                    response = "C:/Users/Dylan/Documents/BradleySchoolWork/CIS430/Project1/NotFound.html";
                    header = "HTTP /1.1 404 File Not Found";
                    break;
                case "QUIT": 
                    header = "QUIT";
                    break;                    
                default:
                    response = "C:/Users/Dylan/Documents/BradleySchoolWork/CIS430/Project1/NotFound.html";
                    header = "HTTP /1.1 404 File Not Found";
                    break;
            }

            header += "\n";
            response += "\n";

            outToClient.writeBytes(header);
            outToClient.writeBytes(response);

            //welcomeSocket.close();
        }
    }
}
