package ipsis.woot.event;

import ipsis.Woot;
import ipsis.woot.reference.Settings;
import ipsis.woot.util.DamageSourceWoot;
import ipsis.woot.util.MobUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerLivingDropsEvent {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingDropsEvent(LivingDropsEvent e) {

        DamageSourceWoot damageSourceWoot = DamageSourceWoot.getDamageSource(e.source.damageType);
        if (damageSourceWoot != null) {

            /**
             *  Killed in one of our spawners, but we dont care which one.
             *  We only store the drop information.
             */
            String mobID = MobUtil.getMobName((EntityLiving)e.entity);
            if (!Woot.spawnerManager.isFull(mobID, damageSourceWoot.getEnchantKey()))
                Woot.spawnerManager.addDrops(mobID, damageSourceWoot.getEnchantKey(), e.drops);

            e.setCanceled(true);
        } else if (!Settings.strictFactorySpawns) {

            /**
             * Convert the non-spawner kill into a damage source if possible
             */
            String mobID = MobUtil.getMobName((EntityLiving)e.entity);
            damageSourceWoot = DamageSourceWoot.getDamageSource(e.lootingLevel);
            if (damageSourceWoot != null && !Woot.spawnerManager.isFull(mobID, damageSourceWoot.getEnchantKey()))
                Woot.spawnerManager.addDrops(mobID, damageSourceWoot.getEnchantKey(), e.drops);
        }
    }
}
