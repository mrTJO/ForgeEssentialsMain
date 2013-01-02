package com.ForgeEssentials.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ladderNames", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class LadderNameTable extends BaseTable
{
	private int _ladderId;
	private String _ladderName;
	
	public LadderNameTable()
	{
		
	}
	
	public LadderNameTable(String ladderName)
	{
		_ladderName = ladderName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ladderID", nullable = false)
	public int getLadderId()
	{
		return _ladderId;
	}
	
	@SuppressWarnings("unused")
	private void setLadderId(int ladderId)
	{
		_ladderId = ladderId;
	}
	
	@Column(name = "name", length = 40, nullable = false)
	public String getLadderName()
	{
		return _ladderName;
	}
	
	public void setLadderName(String ladderName)
	{
		_ladderName = ladderName;
	}
}
