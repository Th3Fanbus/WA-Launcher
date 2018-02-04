/*
 * SKCraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.launcher.launcher;

import com.google.common.base.Supplier;
import com.launcher.launcher.swing.SwingHelper;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.net.URI;

@Log
public class FancyLauncher {

    public static void main(final String[] args) {
        Launcher.setupLogger();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String version = System.getProperty("sun.arch.data.model");
				log.log(Level.INFO, "Java version string: " + version);
                if(version.contains("64")) {
	                try {
	                    Thread.currentThread().setContextClassLoader(FancyLauncher.class.getClassLoader());
	                    UIManager.getLookAndFeelDefaults().put("ClassLoader", FancyLauncher.class.getClassLoader());
	                    UIManager.getDefaults().put("SplitPane.border", BorderFactory.createEmptyBorder());
	                    JFrame.setDefaultLookAndFeelDecorated(true);
	                    JDialog.setDefaultLookAndFeelDecorated(true);
	                    System.setProperty("sun.awt.noerasebackground", "true");
	                    System.setProperty("substancelaf.windowRoundedCorners", "false");
	
	                    if (!SwingHelper.setLookAndFeel("com.launcher.launcher.skin.LauncherLookAndFeel")) {
	                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                    }
	
	                    Launcher launcher = Launcher.createFromArguments(args);
	                    launcher.setMainWindowSupplier(new CustomWindowSupplier(launcher));
	                    launcher.showLauncherWindow();
	                } catch (Throwable t) {
	                    log.log(Level.WARNING, "Load failure", t);
	                    SwingHelper.showErrorDialog(null, "Uh oh! The updater couldn't be opened because a " +
	                            "problem was encountered.", "Launcher error", t);
	                }
                } else {
                    SwingHelper.showErrorDialog(null, "Uh oh! You need 64-Bit Java 8 Minimum!", "WorldAutomation.Net");
                    try {
                        Desktop.getDesktop().browse(new URI("https://java.com/en/download/"));
                    } catch (Exception e) { }
                }
            }
        });
    }

    private static class CustomWindowSupplier implements Supplier<Window> {

        private final Launcher launcher;

        private CustomWindowSupplier(Launcher launcher) {
            this.launcher = launcher;
        }

        @Override
        public Window get() {
            return new FancyLauncherFrame(launcher);
        }
    }

}
