import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import JokeApp from './components/JokeApp';
import SignUp from './components/Signup';
import Login from './components/Login';
import CurrencyConverter from './components/CurrencyConverter';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/jokes" element={<JokeApp />} />
          <Route path="/login" element={<Login />} />
          <Route path="/currency" element={<CurrencyConverter/>} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
