import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AddTodoPage } from "./component/pages/AddTodoPage";
import { EditTodoPage } from "./component/pages/EditTodoPage";
import { TopPage } from "./component/pages/TopPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<TopPage />} />
          <Route path="/add" element={<AddTodoPage />} />
          <Route path="/edit" element={<EditTodoPage />} />
        </Routes>
      </BrowserRouter>
  );
}
export default App;