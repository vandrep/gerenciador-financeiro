import React, {useEffect, useState} from "react";
import axios from "axios";

export const pagamento = {
    idEntidade: "",
    data: "",
    valorMonetario: 0,
    nomeFornecedor: "",
    nomeBeneficiario: "",
    conjuntoItemPago: []
}

export function IncluiPagamento() {
    const [sucesso, setSucesso] = useState(false);
    const [falha, setFalha] = useState(false);
    const [dadosFormulario, setDadosFormulario] = useState(pagamento);
    const {data, valor, nomeFornecedor, nomeBeneficiario, conjuntoItemPago} = dadosFormulario;

    const onChange = (evento) => {
        setDadosFormulario(prevState => ({
            ...prevState,
            [evento.target.id]: evento.target.value,
        }));
    }

    const onSubmit = async (evento) => {
        evento.preventDefault();

        try {
            axios.post("http://localhost:8080/pagamento/cria",
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
                <label htmlFor="data">Data</label>
                <input type="date" id="data" value={data} onChange={onChange}/>

                <label htmlFor="valor">Valor</label>
                <input type="number" id="valor" value={valor} onChange={onChange}/>

                <label htmlFor="nomeFornecedor">fornecedor</label>
                <input type="text" id="nomeFornecedor" value={nomeFornecedor} onChange={onChange}/>

                <label htmlFor="nomeBeneficiario">beneficiario</label>
                <input type="text" id="nomeBeneficiario" value={nomeBeneficiario} onChange={onChange}/>

                <button type="submit">Enviar pagamento</button>
            </form>

            {falha && <p>Opa, não foi possível inserir...</p>}
            {sucesso && <p>Pagamento inserido!</p>}
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

export async function ListaTodosPagamentos(){
    return (await axios.get("http://localhost:8080/pagamento/listaPagamentos")).data;
}