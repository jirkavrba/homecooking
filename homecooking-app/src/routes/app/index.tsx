import { createFileRoute, Link } from "@tanstack/react-router";
import { ActionBar, Button, HStack, Portal } from "@chakra-ui/react";
import { PostsFeed } from "@/components/PostsFeed/PostsFeed.tsx";

export const Route = createFileRoute("/app/")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<HStack>
			<PostsFeed />
			<ActionBar.Root open={true}>
				<Portal>
					<ActionBar.Positioner>
						<ActionBar.Content>
							<Button size="xl" asChild>
								<Link to="/app/create">Přidat příspěvek</Link>
							</Button>
						</ActionBar.Content>
					</ActionBar.Positioner>
				</Portal>
			</ActionBar.Root>
		</HStack>
	);
}
