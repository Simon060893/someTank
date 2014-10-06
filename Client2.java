package com.kademika.tanks.connectWithTwoClients;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Админ on 30.09.2014.
 */
public class Client2 extends UserFrame {
    private String tankCurrent;
    public Client2(String str) {
        super(str);
        tankCurrent=currentTank;
    }

    @Override
    public void connectToServer() {

        try (
                Socket socket = new Socket("localhost", 4444);
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())
        ) {
//            out.write(tankCurrent.getBytes());
            getContentPane().add(createPanel1());
                    System.out.println("Client2 succsefuly conected");
                        try {
                            out.write(getCurrentComand());
                            out.write(currentTank.getBytes());
                            setCurrentComand(0);
                            Thread.currentThread().sleep(100);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client2("Client2");
    }
}
