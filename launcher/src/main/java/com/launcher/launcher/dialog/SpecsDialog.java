/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.launcher.launcher.dialog;

import com.launcher.launcher.swing.ActionListeners;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SpecsDialog extends JDialog {

    public SpecsDialog(Window parent) {
        super(parent, "Computer Specifications", ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        String osStr = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
        String javaStr = System.getProperty("java.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.version") ;
        
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets dialog"));
        
        container.add(new JLabel("<html><center><img src=https://www.worldautomation.net/images/launcher-about.png>"), "align center, wrap");
        
        //container.add(new JLabel("<html>You are using WA Launcher, an open-source customizable<br>"), "align center, wrap");
        //container.add(new JLabel("<html>launcher platform that anyone can use.<br><br>"), "align center, wrap");
        
        container.add(new JLabel("<html><h2>System specifications summary</h2><br>"), "align center, wrap");
        container.add(new JLabel("<html>Available processor cores: " + Runtime.getRuntime().availableProcessors() + "<br>"), "align center, wrap");
        //container.add(new JLabel("<html>Free memory (bytes): " + Runtime.getRuntime().freeMemory() + "<br>"), "align center, wrap");
        container.add(new JLabel("<html>Operating system: " + osStr + "<br>"), "align center, wrap");
        container.add(new JLabel("<html>Java version: " + javaStr + "<br>"), "align center, wrap");
        container.add(new JLabel("<html>Java bitness: " + System.getProperty("sun.arch.data.model") + "<br><br>"), "align center, wrap");
        container.add(new JLabel("<html>```</center><br><br>"), "align center, wrap");
        
	//JButton discordButton = new JButton("<html><img src=https://www.worldautomation.net/images/launcher-about-discord.png>");
	//container.add(discordButton, "align center, wrap");
	//discordButton.addActionListener(ActionListeners.openURL(this, "https://discord.gg/Dvjvtee"));
		
        //JButton sourceCodeButton = new JButton("Website");      
	//container.add(sourceCodeButton, "span, split 3, sizegroup bttn");
        //sourceCodeButton.addActionListener(ActionListeners.openURL(this, "https://www.worldautomation.net"));
        
	JButton okButton = new JButton("OK");
        container.add(okButton, "tag ok, sizegroup bttn");
        
        add(container, BorderLayout.CENTER);
        
        getRootPane().setDefaultButton(okButton);
        getRootPane().registerKeyboardAction(ActionListeners.dispose(this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        okButton.addActionListener(ActionListeners.dispose(this));

    }

    public static void showSpecsDialog(Window parent) {
        SpecsDialog dialog = new SpecsDialog(parent);
        dialog.setVisible(true);
    }
}

