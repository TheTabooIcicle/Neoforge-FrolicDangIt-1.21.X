package com.tabooicicle.frolicdangit.event;

import com.tabooicicle.frolicdangit.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@EventBusSubscriber(modid = "frolicdangitptone")
public class ModEffectEvents {


    @SubscribeEvent
    public static void onViewportFog(ViewportEvent.RenderFog event) {
        if (event.getCamera().getEntity() instanceof LivingEntity living &&
                living.hasEffect(ModEffects.SNEAKY_BLIND)) {

            event.setNearPlaneDistance(0.0F);

            event.setFarPlaneDistance(5.0F);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingVisibility(LivingEvent.LivingVisibilityEvent event) {
        if (event.getEntity().hasEffect(ModEffects.SNEAKY_INVIS)) {
            event.modifyVisibility(0.07);
        }
    }
}