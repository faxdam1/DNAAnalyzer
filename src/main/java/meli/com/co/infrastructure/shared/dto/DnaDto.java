package meli.com.co.infrastructure.shared.dto;
import java.io.Serializable;

public class DnaDto implements Serializable {


    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return this.isMutant;
    }

    public void setMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }

    private String [] dna;

    public DnaDto(String[] dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }
    public DnaDto(String[] dna) {
        this.dna = dna;
    }
    public DnaDto() {}

    private boolean isMutant;

}
