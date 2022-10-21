package br.com.pine.gerenciador.modelo.dominio;

import java.time.LocalDateTime;
public interface EventoDeDominio {
    String idEntidade();

    LocalDateTime getOcorridoEm();

    int getVersao();

    Comando getComando();
}
