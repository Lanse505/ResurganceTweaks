package lanse505.resurgancetweaks.compat.aquamunda.tweakers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lanse505.resurgancetweaks.ResurganceTweaks;
import mcjty.aquamunda.recipes.GrindstoneRecipe;
import mcjty.aquamunda.recipes.GrindstoneRecipeRepository;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.resurgancetweaks.Grindstone")
@ZenRegister
public class GrindstoneTweaker {
    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output, int grindTime) {
        if (input == null || input.isEmpty()) {
            CraftTweakerAPI.logError("Input can not be Null or Empty!");
        } else if (output == null || output.isEmpty()) {
            CraftTweakerAPI.logError("Output can not be Null or Empty!");
        } else if (grindTime <= 0) {
            CraftTweakerAPI.logError("Grind Time can not be lower than 1!");
            CraftTweakerAPI.logError("Grind Time is set to 1!");
        } else {
            ResurganceTweaks.LATE_ADDITIONS.add(new Add(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output), grindTime));
        }
    }

    public static class Add implements IAction {
        ItemStack input;
        ItemStack output;
        int grindTime;

        public Add(ItemStack input, ItemStack output, int grindTime) {
            this.input = input;
            this.output = output;
            if (grindTime <= 0) {
                this.grindTime = 1;
            } else {
                this.grindTime = grindTime;
            }
        }

        @Override
        public void apply() {
            GrindstoneRecipe grindstoneRecipe = new GrindstoneRecipe(input, output, grindTime);
            GrindstoneRecipeRepository.addRecipe(grindstoneRecipe);
        }

        @Override
        public String describe() {
            return "Adding Grind Stone Recipe: " + input.getDisplayName() + " -> " + output.getDisplayName() + " : With Grind Time: " + grindTime;
        }
    }
}
