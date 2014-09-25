package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.jam.Jam;
import dk.mrspring.kitchen.tileentity.TileEntityJamJar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Created by MrSpring on 25-09-2014 for ModJam4.
 */
public class ItemBlockJamJar extends ItemBlock
{
    public ItemBlockJamJar(Block name)
    {
        super(name);
        this.setCreativeTab(Kitchen.instance.tab);
        this.setUnlocalizedName("jam_jar");
        this.setTextureName(ModInfo.modid + ":jam_jar");
        this.setMaxStackSize(1);
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        if (par1ItemStack.getItemDamage() != 0)
        {
            NBTTagCompound compound = par1ItemStack.getTagCompound();
            if (compound != null)
            {
                NBTTagCompound jamInfo = compound.getCompoundTag("JamInfo");
                if (jamInfo != null)
                {
                    Jam jam = Jam.valueOf(jamInfo.getString("JamType"));
                    return StatCollector.translateToLocal("jam." + jam.name().toLowerCase() + ".name") + " " + StatCollector.translateToLocal("item.jam_jar.filled.name");
                }
            }
        }
        return StatCollector.translateToLocal("item.jam_jar.empty.name");
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        if (super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata))
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, stack.getItemDamage());
            TileEntityJamJar tileEntity = TileEntityJamJar.create(stack);
            world.setTileEntity(x, y, z, tileEntity);
            return true;
        } else return false;
    }
}
