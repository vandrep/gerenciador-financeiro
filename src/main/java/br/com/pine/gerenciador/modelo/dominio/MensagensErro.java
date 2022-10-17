package br.com.pine.gerenciador.modelo.dominio;

public enum MensagensErro {
    MOEDA_NULA("A Moeda deve ser informada."),
    MOEDA_MENOR_QUE_ZERO("Valor Monetário deve ser maior ou igual a 0."),
    MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA("Quantidade de casas decimais do Valor Monetário inválida."),
    ITEM_PAGO_NOME_NULO("Nome do Item Pago deve ser informado."),
    ITEM_PAGO_NOME_VAZIO("Nome do Item Pago deve estar preenchido."),
    ITEM_PAGO_VALOR_NEGATIVO("Valor do Item Pago deve ser maior ou igual a 0."),
    ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM("Quantidade de Item Pago deve ser maior ou igual a 1"),
    ITEM_PAGO_UNIDADE_MEDIDA_NULA("Unidade de Medida do Item Pago deve ser informada."),
    ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO("Item Pago não existe na Transação informada."),
    TRANSACAO_ID_NULO("Id da Transação deve ser informado."),
    TRANSACAO_ID_VAZIO("Id da Transação deve ser preenchido."),
    TRANSACAO_VALOR_NEGATIVO("Valor da Transação deve ser maior ou igual a 0."),
    TRANSACAO_NOME_DO_PAGADOR_NULO("Nome do Pagador da Transação deve ser informado."),
    TRANSACAO_NOME_DO_PAGADOR_VAZIO("Nome do Pagador da Transação deve estar preenchido."),
    TRANSACAO_NOME_DO_RECEBEDOR_NULO("Nome do Pagador da Transação deve ser informado."),
    TRANSACAO_NOME_DO_RECEBEDOR_VAZIO("Nome do Pagador da Transação deve estar preenchido."),
    ID_ENTIDADE_INVALIDA("Transação não corresponde ao Comando."),
    DUMMY("DUMMY");
    public String mensagem;
    MensagensErro(String umaMensagem){
        this.mensagem = umaMensagem;
    }
}
