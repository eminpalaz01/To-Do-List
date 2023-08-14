import React, { useState } from "react";
import { CssBaseline } from "@mui/material";
import Login from "./pages/LoginPage";
import TodoList from "./pages/TodoListPage";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = (status) => {
    setIsLoggedIn(status);
  };

  return (
    <div className="App">
      <CssBaseline />
      <header className="App-header">
        {isLoggedIn ? (
          <TodoList isLoggedIn={isLoggedIn} />
        ) : (
          <Login onLogin={handleLogin} />
        )}
      </header>
    </div>
  );
}

export default App;
