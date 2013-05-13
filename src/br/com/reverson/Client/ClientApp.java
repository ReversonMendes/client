package br.com.reverson.Client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket.common.Mensagem;

public class ClientApp {

    public static void main(String[] args) {
        try {
            OutputStream saida;

            Socket socket = new Socket("localhost", 1234);
            //Escreve para servidor
            saida = socket.getOutputStream();
            PrintStream print = new PrintStream(saida);
            print.println("DATA_HORA");

            //Ler do servidor
            InputStream in = socket.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            String resp = read.readLine();
            //imprime a resposta do servidor
            System.out.println("Resp:" + resp);

            print.println("MSG");

            Mensagem m = new Mensagem();
            m.setData(new Date());
            m.setDestino("Corinthians");
            m.setMsg("Vai Corinthians !!!!!");

            Gson gson = new Gson();
            String msg = gson.toJson(m);
            print.println(msg);
            
            resp = read.readLine();
            //imprime a resposta do servidor
            System.out.println("Resp:" + resp);

            //fechando as conex~oes
            print.close();
            saida.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}