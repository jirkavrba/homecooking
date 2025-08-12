import type {FC, ReactNode} from "react";
import {useAxiosAuthInterceptor} from "@/hooks/useAxiosAuthInterceptor.tsx";

export type AuthenticationProviderProps = {
    children?: ReactNode;
};

export const AuthenticationProvider: FC<AuthenticationProviderProps> = ({children}) => {
    useAxiosAuthInterceptor();

    return (
        <>
            {children}
        </>
    )
};
