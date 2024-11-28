package com.rznan.entity;

public class Onibus {
    private int id;
    private int quantidadeMaxPassageiros;
    private double velocidadeMaxima;
    private int numeroDePneus;
    private String marcaFabricante;

    public Onibus() {
    }

    public Onibus(int id, int quantidadeMaxPassageiros, double velocidadeMaxima, int numeroDePneus, String marcaFabricante) {
        this.id = id;
        this.quantidadeMaxPassageiros = quantidadeMaxPassageiros;
        this.velocidadeMaxima = velocidadeMaxima;
        this.numeroDePneus = numeroDePneus;
        this.marcaFabricante = marcaFabricante;
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

    public int getNumeroDePneus() {
        return numeroDePneus;
    }

    public void setNumeroDePneus(int numeroDePneus) {
        this.numeroDePneus = numeroDePneus;
    }

    public String getMarcaFabricante() {
        return marcaFabricante;
    }

    public void setMarcaFabricante(String marcaFabricante) {
        this.marcaFabricante = marcaFabricante;
    }
}
