package br.com.pine.gerenciador.modelo.dominio;

public enum MensagemErro {
    VALOR_MONETARIO_VALOR_MENOR_QUE_ZERO("Valor Monetário deve ser maior ou igual a 0."),

    ID_TRANSACAO_NULO("ID da Transação deve ser informado."),
    ID_TRANSACAO_VAZIO("ID da Transação deve estar preenchido."),
    ID_TRANSACAO_TAMANHO_INVALIDO("ID da Transação deve ter no máximo 36 caracteres."),

    ID_PAGAMENTO_NULO("ID do Pagamento deve ser informado."),
    ID_PAGAMENTO_VAZIO("ID do Pagamento deve estar preenchido."),
    ID_PAGAMENTO_TAMANHO_INVALIDO("ID do Pagamento deve ter no máximo 36 caracteres."),

    ID_PARCELA_NULO("ID da Parcela deve ser informado."),
    ID_PARCELA_VAZIO("ID da Parcela deve estar preenchido."),
    ID_PARCELA_TAMANHO_INVALIDO("ID da Parcela deve ter no máximo 36 caracteres."),

    ID_CONTA_NULO("ID da Conta deve ser informado."),
    ID_CONTA_VAZIO("ID da Conta deve estar preenchido."),
    ID_CONTA_TAMANHO_INVALIDO("ID da Conta deve ter no máximo 36 caracteres."),

    ITEM_PAGO_NOME_NULO("Nome do Item Pago deve ser informado."),
    ITEM_PAGO_VALOR_MONETARIO_UNIDADE_NULO("Valor Monetário do Item Pago deve ser informada."),
    ITEM_PAGO_QUANTIDADE_NULA("Quantidade do Item Pago deve ser informada."),
    ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO("Item Pago não existe na Transação informada."),

    ITEM_PAGO_DESCRICAO_NULO("Descrição da Transação deve ser informado."),
    ITEM_PAGO_DESCRICAO_VAZIO("Descrição da Transação deve estar preenchido."),
    ITEM_PAGO_DESCRICAO_TAMANHO_INVALIDO("Descrição da Transação deve ter no máximo 120 caracteres."),

    TRANSACAO_PAGAMENTO_NULO("Pagamento da Transação deve ser informado."),
//    TRANSACAO_PAGADOR_NULO("Pagamento deve ser informado."),
//    TRANSACAO_NOME_DO_PAGADOR_TAMANHO_INVALIDO("Nome do Pagador da Transação deve ter no máximo 60 caracteres."),
//    TRANSACAO_NOME_DO_RECEBEDOR_NULO("Nome do Recebedor da Transação deve ser informado."),
//    TRANSACAO_NOME_DO_RECEBEDOR_VAZIO("Nome do Recebedor da Transação deve estar preenchido."),
//    TRANSACAO_NOME_DO_RECEBEDOR_TAMANHO_INVALIDO("Nome do Recebedor da Transação deve ter no máximo 60 caracteres."),
//    TRANSACAO_DEVE_TER_PELO_MENOS_UM_ITEM_PAGO("Transação deve ter pelos menos um item pago."),

    ID_ENTIDADE_INVALIDA("Transação não corresponde ao Comando."),


    QUANTIDADE_TIPO_UNIDADE_DE_MEDIDA_NULO("Tipo de Unidade de Medida da Quantidade deve ser informado."),
    QUANTIDADE_MULTIPLICADOR_NULO("Quantidade deve ser informada."),

    TIPO_UNIDADE_DE_MEDIDA_INVALIDA("Tipo de Unidade de Medida não definida."),
    TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO("Multiplicador do Tipo de Unidade de Medida fora do intervalo."),


    DUMMY("DUMMY");
    public String mensagem;

    MensagemErro(String umaMensagem) {
        this.mensagem = umaMensagem;
    }
}
