public interface EventChannel {
    public void sendEvent(String publisher, Event event);
    public void addPublisher(String publisher);
    public void addSubscriber(String publisher, Subscriber subscriber);
}