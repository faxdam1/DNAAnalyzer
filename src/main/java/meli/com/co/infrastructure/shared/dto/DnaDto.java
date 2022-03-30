package meli.com.co.infrastructure.shared.dto;
import java.io.Serializable;

public class DnaDto implements Serializable {

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    private String [] dna;

}
