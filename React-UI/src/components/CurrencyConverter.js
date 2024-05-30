import React, { useState, useEffect } from 'react';
import Select from 'react-select';
import './CurrencyConverter.css';

const CurrencyConverter = () => {
  const [fromCurrency, setFromCurrency] = useState({ value: 'USD', label: 'USD' });
  const [toCurrency, setToCurrency] = useState({ value: 'EUR', label: 'EUR' });
  const [amount, setAmount] = useState(1);
  const [result, setResult] = useState(null);

  const currencies = [
    { value: 'USD', label: 'USD' },
    { value: 'EUR', label: 'EUR' },
    { value: 'INR', label: 'INR' },

  ];

  useEffect(() => {
    if (amount > 0) {
      convertCurrency();
    }
  }, [amount, fromCurrency, toCurrency]);

  const convertCurrency = async () => {
    // try {
    //   // Simulating API call delay
    //   await new Promise(resolve => setTimeout(resolve, 1000));
    //   const response = await fetch(`https://api.exchangerate-api.com/v4/latest/${fromCurrency.value}`);
    //   const data = await response.json();
    //   const rate = data.rates[toCurrency.value];
    //   setResult((amount * rate).toFixed(2));
    // } catch (error) {
    //   console.error('Error converting currency:', error);
    // }

    try {
        const response = await fetch(`http://localhost:8765/conversion/convert/${fromCurrency.value}/${toCurrency.value}/${amount}`);
        const data = await response.json();
        console.log(data);
        
        setResult(data);
      } catch (error) {
        console.error('Error converting currency:', error);
      }
  };

  return (
    <div className="container">
      <div className="converter p-4 shadow-sm rounded">
        <h1 className="text-center mb-4">Currency Converter</h1>
        <div className="mb-3">
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            className="form-control"
            placeholder="Amount"
          />
        </div>
        <div className="dropdowns d-flex justify-content-between mb-3">
          <Select
            value={fromCurrency}
            onChange={setFromCurrency}
            options={currencies}
            className="flex-grow-1 me-2"
          />
          <Select
            value={toCurrency}
            onChange={setToCurrency}
            options={currencies}
            className="flex-grow-1 ms-2"
          />
        </div>
        {result && (
          <div className="result text-center mt-4">
            <h4>{amount} {fromCurrency.label} = {result} {toCurrency.label}</h4>
          </div>
        )}
      </div>
    </div>
  );
};

export default CurrencyConverter;
