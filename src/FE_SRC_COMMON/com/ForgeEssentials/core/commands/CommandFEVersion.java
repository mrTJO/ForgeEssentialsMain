package com.ForgeEssentials.core.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import com.ForgeEssentials.util.Version;

public class CommandFEVersion extends ForgeEssentialsCommandBase
{

	@Override
	public String getCommandName()
	{
		return "feversion";
	}

	@Override
	public void processCommandPlayer(EntityPlayer player, String[] args)
	{
		player.addChatMessage("You are currently running ForgeEssentials version " + Version.getVersion());
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
		sender.sendChatToPlayer("You are currently running ForgeEssentials version " + Version.getVersion());
	}

	@Override
	public String getSyntaxConsole()
	{
		return "/feversion";
	}

	@Override
	public String getSyntaxPlayer(EntityPlayer player)
	{
		return "/feversion";
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return true;
	}

	@Override
	public boolean canPlayerUseCommand(EntityPlayer player)
	{
		return checkCommandPerm(player);
	}

	@Override
	public String getInfoConsole()
	{
		return "Get the current version of ForgeEssentials";
	}

	@Override
	public String getInfoPlayer(EntityPlayer player)
	{
		return "Get the current version of ForgeEssentials";
	}

	@Override
	public String getCommandPerm()
	{
		return "ForgeEssentials.CoreCommands."+getCommandName();
	}
}