import React from 'react';
import '../styles/SuccessDialog.css';

const SuccessDialog = ({ message, onClose }) => {
    return (
        <div className="success-dialog-overlay">
            <div className="success-dialog">
                <p>{message}</p>
                <button onClick={onClose} className="close-button">OK</button>
            </div>
        </div>
    );
};

export default SuccessDialog;
