package meli.com.co.domain.model;
import java.io.Serializable;

public class Stats implements Serializable {

    private Long count_mutant_dna;
    private Long count_human_dna;
    private Float ratio;

    public Stats(){}
    public Stats(Long count_mutant_dna, Long count_human_dna) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio =count_human_dna==0?-1:((float)count_mutant_dna/count_human_dna);
    }

    public Long getCount_mutant_dna() {
        return count_mutant_dna;
    }
    public Long getCount_human_dna() {
        return count_human_dna;
    }
    public Float getRatio() {
        return ratio;
    }

}
