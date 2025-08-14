import type { MealPostDto, PostAuthorDto } from "@/generated/api.ts";
import {
  Accordion,
  Card,
  Image,
  RatingGroup,
  Text,
  HStack,
  Timeline,
  Tag,
  Avatar,
} from "@chakra-ui/react";
import type { FC } from "react";
import {
  LuChefHat,
  LuFlame,
  LuHam,
  LuHandCoins,
  LuTimer,
} from "react-icons/lu";
import { FormattedDate } from "./FormattedDate";

export type PostProps = {
  post: MealPostDto;
  author: PostAuthorDto;
};

export const Post: FC<PostProps> = ({ post, author }) => {
  return (
    <Card.Root maxW="sm" overflow="hidden">
      <Image src={post.image_url} alt={post.title} />
      <Card.Body gap="2">
        <Card.Title>{post.title}</Card.Title>
        {post.rating && (
          <RatingGroup.Root count={5} size="xs" value={post.rating} readOnly>
            <RatingGroup.Control />
          </RatingGroup.Root>
        )}
        {post.description && (
          <Card.Description>{post.description}</Card.Description>
        )}
        <HStack>
          {post.price_czk_per_portion && (
            <Tag.Root size="lg">
              <Tag.StartElement>
                <LuHandCoins />
              </Tag.StartElement>
              {post.price_czk_per_portion} Kč
            </Tag.Root>
          )}
          {post.kcal_per_portion && (
            <Tag.Root size="lg">
              <Tag.StartElement>
                <LuFlame />
              </Tag.StartElement>
              {post.kcal_per_portion} kcal
            </Tag.Root>
          )}
          {post.preparation_time_minutes && (
            <Tag.Root size="lg">
              <Tag.StartElement>
                <LuTimer />
              </Tag.StartElement>
              {post.preparation_time_minutes} min
            </Tag.Root>
          )}
        </HStack>
        <Accordion.Root collapsible defaultValue={["info"]}>
          {post.ingredients_list && (
            <Accordion.Item value="ingredients">
              <Accordion.ItemTrigger>
                <HStack flex="1" gap={4}>
                  <LuHam fontSize="sm" color="gray" />
                  Ingredience
                </HStack>
                <Accordion.ItemIndicator />
              </Accordion.ItemTrigger>
              <Accordion.ItemContent>
                <Accordion.ItemBody>
                  <HStack wrap="wrap">
                    {post.ingredients_list.split("\n").map((it) => (
                      <Tag.Root size="lg">
                        <Tag.Label>{it}</Tag.Label>
                      </Tag.Root>
                    ))}
                  </HStack>
                </Accordion.ItemBody>
              </Accordion.ItemContent>
            </Accordion.Item>
          )}
          {post.recipe && (
            <Accordion.Item value="recipe">
              <Accordion.ItemTrigger>
                <HStack flex="1" gap={4}>
                  <LuChefHat fontSize="sm" color="gray" />
                  Recept
                </HStack>
                <Accordion.ItemIndicator />
              </Accordion.ItemTrigger>
              <Accordion.ItemContent>
                <Accordion.ItemBody>
                  <Timeline.Root variant="subtle">
                    {post.recipe.split("\n").map((step, index) => (
                      <Timeline.Item>
                        <Timeline.Connector>
                          <Timeline.Separator />
                          <Timeline.Indicator>{index + 1}</Timeline.Indicator>
                        </Timeline.Connector>
                        <Timeline.Content>
                          <Timeline.Title>{step}</Timeline.Title>
                        </Timeline.Content>
                      </Timeline.Item>
                    ))}
                  </Timeline.Root>
                </Accordion.ItemBody>
              </Accordion.ItemContent>
            </Accordion.Item>
          )}
        </Accordion.Root>
      </Card.Body>
      <Card.Footer gap="2" justifyContent="space-between">
        <HStack>
          <Avatar.Root size="2xs">
            <Avatar.Image src={author.avatar_url} alt={author.username} />
          </Avatar.Root>
          <Text>{author.username}</Text>
        </HStack>
        <Text fontSize="xs" color="gray">
          <FormattedDate date={new Date(post.posted_at)} />
        </Text>
      </Card.Footer>
    </Card.Root>
  );
};
