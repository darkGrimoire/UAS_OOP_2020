import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {
    private List<Pelanggan> daftar_pelanggan;
    private Map<String, List<Properti>> daftar_properti;
    private Map<String, Class<?>> daftar_fasilitas;
    private Channel channel;

    public Registry() {
        daftar_pelanggan = new ArrayList<>();
        daftar_properti = new HashMap<>();
        daftar_fasilitas = new HashMap<>();
        channel = new Channel();
        loadFasilitas();
        System.out.println(daftar_fasilitas);
    }

    private void loadFasilitas() {
        File f = new File("plugins");
        String names[] = f.list();
        for (String name : names) {
            try {
                Class<?> c = Class.forName(name.split("\\.")[0]);
                Class<?> abstract_class = c.getSuperclass();
                if (abstract_class.getName().equals("Fasilitas")) {
                    daftar_fasilitas.putIfAbsent(c.getName(), c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Fasilitas addFasilitas(Properti properti, String fasilitas, long harga) throws Exception {
        if (!daftar_fasilitas.containsKey(fasilitas)) {
            throw new FasilitasNotFound("Fasilitas " + fasilitas + " not found!");
        }
        return (Fasilitas) daftar_fasilitas.get(fasilitas).getConstructor(new Class[] { Properti.class, Long.TYPE })
                .newInstance(new Object[] { properti, harga });
    }

    public void addPelanggan(Pelanggan pelanggan){
        daftar_pelanggan.add(pelanggan);
    }
    public void addPemilik(String pemilik){
        daftar_properti.putIfAbsent(pemilik, new ArrayList<>());
    }
    public void addPemilikProperti(String pemilik, Properti properti){
        addPemilik(pemilik);
        daftar_properti.get(pemilik).add(properti);
    }
    public void addPemilikProperti(String pemilik, List<Properti> properti){
        addPemilik(pemilik);
        daftar_properti.get(pemilik).addAll(properti);
    }
    public void addPemilikProperti(String pemilik, Properti properti[]){
        addPemilik(pemilik);
        for (Properti item: properti){
            daftar_properti.get(pemilik).add(item);
        }
    }
    public List<Pelanggan> getPelanggan(){
        return daftar_pelanggan;
    }
    public void kirimNotifikasi(String publisher, String msg){
        channel.sendEvent(publisher, new NotificationEvent(msg));
    }
    
    public void listProperti(){
        int i = 1;
        for (Map.Entry<String, List<Properti>> list_properti : daftar_properti.entrySet()){
            System.out.println("\nList semua properti milik " + list_properti.getKey());
            for (Properti properti: list_properti.getValue()){
                System.out.println(i + ". " + properti);
                i++;
            }
        }
    }
    public void listReservasi(String date){
        int i = 1;
        for (Map.Entry<String, List<Properti>> list_properti : daftar_properti.entrySet()){
            System.out.println("\n" + list_properti.getKey() + " properti tersedia pada tanggal "+ date +":");
            for (Properti properti: list_properti.getValue()){
                if (!properti.isReserved(date)){
                    System.out.println(i + ". " + properti);
                }
                i++;
            }
        }
    }
    public void reservasi(Pelanggan pelanggan, String tanggal, int id_properti) throws PropertiNotAvailable {
        int i = 1;
        for (Map.Entry<String, List<Properti>> list_properti : daftar_properti.entrySet()){
            for (Properti properti: list_properti.getValue()){
                if (i == id_properti){
                    if (properti.isReserved(tanggal)){
                        throw new PropertiNotAvailable("Properti "+id_properti+" tidak tersedia untuk tanggal "+tanggal);
                    }
                    properti.addReservationDate(tanggal);
                    System.out.println("\n+--------------------------------------------------------------------------------------------------------+");
                    System.out.println("+                                            DETAIL RESERVASI                                            +");
                    System.out.println(properti.getDeskripsi() + ".");
                    System.out.println("HARGA TOTAL: " + properti.getHarga());
                    System.out.println("TANGGAL RESERVASI: "+ tanggal);
                    System.out.println("+                             TERIMA KASIH TELAH MENGGUNAKAN JASA BnB Bangsat                            +");
                    System.out.println("+--------------------------------------------------------------------------------------------------------+");
                    channel.addSubscriber(list_properti.getKey(), pelanggan);
                }
                i++;
            }
        }
    }
}