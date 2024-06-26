/* eslint-disable react/prop-types */
// eslint-disable-next-line no-unused-vars
import React from "react";
import './botao.css';

 function Botao({children}){
    // const { acao, classe, disabled = false} = props;

    return <button className={'botao'}>{children}</button>
    // return <button className={'botao'} disabled={disabled} onClick={(e) => acao(e)}>{children}</button>
}

export default Botao;

