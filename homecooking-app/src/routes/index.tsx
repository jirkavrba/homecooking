import {createFileRoute, redirect} from "@tanstack/react-router";
import {useAxiosAuthInterceptor} from "@/hooks/useAxiosAuthInterceptor.ts";

export const Route = createFileRoute("/")({
    component: App,
    beforeLoad: ({context}) => {
        const destination = context.authenticated ? "/app" : "/login";
        throw redirect({to: destination});
    },
});

function App() {
    useAxiosAuthInterceptor();

    return <div className="text-center">...</div>;
}
