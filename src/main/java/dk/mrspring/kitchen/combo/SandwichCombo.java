package dk.mrspring.kitchen.combo;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class SandwichCombo
{
	protected String name = "default";

	protected boolean useTranslator = true;

	protected String unlocalizedToolTip = "Default Tooltip! Change in the Config!";
	protected String unlocalizedName = "Default Name! Change in the Config!";
	protected String unlocalizedDescription = "Default Description! Change in the Config!";

	public SandwichCombo()
	{

	}

	public static SandwichCombo getFromConfig(Configuration configuration)
	{
		SandwichCombo combo = new SandwichCombo();

		configuration.load();



		configuration.save();
	}

	public SandwichCombo(String translatableName)
	{
		this.useTranslator = true;
		this.name = translatableName;
	}

	public SandwichCombo setLocalizableName(String name)
	{
		this.name = name;
		this.useTranslator = true;
		return this;
	}

	public String getUnlocalizedDescription()
	{
		return this.unlocalizedDescription;
	}

	public String getUnlocalizedToolTip()
	{
		return this.unlocalizedToolTip;
	}

	public String getUnlocalizedName()
	{
		return this.unlocalizedName;
	}

	public void setUnlocalizedDescription(String unlocalizedDescription)
	{
		this.unlocalizedDescription = unlocalizedDescription;
	}

	public void setUnlocalizedToolTip(String unlocalizedToolTip)
	{
		this.unlocalizedToolTip = unlocalizedToolTip;
	}

	public String getDescriptuon()
	{
		if (!this.useTranslator)
			return this.getUnlocalizedDescription();
		else
		{
			if (StatCollector.canTranslate("combo." + this.name + ".description"))
				return StatCollector.translateToLocal("combo." + this.name + ".description");
			else
				return this.getUnlocalizedDescription();
		}
	}

	public String getTooltip()
	{
		if (!this.useTranslator)
			return this.getUnlocalizedToolTip();
		else
		{
			if (StatCollector.canTranslate("combo." + this.name + ".tool_tip"));
				return StatCollector.translateToLocal("combo." + this.name + ".tool_tip");
			else
				return this.getUnlocalizedToolTip();
		}
	}

	public String getName()
	{
		if (!this.useTranslator)
			return this.getUnlocalizedName();
		else
		{
			if (StatCollector.canTranslate("combo." + this.name + ".name"))
				return StatCollector.translateToLocal("combo." + this.name + ".name");
			else
				return this.getUnlocalizedName();
		}
	}
}
