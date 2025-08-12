import {Dialog, HStack, Spinner, Text} from "@chakra-ui/react";
import {createFileRoute, useRouter} from "@tanstack/react-router";
import {z} from "zod";
import {useLoginWithMagicLink} from "@/generated/api.ts";
import {useEffect} from "react";
import {Route as NotFoundRoute} from "./error.tsx";
import {Route as AppRoute} from "@/routes/app/index.tsx";

export const Route = createFileRoute("/login/magic-link/$token")({
    params: {
        parse: (params) => ({
            token: z.string().min(1).parse(params.token),
        }),
    },
    component: RouteComponent,
});

function RouteComponent() {
    const {mutate: login} = useLoginWithMagicLink({});

    const router = useRouter();
    const {token} = Route.useParams();

    const redirectToAppPage = AppRoute.useNavigate();
    const redirectToErrorPage = NotFoundRoute.useNavigate();

    useEffect(() => {
    	login(
    		{ data: { token } },
    		{
    			onError: () =>
    				void redirectToErrorPage({ to: "/login/magic-link/error" }),
    			onSuccess: ({ data }) => {
    				localStorage.setItem("authToken", data.auth_token);
    				redirectToAppPage({ to: "/app" });
    				router.invalidate();
    			},
    		},
    	);
    }, [token, login]);

    return (
        <>
            <Dialog.Header>
                <Dialog.Title>Přihlášení přes odkaz</Dialog.Title>
            </Dialog.Header>
            <Dialog.Body>
                <HStack colorPalette="cyan">
                    <Spinner color="colorPalette.600"/>
                    <Text color="colorPalette.600">Probíhá přihlašování...</Text>
                </HStack>
            </Dialog.Body>
        </>
    );
}
