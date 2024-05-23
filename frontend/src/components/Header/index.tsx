import { AppBar, Box, Toolbar, Typography, Button } from "@mui/material";
import CatchingPokemonIcon from "@mui/icons-material/CatchingPokemon";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../contexts/Auth/useAuth";

const navItems = [{ label: "Pokédex", to: "/admin/categories" }];

export const Header = () => {
    const navigate = useNavigate();
    const { token, logout } = useAuth();

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar component="nav" position="static">
                <Toolbar>
                    <CatchingPokemonIcon sx={{ mr: 1 }} />
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            fontFamily: "monospace",
                            fontWeight: 700,
                            letterSpacing: ".3rem",
                            color: "inherit",
                            textDecoration: "none",
                            flexGrow: 1,
                        }}
                    >
                        POKÉMARKET
                    </Typography>

                    <Box sx={{ display: { xs: "none", sm: "block" } }}>
                        {navItems.map((item) => (
                            <Button
                                key={item.label}
                                color="inherit"
                                sx={{ mr: 2 }}
                                onClick={() => {
                                    navigate(item.to);
                                }}
                            >
                                {item.label}
                            </Button>
                        ))}
                    </Box>
                    {!token ? (
                        <Button
                            onClick={() => {
                                navigate("/signin");
                            }}
                            color="inherit"
                        >
                            Login
                        </Button>
                    ) : (
                        <Button onClick={logout} color="inherit">
                            Logout
                        </Button>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
};
