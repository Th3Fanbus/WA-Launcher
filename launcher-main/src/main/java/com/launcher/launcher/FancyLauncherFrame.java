/*
 * SKCraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.launcher.launcher;

import com.launcher.launcher.dialog.LauncherFrame;
import com.launcher.launcher.swing.SwingHelper;
import com.launcher.launcher.swing.WebpagePanel;
import lombok.NonNull;

import javax.swing.*;

public class FancyLauncherFrame extends LauncherFrame {

    /**
     * Create a new frame.
     *
     * @param launcher the launcher
     */
    public FancyLauncherFrame(@NonNull Launcher launcher) {
        super(launcher);

        setSize(800, 500);
        setLocationRelativeTo(null);

        SwingHelper.removeOpaqueness(getInstancesTable());
        SwingHelper.removeOpaqueness(getInstanceScroll());
        getInstanceScroll().setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected JPanel createContainerPanel() {
        return new FancyBackgroundPanel();
    }

    @Override
    protected WebpagePanel createNewsPanel() {
        WebpagePanel panel = super.createNewsPanel();
        panel.setBrowserBorder(BorderFactory.createEmptyBorder());
        return panel;
    }

}
