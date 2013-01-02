package com.ForgeEssentials.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
public class PermissionTable extends BaseTable
{
	private int _permissionId;
	private int _permissionTarget;
	private boolean _permissionIsGroup;
	private String _permissionName;
	private boolean _permissionIsAllowed;
	private ZoneTable _permissionZone;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "permissionId", nullable = false)
	public int getPermissionId()
	{
		return _permissionId;
	}
	
	@SuppressWarnings("unused")
	private void setPermissionId(int permissionId)
	{
		_permissionId = permissionId;
	}
	
	@Column(name = "target", nullable = false)
	public int getPermissionTarget()
	{
		return _permissionTarget;
	}
	
	public void setPermissionTarget(int permissionTarget)
	{
		_permissionTarget = permissionTarget;
	}
	
	@Column(name = "isGroup", nullable = false)
	public boolean getPermissionIsGroup()
	{
		return _permissionIsGroup;
	}
	
	public void setPermissionIsGroup(boolean permissionIsGroup)
	{
		_permissionIsGroup = permissionIsGroup;
	}
	
	@Column(name = "perm", nullable = false)
	public String getPermissionName()
	{
		return _permissionName;
	}
	
	public void setPermissionName(String permissionName)
	{
		_permissionName = permissionName;
	}
	
	@Column(name = "allowed", nullable = false)
	public boolean getPermissionIsAllowed()
	{
		return _permissionIsAllowed;
	}
	
	public void setPermissionIsAllowed(boolean permissionIsAllowed)
	{
		_permissionIsAllowed = permissionIsAllowed;
	}
	
	@ManyToOne
	@JoinColumn(name = "zoneID", referencedColumnName = "zoneID")
	public ZoneTable getPermissionZone()
	{
		return _permissionZone;
	}
	
	public void setPermissionZone(ZoneTable permissionZone)
	{
		_permissionZone = permissionZone;
	}
}
