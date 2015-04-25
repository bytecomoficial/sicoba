package net.servehttp.bytecom.service.administrador;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import net.servehttp.bytecom.persistence.jpa.administrador.UserJPA;
import net.servehttp.bytecom.persistence.jpa.entity.administrador.Authentication;
import net.servehttp.bytecom.persistence.jpa.entity.administrador.UserAccount;
import net.servehttp.bytecom.persistence.jpa.extra.GenericoJPA;
import net.servehttp.bytecom.util.ImageUtil;

public class AccountService implements Serializable {

  private static final String IMG_USERS = "/img/users/";
  private static final long serialVersionUID = -8296012997453708684L;
  private static final String IMG_DEFAULT = "avatar_male.png";

  @Inject
  private UserJPA userJPA;

  @Inject
  private GenericoJPA jpa;

  public List<UserAccount> findUsersAccounts() {
    return userJPA.buscarTodosUserAccount();
  }

  public boolean emailAvaliable(UserAccount userAccount) {
    return userJPA.emailAvaliable(userAccount);
  }

  public UserAccount findUserAccountByUsername(String username) {
    return userJPA.findUserAccountByUsername(username);
  }

  public void createPictureInSession(UserAccount user) {
    String filename = user.getId() + ".png";

    FacesContext context = FacesContext.getCurrentInstance();
    ServletContext servletcontext = (ServletContext) context.getExternalContext().getContext();
    String folderImages = servletcontext.getRealPath(IMG_USERS);

    String imagemGerada = ImageUtil.exibirImagem(user.getImg(), filename, folderImages);
    if (imagemGerada != null) {
      user.setImageGerada(IMG_USERS + filename);
    } else {
      user.setImageGerada(IMG_USERS + IMG_DEFAULT);
    }
  }

  public UserAccount findUserAccountById(int id) {
    return jpa.buscarPorId(UserAccount.class, id);
  }

  public Authentication findAuthenticationByUserAccount(UserAccount userAccount) {
    return userJPA.findAuthenticationByUserAccount(userAccount);
  }

  public void remover(UserAccount userAccount) {
    jpa.remover(userAccount);
  }

}
