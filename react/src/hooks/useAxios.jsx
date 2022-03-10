import axios from "axios";
import { useState } from "react"
import { useNavigate } from "react-router-dom";

export const useAxios = () => {
  const navigate = useNavigate();
  const [todoList, setTodoList] = useState([]);
  const [ errorMsg, setErrorMsg ] = useState("");
  
  //リスト取得
  const getList = () => {
    axios
      .get("http://localhost:8080/")
      .then((response) => {
        setTodoList(response.data);
      })
      .catch(err => {
        console.log(err);
      })
  }
  //追加メソッド
  const handleAdd = (todo, date) => {
    axios
      .post("http://localhost:8080/add", {
        todo: todo,
        kijitsu: date
      })
      .then((response) => {
        console.log(response);
        navigate("../");
      })
      .catch((error) => {
        setErrorMsg(error.response.data.message);
      });
  }
  //編集メソッド
  const handleEdit = (id, todo, date) => {
    axios
      .post("http://localhost:8080/edit", {
        id: id,
        todo: todo,
        kijitsu: date
      })
      .then((response) => {
        console.log(response);
        console.log("id:" + id);
        console.log("todo:" + todo);
        console.log("date:" + date);

        navigate("../");
      })
      .catch((error) => {
        console.log(error);
      });
  }
  //削除メソッド
  const handleDelete = (id) => {
    axios
      .post("http://localhost:8080/delete", {
        id: id
      })
      .then((response) => {
        console.log(response);
        getList();
      })
      .catch(err => {
        console.log(err);
      })
  }
  //完了メソッド
  const handleDone = (id) => {
    axios
      .post("http://localhost:8080/done", {
        id: id
      })
      .then((response) => {
        console.log(response);
        getList();
      })
      .catch(err => {
        console.log(err);
      })
  }

  return {
    todoList,
    errorMsg,
    setTodoList,
    getList,
    handleAdd,
    handleEdit,
    handleDelete,
    handleDone
  }
}