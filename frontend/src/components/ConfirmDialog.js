import React from 'react';
import '../styles/ConfirmDialog.css';

const ConfirmDialog = ({ message, onConfirm, onCancel }) => {
    return (
        <div className="confirm-dialog-overlay">
            <div className="confirm-dialog">
                <p>{message}</p>
                <div className="confirm-dialog-buttons">
                    <button onClick={onConfirm} className="confirm-button">Yes</button>
                    <button onClick={onCancel} className="cancel-button">No</button>
                </div>
            </div>
        </div>
    );
};

export default ConfirmDialog;
