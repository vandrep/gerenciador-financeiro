import './App.css';
import {Link, Outlet, useLoaderData,} from "react-router-dom";
import {IncluiTransacao, ListaPagamentos} from "./components/Transacao";

export async function loader() {
    const pagamentos = await ListaPagamentos();
    return {pagamentos};
}
//
// export async function action(){
//     await createContact();
// }

export default function App() {
    const {pagamentos} = useLoaderData();
    return (
        <>
            <div id="cabecalho">
                <IncluiTransacao />
            </div>
            <div id="sidebar">
                <h1>Gerenciador Financeiro</h1>
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
                            {pagamentos.map((contact) => (
                                <li key={contact.id}>
                                    <Link to={`contacts/${contact.id}`}>
                                        {contact.first || contact.last ? (
                                            <>
                                                {contact.first} {contact.last}
                                            </>
                                        ) : (
                                            <i>No Name</i>
                                        )}{" "}
                                        {contact.favorite && <span>★</span>}
                                    </Link>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No contacts</i>
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
