import { Link } from "@tanstack/react-router";
import { Stack, Text } from "@chakra-ui/react";

export default function Header() {
	return (
		<Stack paddingX={10} paddingY={4}>
			<Link to="/">
				<Text textStyle="lg" fontWeight="black">
					Homecooking
				</Text>
			</Link>
		</Stack>
	);
}
