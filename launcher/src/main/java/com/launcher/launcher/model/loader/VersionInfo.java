/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.launcher.launcher.model.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.launcher.launcher.model.minecraft.Library;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionInfo {

    private String minecraftArguments;
    private String mainClass;
    private List<Library> libraries;

}
