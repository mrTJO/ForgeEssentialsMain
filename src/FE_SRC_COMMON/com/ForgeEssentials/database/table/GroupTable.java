package com.ForgeEssentials.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="groups", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class GroupTable extends BaseTable
{
	private int _groupId;
	private String _groupName;
	private GroupTable _groupParent;
	private short _groupPriority;
	private ZoneTable _groupZone;
	private String _groupPrefix;
	private String _groupSuffix;
	
	public GroupTable()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "groupID", nullable = false)
	public int getGroupId()
	{
		return _groupId;
	}
	
	@SuppressWarnings("unused")
	private void setGroupId(int groupId)
	{
		_groupId = groupId;
	}
	
	@Column(name = "name", length = 40, nullable = false)
	public String getGroupName()
	{
		return _groupName;
	}
	
	public void setGroupName(String groupName)
	{
		_groupName = groupName;
	}
	
	@OneToOne
	@JoinColumn(name = "parent", referencedColumnName = "groupID")
	public GroupTable getGroupParent()
	{
		return _groupParent;
	}
	
	public void setGroupParent(GroupTable groupParent)
	{
		_groupParent = groupParent;
	}
	
	@Column(name = "priority", nullable = false)
	public short getGroupPriority()
	{
		return _groupPriority;
	}
	
	public void setGroupPriority(short groupPriority)
	{
		_groupPriority = groupPriority;
	}
	
	@ManyToOne
	@JoinColumn(name = "zone", referencedColumnName = "zoneID")
	public ZoneTable getGroupZone()
	{
		return _groupZone;
	}
	
	public void setGroupZone(ZoneTable groupZone)
	{
		_groupZone = groupZone;
	}
	
	@Column(name = "prefix", length = 20, columnDefinition = "varchar(20) default ''")
	public String getGroupPrefix()
	{
		return _groupPrefix;
	}
	
	public void setGroupPrefix(String groupPrefix)
	{
		_groupPrefix = groupPrefix;
	}
	
	@Column(name = "suffix", length = 20, columnDefinition = "varchar(20) default ''")
	public String getGroupSuffix()
	{
		return _groupSuffix;
	}
	
	public void setGroupSuffix(String groupSuffix)
	{
		_groupSuffix = groupSuffix;
	}
}
