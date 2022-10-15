import {
    IncluiPagamento,
    // ListaPagamentos,
    ListaTodosPagamentos, pagamento
} from "../components/Pagamento";
import {
    Outlet,
    Link,
    useLoaderData,
} from "react-router-dom";

export async function loader() {
    const pagamentos = await ListaTodosPagamentos();
    return { pagamentos };
}

export default function Root() {
    const {pagamentos} = useLoaderData();
    return (
        <>
            <div id="cabecalho">
                <IncluiPagamento />
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
                <nav>
                    {pagamentos.length ? (
                        <ul>
                            {pagamentos.map((pagamento) => (
                                <li key={pagamento.idEntidade}>
                                    <Link to={`pagamentos/${pagamento.idEntidade}`}>
                                        {pagamento.idEntidade}
                                    </Link>

                                    {pagamento.data}
                                </li>
                            ))}
                    {/*        {pagamentos.map((contact) => (*/}
                    {/*            <li key={contact.id}>*/}
                    {/*                <Link to={`contacts/${contact.id}`}>*/}
                    {/*                    {contact.first || contact.last ? (*/}
                    {/*                        <>*/}
                    {/*                            {contact.first} {contact.last}*/}
                    {/*                        </>*/}
                    {/*                    ) : (*/}
                    {/*                        <i>No Name</i>*/}
                    {/*                    )}{" "}*/}
                    {/*                    {contact.favorite && <span>★</span>}*/}
                    {/*                </Link>*/}
                    {/*            </li>*/}
                    {/*        ))}*/}
                        </ul>
                    ) : (
                        <p>
                            <i>Sem pagamentos</i>
                        </p>
                    )}
                </nav>
            </div>
            <div id="detail">
                <Outlet/>
            </div>
        </>
    );
}