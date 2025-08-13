import type { FC } from "react";
import { useFormContext } from "react-hook-form";
import type { PostFormData } from "./PostCreationForm";
import {
  Button,
  Field,
  HStack,
  IconButton,
  RatingGroup,
} from "@chakra-ui/react";
import { LuStar, LuTrash } from "react-icons/lu";

export const RatingInput: FC = () => {
  const { watch, setValue, register } = useFormContext<PostFormData>();

  const currentRating = watch("rating");

  return (
    <Field.Root>
      <Field.Label>Hodnocení</Field.Label>
      {currentRating ? (
        <HStack
          width="full"
          borderWidth={1}
          rounded="md"
          padding={1}
          justifyContent="space-between"
        >
          <RatingGroup.Root count={5} size="lg" marginLeft={4}>
            <RatingGroup.HiddenInput {...register("rating")} />
            <RatingGroup.Control />
          </RatingGroup.Root>
          <IconButton
            variant="ghost"
            onClick={() => setValue("rating", undefined)}
          >
            <LuTrash />
          </IconButton>
        </HStack>
      ) : (
        <Button
          variant="outline"
          width="full"
          onClick={() => setValue("rating", 5)}
        >
          <LuStar />
          Přidat hodnocení
        </Button>
      )}
    </Field.Root>
  );
};
