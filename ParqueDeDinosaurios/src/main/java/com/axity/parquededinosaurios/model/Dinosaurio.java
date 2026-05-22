package com.axity.parquededinosaurios.model;

public abstract class Dinosaurio {
    private int id;
    private String nombre,especie;
    private EstadoDinosaurio estado;

    public Dinosaurio(int id, String nombre, String especie, EstadoDinosaurio estado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.estado = estado;
    }

    public Dinosaurio() {

    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    
    public EstadoDinosaurio getEstado() { return estado; }
    public void setEstado(EstadoDinosaurio estado) { this.estado = estado; }

    abstract public Double getPobabilidadAtaque();
    abstract public String getDieta();
    abstract public Double getCostoAlimenticio();


    public void escapar(){ estado = EstadoDinosaurio.ESCAPADO;}
    public void capturado(){ estado = EstadoDinosaurio.CAPTURADO;}
    public void encerrado(){ estado = EstadoDinosaurio.ENCERRADO;}
}
