import styled from "@emotion/styled";

function App() {

  return (
    <SContainer>
      <h1>ToDoリスト</h1>
      <SContentWrapper>
      <SItemWrapper>
      <SItem>内容</SItem>
      <SItem>期日</SItem>
      </SItemWrapper>
      
      <SUl>
        <li>
      <SMemoWrapper>
        <STodo>ランニングをする</STodo>
        <SDate>2020/07/20</SDate>
        <SButtonWrapper>
        <SButton>編集</SButton>
        <SButton>削除</SButton>
        <SButton>完了</SButton>
        </SButtonWrapper>
      </SMemoWrapper>
      </li>
      </SUl>
      
      <SButton>追加</SButton>
      </SContentWrapper>
    </SContainer>
  );
}
export default App;
const SContainer = styled.div`
width: 700px;
border: solid 1px indigo;
margin: 0 auto;
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
margin: 0px
`
const SMemoWrapper = styled.div`
display: flex;
align-items: center;
width: 100%;
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
