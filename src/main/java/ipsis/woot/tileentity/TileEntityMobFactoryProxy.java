package ipsis.woot.tileentity;

import ipsis.woot.init.ModBlocks;
import ipsis.woot.tileentity.ng.farmblocks.IFarmBlockConnection;
import ipsis.woot.tileentity.ng.farmblocks.IFarmBlockMaster;
import ipsis.woot.tileentity.ng.farmblocks.IFarmBlockProxy;
import ipsis.woot.tileentity.ng.farmblocks.ProxyMasterLocator;
import ipsis.woot.util.WorldHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityMobFactoryProxy extends TileEntity implements IFarmBlockConnection, IFarmBlockProxy {

    private IFarmBlockMaster farmBlockMaster = null;

    public boolean hasMaster() { return farmBlockMaster != null; }

    public void blockAdded() {

        IFarmBlockMaster tmpMaster = new ProxyMasterLocator().findMaster(getWorld(), getPos(), this);
        if (tmpMaster != null)
            tmpMaster.interruptFarmProxy();
    }

    @Override
    public void invalidate() {

        // Master will be set by the farm when it finds the block
        if (hasMaster())
            farmBlockMaster.interruptFarmProxy();
    }

    /**
     * Client stuff
     */

    boolean isClientFormed;
    public boolean isClientFormed() { return isClientFormed; }

    /**
     * ChunkData packet handling
     * Currently calls readFromNBT on reception
     */
    @Override
    public NBTTagCompound getUpdateTag() {

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("formed", farmBlockMaster != null);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {

        super.handleUpdateTag(tag);
        isClientFormed = tag.getBoolean("formed");
    }

    /**
     * UpdateTileEntity packet handling
     */

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        NBTTagCompound nbtTagCompound = getUpdateTag();
        return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {

        handleUpdateTag(pkt.getNbtCompound());
        WorldHelper.updateClient(getWorld(), getPos());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (hasMaster())
            return farmBlockMaster.hasCapability(capability, facing);

        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (hasMaster())
            return farmBlockMaster.getCapability(capability, facing);

        return super.getCapability(capability, facing);
    }

    /**
     * IFarmBlockConnection
     */
    public void clearMaster() {

        if (farmBlockMaster != null) {
            farmBlockMaster = null;

            WorldHelper.updateClient(getWorld(), getPos());
            WorldHelper.updateNeighbors(getWorld(), getPos(), ModBlocks.blockProxy);
        }
    }

    public void setMaster(IFarmBlockMaster master) {

        if (farmBlockMaster != master) {
            farmBlockMaster = master;

            WorldHelper.updateClient(getWorld(), getPos());
            WorldHelper.updateNeighbors(getWorld(), getPos(), ModBlocks.blockProxy);
        }
    }

    public BlockPos getStructurePos() {
        return getPos();
    }

    /**
     * IFarmBlockProxy
     */
    public boolean isProxy() {

        return true;
    }

    public boolean isExtender() {

        return false;
    }
}
