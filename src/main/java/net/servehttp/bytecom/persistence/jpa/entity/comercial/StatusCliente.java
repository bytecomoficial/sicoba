package net.servehttp.bytecom.persistence.jpa.entity.comercial;

import java.util.Optional;

import net.servehttp.bytecom.service.provedor.MikrotikPPP;

public enum StatusCliente {
  INATIVO {
    @Override
    public String getProfile(Cliente cliente) {
      return "INATIVO";
    }

    @Override
    public void atualizarConexao(Cliente cliente, MikrotikPPP mikrotikPPP) throws Exception {
      if (cliente.getConexao() != null) {
        mikrotikPPP.salvarSecret(cliente.getConexao());
      }
    }
  },
  ATIVO {
    @Override
    public String getProfile(Cliente cliente) {
      Optional<Cliente> c = Optional.ofNullable(cliente);
      return c.map(Cliente::getContrato).map(Contrato::getPlano).map(Plano::getNome).orElse(null);
    }

    @Override
    public void atualizarConexao(Cliente cliente, MikrotikPPP mikrotikPPP) throws Exception {
      if (cliente.getConexao() != null) {
        mikrotikPPP.salvarSecret(cliente.getConexao());
      }
    }
  },
  CANCELADO {
    @Override
    public String getProfile(Cliente cliente) {
      return null;
    }

    @Override
    public void atualizarConexao(Cliente cliente, MikrotikPPP mikrotikPPP) throws Exception {
      if (cliente.getConexao() != null) {
        mikrotikPPP.removerSecret(cliente.getConexao());
        cliente.setConexao(null);
      }
    }
  };


  public abstract String getProfile(Cliente cliente);

  public abstract void atualizarConexao(Cliente cliente, MikrotikPPP mikrotikPPP) throws Exception;

}
