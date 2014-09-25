package dk.mrspring.kitchen.item.jam;

/**
 * Created by MrSpring on 25-09-2014 for ModJam4.
 */
public enum Jam
{
    EMPTY(0,0,0),
	STRAWBERRY(247, 35, 12),
	APPLE(219, 247, 170);

	final float r, g, b;

	Jam(float red, float green, float blue)
	{
		this.r = red;
		this.g = green;
		this.b = blue;
	}

    public float getRed()
    {
        return r;
    }

    public float getGreen()
    {
        return g;
    }

    public float getBlue()
    {
        return b;
    }

    Jam(int r, int g, int b)
	{
		this(((float) r) / 255, ((float) g / 255), ((float) b) / 255);
	}


}
