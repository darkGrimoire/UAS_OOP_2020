import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Channel implements EventChannel{
    private Map<String, List<Subscriber>> subcribers;

    public Channel(){
        this.subcribers = new HashMap<>();
    }

    @Override
    public void sendEvent(String publisher, Event event) {
        try {
            if (this.subcribers.get(publisher) == null) {
                return;
            }
            for (Subscriber s: this.subcribers.get(publisher)) {
                s.onEvent(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPublisher(String publisher) {
        subcribers.putIfAbsent(publisher, new ArrayList<>());

    }

    @Override
    public void addSubscriber(String publisher, Subscriber subscriber) {
        addPublisher(publisher);
        subcribers.get(publisher).add(subscriber);
    }   
}