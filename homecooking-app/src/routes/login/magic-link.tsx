import { createFileRoute, Outlet } from "@tanstack/react-router";

export const Route = createFileRoute("/login/magic-link")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<div className="w-96 p-10 border border-neutral-500">
			MAGIC_LINK
			<Outlet />
		</div>
	);
}
