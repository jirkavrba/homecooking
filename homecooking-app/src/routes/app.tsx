import { createFileRoute, Outlet, redirect } from "@tanstack/react-router";
import { UserInfo } from "@/components/UserInfo.tsx";
import { VStack } from "@chakra-ui/react";

export const Route = createFileRoute("/app")({
	component: RouteComponent,
	beforeLoad: ({ context }) => {
		if (!context.authenticated) {
			throw redirect({ to: "/login" });
		}
	},
});

function RouteComponent() {
	return (
		<VStack gap={8} paddingX={10} align="stretch">
			<UserInfo />
			<Outlet />
		</VStack>
	);
}
