package lanse505.resurgancetweaks.proxies;

import lanse505.resurgancetweaks.ResurganceTweaks;
import lanse505.resurgancetweaks.compat.CompatModule;
import lanse505.resurgancetweaks.utils.configs.ResurganceConfigs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        ResurganceTweaks.logger.info("Pre-Initilization");
        CompatModule.doModulesPreInit();
    }

    public void init(FMLInitializationEvent e) {
        ResurganceTweaks.logger.info("Initilization");
        CompatModule.doModulesInit();
    }

    public void postInit(FMLPostInitializationEvent e) {
        ResurganceTweaks.logger.info("Post-Initilization");
        CompatModule.doModulesPostInit();
        ResurganceConfigs.postInit(e);
    }

    public void serverStart(FMLServerStartingEvent e) {
        ResurganceTweaks.logger.info("Server Starting");
    }
}
