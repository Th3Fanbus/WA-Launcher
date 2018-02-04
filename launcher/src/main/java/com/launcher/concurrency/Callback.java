/*
* WorldAutomation.Net Launcher
* Based off of sk89q's Source
* https://www.worldautomation.net
*/

package com.launcher.concurrency;

public interface Callback<T> {

    void handle(T value);

}
