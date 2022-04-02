package meli.com.co.domain.service;

import java.util.HashMap;

public class DnaAnalyzerService extends Service {

    private String [] dictionary=new String[]{"AAAA","TTTT","CCCC","GGGG"};

    public boolean isMutant(String[] dnaSecuence) {
        HashMap<String, Integer> hashTable = new HashMap<>();
        String[][] dna = this.convertArray(dnaSecuence);
        for (int row = 0; row < dna.length; row++) {
            for (int col = 0; col < dna[row].length; col++) {
                this.indexSecuenceNode(dna, row, col,hashTable);
            }
        }
        int countSecuence=0;
        for(String secuence:dictionary){
            if(hashTable.containsKey(secuence))
                countSecuence = countSecuence + hashTable.get(secuence).intValue();
        }
        return countSecuence>1;
    }

    private void indexSecuenceNode(String[][] dna, int row, int col, HashMap<String,Integer> hashTable) {
        int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
        int R = dna.length, C = dna[0].length;
        int len = 4;
        for (int dir = 0; dir < 8; dir++) {
            int rd = row + x[dir];
            int cd = col + y[dir];
            String sequence = this.findSequence(R, C, len, rd, cd, dna, row, col, x, y, dir);
            if (sequence.length() == len) {
                this.markSequence(R, C, len, rd, cd, dna, row, col, x, y, dir);
                this.indexSequence(sequence,hashTable);
            }
        }
    }

    private String findSequence(int R, int C, int len, int rd, int cd, String dna[][], int row, int col, int[] x, int y[], int dir) {
        String sequence = "" + dna[row][col];
        for (int k = 1; k < len; k++) {
            if (rd >= R || rd < 0 || cd >= C || cd < 0) {
                break;
            }
            if (dna[row][col].equals(dna[rd][cd]) && !dna[rd][cd].equals("X")) {
                sequence = sequence + dna[rd][cd];
            } else {
                break;
            }
            rd += x[dir];
            cd += y[dir];
        }
        return sequence;
    }

    private void indexSequence(String secuence,HashMap<String,Integer> hashTable) {
        if (hashTable.containsKey(secuence)) {
            hashTable.put(secuence, hashTable.get(secuence).intValue() + 1);
        } else {
            hashTable.put(secuence, Integer.valueOf(1));
        }
    }

    private void markSequence(int R, int C, int len, int rd, int cd, String dna[][], int row, int col, int[] x, int y[], int dir) {
        dna[row][col] = "X";
        for (int k = 1; k < len; k++) {
            if (rd >= R || rd < 0 || cd >= C || cd < 0) {
                break;
            }
            dna[rd][cd] = "X";
            rd += x[dir];
            cd += y[dir];
        }
    }

    private String[][] convertArray(String[] array) {
        String[][] dna = new String[array.length][array[0].length()];
        for (int i = 0; i < array.length; i++) {
            dna[i] = array[i].split("");
        }
        return dna;
    }

}








