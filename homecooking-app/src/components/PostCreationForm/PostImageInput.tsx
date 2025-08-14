import type { FC } from "react";
import {
	FileUpload,
	HStack,
	VStack,
	Image,
	Spinner,
	Text,
	Button,
} from "@chakra-ui/react";
import { useUploadImage } from "@/generated/api.ts";
import type { FileAcceptDetails } from "node_modules/@chakra-ui/react/dist/types/components/file-upload/namespace";
import { useFormContext } from "react-hook-form";
import type { PostFormData } from "./PostCreationForm.tsx";

type ImagePreviewProps = {
	imageUrl: string;
	onRemoveImage: () => void;
};

export const ImagePreview: FC<ImagePreviewProps> = ({
	imageUrl,
	onRemoveImage,
}) => {
	return (
		<VStack alignItems="stretch">
			<Image src={imageUrl} rounded="lg" />
			<Button variant="outline" onClick={onRemoveImage}>
				Chci vybrat jiný obrázek
			</Button>
		</VStack>
	);
};

export const PostImageInput: FC = () => {
	const { watch, setValue } = useFormContext<PostFormData>();
	const { mutate: uploadImage, isPending: isUploading } = useUploadImage();
	const imageUrl = watch("imageUrl");
	const handleFileUpload = (file: FileAcceptDetails) => {
		uploadImage(
			{
				data: {
					file: file.files[0],
				},
			},
			{
				onSuccess: (response) => {
					setValue("imageUrl", response.data.file_url);
				},
			},
		);
	};

	if (imageUrl && imageUrl.length > 0) {
		return (
			<ImagePreview
				imageUrl={imageUrl}
				onRemoveImage={() => setValue("imageUrl", "")}
			/>
		);
	}

	return (
		<FileUpload.Root
			alignSelf="stretch"
			accept={["image/png", "image/jpeg"]}
			maxFileSize={25 * 1024 * 1024}
			onFileAccept={handleFileUpload}
		>
			<FileUpload.Label>
				Obrázek
				<Text as="span" color="fg.error">
					{" "}
					*
				</Text>
			</FileUpload.Label>
			<FileUpload.HiddenInput name="imageUrl" />
			<FileUpload.Dropzone width="full" cursor="pointer">
				<FileUpload.DropzoneContent>
					{isUploading ? (
						<HStack>
							<Spinner />
							<Text>Nahrávám obrázek...</Text>
						</HStack>
					) : (
						<h1>Vybrat obrázek</h1>
					)}
				</FileUpload.DropzoneContent>
			</FileUpload.Dropzone>
		</FileUpload.Root>
	);
};
