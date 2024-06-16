import {
    Card,
    CardContent,
    Typography,
    Grid,
    CardActions,
    Button,
} from "@mui/material";

interface OrderSummaryProps {
    total: number;
    itemsCount: number;
    confirmOrder: () => void;
}

export const OrderSummary = ({
    total,
    itemsCount,
    confirmOrder,
}: OrderSummaryProps) => {
    return (
        <Card style={{ marginTop: 10 }} elevation={15}>
            <CardContent>
                <Typography variant="body1" component="h1">
                    Order Summary
                </Typography>
                <Typography variant="subtitle2">
                    <hr />
                </Typography>
                <Grid container>
                    <Grid item xs={11} sm={11} md={11} lg={11}>
                        <Typography variant="body1" component="div">
                            Total
                        </Typography>
                    </Grid>
                    <Grid item xs={1} sm={1} md={1} lg={1}>
                        <Typography variant="h6" component="div">
                            ${total}
                        </Typography>
                    </Grid>
                </Grid>
            </CardContent>

            <CardActions>
                <Button onClick={confirmOrder} size="large">
                    Confirm Order ({itemsCount})
                </Button>
            </CardActions>
        </Card>
    );
};
