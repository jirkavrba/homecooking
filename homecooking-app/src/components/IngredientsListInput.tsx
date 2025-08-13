import { useFormContext } from "react-hook-form";
import type { PostFormData } from "./PostCreationForm";
import {
	Button,
	CloseButton,
	Field,
	Input,
	InputGroup,
} from "@chakra-ui/react";
import { LuHam, LuShoppingBag } from "react-icons/lu";
import { useEffect, useState } from "react";

export const IngredientsListInput = () => {
	const { setValue } = useFormContext<PostFormData>();
	const [ingredients, setIngredients] = useState<Array<string>>([]);

	const addIngredient = () => {
		setIngredients((current) => [...current, ""]);
	};

	const updateIngredient = (index: number, value: string) => {
		setIngredients((current) =>
			[...current].map((ingredient, i) => (i === index ? value : ingredient)),
		);
	};

	const removeIngredient = (index: number) => {
		setIngredients((current) => [...current].filter((_, i) => i !== index));
	};

	useEffect(() => {
		const filteredIngredients = ingredients.filter(
			(item) => item.trim().length > 0,
		);

		if (filteredIngredients.length === 0) {
			setValue("ingredientsList", undefined);
			return;
		}

		const serialized = filteredIngredients
			.filter((value) => value.trim())
			.map((value) => `- ${value}`)
			.join("\n");

		setValue("ingredientsList", serialized);
	}, [ingredients, setValue]);

	return (
		<>
			<Field.Root>
				<Field.Label>Ingredience</Field.Label>

				{ingredients.map((ingredient, index) => (
					<InputGroup
						key={index}
						startElement={<LuShoppingBag />}
						endElement={<CloseButton onClick={() => removeIngredient(index)} />}
					>
						<Input
							value={ingredient}
							onChange={(event) => updateIngredient(index, event.target.value)}
						/>
					</InputGroup>
				))}
			</Field.Root>
			<Button variant="outline" onClick={addIngredient}>
				<LuHam />
				Přidat ingredienci
			</Button>
		</>
	);
};
