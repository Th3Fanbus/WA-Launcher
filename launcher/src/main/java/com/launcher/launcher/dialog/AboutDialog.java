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

public class AboutDialog extends JDialog {

    public AboutDialog(Window parent) {
        super(parent, "About", ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets dialog"));

        container.add(new JLabel("<html><center><img src=https://www.worldautomation.net/images/launcher-about.png>"), "align center, wrap");
        container.add(new JLabel("<html>Licensed under GNU General Public License, version 3.<br><br>"), "align center, wrap");
        container.add(new JLabel("<html>You are using WA Launcher, an open-source customizable<br>"), "align center, wrap");
        container.add(new JLabel("<html>launcher platform that anyone can use.<br><br>"), "align center, wrap");
        container.add(new JLabel("<html>WA does not necessarily endorse the version of<br>"), "align center, wrap");
        container.add(new JLabel("<html>the launcher that you are using.<br><br>"), "align center, wrap");
        container.add(new JLabel("<html>Original Source Credit to SKCraft.</center><br><br>"), "align center, wrap");
        
		JButton discordButton = new JButton("<html><img src=https://www.worldautomation.net/images/launcher-about-discord.png>");
		container.add(discordButton, "align center, wrap");
		discordButton.addActionListener(ActionListeners.openURL(this, "https://discord.gg/Dvjvtee"));
		
        JButton sourceCodeButton = new JButton("Website");      
		container.add(sourceCodeButton, "span, split 3, sizegroup bttn");
        
		JButton okButton = new JButton("OK");
        container.add(okButton, "tag ok, sizegroup bttn");

        add(container, BorderLayout.CENTER);

        getRootPane().setDefaultButton(okButton);
        getRootPane().registerKeyboardAction(ActionListeners.dispose(this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        okButton.addActionListener(ActionListeners.dispose(this));
        sourceCodeButton.addActionListener(ActionListeners.openURL(this, "https://www.worldautomation.net"));

    }

    public static void showAboutDialog(Window parent) {
        AboutDialog dialog = new AboutDialog(parent);
        dialog.setVisible(true);
    }
}

