import styled from "@emotion/styled";

export const AddTodoPage = () => {

    return (
        <SContainer>
            <h1>ToDoリスト</h1>
            <SContentWrapper>
                <SItemWrapper>
                    <STextbox>
                        <p>Todo内容</p>
                        <SInput type="text"></SInput>
                    </STextbox>
                    <STextbox>
                        <p>期日</p>
                        <SInput type="text"></SInput>
                    </STextbox>
                    <SButtonWrapper>
                    <SButton>編集</SButton>
                    </SButtonWrapper>
                    
                </SItemWrapper>
                
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
font-weight: bold;
width: 70%;
`
const STextbox = styled.div`
display: flex;
align-items: center;
`
const SInput = styled.input`
width: 300px;
margin-right: 30px;
margin-left: auto;
`
const SButtonWrapper = styled.div`
display: flex;
align-items: center;
margin-top: 15px;
margin-bottom: 10px;
`
const SButton = styled.button`
width: 80px;
margin-right: 30px;
margin-left: auto;
`
