public abstract class Fasilitas extends Properti {
    private final Properti properti_decorated;

    public Fasilitas(Properti properti) {
        this.properti_decorated = properti;
    }

    @Override
    public String getDeskripsi(){
        return properti_decorated.getDeskripsi();
    }

    @Override
    public long getHarga(){
        return properti_decorated.getHarga();
    }

    @Override
    public boolean isReserved(String date){
        return properti_decorated.isReserved(date);
    }

    @Override
    public void addReservationDate(String date) {
        properti_decorated.addReservationDate(date);
    }
}