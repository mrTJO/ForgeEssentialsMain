package com.ForgeEssentials.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="players", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class PlayerTable extends BaseTable
{
	private int _playerId;
	private String _playerUsername;
	
	public PlayerTable()
	{
		
	}
	
	public PlayerTable(String playerUsername)
	{
		_playerUsername = playerUsername;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "playerID", nullable = false)
	public int getPlayerId()
	{
		return _playerId;
	}
	
	@SuppressWarnings("unused")
	private void setPlayerId(int playerId)
	{
		_playerId = playerId;
	}
	
	@Column(name = "username", nullable = false)
	public String getPlayerUsername()
	{
		return _playerUsername;
	}
	
	public void setPlayerUsername(String playerUsername)
	{
		_playerUsername = playerUsername;
	}
}
