package dk.mrspring.kitchen.item.board;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

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
     */
    public void onRightClicked(NBTTagCompound specialTagInfo, ItemStack item);

    /***
     * Gets called when the item is added to the board. Add whatever info you need to the NBTTagCompound that you may need in the right-click event.
     * @param specialTagInfo The special tag info. Add right-click count on this tag to be stored for next right-click, or changing the model in some way.
     * @param item The Item that was added to the board. Metadata, tags and other stuff can be salvaged if needed.
     */
    public void onAddedToBoard(NBTTagCompound specialTagInfo, ItemStack item);

	/***
	 * @param specialTagInfo Used to determine model based on special info. Is null if item is not the top-most model.
	 * @param itemIndex The index the Item has in the list.
	 * @param item The ItemStack being rendered.
	 * @param itemStacks Item currently being held by the Board. Use to make models special if another layer is on top of the current one.
	 * @return Returns the model for rendering the Item on the board. Null is it has no model.
	 */
	public ModelBase getModel(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks);

	/***
	 * @param specialTagInfo Used to determine model's height based on special info. Is null if item is not the top-most model.
	 * @param itemIndex The index the Item has in the list.
	 * @param item The ItemStack being rendered.
	 * @param itemStacks Item currently being held by the Board. Use to make model heights special if another layer is on top of the current one.
	 * @return Returns the height of the model. Return 1 for normal Item.
	 */
	public double getRenderHeight(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks);

    /***
     * @param specialTagInfo The special tag info of the bellow item.
     * @param toAdd The Item that the player is trying to add the board.
     * @param topItem The top most item, the toAdd will be added on top of.
     * @return Returns true if the Item can be added on top. False if not. See ItemButter for example.
     */
    public boolean canAddOnTop(NBTTagCompound specialTagInfo, ItemStack toAdd, ItemStack topItem);

	/***
	 * @param specialTagInfo The special tag info. Null if nothing is stored. Remember to null-check!
	 * @param toRemove The ItemStack the player is trying to remove. Used for metadata/NBT special stuff.
	 * @return Returns true if the Item can be removed from the Board.
	 */
	public boolean canBeRemoved(NBTTagCompound specialTagInfo, ItemStack toRemove);

	/***
	 * Gets called when the item is removed from the board, or when the Board is destroyed, and determines whether the item should be spawned in the World.
	 * @param specialTagInfo The special tag info. Null if nothing is stored. Remember to null-check!
	 * @param removed The removed item. For metadata stuff.
	 * @return Returns true if the Item should be spawned in the World when it's removed from the Board.
	 */
	public boolean dropItem(NBTTagCompound specialTagInfo, ItemStack removed);
}
