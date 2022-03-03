import styled from "@emotion/styled";

function App() {
  const todoList = [
    {text: "ランニングをする", date: "2020/07/20"},
    {text: "買い物に行く", date: ""},
    {text: "本を読む", date: "2020/07/23"},
  ]

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
            <li>
            <SMemoWrapper>
              <STodo>{todo.text}</STodo>
              <SDate>{todo.date}</SDate>
              <SButtonWrapper>
                <SButton>編集</SButton>
                <SButton>削除</SButton>
                <SButton>完了</SButton>
              </SButtonWrapper>
            </SMemoWrapper>
          </li>
          ))}          
        </SUl>

        <SButton>追加</SButton>
      </SContentWrapper>
    </SContainer>
  );
}
export default App;

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
