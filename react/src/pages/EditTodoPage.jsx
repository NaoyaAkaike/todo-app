import styled from "@emotion/styled";
import axios from "axios";
import { useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { useStateIfMounted } from "use-state-if-mounted";

export const EditTodoPage = () => {
    const location = useLocation();

    const [ todo, setTodo ] = useStateIfMounted("");
    const [ date, setDate ] = useStateIfMounted("");

    useEffect(()=> {
        setTodo(location.state.todo);
        setDate(location.state.kijitsu);
    },[]);

    const onChangeTodo = (e)=> {
        setTodo(e.target.value);
    }

    const onChangeDate = (e)=> {
        setDate(e.target.value);
    }

    const handleEdit = () => {
        axios
        .post("http://localhost:8080/edit",{
            preTodo: location.state.todo,
            preKijitsu: location.state.kijitsu,
            todo: todo,
            kijitsu: date
        })
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        });        
    }

    return (
        <SContainer>
            <h1>ToDoリスト</h1>
            <SContentWrapper>
                <SItemWrapper>
                    <STextbox>
                        <p>Todo内容</p>
                        <SInput
                        type="text"
                        //defaultValue={location.state.todo}
                        value={todo}
                        onChange={onChangeTodo}
                        ></SInput>
                    </STextbox>
                    <STextbox>
                        <p>期日</p>
                        <SInput
                        type="text"
                        //defaultValue={location.state.kijitsu}
                        value={date}
                        onChange={onChangeDate}
                        ></SInput>
                    </STextbox>
                    <SButtonWrapper>
                        <SLinkButton>
                            <Link to="/" onClick={handleEdit}>
                                <SButton>編集</SButton>
                            </Link>
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
`
const SLinkButton = styled.div`
margin-right: 30px;
margin-left: auto;
`