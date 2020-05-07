public class NotificationEvent implements Event{
    private String event;

    public NotificationEvent(String msg){
        this.event = msg;
    }
    @Override
    public Object getInfo() {
        return (String) event;
    }
    
}