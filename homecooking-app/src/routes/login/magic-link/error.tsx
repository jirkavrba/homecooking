import { createFileRoute, Link } from "@tanstack/react-router";

export const Route = createFileRoute("/login/magic-link/error")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<div className="flex flex-col items-center">
			<h1>Tenhle odkaz nefunguje.</h1>
			<p>Pravděpodobně vypršela jeho platnost.</p>

			<Link to={"/login"}>
				<button className="bg-black text-white p-4 cursor-pointer">
					Zpět na přihlášení
				</button>
			</Link>
		</div>
	);
}
