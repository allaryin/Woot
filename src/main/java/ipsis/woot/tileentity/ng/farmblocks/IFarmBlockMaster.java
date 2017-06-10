package ipsis.woot.tileentity.ng.farmblocks;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public interface IFarmBlockMaster {

    void interruptFarmStructure();
    void interruptFarmUpgrade();
    void interruptFarmProxy();
    boolean hasCapability(Capability<?> capability, EnumFacing facing);
    <T> T getCapability(Capability<T> capability, EnumFacing facing);
}
