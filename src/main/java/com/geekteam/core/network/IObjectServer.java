package com.geekteam.core.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.geekteam.core.model.network.INetworkData;

/**
 * Definit le comportement du serveur de noms.
 * @author Nicolas
 *
 */
public interface IObjectServer extends Remote {

	/**
	 * Permet d'enregistrer une nouvelle partie sur le serveur de noms.
	 * @param id Identifiant de la partie.
	 * @param gameData Partie a enregistrer sur le serveur.
	 * @throws RemoteException
	 */
	public void save(long id, INetworkData gameData) throws RemoteException;
	
	/**
	 * Permet le chargement d'une partie enregistree sur le serveur.
	 * @param id Identifiant de la partie.
	 * @return La partie a recuperer.
	 * @throws RemoteException
	 */
	public INetworkData load(long id) throws RemoteException;
	
	/**
	 * Renvoie un identifiant disponible sur le serveur de noms.
	 * @return
	 * @throws RemoteException
	 */
	public long getAvailableID() throws RemoteException;
	
	/**
	 * Supprime une partie du serveur de noms.
	 * @param id Identifiant de la partie a supprimer.
	 * @throws RemoteException
	 */
	public void delete(long id) throws RemoteException;
}
