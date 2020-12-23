package dev.dankom.torn;

import dev.dankom.torn.alt.AltManager;
import dev.dankom.torn.command.CommandManager;
import dev.dankom.torn.file.FileManager;
import dev.dankom.torn.gui.clickgui.ClickGui;
import dev.dankom.torn.gui.notification.NotificationManager;
import dev.dankom.torn.gui.tabgui.SubTab;
import dev.dankom.torn.gui.tabgui.Tab;
import dev.dankom.torn.gui.tabgui.TabGui;
import dev.dankom.torn.module.ModuleManager;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.SettingsManager;
import dev.dankom.torn.listeners.ClickListener;
import dev.dankom.torn.theme.Theme;
import dev.dankom.torn.util.StringUtil;
import dev.dankom.torn.util.wrapper.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Mod(modid = Torn.MODID, version = Torn.VERSION)
public class Torn
{
    public static final String MODID = "torn";
    public static final String VERSION = "1.0";

    public static String CLIENT_NAME = "Torn", CLIENT_VERSION = "B1.0", CLIENT_AUTHOR = "Dankom";

    private final static Wrapper wrapper = new Wrapper();
    private final static SettingsManager settingsManager = new SettingsManager();
    private final static ModuleManager moduleManager = new ModuleManager();
    private final static ClickGui clickGui = new ClickGui();
    private final static AltManager altManager = new AltManager();
    private static FileManager fileManager = new FileManager();
    private static CommandManager commandManager = new CommandManager();
    private static TabGui<Module> tabGui = new TabGui<>();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        getFileManager().load();

        MinecraftForge.EVENT_BUS.register(new ClickListener());
        MinecraftForge.EVENT_BUS.register(this);

        HashMap<Category, List<Module>> moduleCategoryMap = new HashMap<>();

        for (Module module : Torn.getModuleManager().getModules()) {
            if (!moduleCategoryMap.containsKey(module.getCategory())) {
                moduleCategoryMap.put(module.getCategory(), new ArrayList<>());
            }

            moduleCategoryMap.get(module.getCategory()).add(module);
        }

        moduleCategoryMap.entrySet().stream().sorted(Comparator.comparingInt(cat -> cat.getKey().toString().hashCode())).forEach(cat -> {
            Tab<Module> tab = new Tab<>(cat.getKey().toString());

            for (Module module : cat.getValue()) {
                tab.addSubTab(new SubTab<>(module.getName(), subTab -> subTab.getObject().setToggled(!subTab.getObject().isToggled()), module));
            }

            tabGui.addTab(tab);
        });
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public static ClickGui getClickGui() {
        return clickGui;
    }

    public static AltManager getAltManager() {
        return altManager;
    }

    public static Wrapper getWrapper() {
        return wrapper;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static TabGui getTabGUI() {
        return tabGui;
    }

    public static void save() {
        getFileManager().save();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent e) {
        if (!e.type.equals(RenderGameOverlayEvent.ElementType.CROSSHAIRS)) return;

        String type = getSettingsManager().getSetting(getModuleManager().getModule("ClickGUI"), "Watermark Type").getValString();
        if (type.equalsIgnoreCase("Large")) {
            GL11.glScaled(2.0, 2.0, 2.0);
            int i = fontRenderer().drawString(Torn.CLIENT_NAME, 2, 2, Theme.getColorInt(), true);
            GL11.glScaled(0.5, 0.5, 0.5);

            fontRenderer().drawString(Torn.CLIENT_VERSION, i * 2, fontRenderer().FONT_HEIGHT * 2 - 7, Theme.getColorInt(), true);
            fontRenderer().drawString("by " + Torn.CLIENT_AUTHOR, 8, fontRenderer().FONT_HEIGHT * 2 + 2, Theme.getColorInt(), true);
        } else if (type.equalsIgnoreCase("Small")) {
            fontRenderer().drawString(Torn.CLIENT_NAME + " " + Torn.CLIENT_VERSION, 2, 2, Theme.getColorInt());
        }
    }

    public FontRenderer fontRenderer() {
        return Minecraft.getMinecraft().fontRendererObj;
    }
}
