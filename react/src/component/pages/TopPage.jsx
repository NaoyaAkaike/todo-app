import styled from "@emotion/styled";
import { Link } from "react-router-dom";
import React, { useEffect } from "react";
import { useAxios } from "../hooks/useAxios";
import { judgeDate } from "../functions/JudgeDate";

export const TopPage = () => {

  const { todoList, getList, handleDelete, handleDone } = useAxios();

  useEffect(() => {
    getList();
  }, []);

  const displayItems = (todo, kijitsu, isCompleted)=> {
    if (isCompleted) {
      return (
        <><STodo><strike>{todo}</strike></STodo>
        <SDate><strike>{kijitsu}</strike></SDate></>
      )
    } else {
      const isExpired = judgeDate(kijitsu);
      return (
        isExpired
        ? <><STodo style={{color:"red"}}>{todo}</STodo>
          <SDate style={{color:"red"}}>{kijitsu}</SDate></>
        : <><STodo>{todo}</STodo>
          <SDate>{kijitsu}</SDate></>
      )
    }
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
          {todoList.map((list, index) => {
            return (
              <li key={list.id}>
                <SMemoWrapper>
                  {displayItems(list.todo, list.kijitsu, list.isCompleted)}
                  <SButtonWrapper>
                    <Link to={"/edit"} state={{ id: list.id, todo: list.todo, kijitsu: list.kijitsu }}>
                      <SButton>編集</SButton>
                    </Link>
                    <SButton onClick={() => handleDelete(list.id)}>削除</SButton>
                    {list.isCompleted  //完了判定
                      ? <SButton onClick={() => handleDone(list.id)} disabled>完了</SButton>
                      : <SButton onClick={() => handleDone(list.id)}>完了</SButton>}
                  </SButtonWrapper>
                </SMemoWrapper>
                {judgeDate(list.kijitsu)}
              </li>              
            )})}
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
