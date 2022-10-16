package br.com.pine.gerenciador.modelo.dominio;

import java.util.regex.Pattern;

public class Validador {

    protected Validador() {
        super();
    }

    protected void validaPadraoIgualIgnoreCase(String umaString, String umPadrao, String umaMensagem){
        if(!Pattern.compile(umPadrao, Pattern.CASE_INSENSITIVE).matcher(umaString).matches()){
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoIgual(Object umObjeto1, Object umObjeto2, String umaMensagem) {
        if (!umObjeto1.equals(umObjeto2)) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoFalso(boolean umBoolean, String umaMensagem) {
        if (umBoolean) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaComprimentoArgumento(String umaString, int umMaximo, String umaMensagem) {
        int comprimento = umaString.trim().length();
        if (comprimento > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaComprimentoArgumento(String umaString, int umMinimo, int umMaximo, String umaMensagem) {
        int comprimento = umaString.trim().length();
        if (comprimento < umMinimo || comprimento > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoNaoVazio(String umaString, String umaMensagem) {
        if (umaString == null || umaString.trim().isEmpty()) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoNaoIgual(Object umObjeto1, Object umObjeto2, String umaMensagem) {
        if (umObjeto1.equals(umObjeto2)) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoNaoNulo(Object umObjeto, String umaMensagem) {
        if (umObjeto == null) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoMaiorOuIgualA(int umValor, int umMinimo, String umaMensagem) {
        if (umValor < umMinimo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoMaiorOuIgualA(float umValor, float umMinimo, String umaMensagem) {
        if (umValor < umMinimo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaIntervaloArgumento(double umValor, double umMinimo, double umMaximo, String umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaIntervaloArgumento(float umValor, float umMinimo, float umMaximo, String umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaIntervaloArgumento(int umValor, int umMinimo, int umMaximo, String umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaIntervaloArgumento(long umValor, long umMinimo, long umMaximo, String umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaArgumentoVerdadeiro(boolean umBoolean, String umaMensagem) {
        if (!umBoolean) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }

    protected void validaEstadoFalso(boolean umBoolean, String umaMensagem) {
        if (umBoolean) {
            throw new IllegalStateException(umaMensagem);
        }
    }

    protected void validaEstadoVerdadeiro(boolean umBoolean, String umaMensagem) {
        if (!umBoolean) {
            throw new IllegalStateException(umaMensagem);
        }
    }

    protected void validaIdEntidade(String idEntidade, String idComando, String umaMensagem){
        if (!idEntidade.equals(idComando)) {
            throw new IllegalArgumentException(umaMensagem);
        }
    }
}
