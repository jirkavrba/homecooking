import { Outlet, createRootRouteWithContext } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/react-router-devtools";

import Header from "../components/Header";

import TanStackQueryLayout from "../integrations/tanstack-query/layout.tsx";

import type { QueryClient } from "@tanstack/react-query";

interface AppRouterContext {
	queryClient: QueryClient;
}

export const Route = createRootRouteWithContext<AppRouterContext>()({
	component: () => (
		<>
			<Header />
			<Outlet />
			<TanStackRouterDevtools />
			<TanStackQueryLayout />
		</>
	),
});
