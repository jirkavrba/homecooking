import {useRouteContext} from "@tanstack/react-router";
import {useGetUserInfo} from "@/generated/api.ts";
import {Avatar, HStack, Skeleton, Spinner, Text} from "@chakra-ui/react";

export const UserInfo = () => {
    const {authenticated} = useRouteContext({from: "/app"});
    const {data: user, isLoading} = useGetUserInfo({query: {enabled: authenticated}});

    if (!authenticated) {
        return;
    }

    if (isLoading || !user) {
        return (
            <HStack gap={4}>
                <Avatar.Root>
                    <Spinner/>
                </Avatar.Root>
                <Skeleton height="5" width="40"/>
            </HStack>
        );
    }

    return (
        <HStack gap={4}>
            <Avatar.Root>
                <Avatar.Fallback name={user.data.username}/>
                <Avatar.Image src={user.data.avatar_url}/>
            </Avatar.Root>
            <Text fontWeight="medium">@{user.data.username}</Text>
        </HStack>
    );
};