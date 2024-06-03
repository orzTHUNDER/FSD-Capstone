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

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [open, setOpen] = useState(false);
  const [alertType, setAlertType] = useState('success');
  const [alertMessage, setAlertMessage] = useState('');
  const navigate = useNavigate();

  const handleSignUp = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      setAlertType('error');
      setAlertMessage('Email and password are required');
      setOpen(true);
      return;
    }

    //http://localhost:8765/authentication/signup
    try {
      const response = await axios.post('http://127.0.0.1:52130/authentication/signup', {
        email,
        password,
      }
      // const response = await axios.post('http://localhost:8082/signup', {
      //   email,
      //   password,
      // }
    );

      if (response.status === 200) {
        setAlertType('success');
        setAlertMessage('Sign up successful');
        setOpen(true);
        setTimeout(() => {
          setOpen(false);
          navigate('/login'); // Redirect to login page after successful sign up
        }, 5000);
      // } else {
      //   setAlertType('error');
      //   setAlertMessage(response.data.message || 'Sign up failed');
      //   setOpen(true);
      // }
    } }
    catch (error) {
      console.error('Error:', error);
      setAlertType('error');
      setAlertMessage(error.response.data);
      setOpen(true);
    }
  };

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  return (
    <Container>
      <Title>Sign Up</Title>
      <Form onSubmit={handleSignUp}>
        <InputContainer>
          <Input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </InputContainer>
        <InputContainer>
          <Input
            type={passwordVisible ? 'text' : 'password'}
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <VisibilityToggle type="button" onClick={togglePasswordVisibility}>
            {passwordVisible ? <VisibilityOffIcon /> : <VisibilityIcon />}
          </VisibilityToggle>
        </InputContainer>
        <Button type="submit">Sign Up</Button>
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
        Already a user? <Link to="/login">Login</Link>
      </p>
    </Container>
  );
};

export default SignUp;
