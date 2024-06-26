package ru.noname070.lab6.client.io;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import lombok.Getter;
import ru.noname070.lab6.client.handlers.Command;
import ru.noname070.lab6.client.handlers.NewElementHandler;
import ru.noname070.lab6.client.io.seriallizer.Serializer;
import ru.noname070.lab6.client.utils.L18n;

/**
 * connection`nd`communication with the server
 */
public class Connection {
    @Getter private Socket connection;
    @Getter private BufferedWriter out;
    @Getter private BufferedReader in;

    @Getter private static Gson gson = new GsonBuilder().create();

    /**
     * default construct
     * 
     * @param host
     * @param port 0x0 - 0xffff ports
     * @throws IOException 
     * @apiNote also can stop your app xd
     */
    public Connection(String host, int port) {
        try {
            this.connection = new Socket(host, port);
            this.in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.connection.getOutputStream()));

        } catch (java.net.ConnectException e) {
            System.err.println("server is down.");
            System.exit(-1);
        } catch (UnknownHostException e) {
            System.err.println(L18n.getGeneralBundle().getString("net.err.cant_send"));
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(L18n.getGeneralBundle().getString("net.err.cant_send"));
            e.printStackTrace();
            System.exit(-1);
        // } finally {
        //     this.connection.close();
        //     this.in.close();
        //     this.out.close();
        }
    }

    /**
     * processes command, sends it to the server and receives the response
     * 
     * @param args clear input from console
     * @throws IOException
     */
    public void processCommand(String args[]) throws IOException {

        Command command = new Command();
        switch (args.length) {
            case 1:
                command.setName(args[0]);
                break;

            case 2:
                command.setName(args[0]);
                command.setArgs(args[1]);
                break;

            default:
                return;
        }

        // клиент не должен знать какие команды есть на сервере
        // if (command.getName().equals("add")) {
        // if (!command.getArgs().equals("devrnd")) // dev only
        // command.setOrg(new Serializer().serialize(NewElementHandler.newElement()));
        // }

        if (command.getName().equals("exit")) {
            this.connection.close();
            System.out.println(L18n.getGeneralBundle().getString("command.exit.message"));
            System.exit(0);
        }

        try {
            this.out.write(gson.toJson(command) + "\n");
            this.out.flush();

            String response = this.in.readLine();
            // untested
            if (response.equals("dd_need_element")) {
                command.setOrg(new Serializer().serialize(NewElementHandler.newElement(System.in, System.out)));
                this.out.write(gson.toJson(command) + "\n");
                this.out.flush();
            } else
                // костылек, но иначе надо запариваться с буффером
                System.out.println(response.replace("|||", "\n"));
        } catch (JsonIOException e) {
            System.err.println(L18n.getGeneralBundle().getString("json.err.cant_ser"));
        } catch (Exception e) {
            System.err.println(L18n.getGeneralBundle().getString("net.err.cant_send"));
            this.connection.close();
        }

    }

    /**
     * check connection status
     * 
     * @return connction colosed ? false : true
     */
    public boolean isWorking() {
        return !this.connection.isClosed();
    }

    @Override
    protected void finalize() throws IOException {
        this.out.close();
        this.in.close();
        this.connection.close();
    }

}