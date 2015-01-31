package com.geekteam.core.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.TreeMap;

import com.geekteam.core.model.network.INetworkData;

public class ObjectServer extends UnicastRemoteObject implements IObjectServer {

	// Attributes
	private static final long serialVersionUID = 1L;
	
	private TreeMap<Long, INetworkData> games =new TreeMap<>();

	// Builder
	protected ObjectServer() throws RemoteException {
		super();
	}

	// IObjectServer implementation
	@Override
	public void save(long id, INetworkData gameData) throws RemoteException {
		games.put(id, gameData);
	}

	@Override
	public INetworkData load(long id) throws RemoteException {
		return games.get(id);
	}

	@Override
	public long getAvailableID() throws RemoteException {
		return games.size();
	}

	@Override
	public void delete(long id) throws RemoteException {
		games.remove(id);
	}

}
