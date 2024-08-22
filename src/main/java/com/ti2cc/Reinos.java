package com.ti2cc;

public class Reinos {
	private int codigo;
	private String nome;
	private String pais;
	private int ano;
	
	public Reinos() {
        this.codigo = -1;
        this.nome = "";
        this.pais = "";
        this.ano = 0;
    }

    
    public Reinos(int codigo, String nome, String pais, int ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.pais = pais;
        this.ano = ano;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    @Override
    public String toString() {
        return "Reino [codigo=" + codigo + ", nome=" + nome + ", pais=" + pais + ", ano=" + ano + "]";
    }

    
    
}
