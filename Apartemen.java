public class Apartemen extends Properti {
    private int jum_kamar;

    public Apartemen(String deskripsi, long harga_dasar, int jum_kamar){
        super(deskripsi, harga_dasar);
        this.jum_kamar = jum_kamar;
    }

    @Override
    public String getDeskripsi() {
        return "Apartemen " + jum_kamar + " kamar. " + deskripsi;
    }
}