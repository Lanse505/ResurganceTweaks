package lanse505.resurgancetweaks;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import lanse505.resurgancetweaks.proxies.CommonProxy;
import lanse505.resurgancetweaks.utils.Constants;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, dependencies = Constants.DEPS, acceptedMinecraftVersions = Constants.MCVERS)
public class ResurganceTweaks {
    public static final List<IAction> LATE_ADDITIONS = new LinkedList<>();
    public static Logger logger;
    @SidedProxy(clientSide = "lanse505.resurgancetweaks.proxies.ClientProxy", serverSide = "lanse505.resurgancetweaks.proxies.CommonProxy", modId = Constants.MODID)
    private static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        if (Loader.isModLoaded("crafttweaker")) {
            LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
        }
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        proxy.serverStart(event);
    }
}
