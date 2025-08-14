import {Card, Skeleton, Stack} from "@chakra-ui/react";

export const PostSkeleton = () => {

    return (
        <Card.Root maxW="sm" overflow="hidden" width="full">
            <Skeleton width="full" height={200}/>
            <Card.Body gap="2">
                <Card.Title>
                    <Skeleton height={5} width={200}/>
                </Card.Title>
                <Card.Description>
                    <Stack marginTop={4}>
                        <Skeleton height={3} width={150}/>
                        <Skeleton height={3} width={200}/>
                        <Skeleton height={3} width={130}/>
                        <Skeleton height={3} width={250}/>
                    </Stack>
                </Card.Description>
            </Card.Body>
        </Card.Root>
    );
}