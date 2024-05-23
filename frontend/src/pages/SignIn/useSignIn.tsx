import axios from "axios";
import { useAuth } from "../../contexts/Auth/useAuth";
import { API_ENDPOINT } from "../../const";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export const useSignIn = () => {
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const input = {
            email: data.get("email"),
            password: data.get("password"),
        };

        try {
            const response = await axios.post(`${API_ENDPOINT}/signin`, input);
            login(response.data.message);
            Swal.fire({
                icon: "success",
                title: "Success",
                text: "User signin successful",
            });

            navigate("/");
        } catch (error) {
            console.error(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "An error occurred. Please try again",
            });
        }
    };

    return { handleSubmit };
};
