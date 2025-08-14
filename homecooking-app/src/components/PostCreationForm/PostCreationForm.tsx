import { FormProvider, useForm } from "react-hook-form";
import {
  Button,
  Field,
  Input,
  InputGroup,
  Textarea,
  VStack,
} from "@chakra-ui/react";
import { PostImageInput } from "@/components/PostCreationForm/PostImageInput.tsx";
import { zodResolver } from "@hookform/resolvers/zod";
import z from "zod";
import { IngredientsListInput } from "./IngredientsListInput.tsx";
import { RecipeInput } from "./RecipeInput.tsx";
import { RatingInput } from "./RatingInput.tsx";
import { useCreatePost, type CreatePostRequest } from "@/generated/api.ts";
import { Route as FeedRoute } from "@/routes/app";
import { Link } from "@tanstack/react-router";

export type PostFormData = {
  title: string;
  imageUrl: string;
  description?: string;
  ingredientsList?: string;
  recipe?: string;
  rating?: number;
  priceCzkPerPortion?: number;
  kcalPerPortion?: number;
  preparationTimeMins?: number;
};

const resolver = zodResolver(
  z.object({
    title: z.string().nonempty({
      message: "Bez názvu to nepůjde...",
    }),
    imageUrl: z.string().nonempty({
      message: "Bez obrázku by to nevypadalo hezky...",
    }),
    description: z.string().optional(),
    ingredientsList: z.string().optional(),
    recipe: z.string().optional(),
    rating: z.number().int().min(1).max(5).optional(),
    priceCzkPerPortion: z
      .nan()
      .or(
        z.number().int().min(0, {
          message: "Infinite money glitch???",
        }),
      )
      .optional(),
    kcalPerPortion: z
      .nan()
      .or(
        z.number().int().min(0, {
          message: "Takhle jídlo nefunguje kámo",
        }),
      )
      .optional(),
    preparationTimeMins: z
      .nan()
      .or(
        z.number().int().min(0, {
          message: "Dobrý cestování časem kámo",
        }),
      )
      .optional(),
  }),
);

export const PostCreationForm = () => {
  const navigate = FeedRoute.useNavigate();
  const form = useForm<PostFormData>({
    mode: "onChange",
    resolver,
  });

  const { mutate: submitPost, isPending: isSubmitting } = useCreatePost();
  const { register, formState, trigger, getFieldState, getValues } = form;

  const handleSubmit = () => {
    if (!formState.isValid) {
      return;
    }

    const data = getValues();
    const mappedData: CreatePostRequest = {
      title: data.title,
      image_url: data.imageUrl,
      description: data.description,
      ingredients_list: data.ingredientsList,
      recipe: data.recipe,
      rating: Number.isFinite(data.rating) ? data.rating : undefined,
      priceCzkPerPortion: Number.isFinite(data.priceCzkPerPortion)
        ? data.priceCzkPerPortion
        : undefined,
      kcalPerPortion: Number.isFinite(data.kcalPerPortion)
        ? data.kcalPerPortion
        : undefined,
      preparationTimeMins: Number.isFinite(data.preparationTimeMins)
        ? data.preparationTimeMins
        : undefined,
    };

    submitPost(
      {
        data: mappedData,
      },
      {
        onSuccess: () => {
          navigate({ from: "/app/create" });
        },
      },
    );
  };

  return (
    <FormProvider {...form}>
      <VStack alignItems="stretch">
        <PostImageInput />

        <Field.Root required invalid={getFieldState("title").invalid}>
          <Field.Label>
            Název <Field.RequiredIndicator />
          </Field.Label>
          <Input
            type="text"
            placeholder="Svíčková se šesti"
            {...register("title", {
              required: true,
              onChange: () => trigger("title"),
            })}
          />
          <Field.ErrorText>{formState.errors.title?.message}</Field.ErrorText>
        </Field.Root>

        <Field.Root>
          <Field.Label>Popis</Field.Label>
          <Textarea
            {...register("description", {
              onChange: () => trigger("description"),
            })}
            placeholder="Byla to chálka jak kretén, kamarádi"
          ></Textarea>
        </Field.Root>

        <IngredientsListInput />

        <RecipeInput />

        <RatingInput />

        <Field.Root invalid={getFieldState("priceCzkPerPortion").invalid}>
          <Field.Label>Cena / porce</Field.Label>
          <InputGroup endAddon="Kč">
            <Input
              type="number"
              placeholder="100"
              {...register("priceCzkPerPortion", {
                required: true,
                valueAsNumber: true,
                value: undefined,
                onChange: () => trigger("priceCzkPerPortion"),
              })}
            />
          </InputGroup>
          <Field.ErrorText>
            {formState.errors.priceCzkPerPortion?.message}
          </Field.ErrorText>
        </Field.Root>

        <Field.Root invalid={getFieldState("kcalPerPortion").invalid}>
          <Field.Label>Kcal / porce</Field.Label>
          <InputGroup endAddon="kcal">
            <Input
              type="number"
              placeholder="500"
              {...register("kcalPerPortion", {
                required: true,
                valueAsNumber: true,
                value: undefined,
                onChange: () => trigger("kcalPerPortion"),
              })}
            />
          </InputGroup>
          <Field.ErrorText>
            {formState.errors.kcalPerPortion?.message}
          </Field.ErrorText>
        </Field.Root>

        <Field.Root invalid={getFieldState("preparationTimeMins").invalid}>
          <Field.Label>Doba přípravy</Field.Label>
          <InputGroup endAddon="min">
            <Input
              type="number"
              placeholder="20"
              {...register("preparationTimeMins", {
                required: true,
                valueAsNumber: true,
                value: undefined,
                onChange: () => trigger("preparationTimeMins"),
              })}
            />
          </InputGroup>
          <Field.ErrorText>
            {formState.errors.preparationTimeMins?.message}
          </Field.ErrorText>
        </Field.Root>

        <Button
          disabled={!formState.isValid}
          marginTop={4}
          onClick={handleSubmit}
          loading={isSubmitting}
        >
          Přidat příspěvek
        </Button>

        <Button variant="outline" asChild marginBottom={4}>
          <Link to="/app">Zpět</Link>
        </Button>
      </VStack>
    </FormProvider>
  );
};
