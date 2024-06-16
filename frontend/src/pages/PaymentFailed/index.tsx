import { Alert, Container } from "@mui/material";

export const PaymentFailed = () => {
    return (
        <Container maxWidth="xl" sx={{ marginTop: 5 }}>
            <Alert severity="error">Payment failed</Alert>
        </Container>
    );
};
