import { PostCreationForm } from "@/components/PostCreationForm.tsx";
import { createFileRoute } from "@tanstack/react-router";
import { Stack, Text, VStack } from "@chakra-ui/react";

export const Route = createFileRoute("/app/create")({
	component: RouteComponent,
});

function RouteComponent() {
	return (
		<Stack gap={4}>
			<Text textStyle="lg" fontWeight="black">
				Přidat nový příspěvek
			</Text>
			<PostCreationForm />
		</Stack>
	);
}
