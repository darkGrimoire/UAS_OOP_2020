public class Main {
    public static void main(String[] args) {
        // /* TESTING FASILITAS DAN PROPERTI */
        // Properti hotel_standard = new Hotel("Sebuah hotel standar punya pemilik
        // pertama.", 400000, TipeHotel.STANDARD);
        // hotel_standard = new Sarapan(hotel_standard, 80000);
        // hotel_standard = new KolamRenang(hotel_standard, 0);
        // Properti hotel_deluxe = new Hotel("Sebuah hotel deluxe punya pemilik
        // pertama.", 600000, TipeHotel.DELUXE);
        // System.out.println(hotel_standard);
        // System.out.println(hotel_deluxe);

        // Properti apartemen = new Apartemen("Sebuah apartemen punya pemilik kedua.",
        // 700000, 2);
        // apartemen = new Sarapan(apartemen, 60000);
        // Properti homestay = new Homestay("Sebuah kamar homestay punya pemilik kedua",
        // 150000);
        // System.out.println(apartemen);
        // System.out.println(homestay);
        // /*
        // -------------------------------------------------------------------------------------------------------------
        // */
        // /* TESTING FASILITAS PLUGINS */
        // Registry registry = new Registry();
        // Properti hotel_standard = new Hotel("Sebuah hotel standar punya pemilik
        // pertama.", 400000, TipeHotel.STANDARD);
        // try {
        // hotel_standard = registry.tambahkanFasilitas(hotel_standard, "Sarapan",
        // 80000);
        // hotel_standard = registry.tambahkanFasilitas(hotel_standard, "KolamRenang",
        // 70000);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // System.out.println(hotel_standard);
        // -------------------------------------------------------------------------------------------------------------
        /* TESTING PELANGGAN INTERACTION */
        // Daftarkan pemilik
        Registry registry = new Registry();
        for (int i = 0; i < 10; i++) {
            Properti hotel_standard = new Hotel("Sebuah hotel standar punya pemilik pertama.", 400000,
                    TipeHotel.STANDARD);
            try {
                hotel_standard = registry.addFasilitas(hotel_standard, "Sarapan", 80000);
                hotel_standard = registry.addFasilitas(hotel_standard, "KolamRenang", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            registry.addPemilikProperti("pemilik_pertama", hotel_standard);
        }
        for (int i = 0; i < 3; i++) {
            Properti hotel_deluxe = new Hotel("hotel deluxe ke-" + i + " punya pemilik pertama.", 600000,
                    TipeHotel.DELUXE);
            try {
                hotel_deluxe = registry.addFasilitas(hotel_deluxe, "Sarapan", 80000);
                hotel_deluxe = registry.addFasilitas(hotel_deluxe, "KolamRenang", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            registry.addPemilikProperti("pemilik_pertama", hotel_deluxe);
        }

        for (int i = 0; i < 5; i++) {
            Properti apartemen = new Apartemen("apartemen ke-" + i + " punya pemilik kedua.", 700000, 2);
            try {
                apartemen = registry.addFasilitas(apartemen, "Sarapan", 60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            registry.addPemilikProperti("pemilik_kedua", apartemen);
        }
        for (int i = 0; i < 3; i++) {
            Properti homestay = new Homestay("kamar homestay ke-" + i + " punya pemilik kedua", 150000);
            registry.addPemilikProperti("pemilik_kedua", homestay);
        }
        // Daftarkan pelanggan
        registry.addPelanggan(new Pelanggan("Faris-kun", "faris.ekananda20@gmail.com", "082240722772"));
        registry.addPelanggan(new Pelanggan("X AE S-17 Musk", "AESIR@gmail.com", "088888888888"));

        // Properti Tersedia
        registry.listReservasi("29 April 2000");

        // Reservasi hotel dan homestay pada tgl 29 April 2000
        try {
            registry.reservasi(registry.getPelanggan().get(0), "29 April 2000", 12);
            registry.reservasi(registry.getPelanggan().get(0), "29 April 2000", 19);
        } catch (PropertiNotAvailable e) {
            e.printStackTrace();
        }
        registry.listReservasi("29 April 2000");

        // Reservasi apartemen pada tgl 29 April 2000
        try {
            registry.reservasi(registry.getPelanggan().get(1), "29 April 2000", 16);
        } catch (PropertiNotAvailable e) {
            e.printStackTrace();
        }
        registry.listReservasi("29 April 2000");

        // notifikasi
        registry.kirimNotifikasi("pemilik_pertama", "PROPERTI BATAL DISEWA GARA2 COVID-19");
        registry.kirimNotifikasi("pemilik_kedua", "ramadhan penuh berkah, kalian semua dapat refund");
    }
}