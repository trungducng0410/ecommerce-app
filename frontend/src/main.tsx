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
import { SignUp } from "./pages/SignUp";
import { SignIn } from "./pages/SignIn";
import { AuthProvider } from "./contexts/Auth/AuthContext";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { WishList } from "./pages/WishList";
import { Cart } from "./pages/Cart";

const darkTheme = createTheme({
    palette: {
        mode: "dark",
    },
});

ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
        <ThemeProvider theme={darkTheme}>
            <AuthProvider>
                <BrowserRouter>
                    <CssBaseline />
                    <Header />

                    <Routes>
                        <Route
                            path="/"
                            element={<Home />}
                            errorElement={<ErrorPage />}
                        />
                        <Route path="/signup" element={<SignUp />} />
                        <Route path="/signin" element={<SignIn />} />
                        <Route
                            path="/categories/:categoryId/products"
                            element={<ProductList />}
                        />
                        <Route
                            path="/categories/:categoryId/products/:productId"
                            element={<ProductDetail />}
                        />
                        <Route
                            path="/wishlist"
                            element={
                                <ProtectedRoute>
                                    <WishList />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/cart"
                            element={
                                <ProtectedRoute>
                                    <Cart />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories/add"
                            element={
                                <ProtectedRoute>
                                    <AddCategory />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories"
                            element={
                                <ProtectedRoute>
                                    <CategoriesManagement />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories/:categoryId"
                            element={
                                <ProtectedRoute>
                                    <EditCategory />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories/:categoryId/products"
                            element={
                                <ProtectedRoute>
                                    <ProductsManagement />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories/:categoryId/products/add"
                            element={
                                <ProtectedRoute>
                                    <AddProduct />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path="/admin/categories/:categoryId/products/:productId"
                            element={
                                <ProtectedRoute>
                                    <EditProduct />
                                </ProtectedRoute>
                            }
                        />
                    </Routes>
                </BrowserRouter>
            </AuthProvider>
        </ThemeProvider>
    </React.StrictMode>
);
