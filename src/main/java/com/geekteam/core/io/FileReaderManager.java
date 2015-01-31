package com.geekteam.core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.geekteam.core.model.Card;
import com.geekteam.core.model.game.IGameCard;

public class FileReaderManager {

	// Attributes
	private BufferedReader is;
	private String line;
	private Messenger msg;

	// Builder
	public FileReaderManager() {
	}

	public void open(final String path) {
		msg = new Messenger("FileReaderManager");
		try {
			is = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			msg.sendMessage("Impossible d'ouvrir le fichier " + path);
		}
	}

	public ArrayList<IGameCard> readCards() {
		IGameCard c = null;
		ArrayList<IGameCard> cards = new ArrayList<>();
		try {
			while ((line = is.readLine()) != null) {
				String[] data = line.split("\t");
				int id = Integer.parseInt(data[0]);
				int type = Integer.parseInt(data[1]);
				int subtype = Integer.parseInt(data[2]);
				int cost = Integer.parseInt(data[3]);
				int vp = Integer.parseInt(data[4]);
				String name = data[5];
				int color = Integer.parseInt(data[6]);
				int homeworld = Integer.parseInt(data[7]);
				cards.add(new Card(id, type, subtype, cost, vp, name, color,
						homeworld));
			}
		} catch (IOException e) {
			msg.sendMessage("Probl�me de lecture de la carte");
		} catch (Exception e) {
			msg.sendMessage("La carte n'est pas correctement d�finie : \n"
					+ line);
		}
		return cards;
	}
}
