package ipsis.woot.plugins.bloodmagic;

//import WayofTime.bloodmagic.api.BloodMagicAPI;
//import WayofTime.bloodmagic.api.Constants;
//import WayofTime.bloodmagic.api.registry.RitualRegistry;

//public class BloodMagic {
//
//    public static final String BM_MODID = "bloodmagic";
//
//    public static FluidStack fluidOutput = null;
//
//    @Optional.Method(modid = BM_MODID)
//    public static void init() {
//
//        RitualRegistry.registerRitual(new RitualInfernalMachine());
//        fluidOutput = new FluidStack(BloodMagicAPI.getLifeEssence(), 0);
//    }
//
//    @Optional.Method(modid = BM_MODID)
//    public static void initRecipes() {
//
//        ItemStack out1 = new ItemStack(ModBlocks.blockUpgradeB, 1, BlockMobFactoryUpgradeB.getBlockSplitMeta(EnumSpawnerUpgrade.BLOODMAGIC_I));
//        ItemStack out2 = new ItemStack(ModBlocks.blockUpgradeB, 1, BlockMobFactoryUpgradeB.getBlockSplitMeta(EnumSpawnerUpgrade.BLOODMAGIC_II));
//        ItemStack out3 = new ItemStack(ModBlocks.blockUpgradeB, 1, BlockMobFactoryUpgradeB.getBlockSplitMeta(EnumSpawnerUpgrade.BLOODMAGIC_III));
//
//        Block bloodRune = BloodMagicAPI.getBlock(Constants.BloodMagicBlock.BLOOD_RUNE);
//        ItemStack sacrificeRune = new ItemStack(bloodRune, 1, 3);
//
//
//        GameRegistry.addRecipe(
//                new ShapedOreRecipe(
//                        out1,
//                        "tet", "iui", "tit",
//                        't', Blocks.QUARTZ_BLOCK,
//                        'e', sacrificeRune,
//                        'u', new ItemStack(ModItems.itemFactoryUpgrade, 1, ItemFactoryUpgrade.EnumUpgradeTier.TIER_I.getMeta()),
//                        'i', "dustRedstone"
//                )
//        );
//
//        GameRegistry.addRecipe(
//                new ShapedOreRecipe(
//                        out2,
//                        "tet", "iui", "tet",
//                        't', Items.PRISMARINE_SHARD,
//                        'u', new ItemStack(ModItems.itemFactoryUpgrade, 1, ItemFactoryUpgrade.EnumUpgradeTier.TIER_II.getMeta()),
//                        'e', sacrificeRune,
//                        'i', "blockRedstone"
//                )
//        );
//
//        GameRegistry.addRecipe(
//                new ShapedOreRecipe(
//                        out3,
//                        "tet", "eue", "tet",
//                        't', Blocks.END_STONE,
//                        'u', new ItemStack(ModItems.itemFactoryUpgrade, 1, ItemFactoryUpgrade.EnumUpgradeTier.TIER_III.getMeta()),
//                        'e', sacrificeRune
//                )
//        );
//    }
//}
