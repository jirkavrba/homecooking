import { createFileRoute, Link } from "@tanstack/react-router";
import { Button, Dialog } from "@chakra-ui/react";

export const Route = createFileRoute("/login/magic-link/error")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<>
			<Dialog.Header>
				<Dialog.Title>Tenhle odkaz nefunguje</Dialog.Title>
			</Dialog.Header>
			<Dialog.Body>
				Pravděpodobně vypršela jeho platnost. Ale to nevadí, můžeš si
				vygenerovat nový.
			</Dialog.Body>
			<Dialog.Footer>
				<Link to="/login">
					<Button>Zpět na přihlášení</Button>
				</Link>
			</Dialog.Footer>
		</>
	);
}
