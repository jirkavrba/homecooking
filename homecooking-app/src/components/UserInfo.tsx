import {useRouteContext} from "@tanstack/react-router";
import {useGetUserInfo} from "@/generated/api.ts";

export const UserInfo = () => {
    const {authenticated} = useRouteContext({from: "/app"});
    const {data: user, isLoading} = useGetUserInfo({query: {enabled: authenticated}});

    if (!authenticated) {
        return;
    }

    if (isLoading || !user) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <img src={user.data.avatar_url} alt={user.data.username}/>
            <div>{user.data.username}</div>
        </div>
    )
};