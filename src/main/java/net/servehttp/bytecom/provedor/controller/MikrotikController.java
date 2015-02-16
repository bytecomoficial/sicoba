package net.servehttp.bytecom.provedor.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.servehttp.bytecom.extra.controller.GenericoController;
import net.servehttp.bytecom.provedor.jpa.MikrotikJPA;
import net.servehttp.bytecom.provedor.jpa.entity.Mikrotik;
import net.servehttp.bytecom.util.web.AlertaUtil;

/**
 * 
 * @author clairton
 *
 */
@Named
@ViewScoped
public class MikrotikController extends GenericoController {

  private static final long serialVersionUID = -676355663109515972L;
  private List<Mikrotik> listMikrotik;

  private Mikrotik mikrotik;
  @Inject
  private MikrotikJPA jpa;
  private String senha;

  @PostConstruct
  public void load() {
    consultar();
  }

  private void consultar() {
    listMikrotik = jpa.buscarTodosMikrotik();
  }

  public void novo() {
    mikrotik = new Mikrotik();
  }

  public void limpar() {
    senha = null;
    mikrotik = null;
  }

  public void salvar() {
    try {
      if (senhaValida(mikrotik)) {
        if (mikrotik.getId() > 0) {
          jpa.atualizar(mikrotik);
          AlertaUtil.info("Atualizado com sucesso!");
        } else {
          jpa.salvar(mikrotik);
          AlertaUtil.info("Salvo com sucesso!");
        }
        limpar();
        consultar();
      }
    } catch (Exception e) {
      log(e);
    }
  }

  private boolean senhaValida(Mikrotik m) {
    boolean valido = true;

    if (senha != null) {
      m.setSenha(senha);
    }

    if (m.getId() == 0 && m.getSenha() == null) {
      AlertaUtil.error("Digite uma senha");
      valido = false;
    }
    return valido;
  }

  public void remover() {
    jpa.remover(mikrotik);
    limpar();
    consultar();
  }

  public Mikrotik getMikrotik() {
    return mikrotik;
  }

  public void setMikrotik(Mikrotik mikrotik) {
    this.mikrotik = mikrotik;
  }

  public List<Mikrotik> getListMikrotik() {
    return listMikrotik;
  }

  public void setListMikrotik(List<Mikrotik> listMikrotik) {
    this.listMikrotik = listMikrotik;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
