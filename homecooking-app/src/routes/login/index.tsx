import {Button, Dialog, Link} from "@chakra-ui/react";
import {createFileRoute} from "@tanstack/react-router";

export const Route = createFileRoute("/login/")({
    component: RouteComponent,
});

function RouteComponent() {
    return (
        <>
            <Dialog.Header>
                <Dialog.Title>Přihlášení</Dialog.Title>
            </Dialog.Header>
            <Dialog.Body>
                Pro přihlášení použij tlačítko v <strong>#login</strong> kanálu na
                Homecooking Discord serveru.
            </Dialog.Body>
            <Dialog.Footer>
                <Link href="https://discord.gg/P7g5jCsyTu">
                    <Button>Otevřít Discord</Button>
                </Link>
            </Dialog.Footer>
        </>
    );
}
