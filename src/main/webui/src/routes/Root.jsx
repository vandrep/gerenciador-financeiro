import {
    CriaTransacao,
    listaTodasTransacoes,
    ListaTransacoes
} from "../components/Transacao";
import {
    Outlet,
    useLoaderData,
    useNavigation,
} from "react-router-dom";

export async function listaTransacao() {
    const transacoes = await listaTodasTransacoes();
    return {transacoes};
}

export default function Root() {
    const {transacoes} = useLoaderData();
    const navegacao = useNavigation();

    return (
        <>
            <div id="cabecalho">
                <CriaTransacao/>
            </div>
            <div id="sidebar">
                <h1>Gerenciador Financeiro</h1>
                <ListaTransacoes transacoes={transacoes}/>
            </div>
            <div
                id="detail"
                className={
                    navegacao.state === "loading" ? "loading" : ""
                }
            >
                <Outlet/>
            </div>
        </>
    );
}

function Search() {
    return (
        <>
            <form id="search-form" role="search">
                <input
                    id="q"
                    aria-label="Busca alguma coisa"
                    placeholder="Search"
                    type="search"
                    name="q"
                />
                <div
                    id="search-spinner"
                    aria-hidden
                    hidden={true}
                />
                <div
                    className="sr-only"
                    aria-live="polite"
                ></div>
            </form>
            <form method="post">
                <button type="submit">New</button>
            </form>
        </>
    );
}