package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.transacao.FabricaEventoTransacao;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventoFluxo;
import br.com.pine.gerenciador.portas.adaptadores.saida.FabricaEventoTransacaoProtobuf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class RaizAgregado implements Entidade {
    protected static FabricaEventoTransacao evento = new FabricaEventoTransacaoProtobuf();
    private IdAgregado idAgregado;
    private int versaoAgregado = 0;
    private final List<EventoFluxo> alteracoes = new ArrayList<>();

    protected IdAgregado idAgregado() {
        return idAgregado;
    }

    protected void setIdAgregado(IdAgregado umId) {
        this.idAgregado = umId;
    }

    protected int proximaVersaoDoAgregado() {
        return versaoAgregado + 1;
    }

    public Stream<EventoFluxo> alteracoes() {
        return alteracoes.stream();
    }

    protected void aplica(EventoFluxo umEvento) {
        this.alteracoes.add(umEvento);
        this.atualiza(umEvento);
    }

    protected void atualiza(EventoFluxo evento) {
        try {
            this.getClass().getMethod("when", evento.evento().getClass()).invoke(this, evento.evento());
            this.versaoAgregado++;
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
