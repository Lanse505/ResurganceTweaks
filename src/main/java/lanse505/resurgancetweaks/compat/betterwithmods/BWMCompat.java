package lanse505.resurgancetweaks.compat.betterwithmods;

import lanse505.resurgancetweaks.compat.CompatModule;
import lanse505.resurgancetweaks.compat.betterwithmods.tconstruct.materials.BWMMaterials;

public class BWMCompat extends CompatModule {
    @Override
    public void preInit() {
        BWMMaterials.preInit();
    }

    @Override
    public void init() {
        BWMMaterials.init();
    }

    @Override
    public void postInit() {

    }

    @Override
    public void clientPostInit() {
        BWMMaterials.clientPostInit();
    }
}
