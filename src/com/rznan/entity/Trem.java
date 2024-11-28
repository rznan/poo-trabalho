package com.rznan.entity;

public class Trem {
    private int id;
    private int quantidadeMaxPassageiros;
    private double velocidadeMaxima;
    private double extensaoTotal;
    private String empresaDona;

    public Trem() {
    }

    public Trem(int id, int quantidadeMaxPassageiros, double velocidadeMaxima, double extensaoTotal, String empresaDona) {
        this.id = id;
        this.quantidadeMaxPassageiros = quantidadeMaxPassageiros;
        this.velocidadeMaxima = velocidadeMaxima;
        this.extensaoTotal = extensaoTotal;
        this.empresaDona = empresaDona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeMaxPassageiros() {
        return quantidadeMaxPassageiros;
    }

    public void setQuantidadeMaxPassageiros(int quantidadeMaxPassageiros) {
        this.quantidadeMaxPassageiros = quantidadeMaxPassageiros;
    }

    public double getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(double velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public double getExtensaoTotal() {
        return extensaoTotal;
    }

    public void setExtensaoTotal(double extensaoTotal) {
        this.extensaoTotal = extensaoTotal;
    }

    public String getEmpresaDona() {
        return empresaDona;
    }

    public void setEmpresaDona(String empresaDona) {
        this.empresaDona = empresaDona;
    }
}
