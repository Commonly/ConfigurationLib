/*
 * Copyright (c) 2016 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.jdog.configurationlib.lib;

import me.jdog.configurationlib.lib.exception.InvalidFileException;
import org.bukkit.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by Muricans on 12/17/16.
 *
 * @since 0.0.1
 */

public class Config {
    private Plugin p;
    private File file;
    private FileConfiguration fileConfig;
    private String name;
    public String configVersion = "0.0.1";

    public Config(Plugin p, String name) {
        boolean exception = false;
        try {
            if (!name.endsWith(".yml")) {
                exception = true;
                throw new InvalidFileException();
            }
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        if (!exception) {
            this.p = p;
            this.name = name;
            file = new File(p.getDataFolder(), name);
            fileConfig = YamlConfiguration.loadConfiguration(file);
        }
    }

    public void create() {
        if (!file.exists()) {
            try {
                p.getDataFolder().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        save();
    }

    public void save() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * @param path  The path to be selected.
     * @param value What to set the path to.
     */
    public void set(String path, Object value) {
        fileConfig.set(path, value);
        save();
    }

    /**
     * @param path  The path to be selected.
     * @param value What to set the path to.
     */
    public void addDefault(String path, Object value) {
        fileConfig.addDefault(path, value);
        fileConfig.options().copyDefaults(true);
        save();
    }

    /**
     * @param path The path to get the integer from.
     * @return The integer in that path.
     */
    public int getInt(String path) {
        return fileConfig.getInt(path);
    }

    /**
     * @param path The path to get the string from.
     * @return The string in that path.
     */
    public String getString(String path) {
        return fileConfig.getString(path);
    }

    /**
     * @param path The path to get the String List from.
     * @return The string list in that path.
     */
    public List<String> getStringList(String path) {
        return fileConfig.getStringList(path);
    }

    /**
     * @param path The path to get the boolean from.
     * @return The boolean in that path.
     */
    public boolean getBoolean(String path) {
        return fileConfig.getBoolean(path);
    }

    /**
     * @param path The path to get the object from.
     * @return The object in that path.
     */
    public Object get(String path) {
        return fileConfig.get(path);
    }

    /**
     * @param path Path to get defaults from.
     * @return Defaults from selected path.
     */
    public Configuration getDefaults(String path) {
        return fileConfig.getDefaults();
    }

    public ConfigurationOptions options() {
        if(!file.exists()) {
            create();
        }
        return fileConfig.options();
    }

    /**
     * @param path The path to get the long from.
     * @return The long from selected path.
     */
    public long getLong(String path) {
        return fileConfig.getLong(path);
    }

    /**
     * @param path The path to check.
     * @return
     */
    public boolean isSet(String path) {
        return fileConfig.isSet(path);
    }

    public FileConfiguration getFile() {
        if (!file.exists()) {
            create();
        }
        return fileConfig;
    }

    /**
     * @param path The path to get the color from.
     * @return The color in that path.
     */
    public Color getColor(String path) {
        return fileConfig.getColor(path);
    }

    public void saveResource(boolean set) {
        p.saveResource(name, set);
    }

    /**
     * You can make the YAML file in the resources folder and it'll load it from there.
     * Unfortunately this will not generate the comments you put in the YAML file.
     */
    public void loadFromJar() {
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(p.getResource(name), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                fileConfig.setDefaults(defConfig);
                defConfigStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
