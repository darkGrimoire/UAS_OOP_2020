public class Pelanggan implements Subscriber{
    private String nama;
    private String email;
    private String no_telpon;

    public Pelanggan(String nama, String email, String no_telpon){
        this.nama = nama;
        this.email = email;
        this.no_telpon =no_telpon;
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("\nPelanggan " + nama + " get the notification!");
        System.out.println("Message: " + event.getInfo().toString());
    }
}