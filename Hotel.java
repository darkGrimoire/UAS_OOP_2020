public class Hotel extends Properti {
    private TipeHotel tipe;

    public Hotel(String deskripsi, long harga_dasar, TipeHotel tipe){
        super(deskripsi, harga_dasar);
        this.tipe = tipe;
    }

    @Override
    public String getDeskripsi() {
        return "Kamar Hotel tipe " + tipe + ". " + deskripsi;
    }
}