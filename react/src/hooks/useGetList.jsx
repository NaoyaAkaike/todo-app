import axios from "axios";
import { useState } from "react"

export const useGetList = () => {
    const [todoList, setTodoList] = useState([]);

    const url = ()=> {
        axios
          .get("http://localhost:8080/")
          .then((response) => {
            setTodoList(response.data);
          })
          .catch(err => {
            console.log(err);
          })
      }

      return { todoList, setTodoList, url }
}