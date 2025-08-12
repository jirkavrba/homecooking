import { createFileRoute, Outlet, redirect } from "@tanstack/react-router";

export const Route = createFileRoute("/login")({
	component: RouteComponent,
	beforeLoad: ({ context }) => {
		if (context.authToken) {
			throw redirect({ to: "/app" });
		}
	},
});

function RouteComponent() {
	return (
		<div>
			LOGIN
			<Outlet />
		</div>
	);
}
