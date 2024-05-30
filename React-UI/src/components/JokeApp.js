import React, { useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh; /* Set the container height to full viewport height */
`;

const Title = styled.h1`
  font-size: 2rem;
  color: #000;
`;

const Loading = styled.p`
  font-size: 1.2rem;
  color: #000;
`;

const JokeContainer = styled.div`
  margin-top: 20px;
`;

const JokeText = styled.p`
  font-size: 1.5rem;
  color: red;
  font-weight: bold;
  margin-bottom: 10px;
`;

const Button = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 20px;

  &:hover {
    background-color: #0056b3;
  }
`;

const JokeApp = () => {
  const [joke, setJoke] = useState({});
  const [loading, setLoading] = useState(false);

  const fetchJoke = async () => {
    setLoading(true);
    try {
      const response = await axios.get('http://localhost:8081/joke');
      setJoke(response.data);
    } catch (error) {
      console.error('Error fetching joke:', error);
    }
    setLoading(false);
  };

  return (
    <Container>
      <Title>Joke App</Title>
      {loading ? (
        <Loading>Loading...</Loading>
      ) : (
        <div>
          {joke.setup && joke.punchline ? (
            <JokeContainer>
              <JokeText>{joke.setup}</JokeText>
              <JokeText>{joke.punchline}</JokeText>
              <Button onClick={() => fetchJoke()}>Get Another Joke</Button>
            </JokeContainer>
          ) : (
            <Button onClick={() => fetchJoke()}>Click for Joke</Button>
          )}
        </div>
      )}
    </Container>
  );
};

export default JokeApp;
