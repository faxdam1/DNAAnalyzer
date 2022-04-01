package meli.com.co.infrastructure.shared.dto;

public class QueryMutantDto {
    Query query;
    public QueryMutantDto(boolean mutant){
        this.query=new Query(new Bool(new Must[]{new Must(new Term(mutant))}));
    }
}

class Query {
    Bool bool;
    Query(Bool bool){
        bool=bool;
    }
}

class Bool {
    Bool( Must must []){
        must= must;
    }
    Must must [];
}

class Must {
    Must(Term term){
        term= term;
    }
    Term term;
}

class Term {
    Term(boolean mutant){
        mutant= mutant;
    }
    boolean mutant;
}
