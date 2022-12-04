import React from "react";
import ReactDOM from "react-dom/client";
import PaginaErro from "./components/PaginaErro";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import "./index.css";
import Root, {
    listaTransacao,
} from "./routes/Root";
import {
    Transacao,
    acaoCriaTransacao,
    acaoAtualizaTransacao,
    transacaoLoader,
    EditaTransacao,
    acaoAdicionaItem,
    CriaItem,
} from "./components/Transacao";
import Index from "./components";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        errorElement: <PaginaErro/>,
        loader: listaTransacao,
        action: acaoCriaTransacao,
        children: [
            { index: true, element: <Index /> },
            {
                path: "transacao/:idTransacao",
                element: <Transacao />,
                loader: transacaoLoader,
                errorElement: <PaginaErro/>,
            },
            {
                path: "transacao/:idTransacao/edita",
                element: <EditaTransacao />,
                loader: transacaoLoader,
                action: acaoAtualizaTransacao,
                errorElement: <PaginaErro/>,
            },
            {
                path: "transacao/:idTransacao/criaItem",
                element: <CriaItem />,
                action: acaoAdicionaItem,
                errorElement: <PaginaErro/>,
            },
        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);
