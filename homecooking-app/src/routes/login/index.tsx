import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/login/")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<div>
			Pro přihlášení použij tlačítko v <strong>#login</strong> kanálu na
			Homecooking Discord serveru. Ještě tam nejsi? Nevadí,{" "}
			<a
				href="https://discord.gg/P7g5jCsyTu"
				target="_blank"
				className="text-indigo-700 underline"
			>
				tady je pozvánka
			</a>
			.
		</div>
	);
}
