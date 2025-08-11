import {createFileRoute} from "@tanstack/react-router";
import {z} from "zod";
import {useLoginWithMagicLink} from "@/generated/api.ts";
import {useEffect} from "react";
import {Route as NotFoundRoute} from "./login.magic-link.error.tsx";

export const Route = createFileRoute("/login/magic-link/$token")({
    params: {
        parse: (params) => ({
            token: z.string().min(1).parse(params.token),
        }),
    },
    component: RouteComponent,
});

function RouteComponent() {
    const {mutate: login, data: loginResponse, isError, isSuccess} = useLoginWithMagicLink({});

    const {token} = Route.useParams();
    const redirectToErrorPage = NotFoundRoute.useNavigate();

    useEffect(() => {
        login({data: {token}});
    }, [token, login]);

    useEffect(() => {
        if (isError) {
            redirectToErrorPage({
                to: "/login/magic-link/error"
            });
        }
    }, [isError]);

    useEffect(() => {
        if (isSuccess && loginResponse) {
            console.log(loginResponse.data.auth_token)
        }
    }, [isSuccess, loginResponse]);

    return <div>Logging you in with {token}: {isError ? "error" : "nope"}</div>;
}
