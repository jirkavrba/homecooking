import {Dialog, Portal} from "@chakra-ui/react";
import {createFileRoute, Outlet, redirect} from "@tanstack/react-router";

export const Route = createFileRoute("/login")({
    component: RouteComponent,
    beforeLoad: ({context}) => {
        if (context.token) {
            throw redirect({to: "/app"});
        }
    },
});

function RouteComponent() {
    return (
        <Dialog.Root
            open={true}
            size="xl"
            placement="center"
            motionPreset="slide-in-bottom"
        >
            <Portal>
                <Dialog.Backdrop/>
                <Dialog.Positioner>
                    <Dialog.Content>
                        <Outlet/>
                    </Dialog.Content>
                </Dialog.Positioner>
            </Portal>
        </Dialog.Root>
    );
}
