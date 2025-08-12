import {useEffect} from "react";
import axios from "axios";
import {useAuthentication} from "@/hooks/useAuthentication.tsx";
import { useRouter } from "@tanstack/react-router";

export const useAxiosAuthInterceptor = () => {
    const router = useRouter();
    const {token, authenticated} = useAuthentication();

    useEffect(() => {

        const requestInterceptorId = axios.interceptors.request.use((config) => {
            if (authenticated) {
                config.headers.Authorization = `Bearer ${token}`;
            }

            return config;
        });

        const responseInterceptorId = axios.interceptors.response.use((response) => {
            console.log(response);
            if (response.status === 401) {
                localStorage.removeItem("authToken");
                router.navigate({to: "/login"});
                router.invalidate();
            }

            return response;
        });

        return () => {
            axios.interceptors.request.eject(requestInterceptorId);
            axios.interceptors.response.eject(responseInterceptorId);
        };
    }, [axios, authenticated, token, router]);
};