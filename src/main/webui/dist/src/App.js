import { useState } from 'react';
import './App.css';
import Gerenciador from './Gerenciador';
function App() {
    var _a = useState(0), contador = _a[0], setContador = _a[1];
    function handleButtonClick() {
        setContador(contador + 1);
    }
    return (React.createElement("div", { className: "App" },
        React.createElement(Gerenciador, null)));
}
export default App;
