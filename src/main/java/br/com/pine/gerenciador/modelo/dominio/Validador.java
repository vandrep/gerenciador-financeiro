package br.com.pine.gerenciador.modelo.dominio;

import java.util.regex.Pattern;

public final class Validador {

    public static void validaPadraoIgualIgnoreCase(String umaString, String umPadrao, MensagemErro umaMensagem){
        if(!Pattern.compile(umPadrao, Pattern.CASE_INSENSITIVE).matcher(umaString).matches()){
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoIgual(Object umObjeto1, Object umObjeto2, MensagemErro umaMensagem) {
        if (!umObjeto1.equals(umObjeto2)) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoFalso(boolean umBoolean, MensagemErro umaMensagem) {
        if (umBoolean) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaComprimentoArgumento(String umaString, int umMaximo, MensagemErro umaMensagem) {
        int comprimento = umaString.trim().length();
        if (comprimento > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaComprimentoArgumento(String umaString, int umMinimo, int umMaximo, MensagemErro umaMensagem) {
        int comprimento = umaString.trim().length();
        if (comprimento < umMinimo || comprimento > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoNaoVazio(String umaString, MensagemErro umaMensagem) {
        if (umaString == null || umaString.trim().isEmpty()) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoNaoIgual(Object umObjeto1, Object umObjeto2, MensagemErro umaMensagem) {
        if (umObjeto1.equals(umObjeto2)) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoNaoNulo(Object umObjeto, MensagemErro umaMensagem) {
        if (umObjeto == null) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoMaiorOuIgualA(int umValor, int umMinimo, MensagemErro umaMensagem) {
        if (umValor < umMinimo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoMaiorOuIgualA(float umValor, float umMinimo, MensagemErro umaMensagem) {
        if (umValor < umMinimo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaIntervaloArgumento(double umValor, double umMinimo, double umMaximo, MensagemErro umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaIntervaloArgumento(float umValor, float umMinimo, float umMaximo, MensagemErro umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaIntervaloArgumento(int umValor, int umMinimo, int umMaximo, MensagemErro umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaIntervaloArgumento(long umValor, long umMinimo, long umMaximo, MensagemErro umaMensagem) {
        if (umValor < umMinimo || umValor > umMaximo) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaArgumentoVerdadeiro(boolean umBoolean, MensagemErro umaMensagem) {
        if (!umBoolean) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }

    public static void validaEstadoFalso(boolean umBoolean, MensagemErro umaMensagem) {
        if (umBoolean) {
            throw new IllegalStateException(umaMensagem.mensagem);
        }
    }

    public static void validaEstadoVerdadeiro(boolean umBoolean, MensagemErro umaMensagem) {
        if (!umBoolean) {
            throw new IllegalStateException(umaMensagem.mensagem);
        }
    }

    public static void validaIdEntidade(String idEntidade, String idComando, MensagemErro umaMensagem){
        if (!idEntidade.equals(idComando)) {
            throw new IllegalArgumentException(umaMensagem.mensagem);
        }
    }
}
