public class Homestay extends Properti {
    
    public Homestay(String deskripsi, long harga_dasar){
        super(deskripsi, harga_dasar);
    }

    @Override
    public String getDeskripsi() {
        return "Sebuah Homestay. " + deskripsi;
    }
}