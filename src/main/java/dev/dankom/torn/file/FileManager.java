package dev.dankom.torn.file;

import dev.dankom.torn.Torn;
import dev.dankom.torn.friend.Friend;
import dev.dankom.torn.friend.FriendManager;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Module;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private File dir;
    private File dataFile;

    public FileManager() {
        dir = new File(Minecraft.getMinecraft().mcDataDir, "Torn");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dataFile = new File(dir, "data.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            dataFile.delete();
            dataFile.createNewFile();
            List<String> toSave = new ArrayList<>();

            for (Module m : Torn.getModuleManager().getModules()) {
                toSave.add("MOD:" + m.getName() + ":" + m.isToggled() + ":" + m.getKey());
            }

            for (Setting s : Torn.getSettingsManager().getSettings()) {
                if (s.isCheck()) {
                    toSave.add("SET:" + s.getName() + ":" + s.getParentMod().getName() + ":" + s.getValBoolean());
                } else if (s.isCombo()) {
                    toSave.add("SET:" + s.getName() + ":" + s.getParentMod().getName() + ":" + s.getValString());
                } else if (s.isSlider()) {
                    toSave.add("SET:" + s.getName() + ":" + s.getParentMod().getName() + ":" + s.getValDouble());
                }
            }

            for (Friend f : FriendManager.getFriendList()) {
                toSave.add("FRIEND:" + f.getUsername());
            }

            try {
                PrintWriter pw = new PrintWriter(dataFile);
                for (String str : toSave) {
                    pw.println(str);
                }
                pw.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : lines) {
            String[] args = s.split(":");
            if (s.toLowerCase().startsWith("mod:")) {
                Module m = Torn.getModuleManager().getModule(args[1]);
                if (m != null) {
                    m.setToggled(Boolean.parseBoolean(args[2]));
                    m.setKeyCode(Integer.parseInt(args[3]));
                }
            }
            if (s.toLowerCase().startsWith("set:")) {
                Module m = Torn.getModuleManager().getModule(args[2]);
                if (m != null) {
                    Setting set = Torn.getSettingsManager().getSetting(m, args[1]);
                    if (set != null) {
                        if (set.isCheck()) {
                            set.setValBoolean(Boolean.parseBoolean(args[3]));
                        } else if (set.isCombo()) {
                            set.setValString(args[3]);
                        } else if (set.isSlider()) {
                            set.setValDouble(Double.parseDouble(args[3]));
                        }
                    }
                }
            }
            if (s.toLowerCase().startsWith("friend:")) {
                FriendManager.addFriend(args[1]);
            }
        }
    }
}
