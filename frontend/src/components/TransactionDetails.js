import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/TransactionDetails.css'; 

const TransactionDetails = () => {
    const { id } = useParams();
    const [transaction, setTransaction] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}/${id}`)
            .then(response => setTransaction(response.data))
            .catch(error => console.error('Error fetching transaction details:', error));
    }, [id]);

    const handleReturn = () => {
        navigate('/');
    };

    if (!transaction) return <div>Loading...</div>;

    return (
        <div className="transaction-details-container">
            <h1>Transaction Details</h1>
            <table className="transaction-details-table">
                <tbody>
                    <tr>
                        <th>ID</th>
                        <td>{transaction.id}</td>
                    </tr>
                    <tr>
                        <th>TACC ID</th>
                        <td>{transaction.taccId}</td>
                    </tr>
                    <tr>
                        <th>Entry ID</th>
                        <td>{transaction.entryId}</td>
                    </tr>
                    <tr>
                        <th>Type</th>
                        <td>{transaction.type}</td>
                    </tr>
                    <tr>
                        <th>Amount</th>
                        <td>{transaction.amount}</td>
                    </tr>
                    <tr>
                        <th>Transaction Date</th>
                        <td>{transaction.transactionDate}</td>
                    </tr>
                    <tr>
                        <th>Fund ID</th>
                        <td>{transaction.fundId}</td>
                    </tr>
                    <tr>
                        <th>Date Created</th>
                        <td>{transaction.dateCreated}</td>
                    </tr>
                    <tr>
                        <th>Last Updated</th>
                        <td>{transaction.lastUpdated}</td>
                    </tr>
                </tbody>
            </table>
            <button onClick={handleReturn}>Return</button>
        </div>
    );
};

export default TransactionDetails;
