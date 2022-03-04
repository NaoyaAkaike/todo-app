import styled from "@emotion/styled";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { EditTodoPage } from "./EditTodoPage";

export const TopPage = () => {
  /*デモ用のデータ
  const todoList = [
    {text: "ランニングをする", date: "2020/07/20"},
    {text: "買い物に行く", date: ""},
    {text: "本を読む", date: "2020/07/23"},
  ]*/
  const [todoList, setTodoList] = useState([]);

  useEffect(()=> {
    url();
  },[]);

  const url = ()=> {
    axios
      .get("http://localhost:8080/")
      .then((response) => {
        console.log(response.data);
        setTodoList(response.data);
      })
      .catch(err => {
        console.log(err);
      })
  }

  return (
    <SContainer>
      <h1>ToDoリスト</h1>
      <SContentWrapper>
        <SItemWrapper>
          <SItem>内容</SItem>
          <SItem>期日</SItem>
        </SItemWrapper>

        <SUl>
          {todoList.map((todo, index) => (
            <li key={todo.todo}>
              <SMemoWrapper>
                <STodo>{todo.todo}</STodo>
                <SDate>{todo.kijitsu}</SDate>
                <SButtonWrapper>
                  <Link to="/edit">
                    <SButton>編集</SButton>
                  </Link>
                  <SButton>削除</SButton>
                  <SButton>完了</SButton>
                </SButtonWrapper>
              </SMemoWrapper>
            </li>
          ))}
        </SUl>

        <Link to="/add">
          <SButton>追加</SButton>
        </Link>
      </SContentWrapper>
    </SContainer>
  );
}

//以下css
const SContainer = styled.div`
width: 700px;
border: solid 1px #3366cc;
margin: 0 auto;
margin-top: 20px;
padding: 10px 20px;
`
const SContentWrapper = styled.div`
margin-left:20px;
`
const SItemWrapper = styled.div`
display: flex;
align-items: center;
border-bottom: solid 1px black;
font-weight: bold;
width: 50%;
`
const SItem = styled.p`
width: 50%;
`
const SUl = styled.ul`
padding-left: 0px;
margin: 0px;
margin-bottom: 20px;
`
const SMemoWrapper = styled.div`
display: flex;
align-items: center;
width: 100%;
height: 40px;
`
const STodo = styled.p`
width: 25%;
`
const SDate = styled.p`
width: 25%;
`

const SButtonWrapper = styled.div`
margin-right: 30px;
margin-left: auto;
`
const SButton = styled.button`
width: 80px;
margin-right: 10px;
`
