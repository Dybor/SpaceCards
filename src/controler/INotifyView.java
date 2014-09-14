package controler;

import java.util.ArrayList;

public interface INotifyView {

	public void sendInformationMessage(String str);
	public void refreshAll();
	public void updateGameRound(ArrayList<Boolean> list);
}
