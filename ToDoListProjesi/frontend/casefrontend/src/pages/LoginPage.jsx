import React, { useState } from "react";
import {
  Container,
  Paper,
  Typography,
  TextField,
  Button,
  Snackbar,
  Alert,
} from "@mui/material";
import axiosBackendApi from "../AxiosBackend";

const Login = ({ onLogin }) => {
  const [alertMessage, setAlertMessage] = useState("");
  const [alertSeverity, setAlertSeverity] = useState("success");
  const [open, setOpen] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    localStorage.clear();
    axiosBackendApi
      .post("/auth/login", {
        username: username,
        password: password,
      })
      .then((response) => {
        localStorage.setItem("username", username);
        localStorage.setItem("authToken", response.data.token);

        setAlertMessage("Giriş başarılı!");
        setAlertSeverity("success");

        setOpen(true);
        onLogin(true);
      })
      .catch((err) => {
        console.log(err);
        setAlertMessage("Hatalı kullanıcı adı veya şifre.");
        setAlertSeverity("error");
        setOpen(true);
      });
  };

  const handleRegister = () => {
    axiosBackendApi
      .post("/auth/register", {
        username: username,
        password: password,
      })
      .then((response) => {
        setAlertMessage("Kayıt işlemi başarılı giriş yapabilirsiniz");
        setAlertSeverity("success");
        setOpen(true);
      })
      .catch((err) => {
        setAlertMessage(err.response.data);
        setAlertSeverity("error");
        setOpen(true);
      });
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleLogin(); // Enter tuşuna basıldığında giriş metodu çalışacak
    }
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  return (
    <Container
      maxWidth="sm"
      className="login-container"
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <Paper
        elevation={18}
        className="login-paper"
        style={{ width: "100%", padding: "20px", borderBottom: "none" }}
      >
        <Typography style={{ textAlign: "center" }} variant="h5">
          {" "}
          Giriş Yap
        </Typography>
        <TextField
          label="Kullanıcı Adı"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          fullWidth
          margin="normal"
          onKeyDown={handleKeyPress}
        />
        <TextField
          label="Şifre"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          fullWidth
          margin="normal"
          onKeyDown={handleKeyPress}
        />
        <div
          style={{
            display: "flex",
            flexDirection: "flex",
            justifyContent: "center",
            gap: "10px",
          }}
        >
          <Button variant="contained" color="primary" onClick={handleLogin}>
            Giriş
          </Button>
          <Button variant="contained" color="primary" onClick={handleRegister}>
            Kayıt ol
          </Button>
          <Snackbar
            open={open}
            autoHideDuration={6000}
            onClose={handleClose}
            anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
          >
            <Alert
              onClose={handleClose}
              severity={alertSeverity}
              sx={{
                backgroundColor:
                  alertSeverity === "success" ? "#28a745" : "#dc3545",
                color: "white",
              }}
            >
              {alertMessage}
            </Alert>
          </Snackbar>
        </div>
      </Paper>
    </Container>
  );
};

export default Login;
