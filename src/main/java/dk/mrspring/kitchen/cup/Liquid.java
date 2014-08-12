package dk.mrspring.kitchen.cup;

public enum Liquid
{
    EMPTY(0F, 0F, 0F, 0F),
    WATER(0F, 0F, 1F, .9F);

    final float r, g, b, a;

    private Liquid(float red, float green, float blue, float alpha)
    {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
    }

    public float getRed()
    {
        return this.r;
    }

    public float getGreen()
    {
        return this.g;
    }

    public float getBlue()
    {
        return this.b;
    }

    public float getAlpha()
    {
        return this.a;
    }
}
