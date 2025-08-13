import { useInfiniteQuery } from "@tanstack/react-query";
import { feed } from "@/generated/api.ts";

export const useFeedInfiniteQuery = () => {
  return useInfiniteQuery({
    queryKey: ["feed"],
    queryFn: async ({ pageParam }) => {
      return await feed({ page: pageParam });
    },
    initialPageParam: 0,
    getNextPageParam: (_previousPage, _allPages, lastPageParam) =>
      lastPageParam + 1,
    getPreviousPageParam: (_firstPage, _allPages, lastPageParam) =>
      lastPageParam - 1,
  });
};
