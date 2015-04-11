package net.servehttp.bytecom.persistence.jpa.entity.comercial;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import net.servehttp.bytecom.provedor.jpa.entity.Mikrotik;

@Entity
@Table(name = "conexao")
public class Conexao extends net.servehttp.bytecom.persistence.jpa.entity.extra.EntityGeneric {

  private static final long serialVersionUID = -4166003590731566705L;

  @OneToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "mikrotik_id")
  private Mikrotik mikrotik;

  private String nome;

  private String senha;

  @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "IP inválido")
  @Size(min = 1, max = 50)
  private String ip;

  public Conexao() {}
  
  public String getProfile(){
    return cliente.getStatus().getProfile(cliente);
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Mikrotik getMikrotik() {
    return mikrotik;
  }

  public void setMikrotik(Mikrotik mikrotik) {
    this.mikrotik = mikrotik;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}