import axios from "axios";
import {
    Form,
    NavLink,
    redirect,
    useLoaderData,
    useNavigate,
} from "react-router-dom";
import '../styles/Transacao.css';

export async function acaoAdicionaItem({request, params}) {
    const dadosFormulario = await request.formData();
    const item = Object.fromEntries(dadosFormulario);
    item.idTransacao = params.idTransacao;
    await adicionaItemPago(item);
}

export async function acaoCriaTransacao({request}) {
    const dadosFormulario = await request.formData();
    const transacao = Object.fromEntries(dadosFormulario);
    transacao.idPagamento = "umIdQualquer"
    await criaTransacao(transacao);
}

export async function acaoAtualizaTransacao({request, params}) {
    const dadosFormulario = await request.formData();
    const atualizacoes = Object.fromEntries(dadosFormulario);
    await atualizaTransacao(params.idTransacao, atualizacoes);
    return redirect(`/transacao/${params.idTransacao}`);
}

export async function transacaoLoader({params}) {
    return getTransacao(params.idTransacao);
}

export function ListaTransacoes({transacoes}) {
    return (
        <>
            {transacoes.length ? (
                <table>
                    <thead>
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
                    </thead>
                    <tbody>
                    {transacoes.map((transacao) => (
                        <tr key={transacao.idTransacao}>
                            <td>
                                <NavLink
                                    to={`transacao/${transacao.idTransacao}`}
                                    className={({isActive, isPending}) =>
                                        isActive ? "ativo" :
                                            isPending ? "pendente" : ""
                                    }
                                >
                                    {transacao.idTransacao}
                                </NavLink>
                            </td>
                            <td>{transacao.valor}</td>
                            <td>{transacao.nomeDoPagador}</td>
                            <td>{transacao.nomeDoRecebedor}</td>
                            <td>{transacao.listaDeItemPago.length}</td>
                            <td>{transacao.idPagamento}</td>
                            <td>{transacao.conjuntoCategoria}</td>
                            <td><BotaoAdicionaItem idTransacao={transacao.idTransacao}/></td>
                        </tr>
                    ))}
                    </tbody>
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
    return (
        <>
            <Form
                action={`transacao/${idTransacao}/criaItem`}>
                <button type="submit">+</button>
            </Form>
        </>
    )
}

export function Transacao() {
    const transacao = useLoaderData();
    return (
        <div id="transacao">
            <div className="cabecalho">
                <p>Descrição: {transacao.descricao}</p>
                <p>Valor: {transacao.valor}</p>
                <p>Nome do Pagador: {transacao.nomeDoPagador}</p>
                <p>Nome do Recebedor: {transacao.nomeDoRecebedor}</p>
                <table>
                    <thead>
                    <tr>
                        <th>Descrição</th>
                        <th>Quantidade</th>
                        <th>UN</th>
                        <th>Valor Unitário</th>
                        <th>Valor Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    {transacao.listaDeItemPago.map((itemPago, index) => (
                        <tr key={index}>
                            <td>{itemPago.descricao}</td>
                            <td>{itemPago.quantidade}</td>
                            <td>{itemPago.unidadeDeMedida}</td>
                            <td>{itemPago.valorDaUnidade}</td>
                            <td>{itemPago.valorDaUnidade * itemPago.quantidade}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <div>
                    <Form action="edita">
                        <button type="submit">Editar</button>
                    </Form>
                </div>
            </div>
        </div>
    );
}

export function CriaTransacao() {

    return (
        <FormularioTransacao/>
    )
}

export function EditaTransacao() {
    const transacao = useLoaderData();

    return (
        <FormularioTransacao transacao={transacao}/>
    );
}

export function CriaItem() {
    const transacao = useLoaderData();

    return (
        <FormularioItem transacao={transacao}/>
    )
}

function FormularioTransacao({transacao}) {
    const transacaoNaoPreenchida = transacao == null;
    const navega = useNavigate();

    const handleReset = () => {
        Array.from(document.querySelectorAll("input")).forEach(
            input => (input.value = input.defaultValue)
        );
    }

    return (
        <Form method="post" id="transacao-form">
            <p>
                <label>
                    <span>Descrição</span>
                    <input
                        placeholder="Descrição"
                        aria-label="Descrição"
                        type="text"
                        name="descricao"
                        defaultValue={transacaoNaoPreenchida ? "Descrição" : transacao.descricao}
                    />
                </label>
                <label>
                    <span>Pagador</span>
                    <input
                        placeholder="Pagador"
                        aria-label="Pagador"
                        type="text"
                        name="nomeDoPagador"
                        defaultValue={transacaoNaoPreenchida ? "Pagador" : transacao.nomeDoPagador}
                    />
                </label>
                <label>
                    <span>Recebedor</span>
                    <input
                        placeholder="Recebedor"
                        aria-label="Recebedor"
                        type="text"
                        name="nomeDoRecebedor"
                        defaultValue={transacaoNaoPreenchida ? "Recebedor" : transacao.nomeDoRecebedor}
                    />
                </label>
                <label>
                    <span>Valor</span>
                    <input
                        placeholder="Valor"
                        aria-label="Valor"
                        type="number"
                        name="valor"
                        defaultValue={transacaoNaoPreenchida ? "0" : transacao.valor}
                    />
                </label>
            </p>
            <p>
                <button type="submit">Salvar</button>
                {
                    transacaoNaoPreenchida ?
                        <button
                            type="button"
                            onClick={handleReset}
                        >
                            Limpar
                        </button>
                        :
                        <button
                            type="button"
                            onClick={() => {
                                navega(-1);
                            }}
                        >
                            Cancelar
                        </button>
                }
            </p>
        </Form>
    );
}

function FormularioItem({item}) {
    const itemNaoPreenchido = item == null;
    const navega = useNavigate();

    const handleReset = () => {
        Array.from(document.querySelectorAll("input")).forEach(
            input => (input.value = input.defaultValue)
        );
    }

    return (
        <Form method="post" id="item-form">
            <p>
                <label>
                    <span>Descrição</span>
                    <input
                        placeholder="Descrição"
                        aria-label="Descrição"
                        type="text"
                        name="descricao"
                        defaultValue={itemNaoPreenchido ? "Descrição" : item.descricao}
                    />
                </label>
                <label>
                    <span>Quantidade</span>
                    <input
                        placeholder="Quantidade"
                        aria-label="Quantidade"
                        type="number"
                        name="quantidade"
                        defaultValue={itemNaoPreenchido ? "0" : item.quantidade}
                    />
                </label>
                <label>
                    <span>Unidade de Medida</span>
                    <input
                        placeholder="Unidade de Medida"
                        aria-label="Unidade de Medida"
                        type="text"
                        name="tipoUnidadeMedida"
                        defaultValue={itemNaoPreenchido ? "UN" : item.unidadeMedida}
                    />
                </label>
                <label>
                    <span>Valor</span>
                    <input
                        placeholder="Valor"
                        aria-label="Valor"
                        type="number"
                        name="valorUnidade"
                        defaultValue={itemNaoPreenchido ? "0" : item.valorUnidade}
                    />
                </label>
            </p>
            <p>
                <button type="submit">Salvar</button>
                {
                    itemNaoPreenchido ?
                        <button
                            type="button"
                            onClick={handleReset}
                        >
                            Limpar
                        </button> :
                        <button
                            type="button"
                            onClick={() => {
                                navega(-1);
                            }}
                        >
                            Cancelar
                        </button>
                }
            </p>
        </Form>
    );
}

export async function listaTodasTransacoes() {
    return (await axios.get("http://localhost:8080/transacao/lista")).data;
}

export async function getTransacao(id) {
    return (await axios.get(`http://localhost:8080/transacao/consulta/${id}`)).data;
}

export async function criaTransacao(transacao) {
    return await axios.post("http://localhost:8080/transacao/cria",
        transacao);
}

export async function atualizaTransacao(id, atualizacoes) {
    return await axios.post()
}

async function adicionaItemPago(item) {
    return await axios.post("http://localhost:8080/transacao/adicionaItemPago",
        item);
}
