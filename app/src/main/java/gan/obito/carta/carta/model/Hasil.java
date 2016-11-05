package gan.obito.carta.carta.model;

/**
 * Created by Gasik Sambada on 10/26/2016.
 */
public class Hasil {
    private String nama,usia,gender,merokok,diabetes,kode_warna,tingkat_risiko,deskripsi,kolestrol,tekanan_darah;
    private Integer  risiko_id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMerokok() {
        return merokok;
    }

    public void setMerokok(String merokok) {
        this.merokok = merokok;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getKode_warna() {
        return kode_warna;
    }

    public void setKode_warna(String kode_warna) {
        this.kode_warna = kode_warna;
    }

    public String getTingkat_risiko() {
        return tingkat_risiko;
    }

    public void setTingkat_risiko(String tingkat_risiko) {
        this.tingkat_risiko = tingkat_risiko;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKolestrol() {
        return kolestrol;
    }

    public void setKolestrol(String kolestrol) {
        this.kolestrol = kolestrol;
    }

    public String getTekanan_darah() {
        return tekanan_darah;
    }

    public void setTekanan_darah(String tekanan_darah) {
        this.tekanan_darah = tekanan_darah;
    }

    public Integer getRisiko_id() {
        return risiko_id;
    }

    public void setRisiko_id(Integer risiko_id) {
        this.risiko_id = risiko_id;
    }
}
