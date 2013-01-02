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
@Table(name="groupConnectors", uniqueConstraints =
{
	@UniqueConstraint(columnNames = { "groupID" }),
	@UniqueConstraint(columnNames = { "playerID" }),
	@UniqueConstraint(columnNames = { "zoneID" })
})
public class GroupConnectorTable extends BaseTable
{
	private int _connectorId;
	private GroupTable _groupTable;
	private PlayerTable _playerTable;
	private ZoneTable _zoneTable;
	
	public GroupConnectorTable()
	{
		
	}
	
	public GroupConnectorTable(GroupTable groupTable, PlayerTable playerTable, ZoneTable zoneTable)
	{
		_groupTable = groupTable;
		_playerTable = playerTable;
		_zoneTable = zoneTable;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "connectorID", nullable = false)
	public int getConnectorId()
	{
		return _connectorId;
	}
	
	@SuppressWarnings("unused")
	private void setConnectorId(int connectorId)
	{
		_connectorId = connectorId;
	}
	
	@ManyToOne
	@JoinColumn(name = "groupID", referencedColumnName = "groupID")
	public GroupTable getGroupTable()
	{
		return _groupTable;
	}
	
	public void setGroupTable(GroupTable groupTable)
	{
		_groupTable = groupTable;
	}
	
	@ManyToOne
	@JoinColumn(name = "playerID", referencedColumnName = "playerID")
	public PlayerTable getPlayerTable()
	{
		return _playerTable;
	}
	
	public void setPlayerTable(PlayerTable playerTable)
	{
		_playerTable = playerTable;
	}
	
	@ManyToOne
	@JoinColumn(name = "zoneID", referencedColumnName = "zoneID")
	public ZoneTable getZoneTable()
	{
		return _zoneTable;
	}
	
	public void setZoneTable(ZoneTable zoneTable)
	{
		_zoneTable = zoneTable;
	}
}
