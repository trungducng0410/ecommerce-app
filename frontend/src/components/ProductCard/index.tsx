import {
    Card,
    CardMedia,
    CardContent,
    Typography,
    CardActions,
    Button,
} from "@mui/material";

interface ProductCardProps {
    imageUrl: string;
    name: string;
    price: number;
    description: string;
    onDetail?: () => void;
    showDetail?: boolean;
    onEdit?: () => void;
    showEditButton?: boolean;
}

export const ProductCard = ({
    imageUrl,
    name,
    price,
    description,
    onDetail,
    showDetail,
    onEdit,
    showEditButton,
}: ProductCardProps) => {
    return (
        <Card sx={{ width: 345 }}>
            <CardMedia sx={{ height: 250 }} image={imageUrl} />
            <CardContent>
                <Typography gutterBottom variant="h6" component="div">
                    {name}
                </Typography>
                <Typography variant="body1" color="text.secondary">
                    {price}$
                </Typography>
                <Typography
                    noWrap
                    variant="body2"
                    color="text.secondary"
                    sx={{ fontStyle: "italic" }}
                >
                    {description}
                </Typography>
            </CardContent>
            <CardActions>
                {showDetail ? (
                    <Button onClick={onDetail}>Learn more</Button>
                ) : null}
                {showEditButton ? (
                    <Button onClick={onEdit} size="small">
                        Edit
                    </Button>
                ) : null}
            </CardActions>
        </Card>
    );
};
