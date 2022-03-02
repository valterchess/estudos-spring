package com.restosparacliente.restosparaclientes.model;

import java.util.List;

public class Personagem {
    private long id;
    private List<String> characters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }
}
