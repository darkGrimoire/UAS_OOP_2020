class KolamRenang extends Fasilitas{
    private long harga;

    public KolamRenang(Properti properti, long harga) {
        super(properti);
        this.harga = harga;
    }

    @Override
    public String getDeskripsi(){
        return super.getDeskripsi() + " kolam renang";
    }

    @Override
    public long getHarga(){
        return super.getHarga() + harga;
    }
    
}