package lanse505.resurgancetweaks.compat.aquamunda.tweakers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lanse505.resurgancetweaks.ResurganceTweaks;
import mcjty.aquamunda.recipes.CuttingBoardRecipe;
import mcjty.aquamunda.recipes.CuttingBoardRecipeRepository;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ModOnly("aquamunda")
@ZenClass("mods.resurgancetweaker.CuttingBoard")
@ZenRegister
public class CuttingBoardTweaker {
    @ZenMethod
    public static void addRecipe(IItemStack[] inputs, IItemStack output, int chopTime, boolean useRoller) {
        if (inputs.length > 3) {
            CraftTweakerAPI.logError("Itemstack Inputs Array Can Not Be Bigger Than 3 Items!");
        } else if (inputs == null || inputs.length < 0) {
            CraftTweakerAPI.logError("Itemstack Inputs Array Can Not Be Less Than 1 Item or Null!");
        } else if (output == null || output.isEmpty()) {
            CraftTweakerAPI.logError("Output ItemStack can't be Null or Empty!");
        } else if (chopTime <= 0) {
            CraftTweakerAPI.logError("Chop Time Can Not Be Lower Than 1!");
            CraftTweakerAPI.logError("Setting Chop Time to 1!");
        }

        ItemStack[] realItemInputs = new ItemStack[inputs.length];
        for (int x = 0; x < realItemInputs.length; x++) {
            IItemStack itemStack = inputs[x];
            if (itemStack == null) {
                CraftTweakerAPI.logError("Found Null ItemStack In CuttingBoard Recipe");
            } else {
                realItemInputs[x] = CraftTweakerMC.getItemStack(inputs[x]);
            }
        }
        ItemStack realOutput = CraftTweakerMC.getItemStack(output);
        ResurganceTweaks.LATE_ADDITIONS.add(new Add(realItemInputs, realOutput, chopTime, useRoller));
    }

    public static class Add implements IAction {
        ItemStack[] inputs;
        ItemStack output;
        int chopTime;
        boolean useRoller;

        public Add(ItemStack[] inputs, ItemStack output, int chopTime, boolean useRoller) {
            this.inputs = inputs;
            this.output = output;
            if (chopTime <= 0) {
                this.chopTime = 1;
            } else {
                this.chopTime = chopTime;
            }
            this.useRoller = useRoller;
        }


        @Override
        public void apply() {
            CuttingBoardRecipe cuttingBoardRecipe = new CuttingBoardRecipe(inputs[1], inputs[2], inputs[3], output, chopTime, useRoller);
            CuttingBoardRecipeRepository.addRecipe(cuttingBoardRecipe);
        }

        @Override
        public String describe() {
            StringBuilder builder = new StringBuilder("");
            for (ItemStack stack : inputs) {
                builder.append(stack.getDisplayName());
            }
            return "Added Cutting-Board Recipe: " + "[" + builder + "]" + " -> " + output.getDisplayName() + " , With Chopping Time: " + chopTime + " , Uses Roller: " + useRoller;
        }
    }
}
