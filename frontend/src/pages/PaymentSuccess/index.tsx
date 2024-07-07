import { Alert, Container } from "@mui/material";
import { useAuth } from "../../contexts/Auth/useAuth";
import { useEffect } from "react";
import axios from "axios";
import { API_ENDPOINT } from "../../const";
import { useNavigate } from "react-router-dom";

export const PaymentSuccess = () => {
    const { sessionId, token, clearSession } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        const saveOrder = async () => {
            try {
                await axios.post(
                    `${API_ENDPOINT}/orders/add`,
                    {
                        sessionId,
                    },
                    {
                        headers: {
                            Authorization: token,
                        },
                    }
                );

                clearSession();
                navigate("/orders");
            } catch (err) {
                navigate("/payment/failed");
            }
        };

        saveOrder();
    }, []);

    return (
        <Container maxWidth="xl" sx={{ marginTop: 5 }}>
            <Alert severity="success">Payment successful</Alert>
        </Container>
    );
};
