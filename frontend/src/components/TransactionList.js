import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../styles/TransactionList.css';

const TransactionList = () => {
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}`)
            .then(response => setTransactions(response.data))
            .catch(error => console.error('Error fetching transactions:', error));
    }, []);

    return (
        <div className="transaction-list-container">
            <h1 className="header">Transaction List</h1>
            <Link to="/create" className="create-button">Create New Transaction</Link>
            <table className="transaction-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>TACC ID</th>
                        <th>Entry ID</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Transaction Date</th>
                        <th>Fund ID</th>
                        <th>Date Created</th>
                        <th>Last Updated</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {transactions.map(transaction => (
                        <tr key={transaction.id}>
                            <td>{transaction.id}</td>
                            <td>{transaction.taccId}</td>
                            <td>{transaction.entryId}</td>
                            <td>{transaction.type}</td>
                            <td>{transaction.amount}</td>
                            <td>{transaction.transactionDate}</td>
                            <td>{transaction.fundId}</td>
                            <td>{transaction.dateCreated}</td>
                            <td>{transaction.lastUpdated}</td>
                            <td>
                                <Link to={`/details/${transaction.id}`} className="action-button">Details</Link>
                                <Link to={`/update/${transaction.id}`} className="action-button">Update</Link>
                                <Link to={`/delete/${transaction.id}`} className="action-button">Delete</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default TransactionList;