import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/Home";
import { ErrorPage } from "./pages/Error";
import { AddCategory } from "./pages/AddCategory";
import { CategoriesManagement } from "./pages/CategoriesManagement";
import { Header } from "./components/Header";
import { EditCategory } from "./pages/EditCategory";
import { ProductsManagement } from "./pages/ProductsManagement";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";
import { AddProduct } from "./pages/AddProduct";
import { EditProduct } from "./pages/EditProduct";
import { ProductList } from "./pages/ProductList";
import { ProductDetail } from "./pages/ProductDetail";

const darkTheme = createTheme({
    palette: {
        mode: "dark",
    },
});

ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
        <ThemeProvider theme={darkTheme}>
            <BrowserRouter>
                <CssBaseline />
                <Header />

                <Routes>
                    <Route
                        path="/"
                        element={<Home />}
                        errorElement={<ErrorPage />}
                    />
                    <Route
                        path="/categories/:categoryId/products"
                        element={<ProductList />}
                    />
                    <Route
                        path="/categories/:categoryId/products/:productId"
                        element={<ProductDetail />}
                    />
                    <Route
                        path="/admin/categories/add"
                        element={<AddCategory />}
                    />
                    <Route
                        path="/admin/categories"
                        element={<CategoriesManagement />}
                    />
                    <Route
                        path="/admin/categories/:categoryId"
                        element={<EditCategory />}
                    />
                    <Route
                        path="/admin/categories/:categoryId/products"
                        element={<ProductsManagement />}
                    />
                    <Route
                        path="/admin/categories/:categoryId/products/add"
                        element={<AddProduct />}
                    />
                    <Route
                        path="/admin/categories/:categoryId/products/:productId"
                        element={<EditProduct />}
                    />
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    </React.StrictMode>
);
