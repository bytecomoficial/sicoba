package net.servehttp.bytecom.persistence.jpa.financeiro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import net.servehttp.bytecom.persistence.jpa.entity.financeiro.Cedente;
import net.servehttp.bytecom.persistence.jpa.entity.financeiro.Mensalidade;
import net.servehttp.bytecom.persistence.jpa.entity.financeiro.QCedente;
import net.servehttp.bytecom.persistence.jpa.entity.financeiro.QMensalidade;
import net.servehttp.bytecom.persistence.jpa.extra.GenericoJPA;

import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 *
 * @author clairton
 */
@Transactional
public class MensalidadeJPA extends GenericoJPA {

  private static final long serialVersionUID = -5750421956273634462L;
  private QMensalidade m = QMensalidade.mensalidade;

  public void remover(Mensalidade mensalidade) {
    new JPADeleteClause(em, m).where(m.id.eq(mensalidade.getId())).execute();
  }

  public void removerPorBoleto(int inicio, int fim) {
    new JPADeleteClause(em, m).where(m.numeroBoleto.between(inicio, fim)).execute();
  }

  public void setEntityManager(EntityManager em) {
    this.em = em;
  }

  public List<Mensalidade> buscarMensalidadesPorBoletos(int numeroBoletoInicio, int numeroBoletoFim) {
    return new JPAQuery(em).from(m)
        .where(m.numeroBoleto.between(numeroBoletoInicio, numeroBoletoFim)).list(m);
  }

  public Cedente buscarCedente() {
    QCedente c = QCedente.cedente;
    return new JPAQuery(em).from(c).uniqueResult(c);
  }

  public Mensalidade buscarPorId(int id) {
    return super.buscarPorId(Mensalidade.class, id);
  }

  public Mensalidade buscarMensalidadePorNumeroBoleto(int numeroBoleto) {
    return new JPAQuery(em).from(m).where(m.numeroBoleto.eq(numeroBoleto)).uniqueResult(m);
  }
}