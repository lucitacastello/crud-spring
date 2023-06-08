package com.acr.enuns;

public enum Category {
    //para salvar no banco de dados os nomes que estão entre parentese
    BACK_END("Back-end"),
    FRONT_END("Front-end");

    private String value;

    private Category(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //toString
    @Override
    public String toString() {
        return value ;
    }
}
