import axios from "axios";
import { useState } from "react"
import { useNavigate } from "react-router-dom";

export const useAxios = () => {
  const navigate = useNavigate();
  const [todoList, setTodoList] = useState([]);
  
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
        console.log(error);
      });
  }
  //編集メソッド
  const handleEdit = (preTodo, preDate, todo, date) => {
    axios
      .post("http://localhost:8080/edit", {
        preTodo: preTodo,
        preKijitsu: preDate,
        todo: todo,
        kijitsu: date
      })
      .then((response) => {
        console.log(response);
        navigate("../");
      })
      .catch((error) => {
        console.log(error);
      });
  }
  //削除メソッド
  const handleDelete = (todo, kijitsu) => {
    axios
      .post("http://localhost:8080/delete", {
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
  //完了メソッド
  const handleDone = (todo, kijitsu) => {
    axios
      .post("http://localhost:8080/done", {
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

  return {
    todoList,
    setTodoList,
    getList,
    handleAdd,
    handleEdit,
    handleDelete,
    handleDone
  }
}