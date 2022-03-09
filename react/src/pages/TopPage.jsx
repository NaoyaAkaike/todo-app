import styled from "@emotion/styled";
import axios from "axios";
import { useFocusEffect } from "@react-navigation/native";
import { Link } from "react-router-dom";
import React, { useCallback, useEffect, useState } from "react";

export const TopPage = () => {

  const [ todoList, setTodoList ] = useState([]);

  const getList = ()=> {
    
    axios
      .get("http://localhost:8080/")
      .then((response) => {
        console.log(response.data);
        setTodoList(response.data);
        console.log(todoList);
      })
      .catch(err => {
        console.log(err);
      })
  }

  useEffect(() => {
      console.log("initial load A");
      getList();

      return ()=> {
        console.log('clean up A');
      }
    },[]);

/*
  useFocusEffect(
    useCallback(() => {
      console.log("initial load A");
      getList();

      return ()=> {
        console.log('clean up A');
      }
    },[])
  );
*/
  const handleDelete = (todo, kijitsu) => {
    axios
      .post("http://localhost:8080/delete",{
        preTodo: todo,
        preKijitsu: kijitsu        
      })
      .then((response) => {
        console.log(response);
        getList();
      })
      .catch(err => {
        console.log(err);
      })      
  }

  const handleDone = (todo, kijitsu) => {
    axios
      .post("http://localhost:8080/done",{
        preTodo: todo,
        preKijitsu: kijitsu        
      })
      .then((response) => {
        console.log(response);
        getList();
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
          {console.log(todoList)}
          {todoList.map((list) => {     
            return list.deleteFlg === 0 &&     
            <li key={list.todo}>
              {list.sts === 0
            //未完了の場合
            ? <SMemoWrapper>
                <STodo>{list.todo}</STodo>
                <SDate>{list.kijitsu}</SDate>
                <SButtonWrapper>
                  <Link to={"/edit"} state={{todo: list.todo, kijitsu: list.kijitsu }}>
                    <SButton>編集</SButton>
                  </Link>
                  <SButton onClick={ ()=> handleDelete(list.todo, list.kijitsu)}>削除</SButton>
                  <SButton onClick={ ()=> handleDone(list.todo, list.kijitsu) }>完了</SButton>
                </SButtonWrapper>
              </SMemoWrapper>
            //完了の場合
            : <SMemoWrapper>
                <STodo><strike>{list.todo}</strike></STodo>
                <SDate><strike>{list.kijitsu}</strike></SDate>
                <SButtonWrapper>
                  <Link to={"/edit"} state={{todo: list.todo, kijitsu: list.kijitsu }}>
                    <SButton>編集</SButton>
                  </Link>
                  <SButton onClick={ ()=> handleDelete(list.todo, list.kijitsu)}>削除</SButton>
                  <SButton onClick={ ()=> handleDone(list.todo, list.kijitsu) } disabled>完了</SButton>
                </SButtonWrapper>
              </SMemoWrapper>
            }
            </li>
          })}
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
