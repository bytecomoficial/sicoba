package br.com.clairtonluz.sicoba.model.entity.comercial;

import br.com.clairtonluz.sicoba.model.entity.extra.BaseEntity;
import br.com.clairtonluz.sicoba.util.StringUtil;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author clairton
 */
@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity {

    @NotNull(message = "nome é obrigatório")
    private String nome;
    private String rg;
    @Enumerated
    private StatusCliente status;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
    @Email(message = "Email inválido")
    private String email;
    @NotNull(message = "Fone titular é obrigatório")
    @Column(name = "fone_titular")
    private String foneTitular;
    private String contato;
    @Column(name = "fone_contato")
    private String foneContato;

    private String comment;
    @Column(name = "bypass_auto_block_until")
    private LocalDate bypassAutoBlockUntil;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente() {
        this.endereco = new Endereco();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg != null && rg.isEmpty() ? null : rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email != null && email.isEmpty() ? null : email;
    }

    public String getFoneTitular() {
        return foneTitular;
    }

    public void setFoneTitular(String foneTitular) {
        this.foneTitular = StringUtil.removerFormatacaoFone(foneTitular);
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato != null ? contato.toUpperCase() : contato;
    }

    public String getFoneContato() {
        return foneContato;
    }

    public void setFoneContato(String foneContato) {
        this.foneContato = StringUtil.removerFormatacaoFone(foneContato);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        cpfCnpj = StringUtil.removerFormatacaoCpfCnpj(cpfCnpj);
        this.cpfCnpj = cpfCnpj != null && cpfCnpj.isEmpty() ? null : cpfCnpj;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getBypassAutoBlockUntil() {
        return bypassAutoBlockUntil;
    }

    public void setBypassAutoBlockUntil(LocalDate bypassAutoBlockUntil) {
        this.bypassAutoBlockUntil = bypassAutoBlockUntil;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
