package dk.mrspring.kitchen.item.board;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IBoardable
{
    /***
     * If the item has a special right click event. For example: Butter requires you to right-click it multiple times to spread it out on your bread.
     * @param specialTagInfo The special tag info.
     * @return Returns whether to call the onRightClicked method.
     */
    public boolean hasSpecialRightClick(NBTTagCompound specialTagInfo);

    /***
     * Only get's called if hasSpecialRightClick returns true. Handles the right click event. For example: Butter requires you to right-click it multiple times to spread it out on your bread.
     * @param specialTagInfo The special tag info. Add right-click count on this tag to be stored for next right-click, or changing the model in some way.
     * @param item The item being right-clicked with.
     * @return Returns true if the action took place, false if not.
     */
    public boolean onRightClicked(NBTTagCompound specialTagInfo, ItemStack item);

    /***
     * Gets called when the item is added to the board. Add whatever info you need to the NBTTagCompound that you may need in the right-click event.
     * @param specialTagInfo The special tag info. Add right-click count on this tag to be stored for next right-click, or changing the model in some way.
     * @param item The Item that was added to the board. Metadata, tags and other stuff can be salvaged if needed.
     */
    public void onAddedToBoard(NBTTagCompound specialTagInfo, ItemStack item);
}
