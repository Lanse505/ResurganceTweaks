package lanse505.resurgancetweaks.compat.betterwithmods.tconstruct.traits;

import lanse505.resurgancetweaks.utils.UtilityMethods;
import lanse505.resurgancetweaks.utils.configs.ResurganceConfigs.Configs.TConstruct.Traits.Infernal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitInfernal extends AbstractTrait {
    public TraitInfernal() {
        super("infernal", 0xffffff);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if (player instanceof EntityPlayer) {
            if (!world.isRaining() && !player.isInWater() && !world.canSeeSky(player.getPosition())) {
                if (wasEffective) {
                    if (UtilityMethods.tryProcentage(Infernal.ignitionChance)) {
                        player.setFire(2);
                    }
                }
            }
        }
    }
}
