import java.io.*;
import java.net.*;
import java.awt.Desktop;

class TCP_Client {
    public static void main(String argv[]) throws Exception{
        String sentence, modifiedSentence;
        String option,capOption;
        String request,response,header;
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 8808);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //SAMPLE CODE
        // sentence = inFromUser.readLine();
        // outToServer.writeBytes(sentence + '\n');
        // modifiedSentence = inFromServer.readLine();
        // System.out.println("FROM SERVER: " + modifiedSentence);

        //HTTP SERVER CODE
        System.out.println("Select an option: \nA:  GET / HTTP/1.1\nB: GET /index.html HTTP/1.1\nC:  GET /abc.html HTTP/1.1\nD: QUIT the client");

        option = inFromUser.readLine();
        capOption = option.toUpperCase();

        switch(capOption){
            case "A": 
                request = "GET /index.html HTTP/1.1";
                break;
            case "B": 
                request = "GET /index.html HTTP/1.1";
                break;
            case "C": 
                request = "GET /abc.html HTTP/1.1";
                break;
            case "D": 
                request = "QUIT";
                break;
            default: request="QUIT";
                break;
        }
        
        outToServer.writeBytes(request + '\n');

        header = inFromServer.readLine();
        response = inFromServer.readLine();
        System.out.println(header);

        if (header.equals("HTTP /1.1 200 OK")){
            File htmlFile = new File(response);
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        else if (header.equals("HTTP /1.1 404 File Not Found")){
            File htmlFile = new File(response);
            Desktop.getDesktop().browse(htmlFile.toURI());
        }

        clientSocket.close();
    }
}