package com.kademika.tanks.connectWithTwoClients;


import com.kademika.tanks.server.Direction;
import com.kademika.tanks.tank.AbstractTank;
import com.kademika.tanks.tank.Bullet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Админ on 30.09.2014.
 */
public class SimpleTankServer {
    static TankFrame mainFrame;
    private static  ExecutorService service;
    private static int i = 0;
    private static int []loadCkient;
    private static int h=0;

    public static void main(String[] args) throws Exception {
        mainFrame = new TankFrame();
        loadCkient = new int[2];
         service = new ThreadPoolExecutor(0, 2,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy()//connection with 1000 and waiting before they will finished
        );
        final ServerSocket serverSocket = new ServerSocket(4444);
        try {
            while (true) {
                final Socket socket = serverSocket.accept();
                service.submit(new Runnable() {
                                   @Override
                                   public void run() {

                                       if(i<1){
                                           loadCkient[0]=socket.getPort();
                                       }

                                           process(socket);

                                   }
                               });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void process(Socket socket) {
        i++;
        try(
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
        ){
            if(i>1 && !socket.isClosed() ) {
                if (h == 0) {
                    mainFrame.setupPanel.setClientsAreLoaded(true);
                    h++;
                }
                int i=in.read();
            ActionOnServer(i, in.read());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private static void ActionOnServer(int data, int tank) throws Exception {
        if (data == (int) "e".getBytes()[0]) {
            mainFrame.actionField.processmoveRandom();
        } else if (data == (int) "f".getBytes()[0]) {
            mainFrame.actionField.processFire(new Bullet(mainFrame.actionField.getTank(tank).getTankX() + 25,
                    mainFrame.actionField.getTank(tank).getTankY() + 25, mainFrame.actionField.getTank(tank).getDirection()), mainFrame.actionField.getTank(tank));
        } else if (data == (int) "m".getBytes()[0]) {
            mainFrame.actionField.processMove(mainFrame.actionField.getTank(tank));
        } else if (data == (int) "w".getBytes()[0]) {
            mainFrame.actionField.getTank(tank).setDirection(Direction.UP);
        } else if (data == (int) "s".getBytes()[0]) {
            mainFrame.actionField.getTank(tank).setDirection(Direction.DOWN);
        } else if (data == (int) "d".getBytes()[0]) {
            mainFrame.actionField.getTank(tank).setDirection(Direction.RIGHT);
        } else if (data == (int) "a".getBytes()[0]) {
            mainFrame.actionField.getTank(tank).setDirection(Direction.LEFT);
        }
    }
}
