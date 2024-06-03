import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 80vh;
  margin-top: -10vh;
`;

const Title = styled.h1`
  font-size: 2rem;
  color: #333;
  margin-bottom: 20px;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 300px;
`;

const InputContainer = styled.div`
  position: relative;
  display: flex;
  align-items: center;
`;

const Input = styled.input`
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
  width: 100%;
`;

const VisibilityToggle = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  padding: 5px;
  position: absolute;
  right: 5px;
`;

const Button = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #0056b3;
  }
`;

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [open, setOpen] = useState(false);
  const [alertType, setAlertType] = useState('success');
  const [alertMessage, setAlertMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      setAlertType('error');
      setAlertMessage('Email and password are required');
      setOpen(true);
      return;
    }
    //http://localhost:8765/authentication/login
    try {
      const response = await axios.post('http://127.0.0.1:52130/authentication/login', {
        email,
        password,
      }
      // const response = await axios.post('http://localhost:8082/login', {
      //   email,
      //   password,
      // }
    );

      if (response.status === 200) {
        setAlertType('success');
        setAlertMessage('Login successful');
        setOpen(true);
        setTimeout(() => {
          setOpen(false);
          navigate('/currency'); // Redirect to JokeApp after successful login
        }, 5000);
      // } else if (response.status === 400) {
      //   setAlertType('error');
      //   setAlertMessage(response.data.message || 'Invalid Credentials');
      //   setOpen(true);
      //   setTimeout(() => {
      //     setOpen(false);
      //   }, 5000);
      // }
    } }catch (error) {
      console.error('Error:', error);
      setAlertType('error');
      setAlertMessage(error.response.data || 'Login Failed');
      setOpen(true);
    }
  };

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  return (
    <Container>
      <Title>Login</Title>
      <Form onSubmit={handleLogin}>
        <InputContainer>
          <Input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
            title="Please enter a valid email address"
            required
          />
        </InputContainer>
        <InputContainer>
          <Input
            type={passwordVisible ? 'text' : 'password'}
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <VisibilityToggle type="button" onClick={togglePasswordVisibility}>
            {passwordVisible ? <VisibilityOffIcon /> : <VisibilityIcon />}
          </VisibilityToggle>
        </InputContainer>
        <Button type="submit">Login</Button>
      </Form>
      <Snackbar
        open={open}
        autoHideDuration={5000}
        onClose={() => setOpen(false)}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert onClose={() => setOpen(false)} severity={alertType}>
          {alertMessage}
        </Alert>
      </Snackbar>
      <p>
        New user? <Link to="/signup">Signup</Link>
      </p>
    </Container>
  );
};

export default Login;
