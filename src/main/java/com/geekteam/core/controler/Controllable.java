package com.geekteam.core.controler;

/**
 * Definit le comportement du modele lorsqu'il est controle par le controler.
 * @author Nicolas
 *
 */
public interface Controllable {

	public void setController(IControler c);
	public void launchGame();
	public void treatSelectedCard(int id);
	public void validateAction(int i);
	
}
