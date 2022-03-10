import styled from "@emotion/styled";
import { useCallback } from "react";
import { useStateIfMounted } from "use-state-if-mounted";
import { useAxios } from "../hooks/useAxios";

export const AddTodoPage = () => {

    const { errorMsg, handleAdd } =useAxios();
    const [todo, setTodo] = useStateIfMounted("");
    const [date, setDate] = useStateIfMounted("");

    const onChangeTodo = (e) => {
        setTodo(e.target.value);
    }

    const onChangeDate = (e) => {
        setDate(e.target.value);
    }
    console.log(errorMsg);

    return (
        <SContainer>
            <h1>ToDoリスト</h1>
            <SContentWrapper>
                <SItemWrapper>
                    <STextbox>
                        <p>Todo内容</p>
                        <SMessageWrapper>
                        <SInput
                            type="text"
                            value={todo}
                            onChange={onChangeTodo}
                        ></SInput>
                        { (errorMsg==="01") | (errorMsg==="02")
                        ? <SErrorMsg>内容を入力してください</SErrorMsg>
                        : <SErrorMsg></SErrorMsg>}
                        </SMessageWrapper>                        
                    </STextbox>
                    <STextbox>
                        <p>期日</p>
                        <SMessageWrapper>
                        <SInput
                            type="text"
                            value={date}
                            onChange={onChangeDate}
                        ></SInput>
                        { (errorMsg==="01") | (errorMsg==="03")
                        ? <SErrorMsg>正しく入力してください（yyyy-MM-dd）</SErrorMsg>
                        : <SErrorMsg> </SErrorMsg>}                        
                        </SMessageWrapper>                        
                    </STextbox>
                    <SButtonWrapper>
                        <SLinkButton>
                            <SButton onClick={useCallback(()=> handleAdd(todo, date))}>登録</SButton>
                        </SLinkButton>

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
const SMessageWrapper = styled.div`
height: 70px;
margin-right: 30px;
margin-left: auto;
`
const SInput = styled.input`
width: 300px;
margin-top: 24px;
`
const SErrorMsg = styled.p`
margin: 0px 0px;
font-weight: normal;
color: red;
`
const SButtonWrapper = styled.div`
display: flex;
align-items: center;
margin-top: 15px;
margin-bottom: 10px;
`
const SButton = styled.button`
width: 80px;
`
const SLinkButton = styled.div`
margin-right: 30px;
margin-left: auto;
`
