package com.ForgeEssentials.WorldBorder.Effects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class executecommand implements IEffect
{
	private String command = "/say %p Go back while you still can!";
	
	@Override
	public void registerConfig(Configuration config, String category)
	{
		command = config.get(category, "Command", command, "%p gets replaced with the players username").value;
	}

	@Override
	public void execute(EntityPlayerMP player) 
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			DedicatedServer.getServer().executeCommand(command.replaceAll("%p", player.username));
		}
	}
}
