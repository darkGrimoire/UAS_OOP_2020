class Sarapan extends Fasilitas{
    private long harga;

    public Sarapan(Properti properti, long harga) {
        super(properti);
        this.harga = harga;
    }

    @Override
    public String getDeskripsi(){
        return super.getDeskripsi() + " sarapan";
    }

    @Override
    public long getHarga(){
        return super.getHarga() + harga;
    }
    
}