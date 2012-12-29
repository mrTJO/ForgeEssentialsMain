package com.ForgeEssentials.coremod;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

// Kindly do not reference any FE classes outside the coremod package in this class.

public class FEModContainer extends DummyModContainer
{

	public boolean	mod;

	public FEModContainer()
	{
		super(new ModMetadata());
		/* ModMetadata is the same as mcmod.info */
		ModMetadata myMeta = super.getMetadata();
		myMeta.authorList = Arrays.asList(new String[] { "See the ForgeEssentials author list." });
		myMeta.description = "The preloader for Forge Essentials.";
		myMeta.modId = "FEPreLoader";
		myMeta.version = "0.1.0";
		myMeta.name = "Forge Essentials|PreLoader";
		myMeta.url = "";
	}

	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}

}