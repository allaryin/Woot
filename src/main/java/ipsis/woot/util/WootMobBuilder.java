package ipsis.woot.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class WootMobBuilder {

    public static @Nonnull
    WootMob create(String name, String displayName) {

        WootMobName wootMobName = WootMobNameBuilder.create(name);
        return new WootMob(wootMobName, displayName);
    }

    public static @Nonnull WootMob create(@Nonnull EntityLiving entityLiving) {

        WootMobName wootMobName = WootMobNameBuilder.create(entityLiving);
        return new WootMob(wootMobName, entityLiving.getName());
    }

    private final static String NBT_WOOT_MOB_DISPLAY_NAME = "wootMobDisplayName";
    private final static String NBT_WOOT_MOB_DEATHS = "wootMobDeaths";
    public static void writeToNBT(WootMob wootMob, NBTTagCompound tagCompound) {

        if (!wootMob.isValid())
            return;

        if (tagCompound == null)
            tagCompound = new NBTTagCompound();

        tagCompound.setString(NBT_WOOT_MOB_DISPLAY_NAME, wootMob.getDisplayName());
        tagCompound.setInteger(NBT_WOOT_MOB_DEATHS, wootMob.getDeaths());
        WootMobNameBuilder.writeToNBT(wootMob.getWootMobName(), tagCompound);
    }

    public static @Nonnull WootMob create(NBTTagCompound tagCompound) {

        if (tagCompound == null ||
            !tagCompound.hasKey(NBT_WOOT_MOB_DISPLAY_NAME) ||
            !tagCompound.hasKey(NBT_WOOT_MOB_DEATHS))
            return new WootMob();

        return new WootMob(WootMobNameBuilder.create(tagCompound), tagCompound.getString(NBT_WOOT_MOB_DISPLAY_NAME), tagCompound.getInteger(NBT_WOOT_MOB_DEATHS));
    }
}
