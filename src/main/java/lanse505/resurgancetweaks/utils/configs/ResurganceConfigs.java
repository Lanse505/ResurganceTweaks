package lanse505.resurgancetweaks.utils.configs;

import com.google.common.collect.Maps;
import lanse505.resurgancetweaks.compat.CompatModule;
import lanse505.resurgancetweaks.utils.Constants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.Map;

public class ResurganceConfigs {

    private static final String modId = Constants.MODID;

    public static void postInit(FMLPostInitializationEvent event) {

    }

    @Config(modid = modId, type = Config.Type.INSTANCE, name = modId)
    public static class Configs {
        public static Modules modules;
        public static TConstruct tConstruct;

        public static class Modules {
            @Config.Comment({"A list of all mods that Resurgance Tweaks has integrated compatability for", "Setting any of these to false disables the respective compat"})
            public static Map<String, Boolean> compat = Maps.newHashMap(Maps.toMap(CompatModule.moduleClasses.keySet(), (k) -> Boolean.TRUE));
        }

        public static class TConstruct {
            public static Traits traits;

            public static class Traits {
                public static Buttery buttery;
                public static Infernal infernal;

                public static class Buttery {
                    @Config.Comment({"Buttery Tick-Value"})
                    @Config.RangeInt(min = 1)
                    public static int ticks = 20;
                }

                public static class Infernal {
                    @Config.Comment({"Infernal Ignition Chance"})
                    @Config.RangeDouble(min = 0.0, max = 1.0)
                    public static double ignitionChance = 0.15d;
                }
            }
        }
    }
}
