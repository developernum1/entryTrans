import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import ConfirmDialog from './ConfirmDialog';
import '../styles/CreateTransaction.css';

const CreateTransaction = () => {
    const [formData, setFormData] = useState({
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
    const [isSubmitting, setIsSubmitting] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        // Fetch entry types from the backend
        axios.get(`${process.env.REACT_APP_BGL_BASE_URL}/entry-types`)
            .then(response => setEntryTypes(response.data))
            .catch(error => console.error('Error fetching entry types:', error));
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setShowConfirmDialog(true);
    };

    const handleConfirm = () => {
        setShowConfirmDialog(false);
        setIsSubmitting(true);
        axios.post(process.env.REACT_APP_BGL_BASE_URL, formData)
            .then(() => navigate('/'))
            .catch(error => console.error('Error creating transaction:', error))
            .finally(() => setIsSubmitting(false));
    };

    const handleCancel = () => {
        setShowConfirmDialog(false);
    };

    const handleCancelNavigation = () => {
        navigate('/');
    };

    return (
        <div className="create-transaction-container">
            <h1>Create New Transaction</h1>
            <form onSubmit={handleSubmit} className="create-transaction-form">
                <div className="form-group">
                    <label>TACC ID</label>
                    <input type="number" name="taccId" value={formData.taccId} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Entry ID</label>
                    <input type="number" name="entryId" value={formData.entryId} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Entry Type</label>
                    <select name="type" value={formData.type} onChange={handleChange} required>
                        <option value="" disabled>Select Type</option>
                        {entryTypes.map(type => (
                            <option key={type} value={type}>{type}</option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <input type="number" name="amount" value={formData.amount} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Transaction Date</label>
                    <input type="date" name="transactionDate" value={formData.transactionDate} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Fund ID</label>
                    <input type="text" name="fundId" value={formData.fundId} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Date Created</label>
                    <input type="datetime-local" name="date_created" value={formData.date_created} onChange={handleChange} />
                </div>
                <div className="button-group">
                    <button type="submit" className="create-button" disabled={isSubmitting}>
                        {isSubmitting ? 'Creating...' : 'Create'}
                    </button>
                    <button type="button" className="cancel-button" onClick={handleCancelNavigation}>Cancel</button>
                </div>
            </form>

            {showConfirmDialog && (
                <ConfirmDialog
                    message="Are you sure you want to create this transaction?"
                    onConfirm={handleConfirm}
                    onCancel={handleCancel}
                />
            )}
        </div>
    );
};

export default CreateTransaction;
