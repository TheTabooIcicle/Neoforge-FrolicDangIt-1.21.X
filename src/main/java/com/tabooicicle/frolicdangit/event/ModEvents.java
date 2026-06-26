package com.tabooicicle.frolicdangit.event;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.item.ModItems;
import com.tabooicicle.frolicdangit.potion.ModPotions;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = FrolicDangIt.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.FERMENTED_ELDER_EYE.asItem(), ModPotions.ESSENCE_OF_SEEKING);
    }


    public static void onViewportFog(ViewportEvent.RenderFog renderFog) {

    }
}
