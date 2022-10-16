import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

export const transacao = {
    idTransacao: "",
    valor: 0,
    nomeDoPagador: "",
    nomeDoRecebedor: "",
    listaItemPago: [],
    idPagamento: "",
    conjuntoCategoria: []
}

export function IncluiTransacao() {
    const [sucesso, setSucesso] = useState(false);
    const [falha, setFalha] = useState(false);
    const [dadosFormulario, setDadosFormulario] = useState(transacao);
    const {valor, nomeDoPagador, nomeDoRecebedor, listaItemPago} = dadosFormulario;

    const onChange = (evento) => {
        setDadosFormulario(prevState => ({
            ...prevState,
            [evento.target.id]: evento.target.value,
        }));
    }

    const onSubmit = async (evento) => {
        evento.preventDefault();

        try {
            await axios.post("http://localhost:8080/transacao/cria",
                dadosFormulario);
            setSucesso(true)
        } catch (error) {
            setFalha(true);
        }

        // setDadosFormulario(dadosFormularioPadrao);
    }

    return (
        <>
            <h1>Novo Pagamento</h1>
            <p>Crie um novo pagamento</p>

            <form onSubmit={onSubmit}>

                <label htmlFor="valor">Valor</label>
                <input type="number" id="valor" value={valor} onChange={onChange}/>

                <label htmlFor="nomeDoPagador">fornecedor</label>
                <input type="text" id="nomeDoPagador" value={nomeDoPagador} onChange={onChange}/>

                <label htmlFor="nomeDoRecebedor">beneficiario</label>
                <input type="text" id="nomeDoRecebedor" value={nomeDoRecebedor} onChange={onChange}/>

                <button type="submit">Enviar pagamento</button>
            </form>

            {falha && <p>Opa, não foi possível inserir...</p>}
            {sucesso && <p>Pagamento inserido!</p>}
        </>
    )
}

export function ListaTransacoes({transacoes}) {
    return (
        <>
            {transacoes.length ? (
                <table>
                    <tr>
                        <th>Id da Transação</th>
                        <th>Valor</th>
                        <th>Nome do Pagador</th>
                        <th>Nome do Recebedor</th>
                        <th>Quantidade de Itens</th>
                        <th>Id do Pagamento</th>
                        <th>Categorias</th>
                        <th>Adiciona Item</th>
                    </tr>
                    {transacoes.map((transacao) => (
                        <tr>
                            <td>
                                <Link to={`transacao/consulta/${transacao.idTransacao}`}>
                                    {transacao.idTransacao}
                                </Link>
                            </td>
                            <td>{transacao.valor}</td>
                            <td>{transacao.nomeDoPagador}</td>
                            <td>{transacao.nomeDoRecebedor}</td>
                            <td>{transacao.listaItemPago.length}</td>
                            <td>{transacao.idPagamento}</td>
                            <td>{transacao.conjuntoCategoria}</td>
                            <td><BotaoAdicionaItem idTransacao={transacao.idTransacao}/></td>
                        </tr>
                    ))}
                </table>
            ) : (
                <p>
                    <i>Sem transações</i>
                </p>
            )}
        </>
    );
}

function BotaoAdicionaItem({idTransacao}) {
    const payload = {
        idTransacao: idTransacao,
        descricao: "Uma descrição qualquer.",
        quantidade: 5,
        unidadeMedida: "UNIDADE",
        valorUnidade: 30.0
    }
    const handleClick = async (evento) => {
        evento.preventDefault();

        await axios.post("http://localhost:8080/transacao/adicionaItemPago",
            payload);
    }
    return (
        <>
            <button onClick={handleClick}>+</button>
        </>
    )
}

// export function ListaPagamentos() {
//     const [data, setData] = useState(null);
//     const [carregando, setCarregando] = useState(false);
//     const [erro, setErro] = useState(false);
//
//     useEffect(() => {
//         const buscaEventos = async () => {
//             try {
//                 setCarregando(true);
//
//                 const response = await axios.get(
//                     "http://localhost:8080/pagamento/listaTodos"
//                 );
//
//                 setData(response.data);
//                 setCarregando(false);
//                 setErro(false);
//             } catch (erro) {
//                 setErro(true);
//                 setCarregando(false);
//             }
//         };
//
//         buscaEventos();
//     }, []);
//
//     return (
//         <>
//             {carregando && 'Carregando...'}
//
//             {erro && 'Erro ao buscar eventos...'}
//
//             {data && data.map((post) => {
//                 const {ocorridoEm} = post;
//
//                 return (
//                     <article key={ocorridoEm}>
//                         <p>{ocorridoEm}</p>
//                     </article>
//                 );
//             })}
//         </>
//     );
// }

export async function ListaTodasTransacoes() {
    return (await axios.get("http://localhost:8080/transacao/lista")).data;
}