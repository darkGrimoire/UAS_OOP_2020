import java.util.ArrayList;
import java.util.List;

public abstract class Properti {
    protected String deskripsi;
    protected long harga_dasar;
    protected List<String> tanggal_reserved;

    public Properti(){}

    public Properti(String deskripsi, long harga_dasar){
        this.deskripsi = deskripsi;
        this.harga_dasar = harga_dasar;
        this.tanggal_reserved = new ArrayList<>();
    }

    public abstract String getDeskripsi();

    public long getHarga(){
        return harga_dasar;
    }
    public boolean isReserved(String date) {
        return tanggal_reserved.isEmpty() ? false : tanggal_reserved.contains(date);
    }
    public void addReservationDate(String date) {
        tanggal_reserved.add(date);
    }

    public String toString(){
        return getDeskripsi() + ". Harga: " + getHarga();
    }
}