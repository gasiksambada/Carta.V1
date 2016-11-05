package gan.obito.carta.carta.model;

import java.util.ArrayList;

/**
 * Created by Gasik Sambada on 10/26/2016.
 */
public class Pemeriksaan {
    private Hasil result;
    private String status,nama,gender,merokok,diabetes,kode_warna,tingkat_risiko,deskripsi,error_description;
    private float kolesterol;
    private int usia,tekanan_darah,risiko_id;

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public Hasil getResult() {
        return result;
    }

    public void setResult(Hasil result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public float getKolesterol() {
        return kolesterol;
    }

    public void setKolesterol(float kolesterol) {
        this.kolesterol = kolesterol;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public int getTekanan_darah() {
        return tekanan_darah;
    }

    public void setTekanan_darah(int tekanan_darah) {
        this.tekanan_darah = tekanan_darah;
    }

    public int getRisiko_id() {
        return risiko_id;
    }

    public void setRisiko_id(int risiko_id) {
        this.risiko_id = risiko_id;
    }
}
