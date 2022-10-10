package br.com.pine.gerenciador.modelo.dominio;

public enum MensagensErro {
    MOEDA_NULA("A Moeda deve ser informada."),
    MOEDA_MENOR_QUE_ZERO("Valor Monetário deve ser maior ou igual a 0."),
    MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA("Quantidade de casas decimais do Valor Monetário inválida."),
    ITEM_PAGO_NOME_NULO("Nome do Item Pago deve ser informado."),
    ITEM_PAGO_NOME_VAZIO("Nome do Item Pago deve estar preenchido."),
    ITEM_PAGO_VALOR_MONETARIO_VAZIO("Valor Monetario do Item Pago deve ser informado."),
    ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM("Quantidade de Item Pago deve ser maior ou igual a 1"),
    PAGAMENTO_DATA_NULA("Data do Pagamento deve ser informada."),
    PAGAMENTO_VALOR_MONETARIO_NULO("Valor Monetario do Pagamento deve ser informado."),
    PAGAMENTO_NOME_FORNECEDOR_NULO("Nome do Fornecedor do Pagamento deve ser informado."),
    PAGAMENTO_NOME_FORNECEDOR_VAZIO("Nome do Fornecedor do Pagamento deve estar preenchido."),
    PAGAMENTO_NOME_BENEFICIARIO_NULO("Nome do Beneficiário do Pagamento deve ser informado."),
    PAGAMENTO_NOME_BENEFICIARIO_VAZIO("Nome do Beneficiário do Pagamento deve estar preenchido."),
    DUMMY("DUMMY");
    public String mensagem;
    MensagensErro(String umaMensagem){
        this.mensagem = umaMensagem;
    }
}
