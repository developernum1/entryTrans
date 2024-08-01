import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import ConfirmDialog from './ConfirmDialog';
import SuccessDialog from './SuccessDialog';
import '../styles/UpdateTransaction.css';

const UpdateTransaction = () => {
    const { id } = useParams();
    const [transaction, setTransaction] = useState({
        taccId: '',
        entryId: '',
        type: '',
        amount: '',
        transactionDate: '',
        fundId: '',
        date_created: '',
    });
    const [entryTypes, setEntryTypes] = useState([]);
    const [showConfirmDialog, setShowConfirmDialog] = useState(false);
    const [showSuccessDialog, setShowSuccessDialog] = useState(false);
    const [isUpdating, setIsUpdating] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}/${id}`)
            .then(response => setTransaction(response.data))
            .catch(error => console.error('Error fetching transaction:', error));
    }, [id]);

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}/entry-types`)
            .then(response => setEntryTypes(response.data))
            .catch(error => console.error('Error fetching entry types:', error));
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTransaction({ ...transaction, [name]: value });
    };

    const handleUpdate = () => {
        setShowConfirmDialog(true);
    };

    const handleConfirm = () => {
        setShowConfirmDialog(false);
        setIsUpdating(true);
        axios.put(`${process.env.REACT_APP_BGL_BASE_URL}/${id}`, transaction)
            .then(() => setShowSuccessDialog(true))
            .catch(error => console.error('Error updating transaction:', error))
            .finally(() => setIsUpdating(false));
    };

    const handleCancel = () => {
        setShowConfirmDialog(false);
    };

    const handleSuccessClose = () => {
        setShowSuccessDialog(false);
    };

    const handleReturn = () => {
        navigate('/');
    };

    return (
        <div className="update-transaction-container">
            <h1>Update Transaction</h1>
            <div className="update-transaction-form">
                <div className="form-group">
                    <label>ID</label>
                    <span className="form-value">{transaction.id}</span>
                </div>
                <div className="form-group">
                    <label>TACC ID</label>
                    <input type="text" name="taccId" value={transaction.taccId} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Entry ID</label>
                    <input type="number" name="entryId" value={transaction.entryId} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Entry Type</label>
                    <select name="type" value={transaction.type} onChange={handleChange} required>
                        <option value="" disabled>Select Type</option>
                        {entryTypes.map((type) => (
                            <option key={type} value={type}>{type}</option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <input type="number" name="amount" value={transaction.amount} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Transaction Date</label>
                    <input type="date" name="transactionDate" value={transaction.transactionDate} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Fund ID</label>
                    <input type="text" name="fundId" value={transaction.fundId} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Date Created</label>
                    <input type="datetime-local" name="date_created" value={transaction.date_created} onChange={handleChange} />
                </div>
                <div className="button-group">
                    <button onClick={handleUpdate} className="update-button" disabled={isUpdating}>Update</button>
                    <button onClick={handleReturn} className="return-button">Return</button>
                </div>
            </div>

            {showConfirmDialog && (
                <ConfirmDialog
                    message="Are you sure you want to update this transaction?"
                    onConfirm={handleConfirm}
                    onCancel={handleCancel}
                />
            )}

            {showSuccessDialog && (
                <SuccessDialog
                    message="Transaction updated successfully!"
                    onClose={handleSuccessClose}
                />
            )}
        </div>
    );
};

export default UpdateTransaction;
