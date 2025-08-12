import {createFileRoute, Outlet, redirect} from "@tanstack/react-router";
import {UserInfo} from "@/components/UserInfo.tsx";

export const Route = createFileRoute("/app")({
    component: RouteComponent,
    beforeLoad: ({context}) => {
        if (!context.authenticated) {
            throw redirect({to: "/login"});
        }
    },
});

function RouteComponent() {
    return (
        <div>
            <UserInfo/>
            <Outlet/>
        </div>
    );
}
