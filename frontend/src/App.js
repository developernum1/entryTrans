import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import TransactionList from './components/TransactionList';
import CreateTransaction from './components/CreateTransaction';
import UpdateTransaction from './components/UpdateTransaction';
import DeleteTransaction from './components/DeleteTransaction';
import TransactionDetails from './components/TransactionDetails';
import './styles/index.css'; 

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<TransactionList />} />
                <Route path="/create" element={<CreateTransaction />} />
                <Route path="/update/:id" element={<UpdateTransaction />} />
                <Route path="/delete/:id" element={<DeleteTransaction />} />
                <Route path="/details/:id" element={<TransactionDetails />} />
            </Routes>
        </Router>
    );
};

export default App;
