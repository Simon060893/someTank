package com.kademika.tanks.connectWithTwoClients;

import com.kademika.tanks.server.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Админ on 30.09.2014.
 */
public class SetPanel extends JPanel {
    private TankFrame frame;
    private boolean clientsAreLoaded;

    public boolean isClientsAreLoaded() {
        return clientsAreLoaded;
    }

    public void setClientsAreLoaded(boolean clientsAreLoaded) {
        this.clientsAreLoaded = clientsAreLoaded;
    }

    private JButton button_StartGame;

    public SetPanel(TankFrame frame) {
        this.setFocusable(true);
        this.requestFocus();
        this.frame = frame;

        SwingWorker worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                boolean flag = true;
                while (flag) {
                    if (isClientsAreLoaded()) {
                      repaint();
                        flag = false;
                    }
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        worker.execute();



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isClientsAreLoaded()) {
            setComponents();
            setClientsAreLoaded(false);
        }

    }

    private void setComponents() {
        button_StartGame = new JButton("Start game");
        add(button_StartGame);
        button_StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.loadActionField();
                SwingWorker worker = new SwingWorker<Void, Void>() {

                    @Override
                    protected Void doInBackground() throws Exception {
                        System.out.println("RUN EDT: " + SwingUtilities.isEventDispatchThread());
                        return null;
                    }
                };
                worker.execute();
            }
        });
    }

}
