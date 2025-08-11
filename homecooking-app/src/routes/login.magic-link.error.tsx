import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/login/magic-link/error")({
	component: RouteComponent,
});

function RouteComponent() {
	return <div>Tenhle odkaz nefunguje. Pravděpodobně vypršela jeho platnost.</div>;
}
