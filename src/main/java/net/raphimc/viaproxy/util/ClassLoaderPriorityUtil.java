/*
 * This file is part of ViaProxy - https://github.com/RaphiMC/ViaProxy
 * Copyright (C) 2021-2024 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.viaproxy.util;

import net.lenni0451.reflect.ClassLoaders;
import net.raphimc.viaproxy.util.logging.Logger;

import java.io.File;

public class ClassLoaderPriorityUtil {

    public static void loadOverridingJars() {
        final File jarsFolder = new File("jars");
        if (!jarsFolder.exists()) {
            jarsFolder.mkdir();
            return;
        }

        if (jarsFolder.isDirectory()) {
            for (File file : jarsFolder.listFiles()) {
                try {
                    if (file.getName().endsWith(".jar")) {
                        ClassLoaders.loadToFront(file.toURI().toURL());
                        Logger.LOGGER.warn("Loaded overriding jar " + file.getName());
                    }
                } catch (Throwable e) {
                    Logger.LOGGER.error("Failed to load overriding jar " + file.getName(), e);
                }
            }
        }
    }

}
