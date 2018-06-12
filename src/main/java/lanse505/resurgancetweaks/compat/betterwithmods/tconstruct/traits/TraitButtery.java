package lanse505.resurgancetweaks.compat.betterwithmods.tconstruct.traits;

import lanse505.resurgancetweaks.utils.configs.ResurganceConfigs.Configs.TConstruct.Traits.Buttery;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitButtery extends AbstractTrait {
    int x = 0;

    public TraitButtery() {
        super("buttery", 0xF6CB4C);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (isSelected) {
            if (x < Buttery.ticks) {
                x++;
                if (x >= Buttery.ticks) {
                    if (entity instanceof EntityPlayer) {
                        tool.damageItem(1, (EntityLivingBase) entity);
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, Buttery.ticks, 2));
                    }
                    x = 0;
                }
            }
        }
    }
}
