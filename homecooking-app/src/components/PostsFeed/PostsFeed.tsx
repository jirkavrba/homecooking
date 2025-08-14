import { useFeedInfiniteQuery } from "@/hooks/useFeedInfiniteQuery.ts";
import { Button, Stack } from "@chakra-ui/react";
import { PostSkeleton } from "@/components/PostsFeed/PostSkeleton.tsx";
import { Post } from "@/components/PostsFeed/Post.tsx";

export const PostsFeed = () => {
  const { data: feed, isFetching, fetchNextPage } = useFeedInfiniteQuery();
  const posts = feed?.pages?.flatMap((page) => page.data.posts) ?? [];

  return (
    <Stack gap={8} width="full">
      {posts.map((item) => (
        <Post
          key={item.post.share_token}
          post={item.post}
          author={item.author}
        />
      ))}

      {isFetching && (
        <>
          <PostSkeleton />
          <PostSkeleton />
        </>
      )}

      <Stack marginBottom={40}>
        <Button onClick={() => fetchNextPage()}>Načíst další příspěvky</Button>
      </Stack>
    </Stack>
  );
};
