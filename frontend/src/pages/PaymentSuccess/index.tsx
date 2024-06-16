import { Alert, Container } from "@mui/material";
import { useAuth } from "../../contexts/Auth/useAuth";

export const PaymentSuccess = () => {
    const { sessionId } = useAuth();

    console.log(sessionId);

    return (
        <Container maxWidth="xl" sx={{ marginTop: 5 }}>
            <Alert severity="success">Payment successful</Alert>
        </Container>
    );
};
