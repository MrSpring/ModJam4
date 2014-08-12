package dk.mrspring.kitchen.entity;

import dk.mrspring.kitchen.cup.Liquid;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCup extends Entity
{
    int percentFull = 0;
    Liquid holding = Liquid.EMPTY;
    int lifeTime = 0;

    public EntityCup(World par1World)
    {
        super(par1World);
        this.preventEntitySpawning = true;
    }

    public static EntityCup createCupEntity(World world, double x, double y, double z, Liquid liquid, int percentFull)
    {
        EntityCup entityCup = new EntityCup(world);
        entityCup.posX = x;
        entityCup.posY = y;
        entityCup.posZ = z;
        entityCup.setLiquid(liquid, percentFull);
        return entityCup;
    }

    @Override
    public void onUpdate()
    {
        //if (this.lifeTime > 400)
            //this.kill();
        //else
        //{
            super.onUpdate();

            this.motionY -= 0.03999999910593033D;
            this.lifeTime++;

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            System.out.println(" Updating, position: X:" + this.posX + ", Y:" + this.posY + ", Z:" + this.posZ);
        //}

        /*

        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.lifeTime++;

        if (this.lifeTime > 400)
        {
            this.kill();
        }

        if (this.onGround)
        {
            this.motionY *= -0.5D;
        }

        //this.dataWatcher.addObject(2, );

        System.out.println(" Updating, position: X:" + this.posX + ", Y:" + this.posY + ", Z:" + this.posZ);*/
    }

    /*@Override
    public void onUpdate()
    {
        this.lifeTime++;

        if (lifeTime > 400)
        {
            this.kill();
            return;
        }

        this.motionY -= 0.03999999910593033D;
        if (!this.onGround)
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        else this.motionY = 0D;
        System.out.println(" Updating, position: X:" + this.posX + ", Y:" + this.posY + ", Z:" + this.posZ);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }*/

    @Override
    protected void entityInit()
    {

    }

    protected void setLiquid(Liquid liquid, int percentFull)
    {
        this.holding = liquid;
        this.percentFull = percentFull;
    }

    public int getPercentFull()
    {
        return this.percentFull;
    }

    public Liquid getLiquid()
    {
        return this.holding;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        NBTTagCompound cupInfo = var1.getCompoundTag("CupInfo");

        if (cupInfo != null)
        {
            if (cupInfo.hasKey("PercentFull"))
                this.percentFull = cupInfo.getInteger("PercentFull");
            if (cupInfo.hasKey("Liquid"))
                this.holding = Liquid.valueOf(cupInfo.getString("Liquid"));
        } else
        {
            this.percentFull = 0;
            this.holding = Liquid.EMPTY;
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        NBTTagCompound cupInfo = new NBTTagCompound();

        cupInfo.setString("Liquid", this.holding.name());
        cupInfo.setInteger("PercentFull", this.percentFull);

        var1.setTag("CupInfo", cupInfo);
    }
}
