import { useRouterState } from "@tanstack/react-router";

export const useAuthentication = () => {
	const routerState = useRouterState();
	const context = routerState.matches?.find(
		(route) => route.id === "__root__",
	)?.context;

	if (!context) {
		throw new Error("The root route context was not found.");
	}

	return {
		token: context.token,
		authenticated: context.authenticated,
	};
};
