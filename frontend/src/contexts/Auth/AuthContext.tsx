import { ReactNode, createContext, useState } from "react";

interface IAuthContext {
    token: string | null;
    login: (token: string) => void;
    logout: () => void;
    sessionId: string | null;
    setSession: (sessionId: string) => void;
}

const AuthContext = createContext<IAuthContext | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [sessionId, setSessionId] = useState(
        localStorage.getItem("sessionId")
    );

    const login = (token: string) => {
        localStorage.setItem("token", token);
        setToken(token);
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
    };

    const setSession = (sessionId: string) => {
        localStorage.setItem("sessionId", sessionId);
        setSessionId(sessionId);
    };

    return (
        <AuthContext.Provider
            value={{
                token,
                login,
                logout,
                sessionId,
                setSession,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContext;
