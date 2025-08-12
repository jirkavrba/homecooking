import { Outlet, createRootRouteWithContext } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/react-router-devtools";

import Header from "../components/Header";

import TanStackQueryLayout from "../integrations/tanstack-query/layout.tsx";

import type { QueryClient } from "@tanstack/react-query";
import {AuthenticationProvider} from "@/context/AuthenticationProvider.tsx";

interface AppRouterContext {
	queryClient: QueryClient;
	token: string | null;
	authenticated: boolean;
}

export const Route = createRootRouteWithContext<AppRouterContext>()({
	component: () => (
		<AuthenticationProvider>
			<Header />
			<Outlet />
			{/*<TanStackRouterDevtools />*/}
			{/*<TanStackQueryLayout />*/}
		</AuthenticationProvider>
	),
});
