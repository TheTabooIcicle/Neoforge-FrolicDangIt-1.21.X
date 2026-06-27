package com.tabooicicle.frolicdangit.compat;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import com.tabooicicle.frolicdangit.potion.ModPotions;
import com.tabooicicle.frolicdangit.recipe.PearlProcessorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PearlProcessorRecipeCategory implements IRecipeCategory<PearlProcessorRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "pearl_processor");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/pearl_processor/pearl_processor_gui_jei.png");

    private static final ResourceLocation OVAL_FILLED =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/burner.png");
    private static final ResourceLocation WHITE_BARS =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/whitebar.png");

    // added oval and bars
    private static final int OVAL_X = 78;
    private static final int OVAL_Y = 93;
    private static final int OVAL_HEIGHT = 6;
    private static final int OVAL_WIDTH = 18;

    private static final int RIGHT_BAR_X = 120;
    private static final int RIGHT_BAR_Y = 71; // bottom of bar

    private static final int RIGHT_BAR2_X = 120;
    private static final int RIGHT_BAR2_Y = 18; // top of bar

    private static final int LEFT_BAR_X = 46;
    private static final int LEFT_BAR_Y = 71; // bottom of bar


    private static final int LEFT_BAR2_X = 46;
    private static final int LEFT_BAR2_Y = 18; // top of bar

    private static final int BAR_WIDTH = 8;
    private static final int BAR_HEIGHT = 24;

    public static final RecipeType<PearlProcessorRecipe> PEARL_PROCESSOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, PearlProcessorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PearlProcessorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,0, 176, 140);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PEARL_PROCESSOR));
    }

    @Override
    public RecipeType<PearlProcessorRecipe> getRecipeType() {
        return PEARL_PROCESSOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.frolicdangitptone.pearl_processor"); // block.{modname}.{blockname} replace {} with ur directory names
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return IRecipeCategory.super.getWidth();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PearlProcessorRecipe recipe, IFocusGroup focuses) { // TallBlockRecipe -> {blockname}Recipe
        // for each ingredient
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 81).addIngredients(recipe.getIngredients().get(0));
        ItemStack bottleStack = new ItemStack(Items.EXPERIENCE_BOTTLE, recipe.slot1Count());
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 74)
                .addItemStack(bottleStack)
                .setSlotName("Bottle");

        // check if potion or item and handle accordingly
        Ingredient slot2Ingredient = recipe.slot2();
        boolean isPotion = false;
        if (!slot2Ingredient.isEmpty()){
            // 1.21+, getItems() returns a cached array of ItemStacks which are stripped of NBT/Components
            ItemStack[] stacks = slot2Ingredient.getItems();
            if (stacks.length > 0) {
                ItemStack first = stacks[0];
                // check if the first possible item in the ingredient list is a vanilla Potion.
                isPotion = (first.getItem() == net.minecraft.world.item.Items.POTION);
            }
        }
        if (isPotion) {
            // new potionstack with empty NBT or components to build for JEI
            net.minecraft.world.item.ItemStack potionStack = new net.minecraft.world.item.ItemStack(Items.POTION, 1);
            var potionHolder = ModPotions.ESSENCE_OF_SEEKING; // holds unique ID of seeking potion

            // by using DataComponentPatch we can safely apply Data Components to an ItemStack ^.^
            DataComponentPatch patch = DataComponentPatch.builder()
                            .set(DataComponents.POTION_CONTENTS,
                                    PotionContents.EMPTY
                                            .withPotion(potionHolder) // replace empty potion with registered potion
                            )
                                    .build();
            // apply patch to potionstack, contains POTION_CONTENTS component with all the info
            potionStack.applyComponents(patch);

            builder.addSlot(RecipeIngredientRole.INPUT,79,16)
                    .addItemStack(potionStack) // addItemStack instead of addIngredients for JEI visibility
                    .setSlotName("Component");
        } else {
            // if not potion
            builder.addSlot(RecipeIngredientRole.INPUT, 79, 16)
                    .addIngredients(slot2Ingredient)
                    .setSlotName("Component");
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 59, 37).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 45).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 99, 37).addIngredients(recipe.getIngredients().get(5));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 144, 74).addItemStack(recipe.getResultItem(null));
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 45).addItemStack(recipe.getResultItem(null));
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 99, 37).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    // we need draw for the animation

    @Override
    public void draw(PearlProcessorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        // redraw oval
        guiGraphics.blit(OVAL_FILLED, OVAL_X, OVAL_Y, 0, 0, OVAL_WIDTH, OVAL_HEIGHT, OVAL_WIDTH, OVAL_HEIGHT);

        Minecraft mc = Minecraft.getInstance();
        long time = mc.level != null ? mc.level.getGameTime() : 0;


        int barHeight = (int) ((time % 144) / 144.0F * 24);
        int delayedBarHeight = (int) (((time + 2) % 144) / 144.0F * BAR_HEIGHT);

        if (barHeight > 0) {
            // down to top bars
            guiGraphics.blit(WHITE_BARS,
                    LEFT_BAR_X, LEFT_BAR_Y - barHeight,
                    0, BAR_HEIGHT - barHeight,
                    BAR_WIDTH, barHeight,
                    BAR_WIDTH, BAR_HEIGHT);

            guiGraphics.blit(WHITE_BARS,
                    RIGHT_BAR_X, RIGHT_BAR_Y - barHeight,
                    0, BAR_HEIGHT - barHeight,
                    BAR_WIDTH, barHeight,
                    BAR_WIDTH, BAR_HEIGHT);
        }

        if (delayedBarHeight > 0) {
            // top down bars
            guiGraphics.blit(WHITE_BARS,
                    LEFT_BAR2_X, LEFT_BAR2_Y,
                    0, 0,
                    BAR_WIDTH, delayedBarHeight,
                    BAR_WIDTH, BAR_HEIGHT);

            guiGraphics.blit(WHITE_BARS,
                    RIGHT_BAR2_X, RIGHT_BAR2_Y,
                    0, 0,
                    BAR_WIDTH, delayedBarHeight,
                    BAR_WIDTH, BAR_HEIGHT);
        }


    }
}
