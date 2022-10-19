package br.com.pine.gerenciador.modelo.dominio;

public enum MensagensErro {
    MOEDA_NULA("A Moeda deve ser informada."),
    MOEDA_MENOR_QUE_ZERO("Valor Monetário deve ser maior ou igual a 0."),
    MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA("Quantidade de casas decimais do Valor Monetário inválida."),

    ID_TRANSACAO_NULO("ID da Transação deve ser informado."),
    ID_TRANSACAO_VAZIO("ID da Transação deve estar preenchido."),
    ID_TRANSACAO_TAMANHO_INVALIDO("ID da Transação deve ter no máximo 36 caracteres."),

    ID_PAGAMENTO_NULO("ID do Pagamento deve ser informado."),
    ID_PAGAMENTO_VAZIO("ID do Pagamento deve estar preenchido."),
    ID_PAGAMENTO_TAMANHO_INVALIDO("ID do Pagamento deve ter no máximo 36 caracteres."),

    ITEM_PAGO_NOME_NULO("Nome do Item Pago deve ser informado."),
    ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM("Quantidade de Item Pago deve ser maior ou igual a 1"),
    ITEM_PAGO_UNIDADE_MEDIDA_NULA("Unidade de Medida do Item Pago deve ser informada."),
    ITEM_PAGO_VALOR_MONETARIO_NULO("Valor Monetário do Item Pago deve ser informada."),
    ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO("Item Pago não existe na Transação informada."),

    TRANSACAO_DESCRICAO_NULO("Descrição da Transação deve ser informado."),
    TRANSACAO_DESCRICAO_VAZIO("Descrição da Transação deve estar preenchido."),
    TRANSACAO_DESCRICAO_TAMANHO_INVALIDO("Descrição da Transação deve ter no máximo 120 caracteres."),
    TRANSACAO_NOME_DO_PAGADOR_NULO("Nome do Pagador da Transação deve ser informado."),
    TRANSACAO_NOME_DO_PAGADOR_VAZIO("Nome do Pagador da Transação deve estar preenchido."),
    TRANSACAO_NOME_DO_PAGADOR_TAMANHO_INVALIDO("Nome do Pagador da Transação deve ter no máximo 60 caracteres."),
    TRANSACAO_NOME_DO_RECEBEDOR_NULO("Nome do Recebedor da Transação deve ser informado."),
    TRANSACAO_NOME_DO_RECEBEDOR_VAZIO("Nome do Recebedor da Transação deve estar preenchido."),
    TRANSACAO_NOME_DO_RECEBEDOR_TAMANHO_INVALIDO("Nome do Recebedor da Transação deve ter no máximo 60 caracteres."),
    TRANSACAO_DEVE_TER_PELO_MENOS_UM_ITEM_PAGO("Transação deve ter pelos menos um item pago."),

    ID_ENTIDADE_INVALIDA("Transação não corresponde ao Comando."),

    DUMMY("DUMMY");
    public String mensagem;
    MensagensErro(String umaMensagem){
        this.mensagem = umaMensagem;
    }
}
