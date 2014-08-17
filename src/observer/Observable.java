package observer;

public interface Observable {
	  public void setObserver(Observer obs);
	  public void notifyObserver(int i);
}
