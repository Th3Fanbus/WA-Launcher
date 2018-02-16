/*
 * SKCraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.launcher.launcher;

import com.google.common.base.Supplier;
import com.launcher.launcher.dialog.LauncherFrame;

import java.awt.*;

public class DefaultLauncherSupplier implements Supplier<Window> {

    private final FancyLauncher launcher;

    public DefaultLauncherSupplier(FancyLauncher launcher) {
        this.launcher = launcher;
    }

    @Override
    public Window get() {
        return new LauncherFrame(launcher);
    }

}
