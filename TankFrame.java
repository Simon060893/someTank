package com.kademika.tanks.connectWithTwoClients;

import com.kademika.tanks.server.ActionField;
import com.kademika.tanks.server.SetupPanel;
import com.kademika.tanks.util.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Админ on 30.09.2014.
 */
public class TankFrame extends JFrame{
    public ActionField actionField;
    SetPanel setupPanel;

    public TankFrame() throws Exception {
        System.out.println("SETUP EDT: " + SwingUtilities.isEventDispatchThread());

        setupFrame();
        loadSetupPanel();

    }

    private void setupFrame() throws Exception {
        this.setTitle("Tanks");
        this.setLocation(750, 150);
        this.setMinimumSize(new Dimension(Field.BF_WIDTH,Field.BF_WIDTH + 22));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        actionField = new ActionField();

        setupPanel = new SetPanel(this);

        this.getContentPane().add(setupPanel);

        this.setVisible(true);
    }

    public void loadSetupPanel() {
        this.getContentPane().removeAll();
        this.getContentPane().add(setupPanel);
        this.pack();
        setupPanel.setVisible(true);
//        userControl();
    }

    public void loadActionField() {
        actionField.createElementOfPole();
        this.getContentPane().removeAll();
        this.getContentPane().add(actionField);
        this.revalidate();
        this.pack();
        actionField.setVisible(true);



    }


}
