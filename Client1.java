package com.kademika.tanks.connectWithTwoClients;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Админ on 30.09.2014.
 */
public class Client1 extends UserFrame {
    private String tankCurrent;
    public Client1(String str) {
        super(str);
        tankCurrent=currentTank;
    }

    @Override
    public void connectToServer() {

        try (
                Socket socket = new Socket("localhost", 4444);
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())
        ) {
            getContentPane().add(createPanel1());
                    System.out.println("Client 1 succsefuly conected");
                        try {
                            out.write(getCurrentComand());
                            out.write(currentTank.getBytes());
                            System.out.println(getCurrentComand());
                            setCurrentComand(0);

                            Thread.currentThread().sleep(300);
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

        new Client1("Client1");
    }
}
