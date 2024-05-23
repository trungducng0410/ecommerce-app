import { Navigate } from "react-router-dom";
import { useAuth } from "../../contexts/Auth/useAuth";
import { ReactNode } from "react";

export const ProtectedRoute = ({ children }: { children: ReactNode }) => {
    // Getting the value from our cool custom hook
    const { token } = useAuth();

    return token ? children : <Navigate to="/signin" />;
};
