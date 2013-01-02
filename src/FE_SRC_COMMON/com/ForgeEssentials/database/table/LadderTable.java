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
@Table(name="ladders")
public class LadderTable extends BaseTable
{
	private int _ladderId;
	private LadderNameTable _ladderName;
	private GroupTable _ladderGroup;
	private ZoneTable _ladderZone;
	private short _ladderRank;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ladder", nullable = false)
	public int getLadderId()
	{
		return _ladderId;
	}
	
	@SuppressWarnings("unused")
	private void setLadderId(int ladderId)
	{
		_ladderId = ladderId;
	}
	
	@ManyToOne
	@JoinColumn(name = "ladderID", referencedColumnName = "ladderID")
	public LadderNameTable getLadderName()
	{
		return _ladderName;
	}
	
	public void setLadderName(LadderNameTable ladderName)
	{
		_ladderName = ladderName;
	}
	
	@ManyToOne
	@JoinColumn(name = "groupID", referencedColumnName = "groupID")
	public GroupTable getLadderGroup()
	{
		return _ladderGroup;
	}
	
	public void setLadderGroup(GroupTable ladderGroup)
	{
		_ladderGroup = ladderGroup;
	}
	
	@ManyToOne
	@JoinColumn(name = "zoneID", referencedColumnName = "zoneID")
	public ZoneTable getLadderZone()
	{
		return _ladderZone;
	}
	
	public void setLadderZone(ZoneTable ladderZone)
	{
		_ladderZone = ladderZone;
	}
	
	@Column(name = "rank", nullable = false)
	public short getLadderRank()
	{
		return _ladderRank;
	}
	
	public void setLadderRank(short ladderRank)
	{
		_ladderRank = ladderRank;
	}
}
