package lanse505.resurgancetweaks.compat;

import lanse505.resurgancetweaks.ResurganceTweaks;
import lanse505.resurgancetweaks.compat.betterwithmods.BWMCompat;
import lanse505.resurgancetweaks.utils.configs.ResurganceConfigs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CompatModule {
    public static HashMap<String, Class<? extends CompatModule>> moduleClasses = new HashMap<String, Class<? extends CompatModule>>();
    public static Set<CompatModule> modules = new HashSet<CompatModule>();
    public static boolean serverStartingDone = false;

    static {
        moduleClasses.put("betterwithmods", BWMCompat.class);
    }

    public static void doModulesPreInit() {
        for (Map.Entry<String, Class<? extends CompatModule>> e : moduleClasses.entrySet()) {
            if (Loader.isModLoaded(e.getKey())) {
                try {
                    Boolean enabled = ResurganceConfigs.Configs.Modules.compat.get(e.getKey());
                    if (enabled == null || !enabled.booleanValue()) {
                        continue;
                    }
                    CompatModule m = e.getValue().newInstance();
                    modules.add(m);
                    m.preInit();
                } catch (Exception exception) {
                    ResurganceTweaks.logger.error("Compat module for " + e.getKey() + " could not be preInitialized. Report this!");
                }
            }
        }
    }

    public static void doModulesInit() {
        for (CompatModule compat : CompatModule.modules) {
            try {
                compat.init();
            } catch (Exception exception) {
                ResurganceTweaks.logger.error("Compat module for " + compat + " could not be initialized");
            }
        }
    }

    public static void doModulesPostInit() {
        for (CompatModule compat : CompatModule.modules) {
            try {
                compat.postInit();
            } catch (Exception exception) {
                ResurganceTweaks.logger.error("Compat module for " + compat + " could not be postInitialized");
            }
        }
    }

    public static void doModulesLoadComplete() {
        if (!serverStartingDone) {
            serverStartingDone = true;
            for (CompatModule compat : CompatModule.modules) {
                try {
                    compat.loadComplete();
                } catch (Exception exception) {
                    ResurganceTweaks.logger.error("Compat module for " + compat + " could not be initialized");
                    exception.printStackTrace();
                }
            }
        }
    }

    public abstract void preInit();

    public abstract void init();

    public abstract void postInit();

    public void loadComplete() {
    }

    @SideOnly(Side.CLIENT)
    public void clientPreInit() {
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
    }

    @SideOnly(Side.CLIENT)
    public void clientPostInit() {
    }
}
