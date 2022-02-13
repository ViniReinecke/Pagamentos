package dw.pagamentoss.model;

import java.util.Date;

import javax.persistence.*;

import java.util.List;

//import org.hibernate.event.spi.PreUpdateEvent;

@Entity
@Table(name = "Jogador")
public class Jogador {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cod_jogador;

  @Column(nullable = false, length = 60)
  private String nome;

  @Column(nullable = false, length = 60)
  private String email;

  // @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Column(nullable = false)
  private Date datanasc;

  @OneToMany(mappedBy = "cod_jogador")
  private List<Pagamento> pagamentos;


public Jogador(){

  }

public Jogador (int cod_jogador, String nome, String email, Date datanasc){

    this.cod_jogador = cod_jogador;
    this.nome = nome;
    this.email = email;
    this.datanasc = datanasc;
  }

  public int getCod_jogador(){
        return cod_jogador;
  }

  public String getNome(){
      return nome;
  }

  public String getEmail(){
      return email;
  }

  public Date getDatanasc(){
      return datanasc;
  }

  public void setCod_jogador(int cod_jogador){
    this.cod_jogador = cod_jogador;
  }

  public void setNome(String nome){
      this.nome = nome;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setDatanasc(Date datanasc){
      this.datanasc = datanasc;
  }


  @Override
  public String toString(){
      return "Jogador id: " + cod_jogador + "\n Nome do Jogador: " + nome + "\n Email de Jogador: " + email+ "\n Data de Nascimento: " + datanasc;
  }


}

