package lanse505.resurgancetweaks.compat.aquamunda.tweakers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lanse505.resurgancetweaks.ResurganceTweaks;
import mcjty.aquamunda.recipes.CookerRecipe;
import mcjty.aquamunda.recipes.CookerRecipeRepository;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.resurgancetweaks.Cooker")
@ZenRegister
public class CookerTweaker {
    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output, @Optional String outputSoup, int cookTime) {
        String outSoup = outputSoup;

        if (input == null || input.isEmpty()) {
            CraftTweakerAPI.logError("Input can not be Null or Empty");
        } else if (output == null || output.isEmpty()) {
            CraftTweakerAPI.logError("Output can not be Null or Empty");
        } else if (outputSoup == null) {
            outSoup = "-";
        } else if (cookTime <= 0) {
            CraftTweakerAPI.logError("Cook Time can not be 0 or lower than 0");
        } else {
            ResurganceTweaks.LATE_ADDITIONS.add(new Add(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output), outSoup, cookTime));
        }
    }

    public static class Add implements IAction {
        ItemStack input;
        ItemStack output;
        String outputSoup;
        int cookTime;

        public Add(ItemStack input, ItemStack output, String outputSoup, int cookTime) {
            this.input = input;
            this.output = output;
            this.outputSoup = outputSoup;
            this.cookTime = cookTime;
        }

        @Override
        public void apply() {
            CookerRecipe cookerRecipe = new CookerRecipe(input, output, outputSoup, cookTime);
            CookerRecipeRepository.addRecipe(cookerRecipe);
        }

        @Override
        public String describe() {
            return "Added Cooker Recipe: " + input.getDisplayName() + " -> " + output.getDisplayName() + " , With Output Soup: " + outputSoup + " , Cook Time: " + cookTime;
        }
    }
}
