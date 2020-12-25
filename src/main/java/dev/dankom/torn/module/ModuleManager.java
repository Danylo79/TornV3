package dev.dankom.torn.module;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.module.modules.combat.*;
import dev.dankom.torn.module.modules.gui.*;
import dev.dankom.torn.module.modules.misc.*;
import dev.dankom.torn.module.modules.movement.*;
import dev.dankom.torn.module.modules.player.AntiAFK;
import dev.dankom.torn.module.modules.render.ESP;
import dev.dankom.torn.module.modules.render.Fullbright;
import dev.dankom.torn.module.modules.render.XRay;
import dev.dankom.torn.module.modules.world.AutoBreak;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        addModule(new ClickGUI());
        addModule(new AimAssist());
        addModule(new Velocity());
        addModule(new BunnyHop());
        addModule(new TextGUI());
        addModule(new Sprint());
        addModule(new SetbackDetector());
        addModule(new Notifications());
        addModule(new AutoClicker());
        addModule(new HUD());
//        addModule(new Strafe());
//        addModule(new Step());
        addModule(new Fullbright());
        addModule(new Fly());
        addModule(new AutoArmor());
        addModule(new AutoEat());
        addModule(new Twerk());
        addModule(new Glide());
        addModule(new AutoSoup());
        addModule(new KillAura());
        addModule(new AntiKnockback());
        addModule(new AutoFish());
        addModule(new ESP());
        addModule(new Jesus());
        addModule(new Liquids());
        addModule(new Reach());
//        addModule(new XRay());
        addModule(new LongJump());
        addModule(new NoSlow());
        addModule(new AntiAFK());
        addModule(new AutoBreak());
    }

    public void addModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesInCategory(Category c) {
        List<Module> out = new ArrayList<Module>();
        for (Module m : getModules()) {
            if (m.getCategory() == c) {
                out.add(m);
            }
        }
        return out;
    }

    public List<Module> getEnabledModules() {
        List<Module> modules = new ArrayList<Module>();
        for (Module m : getModules()) {
            if (m.isShowInEnabledMods() && m.isToggled() && m.isShowInClickGui()) {
                modules.add(m);
            }
        }
        return modules;
    }
}
