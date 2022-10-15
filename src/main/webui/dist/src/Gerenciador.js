var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
import React from "react";
function Gerenciador() {
    return (React.createElement("header", null,
        React.createElement("div", null,
            React.createElement(FormularioNovoPagamento, null))));
}
var FormularioNovoPagamento = /** @class */ (function (_super) {
    __extends(FormularioNovoPagamento, _super);
    function FormularioNovoPagamento(props) {
        var _this = _super.call(this, props) || this;
        _this.state = {
            data: "",
            valor: 0,
            fornecedor: "Nome do Fornecedor",
            beneficiário: "Nome do Beneficiário",
        };
        _this.handleInputChange = _this.handleInputChange.bind(_this);
        _this.handleSubmit = _this.handleSubmit.bind(_this);
        return _this;
    }
    FormularioNovoPagamento.prototype.handleInputChange = function (event) {
        var _a;
        var target = event.target;
        var value = target.type === 'checkbox' ? target.checked : target.value;
        var name = target.name;
        this.setState((_a = {},
            _a[name] = value,
            _a));
    };
    FormularioNovoPagamento.prototype.handleSubmit = function (event) {
        fetch('http://localhost:8080/pagamento/cria', {
            method: 'POST',
            headers: {
                'Accept': '*/*',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                data: '2022-10-20',
                valor: '50',
                nomeFornecedor: 'yourOtherValue',
                nomeBeneficiario: 'yourOtherValue'
            })
        }).then(function (response) { return response.json(); });
    };
    FormularioNovoPagamento.prototype.render = function () {
        return (React.createElement("form", { onSubmit: this.handleSubmit },
            React.createElement("label", null,
                "Data:",
                React.createElement("input", { name: "data", type: "date", value: this.state.data, onChange: this.handleInputChange })),
            React.createElement("br", null),
            React.createElement("label", null,
                "Valor:",
                React.createElement("input", { name: "valor", type: "number", value: this.state.valor, onChange: this.handleInputChange })),
            React.createElement("label", null,
                "Fornecedor:",
                React.createElement("input", { name: "fornecedor", type: "text", value: this.state.fornecedor, onChange: this.handleInputChange })),
            React.createElement("label", null,
                "Benefici\u00E1rio:",
                React.createElement("input", { name: "beneficiario", type: "text", value: this.state.beneficiario, onChange: this.handleInputChange })),
            React.createElement("input", { type: "submit", value: "Adicionar" })));
    };
    return FormularioNovoPagamento;
}(React.Component));
export default Gerenciador;
