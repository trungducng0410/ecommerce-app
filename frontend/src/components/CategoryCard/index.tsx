import {
    Card,
    CardMedia,
    CardContent,
    Typography,
    Button,
    CardActions,
} from "@mui/material";

export const CategoryCard = ({
    name,
    description,
    imageUrl,
    onViewCards,
    onEdit,
    showEditButton = false,
}: {
    name: string;
    description: string;
    imageUrl: string;
    onViewCards: () => void;
    onEdit?: () => void;
    showEditButton?: boolean;
}) => {
    return (
        <Card sx={{ width: 345 }}>
            <CardMedia sx={{ height: 140 }} image={imageUrl} />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {name}
                </Typography>
                <Typography noWrap variant="body2" color="text.secondary">
                    {description}
                </Typography>
            </CardContent>
            <CardActions>
                <Button variant="contained" size="small" onClick={onViewCards}>
                    View Cards
                </Button>
                {showEditButton ? (
                    <Button size="small" onClick={onEdit}>
                        Edit
                    </Button>
                ) : null}
            </CardActions>
        </Card>
    );
};
