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
import lombok.extern.java.Log;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

@Log
public class SpecsDialog extends JDialog {

    public SpecsDialog(Window parent) {
        super(parent, "Computer Specifications", ModalityType.DOCUMENT_MODAL);

        initComponents(parent);
    }

    private void initComponents(Window parent) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets dialog"));
        container.add(new JLabel("<html><h2>Loading System Specifications...</h2><br>"), "align center, wrap");
        
        add(container, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(parent);
        
        loadSpecs(container);
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void loadSpecs(JPanel oldContainer) {
        SystemInfo si = new SystemInfo();
        final HardwareAbstractionLayer hal = si.getHardware();
        final ComputerSystem computerSystem = hal.getComputerSystem();
        final Baseboard baseboard = computerSystem.getBaseboard();
        final Firmware firmware = computerSystem.getFirmware();
        final CentralProcessor processor = hal.getProcessor();
        final GlobalMemory memory = hal.getMemory();
        
        final String mbStr = baseboard.getManufacturer() + " " + baseboard.getModel() + " " + baseboard.getVersion();
        final String cpuStr = processor + " (" + processor.getPhysicalProcessorCount() + " cores, " + processor.getLogicalProcessorCount() + " threads)";
        final String osStr = System.getProperty("os.name") + " version " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
        final String javaStr = System.getProperty("java.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.version") ;
        final String memStr = FormatUtil.formatBytes(memory.getTotal() - memory.getAvailable()) + " / " + FormatUtil.formatBytes(memory.getTotal());
        final String swapStr = FormatUtil.formatBytes(memory.getSwapUsed()) + " / " + FormatUtil.formatBytes(memory.getSwapTotal());
        
        final String labelOpts = "align center, wrap";
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets dialog"));
        
        container.add(new JLabel("<html><img src=https://www.worldautomation.net/images/launcher-about.png>"), "align center, wrap");
        container.add(new JLabel("<html><h2>System Specifications Summary</h2><br>"), "align center, wrap");
        container.add(new JLabel("<html><b>Computer Manufacturer: </b>" + computerSystem.getManufacturer() + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Computer Manufacturer: </b>" + computerSystem.getModel() + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Motherboard: </b>" + mbStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Firmware: </b>" + firmware.getManufacturer() + " version " + firmware.getVersion() + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>CPU: </b>" + cpuStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>CPU Identifier: </b>" + processor.getIdentifier() + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>CPU Temperature: </b>" + String.format("%.1f°C%n", hal.getSensors().getCpuTemperature()) + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Memory: </b>" + memStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Swap: </b>" + swapStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Operating System: </b>" + osStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Uptime: </b>" + FormatUtil.formatElapsedSecs(processor.getSystemUptime()) + " (HH:MM:SS) <br>"), labelOpts);
        container.add(new JLabel("<html><b>Java Version: </b>" + javaStr + "<br>"), labelOpts);
        container.add(new JLabel("<html><b>Java Architecture: </b>" + System.getProperty("sun.arch.data.model") + "-Bit<br><br>"), labelOpts);
        container.add(new JLabel("<html><br><br>"), "align left, wrap");
        
        //JButton discordButton = new JButton("<html><img src=https://www.worldautomation.net/images/launcher-about-discord.png>");
        //container.add(discordButton, "align center, wrap");
        //discordButton.addActionListener(ActionListeners.openURL(this, "https://discord.gg/Dvjvtee"));
        
        //JButton sourceCodeButton = new JButton("Website");
        //container.add(sourceCodeButton, "span, split 3, sizegroup bttn");
        //sourceCodeButton.addActionListener(ActionListeners.openURL(this, "https://www.worldautomation.net"));
        
        JButton okButton = new JButton("OK");
        container.add(okButton, "tag ok, sizegroup bttn");
        
        remove(oldContainer);
        add(container, BorderLayout.CENTER);
        
        getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(ActionListeners.dispose(this));
        getRootPane().registerKeyboardAction(ActionListeners.dispose(this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public static void showSpecsDialog(Window parent) {
        SpecsDialog dialog = new SpecsDialog(parent);
    }
}

