package ipsis.woot.item;

import ipsis.Woot;
import ipsis.woot.oss.client.ModelHelper;
import ipsis.woot.util.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWoot extends Item {

    public ItemWoot(String name) {
        super();
        setCreativeTab(Woot.tabWoot);
    }

    @SideOnly(Side.CLIENT)
    public void initModel(Item item, String name) {

        ModelHelper.registerItem(item, name);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

    }
}
