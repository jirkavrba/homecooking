import { useFormContext } from "react-hook-form";
import type { PostFormData } from "./PostCreationForm.tsx";
import {
  Button,
  CloseButton,
  Field,
  Input,
  InputGroup,
  VStack,
} from "@chakra-ui/react";
import { LuChefHat } from "react-icons/lu";
import { useEffect, useState } from "react";

export const RecipeInput = () => {
  const { setValue } = useFormContext<PostFormData>();
  const [recipeSteps, setRecipeSteps] = useState<Array<string>>([]);

  const addRecipeStep = () => {
    setRecipeSteps((current) => [...current, ""]);
  };

  const updateRecipeStep = (index: number, value: string) => {
    setRecipeSteps((current) =>
      [...current].map((ingredient, i) => (i === index ? value : ingredient)),
    );
  };

  const removeRecipeStep = (index: number) => {
    setRecipeSteps((current) => [...current].filter((_, i) => i !== index));
  };

  useEffect(() => {
    const filteredSteps = recipeSteps.filter((item) => item.trim().length > 0);

    if (filteredSteps.length === 0) {
      setValue("recipe", undefined);
      return;
    }

    const serialized = filteredSteps.filter((value) => value.trim()).join("\n");

    setValue("recipe", serialized);
  }, [recipeSteps, setValue]);

  return (
    <>
      <Field.Root>
        <Field.Label>Recept</Field.Label>
      </Field.Root>

      <VStack>
        {recipeSteps.map((step, index) => (
          <InputGroup
            startElement={index + 1}
            endElement={<CloseButton onClick={() => removeRecipeStep(index)} />}
          >
            <Input
              value={step}
              onChange={(event) => updateRecipeStep(index, event.target.value)}
            />
          </InputGroup>
        ))}
      </VStack>

      <Button variant="outline" onClick={addRecipeStep}>
        <LuChefHat />
        Přidat krok receptu
      </Button>
    </>
  );
};
