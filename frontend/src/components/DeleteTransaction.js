import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import ConfirmDialog from './ConfirmDialog';
import '../styles/DeleteTransaction.css';

const DeleteTransaction = () => {
    const { id } = useParams();
    const [transaction, setTransaction] = useState(null);
    const [showDialog, setShowDialog] = useState(false);
    const [deleting, setDeleting] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}/${id}`)
            .then(response => setTransaction(response.data))
            .catch(error => console.error('Error fetching transaction:', error));
    }, [id]);

    const handleDelete = () => {
        setShowDialog(true);
    };

    const confirmDelete = () => {
        setDeleting(true);
        axios.delete(`${process.env.REACT_APP_BGL_BASE_URL}/${id}`)
            .then(() => {
                setDeleting(false);
                navigate('/');
            })
            .catch(error => {
                console.error('Error deleting transaction:', error);
                setDeleting(false);
            });
        setShowDialog(false);
    };

    const cancelDelete = () => {
        setShowDialog(false);
    };

    const handleCancel = () => {
        navigate('/');
    };

    if (!transaction) return <div>Loading...</div>;

    return (
        <div className="delete-transaction-container">
            <h1>Delete Transaction</h1>
            <div className="delete-transaction-details">
                <div className="form-group">
                    <label>ID</label>
                    <span className="form-value">{transaction.id}</span>
                </div>
                <div className="form-group">
                    <label>TACC ID</label>
                    <span className="form-value">{transaction.taccId}</span>
                </div>
                <div className="form-group">
                    <label>Entry ID</label>
                    <span className="form-value">{transaction.entryId}</span>
                </div>
                <div className="form-group">
                    <label>Type</label>
                    <span className="form-value">{transaction.type}</span>
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <span className="form-value">{transaction.amount}</span>
                </div>
                <div className="form-group">
                    <label>Transaction Date</label>
                    <span className="form-value">{transaction.transactionDate}</span>
                </div>
                <div className="form-group">
                    <label>Fund ID</label>
                    <span className="form-value">{transaction.fundId}</span>
                </div>
                <div className="form-group">
                    <label>Date Created</label>
                    <span className="form-value">{transaction.dateCreated}</span>
                </div>
                <div className="form-group">
                    <label>Last Updated</label>
                    <span className="form-value">{transaction.lastUpdated}</span>
                </div>

                <div className="button-group">
                    <button onClick={handleDelete} className="delete-button" disabled={deleting}>Delete</button>
                    <button onClick={handleCancel} className="cancel-button">Cancel</button>
                </div>
            </div>

            {showDialog && (
                <ConfirmDialog
                    message="Are you sure you want to delete this transaction?"
                    onConfirm={confirmDelete}
                    onCancel={cancelDelete}
                />
            )}
        </div>
    );
};

export default DeleteTransaction;
