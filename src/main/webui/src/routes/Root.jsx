import {
    IncluiTransacao,
    ListaTodasTransacoes, ListaTransacoes, transacao
} from "../components/Transacao";
import {
    Outlet,
    Link,
    useLoaderData,
} from "react-router-dom";

export async function loader() {
    const transacoes = await ListaTodasTransacoes();
    return { transacoes };
}

export default function Root() {
    const {transacoes} = useLoaderData();
    return (
        <>
            <div id="cabecalho">
                <IncluiTransacao />
            </div>
            <div id="sidebar">
                <h1>Gerenciador Financeiro</h1>
                {/*<ListaPagamentos />*/}
                <div>
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
                </div>
                <ListaTransacoes transacoes={transacoes}/>
            </div>
            <div id="detail">
                <Outlet/>
            </div>
        </>
    );
}