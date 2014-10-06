package com.kademika.tanks.connectWithTwoClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Админ on 30.09.2014.
 */
public abstract class UserFrame extends JFrame {
    private JPanel panel;
    protected String currentTank = "";
    private int currentComand;

    public int getCurrentComand() {
        return currentComand;
    }

    public void setCurrentComand(int currentComand) {
        this.currentComand = currentComand;
    }

    public UserFrame(String str) {
        setTitle(str);
        setLocation(350, 150);
        setMinimumSize(new Dimension(250, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        createPanel();
        getContentPane().add(createPanel());
        setVisible(true);
    }

    protected JPanel createPanel() {
        GridLayout layout = new GridLayout(1, 1);
        panel = new JPanel();
        JPanel panel1 = new JPanel();
        ButtonGroup group = new ButtonGroup();
        final JRadioButton jRadioButton = new JRadioButton("Defender");
        panel1.add(jRadioButton);
        JRadioButton jRadioButton1 = new JRadioButton("Agressor");
        jRadioButton1.setSelected(true);
        panel1.add(jRadioButton1);
        group.add(jRadioButton);
        group.add(jRadioButton1);
        panel.add("North", panel1);
        JButton jButton = new JButton("Connect");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jRadioButton.isSelected()) {
                    currentTank = "D";
                }
                connectToServer();
            }
        });
        panel.add("Center", jButton);
        return panel;
    }

    protected JPanel createPanel1() {
        panel.setVisible(false);
        GridLayout layout = new GridLayout(1, 1);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JButton jButtonU = new JButton("w");
        jButtonU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("w"+currentTank);
            }
        });
        JButton jButtonD = new JButton("s");
        jButtonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("s"+currentTank);
            }
        });
        JButton jButtonR = new JButton("d");
        jButtonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("d"+currentTank);
            }
        });
        JButton jButtonL = new JButton("a");
        jButtonL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("a"+currentTank);
            }
        });
        panel1.add(jButtonU);
        panel1.add(jButtonD);
        panel1.add(jButtonR);
        panel1.add(jButtonL);
        panel.add("West", panel1);
        JPanel panel2 = new JPanel();
        JButton fire = new JButton("f");
        fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("f"+currentTank);
            }
        });
        JButton move = new JButton("m");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cannectToServer1("m"+currentTank);
            }
        });
        panel2.add(fire);
        panel2.add(move);
        panel.add("East", panel2);
        return panel;
    }
    public String getCommand(String str){
        return str;
    }

    public abstract void connectToServer();

    protected void cannectToServer1(String command) {
        try (
                Socket socket = new Socket("localhost", 4444);
                PrintStream out = new PrintStream(socket.getOutputStream());
        ) {
            out.write(command.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
