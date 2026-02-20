package resources.model.interfaces;

public interface Subscriber {
    public void update(IMessage m);
    public Integer getSubKey();
}
