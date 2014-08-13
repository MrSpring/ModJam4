package dk.mrspring.kitchen.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCup extends Entity
{
    public EntityCup(World par1World)
    {
        super(par1World);
    }

    public EntityCup(World world, double x, double y, double z)
    {
        super(world);

        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.motionY -= 0.03999999910593033D;

        if (onGround)
            this.motionY = 0D;

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.posX = var1.getDouble("X");
        this.posY = var1.getDouble("Y");
        this.posZ = var1.getDouble("Z");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setDouble("X", this.posX);
        var1.setDouble("Y", this.posY);
        var1.setDouble("Z", this.posZ);
    }
}
