package com.tabooicicle.frolicdangit.compat;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import com.tabooicicle.frolicdangit.recipe.ModRecipes;
import com.tabooicicle.frolicdangit.recipe.PearlProcessorRecipe;
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

        List<PearlProcessorRecipe> pearlProcessorRecipes = new ArrayList<>();
        for (RecipeHolder<PearlProcessorRecipe> holder : Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ModRecipes.PEARL_PROCESSOR_TYPE.get())) {
            pearlProcessorRecipes.add(holder.value());
        }

        registration.addRecipes(PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE, pearlProcessorRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PearlProcessorScreen.class, 74, 30, 22, 20, //change for click area of gui
                PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PEARL_PROCESSOR.asItem()),
                PearlProcessorRecipeCategory.PEARL_PROCESSOR_RECIPE_RECIPE_TYPE);
    }
}
