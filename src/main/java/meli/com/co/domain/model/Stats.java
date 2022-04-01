package meli.com.co.domain.model;
import java.io.Serializable;

public class Stats implements Serializable {

    private int count_mutant_dna;
    private int count_human_dna;
    private float ratio;

    public Stats(int count_mutant_dna, int count_human_dna) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = count_mutant_dna/count_human_dna;
    }

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }
    public int getCount_human_dna() {
        return count_human_dna;
    }
    public float getRatio() {
        return ratio;
    }

}
