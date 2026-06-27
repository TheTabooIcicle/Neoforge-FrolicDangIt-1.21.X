package com.tabooicicle.frolicdangit.compat;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import com.tabooicicle.frolicdangit.recipe.ModRecipes;
import com.tabooicicle.frolicdangit.recipe.PearlProcessorRecipe;
import com.tabooicicle.frolicdangit.screen.custom.PearlProcessorMenu;
import com.tabooicicle.frolicdangit.screen.custom.PearlProcessorScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIFrolicDangitModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PearlProcessorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (Minecraft.getInstance().level == null) return; // wait until world loads to prevent crashes at startup

        List<RecipeHolder<PearlProcessorRecipe>> holders = new ArrayList<>(
                Minecraft.getInstance().level.getRecipeManager()
                        .getAllRecipesFor(ModRecipes.PEARL_PROCESSOR_TYPE.get())
        );

        // sort by pearl level
        holders.sort((h1, h2) -> {
            PearlProcessorRecipe r1 = h1.value();
            PearlProcessorRecipe r2 = h2.value();
            int level1 = getPearlLevel(r1.outputSlot3());
            int level2 = getPearlLevel(r2.outputSlot3());
            return Integer.compare(level1,level2);
        });

        // extract raw recipes from holders for JEI
        List<PearlProcessorRecipe> sortedRecipes = holders.stream()
                .map(RecipeHolder::value)
                .toList();

        // register sorted recipes
        registration.addRecipes(PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE, sortedRecipes);
    }

    // helper method to get pearl levels for sorting
    private int getPearlLevel(ItemStack output) {
        String itemId = output.getItemHolder().getRegisteredName();

        // force eye of ender as final
        if (itemId.equals("minecraft:ender_eye")) {
            return 999; // high number = end
        }
        if (itemId.contains("eye_stage")) {
            String number = itemId.replaceAll(".*eye_stage", "");
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PearlProcessorScreen.class, 125, 15, 20, 20, //change for click area of gui
                PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PEARL_PROCESSOR.asItem()),
                PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE);
    }
}
