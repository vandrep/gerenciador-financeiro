import {useState} from "react";

export default function Props() {
    const [inputValue, setInputValue] = useState(0);

    return (
        <>
        <h1>Multiplied by two App</h1>

            <input type="number" onChange={(e) => setInputValue(e.target.value)}/>

            <p>Multiplied Numver: {inputValue * 2}</p>
        </>

    )
}