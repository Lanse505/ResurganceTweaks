package lanse505.resurgancetweaks.compat.oreexcavation;

import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import lanse505.resurgancetweaks.utils.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oreexcavation.events.EventExcavate;

public class ExcavationTrait extends Trait {
    public ExcavationTrait() {
        super(new ResourceLocation(Constants.MODID, "excavator"), 1, 3, new ResourceLocation("compatskills:miner"), 1, "");
        this.setIcon(new ResourceLocation("textures/items/diamond_pickaxe.png"));
    }

    @SubscribeEvent
    public void onExcavate(EventExcavate event) {
        EntityPlayer player = event.getAgent().player;
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(this.getParentSkill());
        if (!info.isUnlocked(this)) {
            event.setCanceled(true);
        }
    }
}
