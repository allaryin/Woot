package ipsis.woot.plugins.guideapi;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.category.CategoryItemStack;
import ipsis.woot.plugins.guideapi.book.CategoryIntroduction;
import ipsis.woot.reference.Reference;
import ipsis.woot.util.StringHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

@GuideBook
public class GuideWoot implements IGuideBook {

    public static final Book GUIDE_BOOK = new Book();

    @Nullable
    @Override
    public Book buildBook() {

        GUIDE_BOOK.setAuthor("Ipsis");
        GUIDE_BOOK.setColor(Color.GREEN);
        GUIDE_BOOK.setDisplayName(StringHelper.localize("guide.woot:display"));
        GUIDE_BOOK.setTitle(StringHelper.localize("guide.woot:title"));
        GUIDE_BOOK.setWelcomeMessage(StringHelper.localize("guide.woot:welcome"));
        GUIDE_BOOK.setRegistryName(new ResourceLocation(Reference.MOD_ID, "guide"));
        return GUIDE_BOOK;
    }

    @Override
    public void handlePost(@Nonnull ItemStack bookStack) {

        GUIDE_BOOK.addCategory(new CategoryItemStack(CategoryIntroduction.buildCategory(), "guide.woot:category.introduction", new ItemStack(Items.SKULL, 1)));
    }

    @Nullable
    @Override
    public IRecipe getRecipe(@Nonnull ItemStack bookStack) {

        return new ShapelessOreRecipe(new ResourceLocation(Reference.MOD_ID, "guide"),
                GuideAPI.getStackFromBook(GUIDE_BOOK),
                new ItemStack(Items.BOOK), new ItemStack(Items.ROTTEN_FLESH)).setRegistryName(GUIDE_BOOK.getRegistryName());
    }
}
