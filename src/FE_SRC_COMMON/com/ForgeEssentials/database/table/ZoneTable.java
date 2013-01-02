package com.ForgeEssentials.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "zones", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class ZoneTable extends BaseTable
{
	private int _zoneId;
	private String _zoneName;
	private short _zoneDimension;
	private ZoneTable _zoneParent;
	
	public ZoneTable()
	{
		
	}
	
	public ZoneTable(String zoneName, short zoneDimension)
	{
		_zoneName = zoneName;
		_zoneDimension = zoneDimension;
	}
	
	public ZoneTable(String zoneName, short zoneDimension, ZoneTable zoneParent)
	{
		_zoneName = zoneName;
		_zoneDimension = zoneDimension;
		_zoneParent = zoneParent;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "zoneID", nullable = false)
	public int getZoneId()
	{
		return _zoneId;
	}
	
	@SuppressWarnings("unused")
	private void setZoneId(int zoneId)
	{
		_zoneId = zoneId;
	}
	
	@Column(name = "name", length = 40, nullable = false)
	public String getZoneName()
	{
		return _zoneName;
	}
	
	public void setZoneName(String zoneName)
	{
		_zoneName = zoneName;
	}
	
	@Column(name = "dimension", nullable = false)
	public short getZoneDimension()
	{
		return _zoneDimension;
	}
	
	public void setZoneDimension(short zoneDimension)
	{
		_zoneDimension = zoneDimension;
	}
	
	/**
	 * Get the Parent Zone for This Zone
	 * @return Parent Zone
	 */
	
	@ManyToOne
	@JoinColumn(name = "parent", referencedColumnName = "zoneID")
	public ZoneTable getZoneParent()
	{
		return _zoneParent;
	}
	
	/**
	 * Set the Parent Zone for this Zone
	 * @param zoneParent Parent Zone
	 */
	public void setZoneParent(ZoneTable zoneParent)
	{
		_zoneParent = zoneParent;
	}
}
