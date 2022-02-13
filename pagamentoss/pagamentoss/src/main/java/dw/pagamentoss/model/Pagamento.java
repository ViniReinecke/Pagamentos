package dw.pagamentoss.model;

import javax.persistence.*;



@Entity
@Table(name = "pagamento")

public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_pagamento;

    @Column(nullable = false)
    private short ano;

    @Column(nullable = false)
    private short mes;

    @Column(nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(nullable=false, name = "cod_jogador", foreignKey = @ForeignKey(name = "FK_cod_jogador"))
    private Jogador cod_jogador;

    public Pagamento() {

    }

    public Pagamento(short ano, short mes, double valor, Jogador cod_jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.cod_jogador = cod_jogador;
    }

    public int getCod_pagamento() {
        return cod_pagamento;
    }

    public void setCod_pagamento(int cod_pagamento) {
        this.cod_pagamento = cod_pagamento;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Jogador getCod_jogador() {
        return cod_jogador;
    }

    public void setCod_jogador(Jogador cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "cod_pagamento=" + cod_pagamento + ", ano=" + ano + ", mes=" + mes + ", valor=" + valor + ", cod_jogador=" + cod_jogador + '}';
    }

}