package com.ForgeEssentials.WorldControl;

//Depreciated
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.ForgeEssentials.core.PlayerInfo;
import com.ForgeEssentials.util.FEChatFormatCodes;
import com.ForgeEssentials.util.AreaSelector.Point;

import cpw.mods.fml.common.FMLCommonHandler;

public class WandController
{
	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void playerInteractEvent(PlayerInteractEvent event)
	{
		// only server events please.
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			return;

		// get info now rather than later
		EntityPlayer player = event.entityPlayer;
		PlayerInfo info = PlayerInfo.getPlayerInfo(player);
		
		int id = player.getCurrentEquippedItem() == null ? 0 : player.getCurrentEquippedItem().itemID;
		int damage = 0;
		if (id != 0 && player.getCurrentEquippedItem().getHasSubtypes())
			damage = player.getCurrentEquippedItem().getItemDamage();
		
		if (id != info.wandID || !info.wandEnabled || damage != info.wandDmg)
			return; // wand does not activate

		// left Click
		if (event.action.equals(PlayerInteractEvent.Action.LEFT_CLICK_BLOCK))
		{
			info.setPoint1(new Point(event.x, event.y, event.z));
			player.addChatMessage(FEChatFormatCodes.PURPLE + "Pos1 set to " + event.x + ", " + event.y + ", " + event.z);
			event.setCanceled(true);
		}
		// right Click
		else if (event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
		{
			info.setPoint2(new Point(event.x, event.y, event.z));
			player.addChatMessage(FEChatFormatCodes.PURPLE + "Pos2 set to " + event.x + ", " + event.y + ", " + event.z);
			event.setCanceled(true);
		}
	}
}
